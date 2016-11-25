package com.fong2.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
	private static final String salt="破解不了的盐？FONG2.COM";
	public static String crypt(String password){
		return DigestUtils.md5Hex(password+salt);
	}
}
