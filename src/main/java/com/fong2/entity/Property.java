package com.fong2.entity;

public class Property implements java.io.Serializable {
	private static final long serialVersionUID = -5487431171852782954L;
	private Integer id;
	private String keyword;
	private String value;
	
	public Property() {}

	public Property(String keyword, String value) {
		super();
		this.keyword = keyword;
		this.value = value;
	}
	
	public Property(Integer id, String keyword, String value) {
		super();
		this.id = id;
		this.keyword = keyword;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Property other = (Property) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", keyword=" + keyword + ", value=" + value + "]";
	}
	
}
