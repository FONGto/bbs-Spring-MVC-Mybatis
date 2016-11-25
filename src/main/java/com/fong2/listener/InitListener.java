package com.fong2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fong2.service.PropertyService;
import com.fong2.serviceImpl.PropertyServiceImpl;


public class InitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent scEvent) {
		ServletContext application = scEvent.getServletContext();
        ApplicationContext applicationContext = 
        		WebApplicationContextUtils.getWebApplicationContext(application);
        PropertyService propertyService = 
        		applicationContext.getBean("propertyService",PropertyService.class);
        //System.out.println(propertyService);
        //传入服务器application 保存全局信息
        propertyService.injectPropertiesToApplication(application);
	}

}
