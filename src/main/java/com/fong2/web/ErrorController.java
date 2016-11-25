package com.fong2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping("/error/{errorCode}")
	public String error(@PathVariable(value="errorCode") int errorCode){
		return "/error/"+errorCode;
	}
}
