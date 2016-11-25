package com.fong2.serviceImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fong2.service.UploadFileService;

@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService{

	public String upload(MultipartFile file,String path) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String lastFileName=file.getOriginalFilename();
		lastFileName=lastFileName.substring(lastFileName.lastIndexOf("."));
		String fileName=sdf.format(new Date())+lastFileName;
		File targetFile=new File(path,fileName);
		if(!targetFile.exists())
			targetFile.mkdirs();
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}



}
