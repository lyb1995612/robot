package com.csjbot.robot.biz.pms.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.pms.model.PmsAdvertisementDistribute;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PmsAdvertisementDistributeDao {

	public final static String PREFIX = PmsProductDao.class.getName();
	
    int deleteByPrimaryKey(String sn);

    int inserts(List<PmsAdvertisementDistribute> pmsAdvertisementDistributes);
    
    int insert(PmsAdvertisementDistribute pmsAdvertisementDistribute);

    int insertSelective(PmsAdvertisementDistribute pmsAdvertisementDistribute);

    PmsAdvertisementDistribute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsAdvertisementDistribute pmsAdvertisementDistribute);

    int updateByPrimaryKey(PmsAdvertisementDistribute pmsAdvertisementDistribute);
    
    List<String> selectAdvertisementBySn(String sn);

	  
    /**     
     * @discription 广告list
     * @author CJay       
     * @created 2017年3月21日 下午1:48:23        
     */
//    public <E, K, V> Page<E> pageAndSort(Map<String, Object> params,int current, int pagesize, String sortString);
    
    public PageList<PmsAdvertisementDistribute> page(Map<String, Object> params, PageBounds pager);
}
