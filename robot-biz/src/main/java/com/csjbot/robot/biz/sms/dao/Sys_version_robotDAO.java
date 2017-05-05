package com.csjbot.robot.biz.sms.dao;



import com.csjbot.robot.biz.sms.model.Sys_version_robot;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface Sys_version_robotDAO {
	 //根据category,channel查询数据
	public abstract List<Sys_version_robot> findSysVersionBycach(@Param("category") int category, @Param("channel") int channel);
	
	public abstract Sys_version_robot findSysByVersionCode(@Param("category") int category, @Param("channel") int channel);
	
	public abstract Sys_version_robot findSysByDateUpdate(@Param("category") int category, @Param("channel") int channel);

}
