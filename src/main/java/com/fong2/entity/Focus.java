package com.fong2.entity;

import java.util.Date;

public class Focus implements java.io.Serializable {
	private static final long serialVersionUID = 8807270852265675532L;
	private Integer id;
	private Node node;
	private User user;
	private Date createAt;

	public Focus() {
	}

	public Focus(Node node, User user, Date createAt) {
		this.node = node;
		this.user = user;
		this.createAt = createAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
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
