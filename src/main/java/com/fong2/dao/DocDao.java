package com.fong2.dao;

import java.util.List;

import com.fong2.entity.Doc;

public interface DocDao {

	List<Doc> findAll();
	Integer modifyDoc(Doc doc);

}
