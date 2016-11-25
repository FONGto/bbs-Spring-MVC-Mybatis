package com.fong2.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.CommentDao;
import com.fong2.dao.TopicDao;
import com.fong2.dao.UserDao;
import com.fong2.entity.Comment;
import com.fong2.entity.Topic;
import com.fong2.entity.User;
import com.fong2.service.CommentService;
import com.fong2.serviceRtException.FailedDoneException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.TopicNotFoundException;
import com.fong2.serviceRtException.UserNotFoundException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Resource
	private UserDao userDao;
	@Resource
	private CommentDao commentDao;
	@Resource
	private TopicDao topicDao;
	public Integer findlastCommentUserIdByTopicId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @param 主题id
	 * @return 最新回复用户
	 */
	public User findlastCommentUserByTopicId(int id) {
		int userId=commentDao.findlastCommentUserIdByTopicId(0,id);
		User user=new User();
		user.setId(userId);
		return userDao.findByIdOrUsernameOrNick(user);
	}

	public Comment findlastCommentByTopicId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param 主题id
	 * @return 返回该主题下所有的评论
	 */
	public List<Comment> findAllByTopicId(int id) {
		List<Comment> list= commentDao.findAllByTopicIdOrderFloor(0,id);
		//由于Comment类下的User属性为对象，查询出来的是int userId 
		//因此遍历用id查对象并加入Comment中
		for (Comment cmet : list) {
			//System.out.println(cmet.getId());
			User user=new User();
			user.setId(commentDao.findUserIdByCommentId(cmet.getId()));
			user=userDao.findByIdOrUsernameOrNick(user);
			cmet.setUser(user);
		}
		return list;
	}
	public Comment saveComment(String content,Integer topicId,Integer userId) throws FailedDoneException, NullParameterException,TopicNotFoundException {
		if(content==null||content.trim().isEmpty()){
			throw new NullParameterException("评论内容不能为空");
		}
		if(topicId==null)
			throw new NullParameterException("评论主题Id不能为空");
		Topic topic=new Topic();
		if(topicDao.findUserIdAndNodeIdByTopicId(topicId)==null)
			throw new TopicNotFoundException("主题不存在");
		topic.setId(topicId);
		if(userId==null)
			throw new NullParameterException("评论用户Id不能为空");
		User user=new User();
		user.setId(userId);
		user.setCommentCount(1L);
		if(userDao.findByIdOrUsernameOrNick(user)==null)
			throw new UserNotFoundException("用户不存在");
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setUser(user);
		comment.setTopic(topic);
		comment.setStatus(0);
		comment.setLikeCount(0);
		int i=commentDao.saveComment(comment);
		if(i!=1){
			throw new FailedDoneException("保存评论失败");
		}
		comment=commentDao.findlastCommentByTopicId(0, topicId);
		user=userDao.findByIdOrUsernameOrNick(user);
		comment.setUser(user);
		//更新topic表中评论数
		topic.setCommentCount(comment.getFloor());
		topic.setLastCommentAt(comment.getCreateAt());
		topicDao.updateTopicByTopicId(topic);
		//更新user表中评论数
		userDao.updateUser(user);
		return comment;
	}
	public Map<String, Object> findCommentByUserId(Integer status,Integer userId,int pageNum) throws NullParameterException, UserNotFoundException {
		if(userId==null)
			throw new NullParameterException("userId不能为空");
		User user=new User();
		user.setId(userId);
		if(userDao.findByIdOrUsernameOrNick(user)==null)
			throw new UserNotFoundException("用户不存在");
		PageHelper.startPage(pageNum, 8);
		List<Comment> list=commentDao.findByUserId(status,userId);
		PageInfo<Comment> pages=new PageInfo<Comment>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("comments", list);
		map.put("pages", pages);
		return map;
	}

}
