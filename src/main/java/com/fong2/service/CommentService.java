package com.fong2.service;

import java.util.List;
import java.util.Map;

import com.fong2.entity.Comment;
import com.fong2.entity.User;
import com.fong2.serviceRtException.FailedDoneException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.TopicNotFoundException;
import com.fong2.serviceRtException.UserNotFoundException;

public interface CommentService {
	Integer findlastCommentUserIdByTopicId(int id);
	/**
	 * @param 主题id
	 * @return 最新回复用户
	 */
	User findlastCommentUserByTopicId(int id);
	
	Comment findlastCommentByTopicId(int id);
	/**
	 * @param 主题id
	 * @return 返回该主题下所有的评论
	 */
	List<Comment> findAllByTopicId(int id);
	/**
	 * 保存评论
	 */
	public Comment saveComment(String content,
			Integer topicId,Integer userId) 
			throws FailedDoneException, 
			NullParameterException,TopicNotFoundException;
	public Map<String, Object> findCommentByUserId(Integer status,Integer userId,int pageNum)
			throws NullParameterException,UserNotFoundException;
}
