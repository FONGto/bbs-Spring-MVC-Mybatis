package com.fong2.service;

import java.util.List;
import java.util.Map;

import com.fong2.entity.Topic;
import com.fong2.serviceRtException.NodeNotFoundException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.TopicNotFoundException;
import com.fong2.serviceRtException.UserNotFoundException;

public interface TopicService {
	
	public List<Topic> getAllFilteByStatusOrderByDefault(String status);
	public List<Topic> getAllOrderByHot(String status);
	public List<Topic> getBySectionDefault(String status,String section);
	public Topic findByStatusAndTopicId(String status,String topicId);
	//处理对象内容
	List<Topic> topicAddUserNode(List<Topic> list);
	
	Integer saveTopic(String content,String title,
			String topicNodeId,String userId)
			throws TopicNotFoundException,NullParameterException,
			UserNotFoundException,NodeNotFoundException;
	/**
	 * 查节点主题
	 * @param status
	 * @param nodeId
	 * @return
	 */
	public List<Topic> findByStatusAndNodeId(int status, int nodeId);
	public Map<Object, Object> findByStatusAndUserId(int status, Integer userId,int p)
		throws NullParameterException,UserNotFoundException,TopicNotFoundException;
}
