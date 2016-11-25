package com.fong2.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fong2.entity.Comment;
import com.fong2.entity.Section;
import com.fong2.entity.Topic;
import com.fong2.entity.User;
import com.fong2.service.CommentService;
import com.fong2.service.SectionService;
import com.fong2.service.TopicService;
import com.fong2.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/topics")
public class TopicController {
	@Resource
	TopicService topicService;
	@Resource
	CommentService commentService;
	@Resource
	SectionService sectionService;
	
	@RequestMapping("/")
    public String index() {
        return "/error/404";
    }
	
	@RequestMapping("/{id}")
	public String getTopic(HttpServletRequest rq,HttpServletResponse rp,Model model,
			@RequestParam(value = "p", defaultValue = "1") int p,
			@PathVariable(value = "id") String topicId){
		//System.out.println(rq.getRequestURL()+":"+topicId);
		Topic topic=topicService.findByStatusAndTopicId("0", topicId);
		List<Topic> list=new ArrayList<Topic>();
		list.add(topic);
		list=topicService.topicAddUserNode(list);
		topic=list.get(0);
		User user = (User)rq.getSession().getAttribute("user");
		if (topic != null) {// 存在文章的时候才回复
			PageHelper.startPage(p, 10);
            List<Comment> comments=commentService.findAllByTopicId(topic.getId());
			PageInfo<Comment> commentsPage=new PageInfo<Comment>(comments);
            //Page<Comment> commentPage = commentServ.getByTopicWithLike(topic, p, user);// 按照话题查找
            model.addAttribute("page", commentsPage);
            model.addAttribute("comments", comments);
            //model.addAttribute("otherTopics", topicServ.getByUser(user, 1, 8).getContent());
        } else {
            rp.setStatus(HttpServletResponse.SC_NOT_FOUND);// 抛出404
        }
        model.addAttribute("topic", topic);
		/*
		Topic topic = topicServ.getOne(id);
        User user = (User) request.getSession().getAttribute("user");
        boolean isCollected = collectionServ.isCollected(topic, user);
        model.addAttribute("isCollected", isCollected);
        if (topic != null) {
            Page<Comment> commentPage = commentServ.getByTopicWithLike(topic, p, user);// 按照话题查找
            model.addAttribute("page", commentPage);// 存在文章的时候才回复
            model.addAttribute("comments", commentPage.getContent());
            model.addAttribute("otherTopics", topicServ.getByUser(user, 1, 8).getContent());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 抛出404
        }
        model.addAttribute("topic", topic);
		 * */
		return "topics/show";
	}
	@RequestMapping("/create")
	public String create(HttpSession session){
		User user=(User) session.getAttribute("user");
		if(user==null)
			return "redirect:/account/signin";
		List<Section> sections=sectionService.findAll();
		session.setAttribute("sections", sections);
		return "topics/create";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/createAction.do")
	@ResponseBody
	public JsonResult createAction(String content,String title,
			String topicNodeId,HttpSession session){
		User user=(User) session.getAttribute("user");
		if(user==null){
			return new JsonResult(1,"","请先登录");
		}
		int i=topicService.saveTopic(content, title, topicNodeId, String.valueOf(user.getId()));
	
		return new JsonResult(0,"","主题创建成功");
	}
}
