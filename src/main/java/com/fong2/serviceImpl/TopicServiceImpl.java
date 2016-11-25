package com.fong2.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.CommentDao;
import com.fong2.dao.NodeDao;
import com.fong2.dao.TopicDao;
import com.fong2.dao.UserDao;
import com.fong2.entity.Comment;
import com.fong2.entity.Node;
import com.fong2.entity.Topic;
import com.fong2.entity.TopicNodeUser;
import com.fong2.entity.User;
import com.fong2.service.CommentService;
import com.fong2.service.NodeService;
import com.fong2.service.TopicService;
import com.fong2.serviceRtException.NodeNotFoundException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.TopicNotFoundException;
import com.fong2.serviceRtException.UserNotFoundException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
	@Resource
	private TopicDao topicDao=null;
	@Resource
	private UserDao userDao=null;
	@Resource
	private CommentDao commentDao=null;
	@Resource
	private NodeDao nodeDao=null;
	@Resource
	private NodeService nodeService;
	@Resource 
	private CommentService commentService;
	public List<Topic> getAllFilteByStatusOrderByDefault(String status) {
		List<Topic> list= topicDao.findAllByStatusOrderByLastCommentAtDesc(status);
		list=topicAddUserNode(list);
		return list;
	}
	/**
	 * 最热点主题
	 */
	public List<Topic> getAllOrderByHot(String status) {
		List<Topic> list=topicDao.getAllOrderByHot(status);
		list=topicAddUserNode(list);
		return list;
	}
	/**
	 * 为topiclist增加User Node Comment对象
	 * @param list
	 * @return
	 */
	public List<Topic> topicAddUserNode(List<Topic> list) {
		for (Topic topic : list) {
			//查发帖者id、nodeId
			TopicNodeUser tnu=topicDao.findUserIdAndNodeIdByTopicId(topic.getId());
			//id查人
			User u=new User();
			u.setId(tnu.getUserId());
			User u2=userDao.findByIdOrUsernameOrNick(u);
			//把user对象加进topic对象
			topic.setUser(u2);
			//查该贴最新回帖记录
			Comment lastComment=null;
			//最新回帖者id
			User u4=null;
			if(commentDao.findlastCommentByTopicId(0,topic.getId())!=null){
				lastComment=commentDao.findlastCommentByTopicId(0,topic.getId());
				u4=commentService.findlastCommentUserByTopicId(topic.getId());
			}
			if(u4!=null){
				topic.setLastCommentUser(u4);
				topic.setLastCommentAt(lastComment.getCreateAt());
			}
			//查节点 分类
			if(nodeService.findByTopicNodeId(tnu.getNodeId())!=null)
				topic.setNode(nodeService.findByTopicNodeId(tnu.getNodeId()));
		}
		return list;
	}

	public List<Topic> getBySectionDefault(String status,String sectionId) {
		/*List<Node> nodes=nodeDao.findBySectionId(Integer.valueOf(sectionId));
		List<Topic> list=new ArrayList<Topic>();
		for (Node node: nodes) {
			list.addAll(topicDao.findAllByStatusAndNodeId(status, String.valueOf(node.getId())));
		}
		list=topicAddUserNode(list);
		java.util.Collections.sort(list,new Comparator<Topic>() {
			public int compare(Topic o1, Topic o2) {
				if(o1.getLastCommentAt()==null||o2.getLastCommentAt()==null)
					return 0;
				if(o1.getLastCommentAt().getTime()<o2.getLastCommentAt().getTime())
					return 1;
				else if(o1.getLastCommentAt().getTime()==o2.getLastCommentAt().getTime())
					return 0;
				else 
					return -1;
			}
			
		});
		return list;*/
		List<Topic> topics=topicDao.findAllByStatusAndSectionId(status, sectionId);
		topics=topicAddUserNode(topics);
		return topics;
	}
	
	/**
	 * 
	 */
	public Topic findByStatusAndTopicId(String status,String topicId){
		Topic topic=topicDao.findByStatusAndTopicId(status, topicId);
		topic.setViewCount(topic.getViewCount()+1);
		Topic t=new Topic();
		t.setId(Integer.valueOf(topicId));
		t.setViewCount(topic.getViewCount());
		topicDao.updateTopicByTopicId(t);
		return topic;
	}
	public Integer saveTopic(String content, String title, String topicNodeId, String userId)
			throws TopicNotFoundException, NullParameterException, UserNotFoundException, NodeNotFoundException {
		if(title==null||title.trim().isEmpty())
			throw new NullParameterException("标题不能为空");
		if(content==null||content.trim().isEmpty())
			content=" ";
		/*if(sectionId==null||sectionId.trim().isEmpty()){
		}*/
		if(topicNodeId==null||topicNodeId.trim().isEmpty()){
			throw new NullParameterException("节点不能为空");
		}
		Node node=new Node();
		node.setId(Integer.valueOf(topicNodeId));
		if(userId==null||userId.trim().isEmpty()){
			throw new NullParameterException("用户ID不能为空");
		}
		User user=new User();
		user.setId(Integer.valueOf(userId));
		user.setTopicCount(1L);
		Topic topic=new Topic(node, user, content, title, 0);	
		int i=topicDao.saveTopic(topic);
		node.setTopicCount(1);
		int j=nodeDao.modifyNode(node);
		int k=userDao.updateUser(user);
		if(i+j+k!=3)
			throw new TopicNotFoundException("新建主题失败");
		return i;
	}
	public List<Topic> findByStatusAndNodeId(int status, int nodeId) {
		List<Topic> topics=topicDao.findByStatusAndNodeId(status, nodeId);
		topics=topicAddUserNode(topics);
		return topics;
	}
	public Map<Object, Object> findByStatusAndUserId(int status, Integer userId,int p)
			throws NullParameterException, UserNotFoundException, TopicNotFoundException {
		if(userId==null)
			throw new NullParameterException("userId空异常");
		User user=new User();
		user.setId(userId);
		if(userDao.findByIdOrUsernameOrNick(user)==null)
			throw new UserNotFoundException("user不存在");
		PageHelper.startPage(p, 8);
		List<Topic> topics=topicDao.findByStatusAndUserId(status, userId);
		PageInfo<Topic> pages=new PageInfo<Topic>(topics);
		topics=topicAddUserNode(topics);
		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("topics", topics);
		map.put("pages", pages);
		/*if(topics==null)
			throw new TopicNotFoundException("该用户尚未存在发帖记录");*/
		return map;
	}
	
	
}
