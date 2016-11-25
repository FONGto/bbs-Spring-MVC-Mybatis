package com.fong2.entity;

import java.util.HashSet;
import java.util.Set;

public class Node implements java.io.Serializable {
	private static final long serialVersionUID = 6278423041575132251L;
	private Integer id;
	private Section section;
	private String avatar;
	private String description;
	private long topicCount=1L;
	private String name;
	private int status;
	private Set<Topic> topics = new HashSet<Topic>(0);
	private Set<Focus> focuses = new HashSet<Focus>(0);

	public Node() {
	}

	public Node(Section section, String name, int status) {
		this.section = section;
		this.name = name;
		this.status = status;
	}

	public Node(Section section, String avatar, String description, String name, int status, Set<Topic> topics,
			Set<Focus> focuses) {
		this.section = section;
		this.avatar = avatar;
		this.description = description;
		this.name = name;
		this.status = status;
		this.topics = topics;
		this.focuses = focuses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(long topicCount) {
		this.topicCount = topicCount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Set<Focus> getFocuses() {
		return this.focuses;
	}

	public void setFocuses(Set<Focus> focuses) {
		this.focuses = focuses;
	}

}
