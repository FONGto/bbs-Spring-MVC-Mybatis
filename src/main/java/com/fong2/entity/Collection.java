package com.fong2.entity;

import java.util.Date;


public class Collection implements java.io.Serializable {
	private static final long serialVersionUID = -2735056704544326089L;
	private Integer id;   		//鏀惰棌id
	private Topic topic;		//鏀惰棌涓婚
	private User user;			//鏀惰棌鐢ㄦ埛
	private Date createAt;		//鏀惰棌鏃堕棿

	public Collection() {
	}

	public Collection(Topic topic, User user) {
		this.topic = topic;
		this.user = user;
	}

	public Collection(Topic topic, User user, Date createAt) {
		this.topic = topic;
		this.user = user;
		this.createAt = createAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
