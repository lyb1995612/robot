package com.csjbot.robot.biz.datasource;

  
/**          
     * Description 
     * @author CJay       
     * @created 2017年6月6日 下午2:50:51    
     */

public class DataSourceContextHolder {
	 
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
 
    public static void setDbType(String dbType) {  
           contextHolder.set(dbType);  
    }  
 
    public static String getDbType() {  
           return ((String) contextHolder.get());  
    }  
 
    public static void clearDbType() {  
           contextHolder.remove();  
    }  

}
