package com.csjbot.robot.biz.pms.dao;

import java.util.Map;

import com.csjbot.robot.biz.pms.model.PmsAdvertisement;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PmsAdvertisementDao {
	
	public final static String PREFIX = PmsAdvertisementDao.class.getName();
			
    int deleteByPrimaryKey(String id);

    int insert(PmsAdvertisement record);

    int insertSelective(PmsAdvertisement record);

    PmsAdvertisement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsAdvertisement record);

    int updateByPrimaryKey(PmsAdvertisement record);
	  
    public PageList<PmsAdvertisement> page(Map<String, Object> params, PageBounds pager);
}