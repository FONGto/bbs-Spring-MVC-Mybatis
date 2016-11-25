package com.fong2.entity;

import java.util.Date;


public class Doc implements java.io.Serializable {

	private static final long serialVersionUID = -1463781496520664879L;
	private Integer id;
	private String url;
	private String title;
	private Date createAt;
	private Date updateAt;
	private String content;
	private Long viewCount=1L;

	public Doc() {
	}

	public Doc(String url, String title, String content) {
		this.url = url;
		this.title = title;
		this.content = content;
	}

	public Doc(String url, String title, Date createAt, Date updateAt, String content, Long viewCount) {
		this.url = url;
		this.title = title;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.content = content;
		this.viewCount = viewCount;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

}
