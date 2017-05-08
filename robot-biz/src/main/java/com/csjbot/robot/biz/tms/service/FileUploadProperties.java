package com.csjbot.robot.biz.tms.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Component
public class FileUploadProperties {

    @Resource(name = "appconfig")
    private Properties properties;

    // @Value("${pkg.store.base.dir}")
    // private String baseDir;
    // @Value("${pkg.store.checksum}")
    // private String checksumAlg;
    // @Value("${pkg.store.folder.regex}")
    // private String folderRegex;

    public String getBaseDir() {
        String baseDir = properties.getProperty("pkg.store.base.dir");
        if (isEmpty(baseDir)) throw new NullPointerException("baseDir");
        return baseDir;
    }

    public String getChecksumAlg() {
        String checksumAlg = properties.getProperty("pkg.store.checksum");
        return replaceEmpty(checksumAlg, "SHA1");
    }

    public String getFolderRegex() {
        String folderRegex = properties.getProperty("pkg.store.folder.regex");
        return replaceEmpty(folderRegex, "[0-9a-zA-Z-_]{3,20}");
    }

    public String getBaseUrl() {
        String url = properties.getProperty("pkg.download.base.url");
        if (isEmpty(url)) throw new NullPointerException("baseUrl");
        return url;
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    private String replaceEmpty(String orig, String alter) {
        return isEmpty(orig) ? alter : orig;
    }


    private String replaceChar(String str, int index, CharSequence ch) {
        int len = str.length();
        if (index >= 0 && index < len)
            str = str.substring(0, index) + ch + str.substring(index + 1, len);
        return str;
    }

    @SuppressWarnings("unused")
	private String replaceCharIf(String str, int index, char orig, CharSequence ch) {
        return (str.charAt(index) == orig) ? replaceChar(str, index, ch) : str;
    }
}
