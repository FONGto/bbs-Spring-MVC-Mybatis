package com.fong2.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.SectionDao;
import com.fong2.entity.Section;
import com.fong2.service.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	@Resource
	SectionDao sectionDao=null;
		
	public List<Section> findAll() {
		List<Section> list=sectionDao.getAll();
		return list;
	}

}
