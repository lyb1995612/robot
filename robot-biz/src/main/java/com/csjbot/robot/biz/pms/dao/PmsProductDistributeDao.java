package com.csjbot.robot.biz.pms.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.pms.model.PmsProductDistribute;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PmsProductDistributeDao {

	public final static String PREFIX = PmsProductDao.class.getName();
	
    int deleteByPrimaryKey(String sn);

    int insert(PmsProductDistribute pmsProductDistribute);
    
    int inserts(List<PmsProductDistribute> pmsProductDistributes);    

    int insertSelective(PmsProductDistribute pmsProductDistribute);

    PmsProductDistribute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsProductDistribute pmsProductDistribute);

    int updateByPrimaryKey(PmsProductDistribute pmsProductDistribute);
    
    List<String> selectProductBySn(String sn);

	  
    /**     
     * @discription 产品list
     * @author CJay       
     * @created 2017年3月21日 下午1:48:23        
     */
//    public <E, K, V> Page<E> pageAndSort(Map<String, Object> params,int current, int pagesize, String sortString);
    
    public PageList<PmsProductDistribute> page(Map<String, Object> params, PageBounds pager);
}
