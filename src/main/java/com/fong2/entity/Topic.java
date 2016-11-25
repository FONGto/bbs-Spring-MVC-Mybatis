package com.fong2.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Topic implements java.io.Serializable {
	private static final long serialVersionUID = -4854566491341847894L;
	private Integer id;
	private Node node;
	private User user;
	private String content;
	private Date createAt;
	private Date lastCommentAt;
	private Integer lastCommentUserId;
	
	private User LastCommentUser;
	
	private Long likeCount=0L;
	private Long score=0L;
	private Integer status;
	private String title;
	private Date updateAt;
	private Long viewCount=0L;
	private Long commentCount=0L;
	private Integer collectonId;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Collection> collections = new HashSet<Collection>(0);

	public Topic() {
	}

	public Topic(Node node, User user, String content, String title,Integer status) {
		this.node = node;
		this.user = user;
		this.content = content;
		this.title = title;
		this.status=status;
	}

	public Topic(Node node, User user, String content, Date createAt, Date lastCommentAt, Integer lastCommentUserId,
			Long likeCount, Long score, Integer status, String title, Date updateAt,
			Long viewCount, Long commentCount, Integer collectonId, Set<Comment> comments,
			Set<Collection> collections) {
		this.node = node;
		this.user = user;
		this.content = content;
		this.createAt = createAt;
		this.lastCommentAt = lastCommentAt;
		this.lastCommentUserId = lastCommentUserId;
		this.likeCount = likeCount;
		this.score = score;
		this.status = status;
		this.title = title;
		this.updateAt = updateAt;
		this.viewCount = viewCount;
		this.commentCount = commentCount;
		this.collectonId = collectonId;
		this.comments = comments;
		this.collections = collections;
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

	public Date getLastCommentAt() {
		return this.lastCommentAt;
	}

	public void setLastCommentAt(Date lastCommentAt) {
		this.lastCommentAt = lastCommentAt;
	}

	public Integer getLastCommentUserId() {
		return this.lastCommentUserId;
	}

	public void setLastCommentUserId(Integer lastCommentUserId) {
		this.lastCommentUserId = lastCommentUserId;
	}

	public User getLastCommentUser() {
		return LastCommentUser;
	}

	public void setLastCommentUser(User lastCommentUser) {
		LastCommentUser = lastCommentUser;
	}

	public Long getLikeCount() {
		return this.likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Long getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getCollectonId() {
		return this.collectonId;
	}

	public void setCollectonId(Integer collectonId) {
		this.collectonId = collectonId;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", node=" + node + ", user=" + user + ", content=" + content + ", createAt="
				+ createAt + ", lastCommentAt=" + lastCommentAt + ", lastCommentUserId=" + lastCommentUserId
				+ ", LastCommentUser=" + LastCommentUser + ", likeCount=" + likeCount + ", score=" + score + ", status="
				+ status + ", title=" + title + ", updateAt=" + updateAt + ", viewCount=" + viewCount
				+ ", commentCount=" + commentCount + ", collectonId=" + collectonId + ", comments=" + comments
				+ ", collections=" + collections + "]";
	}
	
}
