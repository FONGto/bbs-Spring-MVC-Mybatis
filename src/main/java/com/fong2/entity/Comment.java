package com.fong2.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = 6000460945777289921L;
	
	private Integer id;
	private Topic topic;
	private User user;
	private String content;
	private Date createAt;
	private long floor;
	private long likeCount=0L;
	private int status;
	private boolean isLike;
	private Set<Notification> notifications = new HashSet<Notification>(0);
	
	private Set<User> likeUsers =new HashSet<User>();

	public Comment() {
	}

	public Comment(Topic topic, User user, String content, Date createAt, long floor, long likeCount, int status,Set<User> likeUsers) {
		this.topic = topic;
		this.user = user;
		this.content = content;
		this.createAt = createAt;
		this.floor = floor;
		this.likeCount = likeCount;
		this.status = status;
		this.likeUsers = likeUsers;
	}

	public Comment(Topic topic, User user, String content, Date createAt, long floor, long likeCount, int status,boolean isLike,
			Set<Notification> notifications,Set<User> likeUsers) {
		this.topic = topic;
		this.user = user;
		this.content = content;
		this.createAt = createAt;
		this.floor = floor;
		this.likeCount = likeCount;
		this.status = status;
		this.isLike = isLike;
		this.notifications = notifications;
		this.likeUsers = likeUsers;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public long getFloor() {
		return this.floor;
	}

	public void setFloor(long floor) {
		this.floor = floor;
	}

	public long getLikeCount() {
		return this.likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getIsLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public Set<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Set<User> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(Set<User> likeUsers) {
		this.likeUsers = likeUsers;
	}
	
}
