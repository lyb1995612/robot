package com.csjbot.robot.biz.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.cms.dao.CmsRobotDao;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.sys.dao.SysDataDictionaryDao;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class CmsRobotService {
	
	@Autowired
	private CmsRobotDao cmsRobotDao;
	
	@Autowired
	private SysDataDictionaryDao sysDicDao;
	
    public int insert(CmsRobot record){
    	return cmsRobotDao.insert(record);
    }
	
	public CmsRobot selectByPrimaryKey(String id){
		return cmsRobotDao.selectByPrimaryKey(id);
	}
	
	public List<SysDataDictionary> findDictionaryByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return sysDicDao.findDictionaryByCode(params);
	}
	
	public int updateByPrimaryKey(CmsRobot record){
		return cmsRobotDao.updateByPrimaryKey(record);
	}
	
	public int delete(String id){
		return cmsRobotDao.delete(id);
	}
	
	public CmsRobot selectByTypeAndSn(int type,String sn){
		return cmsRobotDao.selectByTypeAndSn(type, sn);
	}
	
	public PageList<CmsRobot> page(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return cmsRobotDao.page(params, pager);
    }
	
	public <T, K, V> List<T> getRobotGroupRef(Map<String, Object> params) {
        return cmsRobotDao.getRobotGroupRef(params);
    }
	
	public List<CmsRobot> getAdCmsRobots() {
		return cmsRobotDao.getAdCmsRobots();
	}
	
}
