package com.csjbot.robot.biz.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.csjbot.robot.biz.sys.model.SysAttachment;

import java.util.List;

public interface SysAttachmentDao {
	
	public final static String PREFIX = SysAttachmentDao.class.getName();
			
    int deleteByPrimaryKey(Long id);

    boolean insert(SysAttachment record);

    int insertSelective(SysAttachment record);

    SysAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAttachment record);

    int updateByPrimaryKey(SysAttachment record);

	SysAttachment getAttachByTransInfo(@Param("transaction_id")String transaction_id,@Param("transaction_type")String transaction_type);
	
	SysAttachment getAttachByTransInfoName(@Param("transaction_id")String transaction_id,@Param("transaction_type")String transaction_type,@Param("name")String name);

	void deleteByTransInfo(@Param("transaction_id")String transation_id, @Param("transaction_type")String transation_type);

	List<SysAttachment> getAttachByType(String type);
	
	List<SysAttachment>  getAttachBySn(String sn);
}