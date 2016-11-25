package com.fong2.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.NodeDao;
import com.fong2.dao.SectionDao;
import com.fong2.entity.Node;
import com.fong2.service.NodeService;
import com.fong2.serviceRtException.NodeNotFoundException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.SectionNotFoundException;

@Service("nodeService")
public class NodeServiceImpl implements NodeService{
	@Resource
	private NodeDao nodeDao=null;
	@Resource
	private SectionDao sectionDao;
	public Node findByTopicNodeId(int topicNodeId) {
		if(nodeDao.findByTopicNodeId(topicNodeId)!=null)
			return nodeDao.findByTopicNodeId(topicNodeId);
		else{
			//throw new RuntimeException("该节点id不存在");
			return null;
		}
	}

	public List<Node> findAllOrderByTopicCountDesc(int pages) {
		List<Node> nodes=nodeDao.findAllOrderByTopicCountDesc();
		for(int i=0;i<nodes.size();i++){
			if(i>=pages)
				nodes.remove(i);
		}
		return nodes;
	}

	public List<Node> findBySectionId(Integer sectionId) throws SectionNotFoundException, NullParameterException {
		if(sectionId==null){
			throw new NullParameterException("分类Id不能为空");
		}
		if(sectionDao.findBySectionId(sectionId)==null)
			throw new SectionNotFoundException("该分类Id不存在");
		List<Node> nodes=nodeDao.findBySectionId(sectionId);
		if(nodes==null)
			throw new NodeNotFoundException("该分类下暂无节点");
		return nodes;
	}

	public List<Node> findAllNodes() {
		List<Node> nodes=nodeDao.findAllNodes();
		return nodes;
	}
	
}
