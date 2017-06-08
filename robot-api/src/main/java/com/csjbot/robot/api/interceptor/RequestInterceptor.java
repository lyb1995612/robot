package com.csjbot.robot.api.interceptor;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.biz.datasource.DBHelper;
import com.csjbot.robot.biz.datasource.DataSourceContextHolder;



/**          
     * Description 
     * @author CJay       
     * @created 2017年6月5日 上午9:06:24    
     */


public class RequestInterceptor  implements HandlerInterceptor{

	  
	    /** 
	     * @author CJay       
	     * @created 2017年6月5日 上午9:12:31        
	     */  
	    
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	  
	    /** 
	     * @author CJay       
	     * @created 2017年6月5日 上午9:12:31        
	     */  
	    
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	  
	    /** 
	     * @author CJay       
	     * @created 2017年6月5日 上午9:12:31        
	     */  
	    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println(request.getRequestURI());
		 String sn=request.getParameter("sn");
		 String sql = "select dbName from cms_tenant left join cms_robot on cms_tenant.id=cms_robot.tenant_fk where sn='" + sn + "'"; 
		 DBHelper db1 =new  DBHelper(sql); 
		 String dbName =null;
		 try {  
			 ResultSet    ret = db1.pst.executeQuery();//执行语句，得到结果集  
	            while (ret.next()) {  
	                dbName = ret.getString("dbName");
	                System.out.println(dbName);  
	            }//显示数据  
	            ret.close();  
	            db1.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } 
		 if(dbName==null){
			 return false;
		 }
		 DataSourceContextHolder.setDbType(dbName);
		 return true;
		/*HttpSession session = req.getSession(true);  
		  // 从session 里面获取用户名的信息  
		  Object obj = session.getAttribute(Config.Passport.SESSION_NAME_LOGIN_RESULT);  
		  // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
		  if (obj == null || "".equals(obj.toString())) {  
		   res.sendRedirect(LOGIN_URL);  
		  }  
		  return true;
	*/}

}
