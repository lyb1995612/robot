package com.csjbot.robot.biz.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.base.util.PropertiesUtils;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;

public class ErrorRealm {
	
	static final Logger logger = Logger.getLogger(ErrorRealm.class);
	private String[] allowFiles = convertStrToArray(PropertiesUtils.getValue("image.suffix"));
	private String url = "";
	private static ApplicationContext appCtx;
	private static SysAttachService attachService= appCtx.getBean(SysAttachService.class);
	
	public String uploadAndModifyAttach(SysAttachment attach, String type, MultipartFile file, String dir,
			String foldername) {
		url = "";
		String fileName = file.getOriginalFilename();
		if (attach.getTransaction_id() == null) {
			return "error";
		}
		attach.setTransaction_type(type);
		attach.setOriginal_name(fileName);
		attach.setSuffix(this.getFileExt(fileName));
		attach.setSize((int) file.getSize());
		attach.setFile_type(file.getContentType());
		fileName = getName(fileName);
		attach.setAlias_name(fileName);
		attach.setName(this.getFilePre(fileName));

		String folderUrl = dir + foldername;
		File path = new File(folderUrl);
		if (!path.exists()) {
			try {
				path.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		url = folderUrl + fileName;
		File outFile = new File(url);
		attach.setDirectory(folderUrl);
		attach.setLocation(url);
		//SysAttachService attachService = ApplicationContextHelper.getBean(SysAttachService.class);
		try {
			file.transferTo(outFile);
			attachService.insert(attach);
			return this.url;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "error";
	}

	public String uploadAndModifyAttach(SysAttachment attach, MultipartFile file, String dir, String foldername) {
		url = "";
		String fileName = file.getOriginalFilename();
		if (!checkFileType(fileName) || attach.getTransaction_id() == null || attach.getTransaction_type() == null) {
			return "error";
		}
		attach.setOriginal_name(fileName);
		attach.setSuffix(this.getFileExt(fileName));
		attach.setSize((int) file.getSize());
		attach.setFile_type(file.getContentType());
		fileName = getName(fileName);
		attach.setAlias_name(fileName);
		attach.setName(this.getFilePre(fileName));

		String folderUrl = dir + foldername;
		File path = new File(folderUrl);
		if (!path.exists()) {
			try {
				path.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		url = folderUrl + fileName;
		File outFile = new File(url);
		attach.setDirectory(folderUrl);
		attach.setLocation(url);
		//SysAttachService attachService = ApplicationContextHelper.getBean(SysAttachService.class);
		try {
			file.transferTo(outFile);
			attachService.insert(attach);
			return this.url;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	private String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}
	
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	private String getFilePre(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."));
	}
	
	private String getName(String fileName) {
		Random random = new Random();
		return random.nextInt(10000) + System.currentTimeMillis()
				+ this.getFileExt(fileName);
	}
	
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next().trim();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

}
