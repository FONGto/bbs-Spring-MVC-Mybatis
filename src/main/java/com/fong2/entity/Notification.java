package com.fong2.entity;

public class Notification implements java.io.Serializable {
	private static final long serialVersionUID = -3856760876737368919L;
	private Integer id;
	private Comment comment;
	private User user;
	private String type;
	private int status;

	public Notification() {
	}

	public Notification(Comment comment, User user, String type,int status) {
		this.comment = comment;
		this.user = user;
		this.type = type;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
