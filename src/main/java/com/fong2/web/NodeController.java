package com.fong2.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fong2.entity.Comment;
import com.fong2.entity.Node;
import com.fong2.entity.Topic;
import com.fong2.service.NodeService;
import com.fong2.service.TopicService;
import com.fong2.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/nodes")
public class NodeController {
	@Resource
	NodeService nodeService;
	@Resource
	TopicService topicService; 
	
	@RequestMapping("/list/{sectionId}")
	@ResponseBody
	public JsonResult<List<Node>> nodeList(@PathVariable("sectionId")int sectionId){
		List<Node> nodes=nodeService.findBySectionId(sectionId);
		return new JsonResult<List<Node>>(nodes);
	}
	
	@RequestMapping(value={"/",""})
	public String nodes(HttpServletRequest rq){
		List<Node> nodes=nodeService.findAllNodes();
		rq.setAttribute("nodes", nodes);
		return "nodes/index";
	}
	@RequestMapping("/{node}")
	public String node(Model model,@PathVariable(value="node")int nodeId,
			@RequestParam(value = "p", defaultValue = "1") int p){
		Node node=nodeService.findByTopicNodeId(nodeId);
		PageHelper.startPage(p, 10);
		List<Topic> topics=topicService.findByStatusAndNodeId(0, nodeId);
		PageInfo<Topic> topicsPage=new PageInfo<Topic>(topics);
		model.addAttribute("node", node);
		model.addAttribute("topics", topics);
		model.addAttribute("page", topicsPage);
		return "nodes/list";
	}
}
