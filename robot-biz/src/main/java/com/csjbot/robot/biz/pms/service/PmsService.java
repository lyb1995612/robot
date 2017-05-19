
/**   
 * @Title: DictionaryServiceImpl.java 
 * @Package: com.csjbot.admin.backadmin.sys.service.impl 
 * @Description: TODO
 * @author DCJ  
 * @date 2015-4-29 下午10:33:14 
 * @version 1.0 
 */


package com.csjbot.robot.biz.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.cms.dao.CmsRobotDao;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.pms.dao.PmsAdvertisementDao;
import com.csjbot.robot.biz.pms.dao.PmsAdvertisementDistributeDao;
import com.csjbot.robot.biz.pms.dao.PmsProductDao;
import com.csjbot.robot.biz.pms.dao.PmsProductDistributeDao;
import com.csjbot.robot.biz.pms.dao.Pms_advertisementDAO;
import com.csjbot.robot.biz.pms.dao.Pms_productDAO;
import com.csjbot.robot.biz.pms.model.PmsAdvertisement;
import com.csjbot.robot.biz.pms.model.PmsAdvertisementDistribute;
import com.csjbot.robot.biz.pms.model.PmsProduct;
import com.csjbot.robot.biz.pms.model.PmsProductDistribute;
import com.csjbot.robot.biz.pms.model.Pms_advertisement;
import com.csjbot.robot.biz.pms.model.Pms_product;
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
public class PmsService {
	
	@Autowired
	private PmsProductDao pmsProductDao;
	@Autowired
	private PmsAdvertisementDao pmsAdvertisementDao;
	@Autowired
	private PmsProductDistributeDao pmsProductDistributeDao;
	@Autowired
	private PmsAdvertisementDistributeDao pmsAdvertisementDistributeDao;
	@Autowired
	private CmsRobotDao cmsRobotDao;
	@Autowired
	private Pms_productDAO pms_productDAO;
	@Autowired
	private Pms_advertisementDAO pms_advertisementDAO;
	
    /** 
     * @author CJay       
     * @created 2017年3月21日 下午2:58:49        
     */  
	
    public PageList<PmsProduct> page(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return pmsProductDao.page(params, pager);
    }
    
    public List<Pms_product> selectAll() {
		return pms_productDAO.selectAll();
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
    
	
//	public Page<Map<String, Object>> AdvPageAndSort(Map<String, Object> params,	int current, int pagesize, String sortString) {
//		return pmsAdvertisementDao.pageAndSort(params, current, pagesize, sortString);
//	}
    public PageList<PmsAdvertisement> advPage(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return pmsAdvertisementDao.page(params, pager);
    }
	
    public List<Pms_advertisement> selectAllAd() {
		return pms_advertisementDAO.selectAll();
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

    /** 
     * @author lyb       
     * @created 2017年5月15日 上午10:41:30        
     */ 
	
    public PageList<PmsProductDistribute> disPage(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return pmsProductDistributeDao.page(params, pager);
    }
    
    
	public boolean insert(PmsProductDistribute pmsProductDistribute) {
		return pmsProductDistributeDao.insert(pmsProductDistribute)>0;
	}
	
	public boolean insert(List<PmsProductDistribute> pmsProductDistributes) {
		return pmsProductDistributeDao.inserts(pmsProductDistributes)>0;
	}
	
	public List<CmsRobot> getCmsRobots() {
		return cmsRobotDao.getCmsRobots();
	}
	
	
	public boolean deletePdByPrimaryKey(String sn) {
		return pmsProductDistributeDao.deleteByPrimaryKey(sn)>0;
	}
	
	public List<String> selectProductBySn(String sn) {
		return pmsProductDistributeDao.selectProductBySn(sn);
	}
	
	
    /** 
     * @author lyb       
     * @created 2017年5月15日 上午10:41:30        
     */ 
	
    public PageList<PmsAdvertisementDistribute> adDisPage(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return pmsAdvertisementDistributeDao.page(params, pager);
    }
       
	public boolean insert(PmsAdvertisementDistribute pmsAdvertisementDistribute) {
		return pmsAdvertisementDistributeDao.insert(pmsAdvertisementDistribute)>0;
	}
	
	public boolean insertAd(List<PmsAdvertisementDistribute> pmsAdvertisementDistributes) {
		return pmsAdvertisementDistributeDao.inserts(pmsAdvertisementDistributes)>0;
	}	
	
	public boolean deleteAdByPrimaryKey(String sn) {
		return pmsAdvertisementDistributeDao.deleteByPrimaryKey(sn)>0;
	}
	
	public List<String> selectAdvertisementBySn(String sn) {
		return pmsAdvertisementDistributeDao.selectAdvertisementBySn(sn);
	}


}
