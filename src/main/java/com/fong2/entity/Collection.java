package com.fong2.entity;

import java.util.Date;


public class Collection implements java.io.Serializable {
	private static final long serialVersionUID = -2735056704544326089L;
	private Integer id;   		//收藏id
	private Topic topic;		//收藏主题
	private User user;			//收藏用户
	private Date createAt;		//收藏时间

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
