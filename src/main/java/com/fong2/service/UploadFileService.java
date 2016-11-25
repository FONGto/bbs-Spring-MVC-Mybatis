package com.fong2.service;

import org.springframework.web.multipart.MultipartFile;


public interface UploadFileService {
	
	public String upload(MultipartFile file,String path) throws Exception;

}
