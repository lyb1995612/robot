package com.csjbot.robot.biz.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**          
     * Description 
     * @author CJay       
     * @created 2017年6月6日 下午2:52:54    
     */

public class DynamicDataSource extends AbstractRoutingDataSource {  
	  
   
	
    @Override  
    protected Object determineCurrentLookupKey() {  
           return DataSourceContextHolder.getDbType();  
    }  
 
}  
