package com.csjbot.robot.biz.tms.model;

import java.io.Serializable;
import java.util.Date;

public class PkgFile implements Serializable {
	
    private Long id;

    private String name;

    private String path;

    private Date uploadTime;

    private static final long serialVersionUID = 1L;

    public PkgFile() { }

    public PkgFile(String fileName, String dirPath, Date uploadTime) {
        this.name = fileName;
        this.path = dirPath;
        this.uploadTime = uploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getInfo() {
        return id + " " + path + " " + name;
    }
    
}