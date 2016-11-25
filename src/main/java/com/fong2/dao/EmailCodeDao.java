package com.fong2.dao;

import com.fong2.entity.EmailCode;

public interface EmailCodeDao {
	EmailCode findByEmail(String email);
	Integer addCode(EmailCode ec);
}
