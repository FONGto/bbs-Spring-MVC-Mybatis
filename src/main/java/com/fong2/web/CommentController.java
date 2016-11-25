package com.fong2.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fong2.entity.Comment;
import com.fong2.entity.User;
import com.fong2.service.CommentService;
import com.fong2.utils.JsonResult;

@Controller
@RequestMapping("/comments")
public class CommentController extends AbstExptionController{
	
	@Resource
	private CommentService commentService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/create")
	@ResponseBody
	public JsonResult createComment(String content,Integer topicId,
			HttpSession session,HttpServletRequest rq){
		User user=(User) session.getAttribute("user");
		Comment comment=commentService.saveComment(content, topicId, user.getId());
		//System.out.println(comment.getUser());
		session.setAttribute("comment", comment);
		return new JsonResult(0,"SUCCESS");
	}
	@RequestMapping("/getModel")
	public String getModel(){
		return "comments/_show";
	}
	

}
