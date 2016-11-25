package com.fong2.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fong2.utils.JsonResult;


public abstract class AbstExptionController {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult password(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}
