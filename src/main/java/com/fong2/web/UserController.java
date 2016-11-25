package com.fong2.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fong2.entity.Comment;
import com.fong2.entity.Topic;
import com.fong2.entity.User;
import com.fong2.service.CommentService;
import com.fong2.service.TopicService;
import com.fong2.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/users")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private TopicService topicService;
	@Resource
	private CommentService commentService;
	
	@RequestMapping(value={"/","","index"})
	public String index(Model model){
		List<User> users=userService.findAllUserOrderByNick();
		model.addAttribute("users", users);
		return "users/index";
	}
	
	@RequestMapping(value={"/{id}","/{id}/"})
	public String toUserHomePage(@PathVariable("id") int id){
		
		return "redirect:"+id+"/topics";
	}
	
	
	@RequestMapping(value={"/{id}/{page}"})
	public String userPersonalPage(@PathVariable("id") int id,
			@PathVariable("page") String page,Model model,
			@RequestParam(defaultValue = "1", value = "p") int p,
			HttpServletResponse rp){
		User user=userService.findByIdOrUsernameOrNick(id,null,null);
		model.addAttribute("user", user);
		if("topics".equals(page)||page==null||page.trim().isEmpty()){
			Map<Object, Object> map=topicService.findByStatusAndUserId(0, id, p);
			model.addAttribute("topics", map.get("topics"));
			model.addAttribute("page", map.get("pages"));
			return "users/topics";
		}
		else if("comments".equals(page)){
			Map<String, Object> map= commentService.findCommentByUserId(0, id, p);
			model.addAttribute("comments", map.get("comments"));
			model.addAttribute("page", map.get("pages"));
			return "users/comments";
		}
		else if("collections".equals(page)){
			model.addAttribute("page.total", 0);
			return "users/collections";
		}
		else if("following".equals(page)){
			model.addAttribute("page.total", 0);
			return "users/following";
		}else if("followers".equals(page)){
			model.addAttribute("page.total", 0);
			return "users/followers";
		}
		else
			return "error/404";
	}

}
