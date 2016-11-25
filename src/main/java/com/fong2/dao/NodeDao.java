package com.fong2.dao;

import java.util.List;
import com.fong2.entity.Node;

public interface NodeDao {
	Node findByTopicNodeId(int topicNodeId);
	List<Node> findAllOrderByTopicCountDesc();
	List<Node> findBySectionId(int sectionId);
	List<Node> findAllNodes();
	Integer modifyNode(Node node);
}
