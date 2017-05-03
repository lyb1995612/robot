/*package com.csjbot.admin.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Log4jListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext  servletContext  = event.getServletContext();
		Log4jWebConfig.initLogging(servletContext);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Log4jWebConfig.shutdownLogging(event.getServletContext());
	}

 

}
*/