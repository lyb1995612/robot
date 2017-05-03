package com.csjbot.robot.biz.sys.dao;

import java.util.Map;

import com.csjbot.robot.biz.sys.model.SysConfig;


/** 
 * @Description 
 * @author DCJ
 * @date 2015-4-29 下午4:32:15 
 * @version V1.0
 */
public interface SysConfigDao {
	
	public final static String PREFIX = SysConfigDao.class.getName();

	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param sysConfig
	 * @return  
	 */
	  	
	boolean insertSysConfig(SysConfig sysConfig);


	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	Map<String, Object> findOneSysConfig(Map<String, Object> params);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	boolean delSysConfig(Map<String, Object> params);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param method
	 * @param sysConfig
	 * @return  
	 */
	  	
	String getBeanSql(String method, Object arg);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param method
	 * @param sysConfig
	 * @return  
	 */
	  	
	public String getNamespaceSql(String method);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param method
	 * @param id
	 * @return  
	 */
	  	
	String getMapperSql(String method, Object... args);



	 
	/** 
	 * @Description 
	 * @author DCJ
	 * @param params
	 * @return  
	 */
	  	
	boolean delSysConfigLogo(Map<String, Object> params);


	

}
