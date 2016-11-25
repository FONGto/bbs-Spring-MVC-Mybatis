package com.fong2.dao;

import java.util.List;

import com.fong2.entity.Property;


public interface PropertyDao {
	List<Property> findKeywordAndValue();
}
