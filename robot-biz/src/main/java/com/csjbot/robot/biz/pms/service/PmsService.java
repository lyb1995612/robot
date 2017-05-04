
/**   
 * @Title: DictionaryServiceImpl.java 
 * @Package: com.csjbot.admin.backadmin.sys.service.impl 
 * @Description: TODO
 * @author DCJ  
 * @date 2015-4-29 下午10:33:14 
 * @version 1.0 
 */


package com.csjbot.robot.biz.pms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.biz.pms.dao.PmsAdvertisementDao;
import com.csjbot.robot.biz.pms.dao.PmsProductDao;
import com.csjbot.robot.biz.pms.model.PmsAdvertisement;
import com.csjbot.robot.biz.pms.model.PmsProduct;

/** 
 * @Description 
 * @author DCJ
 * @date 2015-4-29 下午10:33:14 
 * @version V1.0
 */

@Service
public class PmsService {
	
	@Autowired
	private PmsProductDao pmsProductDao;
	@Autowired
	private PmsAdvertisementDao pmsAdvertisementDao;
    /** 
     * @author CJay       
     * @created 2017年3月21日 下午2:58:49        
     */  
	
	public Page<Map<String, Object>> pageAndSort(Map<String, Object> params,int current, int pagesize, String sortString) {
		return pmsProductDao.pageAndSort(params, current, pagesize, sortString);
	}
	  
	
	public boolean insert(PmsProduct product) {
		return pmsProductDao.insert(product)>0;
	}

	
	public PmsProduct selectByPrimaryKey(String id) {
		return pmsProductDao.selectByPrimaryKey(id);
	}
	
	
	public boolean update(PmsProduct product) {
		return pmsProductDao.updateByPrimaryKeySelective(product)>0;
	}
	    
	
	public boolean deleteByPrimaryKey(String id) {
		return pmsProductDao.deleteByPrimaryKey(id)>0;
	}

	  
    /** 
     * @author CJay       
     * @created 2017年3月28日 上午10:37:30        
     */  
    
	
	public Page<Map<String, Object>> AdvPageAndSort(Map<String, Object> params,	int current, int pagesize, String sortString) {
		return pmsAdvertisementDao.pageAndSort(params, current, pagesize, sortString);
	}

	  
	
	public boolean insertAdvertisement(PmsAdvertisement pmsAdvertisement) {
		return pmsAdvertisementDao.insert(pmsAdvertisement)>0;
	}

	
	public boolean deleteAdvByPrimaryKey(String id) {
		return pmsAdvertisementDao.deleteByPrimaryKey(id)>0;
	}

	
	public PmsAdvertisement selectAdvByPrimaryKey(String id) {
		return pmsAdvertisementDao.selectByPrimaryKey(id);
	}

	
	public boolean updateAdvertisement(PmsAdvertisement pmsAdvertisement) {
		return pmsAdvertisementDao.updateByPrimaryKeySelective(pmsAdvertisement)>0;
	}
	


}
