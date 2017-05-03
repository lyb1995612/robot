package com.csjbot.robot.biz.sys.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;
import com.csjbot.robot.biz.util.FileUtil;

@Controller
@RequestMapping(value = "/attach")
public class FileController {

	@Autowired SysAttachService sysAttachService;
	
	/**
	     * @discription 图片流输出
	     * @author CJay       
	     * @created 2017年3月29日 下午4:12:27
	 */
    @RequestMapping(value = "{id}/{type}/pic")  
    public void createFolw(@PathVariable String id,@PathVariable String type, HttpServletRequest request, HttpServletResponse response) {  
        FileInputStream fis = null;  
        OutputStream os = null;  
        SysAttachment sysAttachment = sysAttachService.getAttachByTransInfo(id, type);
        try {  
            fis = new FileInputStream(sysAttachment.getLocation());  
            os = response.getOutputStream();  
            int count = 0;  
            byte[] buffer = new byte[1024 * 8];  
            while ((count = fis.read(buffer)) != -1) {  
                os.write(buffer, 0, count);  
                os.flush();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            fis.close();  
            os.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
   
	/**
	     * @discription 附件下载接口
	     * @author CJay       
	     * @created 2017年3月29日 下午4:11:54
	 */
    @RequestMapping(value = "/{id}/{type}/{name}", method = RequestMethod.GET)
    public void gets(@PathVariable String id, @PathVariable String type, @PathVariable String name,HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
    	String  filename = "";
    	SysAttachment sysAttachment = sysAttachService.getAttachByTransInfo(id, type, name);
        if (sysAttachment == null) return;
        // -------------------------------------------------------------------------------------------------------------------------------------------
        filename = sysAttachment.getOriginal_name();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(sysAttachment.getFile_type() + ";charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + sysAttachService.filenameChange(java.net.URLEncoder.encode(filename, "UTF-8")));//在filename后加上（*=utf-8'zh_cn' ）用于兼容火狐浏览器的下载 文件名称问题
        // -------------------------------------------------------------------------------------------------------------------------------------------
        File file = FileUtils.getFile(sysAttachment.getLocation());
        // -------------------------------------------------------------------------------------------------------------------------------------------
        response.setContentLength(Integer.parseInt(sysAttachment.getSize()+ ""));
        // -------------------------------------------------------------------------------------------------------------------------------------------
        FileCopyUtils.copy(FileUtil.getBytes(file), response.getOutputStream());
    }
}
