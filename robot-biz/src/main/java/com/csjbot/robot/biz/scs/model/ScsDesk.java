package com.csjbot.robot.biz.scs.model;


import java.util.Date;

import com.csjbot.robot.biz.base.entity.PaginationParam;



/**
 * 
 * @author xiongmietao
 * @date 2017/4/17 am 9:42
 * @version V1.0
 */
public class ScsDesk extends PaginationParam{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6383621397342769739L;

	private String id;
	
	private String number;
	
	private String alias;
	
	private String memo;
	
	private Byte valid;
	
	private Double deskx;
	
	private Double desky;
	
	private Double deskz;
	
	private Double deskw;
	
	private Double deskv;
	
	private Double deskq;
	
	private String updater_fk;
	
	private String creator_fk;
	
	private Date date_update;
	
	private Date date_create;
	
	private Byte desk_type;
	
	
    
	public Byte getDesk_type() {
		return desk_type;
	}

	public void setDesk_type(Byte desk_type) {
		this.desk_type = desk_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Byte getValid() {
		return valid;
	}

	public void setValid(Byte valid) {
		this.valid = valid;
	}

	public String getUpdater_fk() {
		return updater_fk;
	}

	public void setUpdater_fk(String updater_fk) {
		this.updater_fk = updater_fk;
	}

	public String getCreator_fk() {
		return creator_fk;
	}

	public void setCreator_fk(String creator_fk) {
		this.creator_fk = creator_fk;
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public Double getDeskx() {
		return deskx;
	}

	public void setDeskx(Double deskx) {
		this.deskx = deskx;
	}

	public Double getDesky() {
		return desky;
	}

	public void setDesky(Double desky) {
		this.desky = desky;
	}

	public Double getDeskz() {
		return deskz;
	}

	public void setDeskz(Double deskz) {
		this.deskz = deskz;
	}

	public Double getDeskw() {
		return deskw;
	}

	public void setDeskw(Double deskw) {
		this.deskw = deskw;
	}

	public Double getDeskv() {
		return deskv;
	}

	public void setDeskv(Double deskv) {
		this.deskv = deskv;
	}

	public Double getDeskq() {
		return deskq;
	}

	public void setDeskq(Double deskq) {
		this.deskq = deskq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate_update() {
		return date_update;
	}

	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}

}
