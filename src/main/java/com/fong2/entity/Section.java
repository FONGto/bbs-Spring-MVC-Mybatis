package com.fong2.entity;

import java.util.HashSet;
import java.util.Set;


public class Section implements java.io.Serializable {
	private static final long serialVersionUID = 4338026465342952298L;
	private Integer id;
	private String name;
	private int sort;
	private Set<Node> nodes = new HashSet<Node>(0);

	public Section() {
	}

	public Section(int sort) {
		this.sort = sort;
	}

	public Section(String name, int sort, Set<Node> nodes) {
		this.name = name;
		this.sort = sort;
		this.nodes = nodes;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}
