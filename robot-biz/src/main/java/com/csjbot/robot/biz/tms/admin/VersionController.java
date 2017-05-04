package com.csjbot.robot.biz.tms.admin;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csjbot.robot.biz.tms.model.PkgFile;
import com.csjbot.robot.biz.tms.model.result.FileResult;
import com.csjbot.robot.biz.tms.model.result.FileResult.PreDefined;
import com.csjbot.robot.biz.tms.service.FSStoreService;
import com.csjbot.robot.biz.tms.service.FileUploadProperties;
import com.csjbot.robot.biz.tms.service.PkgFileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/tms")
public class VersionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

	private final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private PkgFileService fileDBService;
	@Autowired
	private FSStoreService storeService;

	private final String downloadBaseUrl;

	@Autowired
	public VersionController(FileUploadProperties properties) {
		downloadBaseUrl = properties.getBaseUrl();
	}

	@RequestMapping(value = "/versionControl", method = RequestMethod.GET)
	public String versionView(Model model) {
		List<PkgFile> fileList = fileDBService.getAll();
		model.addAttribute("fileList", fileList);
		model.addAttribute("baseUrl", downloadBaseUrl);
		return "tms/version";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam("id") String id) {
		LOGGER.debug("delete file " + id);
		PkgFile file = fileDBService.get(id);
		if (file == null)
			return textResponse(BAD_REQUEST, "未找到文件ID=" + id);
		boolean res = false;
		if (storeService.delete(file.getPath(), file.getName())) {
			if (removeDBInfo(id))
				res = true;
			else
				LOGGER.error("remove db info " + file.getInfo() + " failed");
		} else {
			LOGGER.error("delete " + file.getInfo() + " failed");
		}
		return (res) ? textResponse(OK, id + " 删除成功") : textResponse(INTERNAL_SERVER_ERROR, id + " 删除失败");
	}

	@RequestMapping(value = "/listdir", method = RequestMethod.GET)
	public ResponseEntity<String> listDir() {
		List<String> dirs = fileDBService.getDistinctPaths();
		if (dirs == null || dirs.size() == 0)
			dirs = Collections.singletonList("/");
		String dirJson;
		try {
			dirJson = mapper.writeValueAsString(dirs);
		} catch (JsonProcessingException e) {
			LOGGER.error("jackson serialize error", e);
			dirJson = manualSerialize(dirs);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(dirJson, headers, OK);
	}

	private static String manualSerialize(List<String> dirs) {
		if (dirs == null || dirs.size() == 0)
			return "[]";
		String part = String.join("\",\"", dirs);
		return "[\"" + part + "\"]";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("dir") String dirStr, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return textResponse(BAD_REQUEST, "空文件");
		}
		if (!storeService.checkDirStrSyntax(dirStr)) {
			return textResponse(BAD_REQUEST, "文件夹不符合格式");
		}
		FileResult createDirRes = storeService.createDir(dirStr);
		if (!createDirRes.isSuccess()) {
			return textResponse(createDirRes);
		}
		if (storeService.fileExists(dirStr, file.getOriginalFilename())) {
			return textResponse(FileResult.from(PreDefined.EXISTING_FILE));
		}
		FileResult storeFileRes = storeService.store(dirStr, file);
		if (!storeFileRes.isSuccess()) {
			return textResponse(storeFileRes);
		}
		if (!createDBInfo(dirStr, file.getOriginalFilename())) {
			// todo how to handle errors??
			LOGGER.error("upload file fail to insert to db " + dirStr + file.getName());
		}
		return textResponse(OK, "上传成功");
	}
	/*
	// todo open permission in shiro or move to api project
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<String> findByPattern(@RequestParam("nameptn") String namePtn,
			@RequestParam(value = "orderby", required = false) Integer orderBy) {
		if (namePtn == null || !namePtn.matches(NAME_PTN_REGEX))
			return textResponse(BAD_REQUEST, "查询字串不符合格式，" + "仅支持数字、英文字母、英文句号、下划线和连字符, 至少3字符");
		if (orderBy == null)
			orderBy = 1;
		List<FileEntry> files = fileDBService.findByName(namePtn, orderBy);
		String json = toJson(files);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return json == null ? textResponse(INTERNAL_SERVER_ERROR, "JSON序列化出错")
				: new ResponseEntity<>(json, headers, OK);
	}

	private static final String NAME_PTN_REGEX = "[0-9a-zA-Z-_.]{3,20}"; // todo

	private String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("json serialize", e);
		}
		return null;
	}
	 */
	
	private boolean createDBInfo(String filePath, String fileName) {
		PkgFile file = new PkgFile(fileName, filePath, new Date());
		return (fileDBService.insert(file) == 1);
	}

	private boolean removeDBInfo(String fileId) {
		return (fileDBService.delete(fileId) == 1);
	}

	private ResponseEntity<String> textResponse(FileResult res) {
		return textResponse(res.getStatus(), res.getMessage());
	}

	private ResponseEntity<String> textResponse(HttpStatus status, String msg) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(msg, headers, status);
	}
}