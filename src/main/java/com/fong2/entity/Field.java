package com.fong2.entity;



public class Field implements java.io.Serializable {
	private static final long serialVersionUID = -1421578549422893784L;
	private Integer id;
	private Property property;
	private String content;
	private String meta;

	public Field() {
	}

	public Field(Property property, String content, String meta) {
		this.property = property;
		this.content = content;
		this.meta = meta;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeta() {
		return this.meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

}
