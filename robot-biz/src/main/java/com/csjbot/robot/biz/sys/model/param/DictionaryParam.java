package com.csjbot.robot.biz.sys.model.param;

import com.csjbot.robot.biz.base.entity.PaginationParam;

public class DictionaryParam extends PaginationParam{

	private static final long serialVersionUID = 2038737867799613938L;
	
	private String data_fk;

	public String getData_fk() {
		return data_fk;
	}

	public void setData_fk(String data_fk) {
		this.data_fk = data_fk;
	}
	
}
