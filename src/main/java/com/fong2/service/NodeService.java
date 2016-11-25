package com.fong2.service;

import java.util.List;

import com.fong2.entity.Node;
import com.fong2.serviceRtException.NodeNotFoundException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.SectionNotFoundException;

public interface NodeService {
	public Node findByTopicNodeId(int topicNodeId);
	public List<Node> findAllOrderByTopicCountDesc(int pages);
	public List<Node> findBySectionId(Integer sectionId)
		throws SectionNotFoundException,NullParameterException,NodeNotFoundException;
	public List<Node> findAllNodes();
}
