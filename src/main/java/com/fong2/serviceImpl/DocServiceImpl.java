package com.fong2.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.DocDao;
import com.fong2.entity.Doc;
import com.fong2.service.DocService;

@Service("docService")
public class DocServiceImpl implements DocService{
	
	@Resource
	DocDao docDao;
	
	public List<Doc> findAll() {
		List<Doc> docs=docDao.findAll();
		return docs;
	}

	public Doc findDocByUrl(String url) {
		List<Doc> docs=docDao.findAll();
		for (Doc doc : docs) {
			if(doc.getUrl().equals(url)){
				Doc d1=new Doc();
				d1.setId(doc.getId());
				d1.setViewCount(1L);
				docDao.modifyDoc(d1);
				return doc;
			}
		}
		return null;
	}
	
	


}
