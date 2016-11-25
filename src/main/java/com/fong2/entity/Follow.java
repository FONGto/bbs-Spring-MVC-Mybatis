package com.fong2.entity;

import java.util.Date;

public class Follow implements java.io.Serializable {
	private static final long serialVersionUID = 5850698447971732395L;
	private int id;
	private User userByFollowerId;
	private User userByFollowingId;
	private Date createAt;

	public Follow() {
	}

	public Follow(int id, User userByFollowerId, User userByFollowingId, Date createAt) {
		this.id = id;
		this.userByFollowerId = userByFollowerId;
		this.userByFollowingId = userByFollowingId;
		this.createAt = createAt;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserByFollowerId() {
		return this.userByFollowerId;
	}

	public void setUserByFollowerId(User userByFollowerId) {
		this.userByFollowerId = userByFollowerId;
	}

	public User getUserByFollowingId() {
		return this.userByFollowingId;
	}

	public void setUserByFollowingId(User userByFollowingId) {
		this.userByFollowingId = userByFollowingId;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
