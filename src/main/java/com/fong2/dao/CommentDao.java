package com.fong2.dao;

import java.util.List;

import com.fong2.entity.Comment;

public interface CommentDao {
	Integer findlastCommentUserIdByTopicId(int status,int id);
	Comment findlastCommentByTopicId(int status,int id);
	List<Comment> findAllByTopicIdOrderFloor(int status,int id);
	Integer findUserIdByCommentId(int id);
	Integer saveComment(Comment comment);
	List<Comment> findCommentByUserId(int status,int userId);
	List<Comment> findByUserId(int status,int userId);
}
