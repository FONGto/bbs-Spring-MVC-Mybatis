package com.fong2.entity;

public class TopicNodeUser {
	private Integer id;
	private Integer nodeId;
	private Integer userId;
	public TopicNodeUser(){}
	public TopicNodeUser(Integer id, Integer nodeId, Integer userId) {
		super();
		this.id = id;
		this.nodeId = nodeId;
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
