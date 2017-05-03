package com.csjbot.robot.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.sys.model.param.DictionaryParam;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @Description 
 * @author DCJ
 * @date 2015-4-29 下午10:11:33 
 * @version V1.0
 */
public interface SysDataDictionaryDao {

	public final static String PREFIX = SysDataDictionaryDao.class.getName();
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	public <T, K, V> List<T> findDicById(Map<K, V> params);
	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	public <T, K, V> List<T> findDictionaryByCode(Map<String, Object> params);

	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @param current
	 * @param pagesize
	 * @return  
	 */
	public  PageList<SysDataDictionary> page(DictionaryParam param,PageBounds pager);

	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	public SysDataDictionary findSysDataDicById(Map<String, Object> params);

	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param dicdata
	 * @return  
	 */
	  	
	public int dicDataUpdate(SysDataDictionary dicdata);  
	
	public int dicDataInsert(SysDataDictionary dicdata);  

}
