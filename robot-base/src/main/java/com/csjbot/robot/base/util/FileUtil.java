package com.csjbot.robot.base.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * @author dcj
 * 
 */
public class FileUtil {
	
	static final Logger logger = Logger.getLogger(FileUtil.class);
	// 鏂囦欢鍏佽鏍煎紡
	// private String[] allowFiles = { ".swf", ".wmv", ".flv", ".avi", ".rm",
	// ".rmvb", ".mpeg", ".mpg", ".ogg", ".mov", ".wmv", ".mp4",".gif", ".png",
	// ".jpg", ".jpeg",".exl",".pdf",".word",".txt"};
	private String[] allowFiles = convertStrToArray(PropertiesUtils.getValue("image.suffix"));
	private String url = "";
	private String state = "";
	private String originalName = "";
	
	public static byte[] getBytes(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }
	
	

	
	public String upload(MultipartFile file, String dir, String foldername) {
		url = "";
		String fileName = file.getOriginalFilename();
		if (!checkFileType(fileName)) {
			return "error";
		}
		fileName = getName(fileName);
		String folderUrl = getFolder(dir + foldername);
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		url += foldername + "/" + formater.format(new Date()) + "/" + fileName;
		File outFile = new File(folderUrl + File.separatorChar + fileName);
		try {
			file.transferTo(outFile);
			return this.url;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * 鑾峰彇鏂囦欢鎵╁睍鍚?
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
/*	private String getFilePre(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."));
	}*/

	/**
	 * 鏂囦欢绫诲瀷鍒ゆ柇
	 * 
	 * @param fileName
	 * @return
	 */
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

	/**
	 * 渚濇嵁鍘熷鏂囦欢鍚嶇敓鎴愭柊鏂囦欢鍚?
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return random.nextInt(10000) + System.currentTimeMillis()
				+ this.getFileExt(fileName);
	}

	/**
	 * 鏍规嵁瀛楃涓插垱寤烘湰鍦扮洰褰?骞舵寜鐓ф棩鏈熷缓绔嬪瓙鐩綍杩斿洖
	 * 
	 * @param path
	 * @return
	 */

	private String getFolder(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += File.separatorChar + formater.format(new Date());
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				return "";
			}
		}
		return path;
	}

	public void makeDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				logger.info("Create dir:"+path);
				dir.mkdirs();
			} catch (Exception e) {
				logger.error("Create dir error:"+e.getMessage());
			}
		}
	}

	// 使用String的split 方法
	private String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}
	
	public boolean deleteFile(String fullFilePath) {
        File file = null;
        try {
            file = new File(fullFilePath);
            if (file.exists()) {
            	logger.info("Delete file:"+fullFilePath);
                return file.delete();
            }
        } finally {
            file = null;
        }
        return false;
    }
	
	
   public byte[] download(String fullFilePath) {
	   logger.info(" file:"+fullFilePath);
       byte[] byteArray = null;
        try {
           File file = new File(fullFilePath);
           InputStream in = new FileInputStream(file); 
           if (in != null) {
        	   logger.info("begin Download " + fullFilePath);
               byteArray = IOUtils.toByteArray(in);
               logger.info("Download "+fullFilePath+" success");
           }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return byteArray;
    }
   
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String codeString(InputStream fileName) throws Exception {
		BufferedInputStream bin = new BufferedInputStream(fileName);
		int p = (bin.read() << 8) + bin.read();
		String code = null;

		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
		}

		return code;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

}
