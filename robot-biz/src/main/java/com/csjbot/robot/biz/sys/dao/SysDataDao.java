package com.csjbot.robot.biz.sys.dao;

import java.util.Map;

import com.csjbot.robot.biz.sys.model.SysData;
import com.csjbot.robot.biz.sys.model.param.DictionaryParam;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @Description 
 * @author DCJ
 * @date 2015-4-29 下午10:11:03 
 * @version V1.0
 */
public interface SysDataDao {
	
	public final static String PREFIX = SysDataDao.class.getName();

	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	Map<String, Object> findOneByCode(Map<String, Object> params);


	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param current
	 * @param pagesize
	 * @return  
	 */
	  	
	public PageList<SysData> page( DictionaryParam param,PageBounds pager);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	SysData findSysDataById(Map<String, Object> params);

}
