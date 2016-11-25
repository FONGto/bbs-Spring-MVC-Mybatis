package com.fong2.entity;

import java.util.Date;

public class EmailCode {
	private String email;
	private String code;
	private Date time;
	public EmailCode(){	}
	
	public EmailCode(String email, String code, Date time) {
		super();
		this.email = email;
		this.code = code;
		this.time = time;
	}
	
	public EmailCode(String email, String code) {
		super();
		this.email = email;
		this.code = code;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailCode other = (EmailCode) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailCode [email=" + email + ", code=" + code + ", time="
				+ time + "]";
	}
	
	
	
}
