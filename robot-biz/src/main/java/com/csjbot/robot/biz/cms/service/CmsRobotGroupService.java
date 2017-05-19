package com.csjbot.robot.biz.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.cms.dao.CmsRobotGroupDao;
import com.csjbot.robot.biz.cms.model.CmsRobotGroup;
import com.csjbot.robot.biz.sys.dao.SysDataDictionaryDao;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class CmsRobotGroupService {
	
	@Autowired
	private CmsRobotGroupDao cmsRobotGroupDao;
	
	
	@Autowired
	private SysDataDictionaryDao sysDicDao;
	
    public int insert(CmsRobotGroup record){
    	return cmsRobotGroupDao.insert(record);
    }
	
    public CmsRobotGroup selectByPrimaryKey(String id){
    	return cmsRobotGroupDao.selectByPrimaryKey(id);
    }
    
    public List<SysDataDictionary> findDictionaryByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return sysDicDao.findDictionaryByCode(params);
	}
    
    public List<Map<String, Object>> listRobot(Map<String, Object> params) {
        return cmsRobotGroupDao.listRobot(params);
    }
    
    public int countType(Integer type ,String group_name){
    	return cmsRobotGroupDao.countType(type,group_name);
    }
    
    public CmsRobotGroup selectByGroupName(String group_name){
    	return cmsRobotGroupDao.selectByGroupName(group_name);
    }
    
    public PageList<CmsRobotGroup> page(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return cmsRobotGroupDao.page(params, pager);
    }

}
