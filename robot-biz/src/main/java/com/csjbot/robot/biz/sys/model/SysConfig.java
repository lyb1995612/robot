package com.csjbot.robot.biz.sys.model;

import java.util.Date;

public class SysConfig {
    private String id;

    private String tenant_fk;

    private String name;

    private String key;

    private String value;

    private String type;
    
    private Integer version;
    
    private boolean isEcp;

    private boolean isValid;

    private String updaterFk;

    private String creatorFk;

    private Date dateCreate;

    private Date dateUpdate;
    
    
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isEcp() {
		return isEcp;
	}

	public void setEcp(boolean isEcp) {
		this.isEcp = isEcp;
	}


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdaterFk() {
        return updaterFk;
    }

    public void setUpdaterFk(String updaterFk) {
        this.updaterFk = updaterFk;
    }

    public String getCreatorFk() {
        return creatorFk;
    }

    public void setCreatorFk(String creatorFk) {
        this.creatorFk = creatorFk;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getTenant_fk() {
		return tenant_fk;
	}

	public void setTenant_fk(String tenant_fk) {
		this.tenant_fk = tenant_fk;
	}

    
    
}