package com.fong2.dao;

import java.util.List;

import com.fong2.entity.Section;


public interface SectionDao {
	List<Section> getAll();
	Section findBySectionId(int sectionId);
}
