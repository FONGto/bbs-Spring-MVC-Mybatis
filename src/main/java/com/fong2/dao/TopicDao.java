package com.fong2.dao;

import java.util.List;

import com.fong2.entity.Topic;
import com.fong2.entity.TopicNodeUser;


public interface TopicDao {
	/**
	 * 
	 * @param status 0代表正常主题  1代表已隐藏
	 * @return
	 */
	List<Topic> findAllByStatusOrderByLastCommentAtDesc(String status);
	TopicNodeUser findUserIdAndNodeIdByTopicId(int id);
	List<Topic> getAllOrderByHot(String status);
	List<Topic> findAllByStatusAndSectionId(String status,String sectionId);
	Topic findByStatusAndTopicId(String status,String topicId);
	Integer updateTopicByTopicId(Topic topic);
	Integer saveTopic(Topic topic);
	List<Topic> findByStatusAndNodeId(int status,int nodeId);
	List<Topic> findByStatusAndUserId(int status,int userId);
}
