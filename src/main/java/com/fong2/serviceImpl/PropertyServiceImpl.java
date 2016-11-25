package com.fong2.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.fong2.dao.PropertyDao;
import com.fong2.entity.Property;
import com.fong2.service.PropertyService;
import com.fong2.serviceRtException.NullParameterException;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService{
	
	@Resource
	PropertyDao propertyDao;
	
	private ServletContext application;

	public void updateProperties(List<Property> properties) throws NullParameterException {
		// TODO Auto-generated method stub
		
	}

	public void injectPropertiesToApplication(ServletContext application)
				throws NullParameterException {
		this.application=application;
		List<Property> properties=propertyDao.findKeywordAndValue();
		if(properties==null)
			throw new NullParameterException("从数据库获取站点信息失败");
		for (Property proty : properties) {
			application.setAttribute(proty.getKeyword(), proty.getValue());
		}
	}
	
	

}
