package com.csjbot.robot.biz.cms.model;

import com.csjbot.robot.biz.base.entity.PaginationParam;

/**
 * 
 * @author xiongmietao
 * @date 2017/4/13 am 9:42
 * @version V1.0
 */

public class Customer extends PaginationParam{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -235143425344959101L;

	private String customer;
	
	private String value;
	
	private String code;
	
	private String code_group;
	
	private String remark;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode_group() {
		return code_group;
	}

	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Customer(String customer, String value, String code, String code_group, String remark) {
		super();
		this.customer = customer;
		this.value = value;
		this.code = code;
		this.code_group = code_group;
		this.remark = remark;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [customer=" + customer + ", value=" + value + ", code=" + code + ", code_group=" + code_group
				+ ", remark=" + remark + "]";
	}


}
