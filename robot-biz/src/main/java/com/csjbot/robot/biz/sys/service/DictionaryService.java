package com.csjbot.robot.biz.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.sys.dao.SysDataDao;
import com.csjbot.robot.biz.sys.dao.SysDataDictionaryDao;
import com.csjbot.robot.biz.sys.model.SysData;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.sys.model.param.DictionaryParam;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @Description 
 * @author DCJ
 * @date 2015-4-29 下午10:33:14 
 * @version V1.0
 */

@Service
public class DictionaryService{
	
	@Autowired private SysDataDao sysDataDao;
	@Autowired private SysDataDictionaryDao sysDicDao;

	/**
	 * Description 
	 * @param glmk
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#findOneByCode(java.lang.String) 
	 */ 
		
	
	public Map<String, Object> findOneByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        return sysDataDao.findOneByCode(params);
	}

	/**
	 * Description 
	 * @param dictionaryId
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#findDicById(java.lang.String) 
	 */ 
		
	
	public <T, K, V> List<T> findDicById(Integer dictionaryId, String tenantFk) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("data_fk", dictionaryId);
        params.put("tenant_fk", tenantFk);
        return sysDicDao.findDicById(params);
	}

	/**
	 * Description 
	 * @param bbsj
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#findDictionaryByCode(java.lang.String) 
	 */ 
		
	
	public List<SysDataDictionary> findDictionaryByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return sysDicDao.findDictionaryByCode(params);
	}

	/**
	 * Description 
	 * @param current
	 * @param pageSize
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#page(int, int) 
	 */ 
	public PageList<SysData> page(DictionaryParam param) {
        PageBounds pager = new PageBounds();
        pager.setLimit(param.getPageSize());
        pager.setPage(param.getPageNow() + 1);
        pager.setOrders(Order.formString(param.getSortString()));    
		return sysDataDao.page( param,pager);
	}

	/**
	 * Description 
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#sonpage(java.util.Map, int, int) 
	 */ 
		
	
	public PageList<SysDataDictionary> sonpage(DictionaryParam param) {
        PageBounds pager = new PageBounds();
        pager.setLimit(param.getPageSize());
        pager.setPage(param.getPageNow() + 1);
        pager.setOrders(Order.formString(param.getSortString())); 
		return sysDicDao.page( param,pager);
	}

	/**
	 * Description 
	 * @param parseInt
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#findSysDataById(int) 
	 */ 
		
	
	public SysData findSysDataById(int parseInt) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", parseInt);
		return sysDataDao.findSysDataById(params );
	}

	/**
	 * Description 
	 * @param parseInt
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#findSysDataDicById(int) 
	 */ 
		
	
	public SysDataDictionary findSysDataDicById(int parseInt) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", parseInt);
		return sysDicDao.findSysDataDicById(params);
	}

	/**
	 * Description 
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#dicDataUpdate() 
	 */ 
		
	
	public int dicDataUpdate(SysDataDictionary dicdata) {
		return sysDicDao.dicDataUpdate(dicdata);
	}

	/**
	 * Description 
	 * @param dicdata
	 * @return 
	 * @see com.csjbot.admin.backadmin.sys.service.DictionaryService#dicDataInsert(com.csjbot.admin.data.sys.model.SysDataDictionary) 
	 */ 
		
	
	public int dicDataInsert(SysDataDictionary dicdata) {
		return sysDicDao.dicDataInsert(dicdata);
	}


}
