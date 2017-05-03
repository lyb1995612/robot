package com.csjbot.robot.biz.sys.model.param;

import com.csjbot.robot.base.web.entity.PaginationParam;

/**
 * 角色查询参数
 * 
 * @author cjay
 * 
 */
public class RoleParam extends PaginationParam {

    private static final long serialVersionUID = -8117619171798881885L;

    
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色状态
     */
    private int valid = 2;
    
    private String creator_fk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

	public String getCreator_fk() {
		return creator_fk;
	}

	public void setCreator_fk(String creator_fk) {
		this.creator_fk = creator_fk;
	}
    
}