package com.csjbot.robot.biz.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.biz.sys.dao.SysAttachmentDao;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.util.FileUtil;


@Service
public class SysAttachService{
    
    @Autowired
    private SysAttachmentDao sysAttachDao;
    
	
	public boolean insert(SysAttachment attach) throws ServiceException {
		return sysAttachDao.insert(attach);
	}

	public SysAttachment getAttachByTransInfo(String transaction_id,String transaction_type) {
		return sysAttachDao.getAttachByTransInfo(transaction_id,transaction_type);
	}
	
	
	public SysAttachment getAttachByTransInfoName(String transaction_id,String transaction_type,String fileName) {
		return sysAttachDao.getAttachByTransInfoName(transaction_id,transaction_type,fileName);
	}
	  
	
	public void deleteByTransInfo(String transation_id, String transation_type) {
		SysAttachment sysAttachment = this.getAttachByTransInfo(transation_id, transation_type);
		if(null==sysAttachment)return;
		FileUtil fileUtil = new FileUtil();
		fileUtil.deleteFile(sysAttachment.getLocation());
		sysAttachDao.deleteByTransInfo(transation_id,transation_type);
	}
	
    public String filenameChange(String filename){
  	  filename = filename.replace("+", "%20");
  	  filename = filename.replace("%28", "(");
  	  filename = filename.replace("%29", ")");
  	  filename = filename.replace("%21", "!");
  	  filename = filename.replace("%27", "'");
  	  filename = filename.replace("%7E", "~");
  	  filename = filename.replace("%25", "%");
  	  filename = filename.replace("%2B", "+");
  	  filename = filename.replace("%2F", "/");
  	  filename = filename.replace("%3A", ":");
  	  filename = filename.replace("%3D", "=");
  	  filename = filename.replace("%26", "&");
  	  filename = filename.replace("%40", "@");
  	  filename = filename.replace("%23", "#");
  	  filename = filename.replace("%24", "$");
  	  filename = filename.replace("%5E", "^");
  	  return filename;
    }


}
