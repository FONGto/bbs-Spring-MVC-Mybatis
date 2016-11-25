package com.fong2.service;

import java.util.List;

import com.fong2.entity.Doc;

public interface DocService {
	
	List<Doc> findAll();
	Doc findDocByUrl(String url);
}
