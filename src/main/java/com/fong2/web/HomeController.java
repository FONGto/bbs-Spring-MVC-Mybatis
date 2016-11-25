package com.fong2.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fong2.entity.Doc;
import com.fong2.entity.Node;
import com.fong2.entity.Topic;
import com.fong2.entity.User;
import com.fong2.service.DocService;
import com.fong2.service.NodeService;
import com.fong2.service.SectionService;
import com.fong2.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Controller
public class HomeController {
	@Resource(name="topicService")
	private TopicService topicService=null;
	@Resource(name="sectionService")
	private SectionService sectionService=null;
	@Resource
	private NodeService nodeService=null;
	@Resource
	private DocService docServoce;
	/**
	 * 
	 * @param p 页面数
	 * @param model 返回值
	 * @return
	 */
	@RequestMapping(value={"index.html","","toIndex","toIndex.do"})
	public String index(@RequestParam(defaultValue = "1", value = "p") int p,
            Model model){
		PageHelper.startPage(p, 10);
		List<Topic> topics=topicService.getAllFilteByStatusOrderByDefault("0");
		PageInfo<Topic> topicPage=new PageInfo<Topic>(topics);
		List<Node> nodes=nodeService.findAllOrderByTopicCountDesc(10);
		model.addAttribute("topics", topics);
        model.addAttribute("page", topicPage);
        model.addAttribute("sectionName", "all");
        model.addAttribute("sections", sectionService.findAll());
        model.addAttribute("hotNodes", nodes);
		return "home/index";
	}
	

    /**
     * 显示首页列表
     *
     * @param p 页面数
     * @param section 分类
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "!{section}")
    public String order(@RequestParam(defaultValue = "1", value = "p") int p,
                        @PathVariable(value = "section") String section,
                        HttpServletRequest request,
                        Model model) {
    	//System.out.println(request.getRequestURL());
    	int userId = 0;//默认没有
        if (request.getSession().getAttribute("user") != null) {
            userId = ((User) request.getSession().getAttribute("user")).getId();
        }
        //p分页 10每页10条
        
        List<Node> nodes=nodeService.findAllOrderByTopicCountDesc(10);
        List<Topic> topics;
        PageInfo<Topic> topicPage=null;
        if (section.equals("hot")) {
        	PageHelper.startPage(p, 10);
        	topics = topicService.getAllOrderByHot("0");
        	topicPage=new PageInfo<Topic>(topics);
        }
        /*else if (section.equals("focused")) {
            //topicPage = topicServ.getByFocusedOrderByDefault(userId, p);
            topics= topicService.getAllOrderByHot("0");
        	topicPage=new PageInfo<Topic>(topics);
        }*/ 
        else {
        	PageHelper.startPage(p, 10);
        	topics= topicService.getBySectionDefault("0",section);
        	topicPage=new PageInfo<Topic>(topics);
        }
        model.addAttribute("topics", topics);
        model.addAttribute("page", topicPage);
        model.addAttribute("sectionName", section);
        model.addAttribute("sections", sectionService.findAll());
        model.addAttribute("hotNodes", nodes);
        return "/home/index";
    }
    
    @RequestMapping("/{doc}")
    public String about(@PathVariable(value="doc")String url,
    		Model model){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Doc doc=docServoce.findDocByUrl(url);
    	if(doc!=null){
    		String creaTime=sdf.format(doc.getCreateAt());
    		model.addAttribute("page", doc);
			model.addAttribute("creaTime", creaTime);
			return "page/index";
    	}
    	return "error/404";
    }

}
