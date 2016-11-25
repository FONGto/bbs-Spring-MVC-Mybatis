package com.fong2.service;


public interface SendMailService {
	 public void send(String to, String subject,String text);
	 void forgetPwd(String email);
}
