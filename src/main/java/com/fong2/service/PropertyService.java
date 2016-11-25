package com.fong2.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.fong2.entity.Property;
import com.fong2.serviceRtException.NullParameterException;

public interface PropertyService {
	/**
	 * @return 把把获取到的list 保存到持久层，并调用inject更新
	 * @throws NullParameterException 传入list为空
	 */
	void updateProperties(List<Property> properties) throws NullParameterException;
	/**
	 * @return 把持久层查找的List中每一个对象取出来 读取值并保存在applicationContext中
	 * @throws NullParameterException 从数据库获取站点信息失败
	 */
	void injectPropertiesToApplication(ServletContext application) throws NullParameterException;
}
