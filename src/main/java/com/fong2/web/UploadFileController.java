package com.fong2.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fong2.entity.User;
import com.fong2.service.UploadFileService;
import com.fong2.utils.JsonResult;

@Controller
public class UploadFileController {
	@Resource
	private UploadFileService uploadFileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(MultipartFile file,HttpSession session,
			HttpServletRequest rq){
		String path=session.getServletContext().getRealPath("upload");
		User user=(User) session.getAttribute("user");
		if(user==null)
			return new JsonResult(1,"","请先登录");
		path=path+"/"+user.getId();
		System.out.println(path);
		String result=null;
		try {
			result=uploadFileService.upload(file, path);
			result=rq.getRequestURL()+"/"+user.getId()+"/"+result;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(1,"","上传失败");
		}
		return new JsonResult(result);
	}
}
