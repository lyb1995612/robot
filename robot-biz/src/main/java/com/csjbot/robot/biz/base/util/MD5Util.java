package com.csjbot.robot.biz.base.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


/**          
     * Description 
     * @author CJay       
     * @created 2017年4月20日 下午5:47:22    
     */

public class MD5Util {

	private Logger logger = Logger.getLogger(MD5Util.class);
	
	private String checksumAlg="MD5";
	
    public String genChecksum(MultipartFile file) {
        String hash = null;
        try {
            byte[] bytes = file.getBytes();
            final byte[] hashBytes = MessageDigest.getInstance(checksumAlg).digest(bytes);
            hash = DatatypeConverter.printHexBinary(hashBytes);
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e);
        } catch (IOException e) {
        	logger.error(e);
        }
        return hash;
    }
    
}
