package com.fong2.web;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fong2.entity.User;
import com.fong2.service.SendMailService;
import com.fong2.service.UploadFileService;
import com.fong2.service.UserService;
import com.fong2.serviceRtException.AlreadyExistingException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.UserNotFoundException;
import com.fong2.utils.JsonResult;

@Controller
@RequestMapping("/account")
public class AccountController extends AbstExptionController{
	@Resource
	UserService userService;
	@Resource
	UploadFileService uploadFileService;
	@Resource
	SendMailService sendMailService;
	
	
	@RequestMapping("/signin")
	public String signIn(){
		return "account/signin";
	}
	
	@RequestMapping("/signup")
	public String signUp(){
		return "account/signup";
	}
	
	@RequestMapping("/signout")
	public String signOut(HttpServletRequest rq,RedirectAttributes att){
		rq.getSession().setAttribute("user", null);
        att.addFlashAttribute("msg", "已经登出");
        return "redirect:/";
	}
	
	@RequestMapping("/loginAction.do")
	@ResponseBody
	public JsonResult<User> signinAction(String username,String password,
			HttpSession session){
		User user=userService.findByEmailOrUsername(username, password);
		session.setAttribute("user", user);
		return new JsonResult<User>(user,"登录成功");
	}
	@RequestMapping("/remind.do")
	@ResponseBody
	public String remenber(HttpServletRequest rq,HttpSession session){
		Cookie[] cookies=rq.getCookies();
		String username=null;
		String password=null;
		for (Cookie ck : cookies) {
			if("username".equals(ck.getName()))
				username=ck.getValue();
			if("password".equals(ck.getName()))
				password=ck.getValue();
		}
		if(username!=null&&password!=null){
			User user=userService.findByEmailOrUsername(username, password);
			//System.out.println(user);
			session.setAttribute("user", user);
			return "0";
		}
		return "1";
	}
	
	@RequestMapping("/forget")
	public String forget(){
		return "account/forget";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/findPwd.do")
	@ResponseBody
	public JsonResult findPwd(@RequestParam("email")String email,
			@RequestParam("code")String code,
			@RequestParam("password")String password,
			HttpServletRequest rq,Model model){
		int i=userService.findPwd(email, code, password);
		if(i==1){
			return new JsonResult("密码重置成功！请使用新密码登录");
		}
		return new JsonResult(1,"密码重置失败");
	}
	
	
	@RequestMapping("/createAccount.do")
	@ResponseBody
	public JsonResult<User> createAccount(String username,String email,
			String password,String matchpwd,HttpSession session){
		User user=userService.saveUser(username, email, password, matchpwd);
		session.setAttribute("toLoginUsername", user.getUsername());
		return new JsonResult<User>(user,"注册成功");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/sendEmail")
	@ResponseBody
	public JsonResult sendEmail(@RequestParam(value="email")String email,HttpServletRequest rq){
		sendMailService.forgetPwd(email);
		return new JsonResult(0,"","发送成功");
	}
	
	/**
	 * 检验注册邮箱地址
	 * @param email
	 * @return false表示不存在 可以注册
	 * 			true表示已存在 不能注册
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public JsonResult<User> checkEmail(@RequestParam(value="email")String email){
		
		User count=userService.countByEmailOrUsername(email, null);
		return new JsonResult<User>(count,"该email可以使用");
		
	}
	
	/**
	 * 
	 * @param username
	 * @return false表示不存在 可以注册
	 * 			true表示已存在 不能注册
	 */
	@RequestMapping("/checkUsername")
	@ResponseBody
	public JsonResult<User> checkUsername
		(@RequestParam(value="username")String username){
		
		User count=userService.countByEmailOrUsername(null, username);
		return new JsonResult<User>(count,"该用户名可以使用");
	}
	
	@RequestMapping("/setting")
	public String setting(HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "account/signin";
		}
		return "account/setting";
	}
	
	@RequestMapping("/reset")
	public String reset(){
		return "account/reset";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/checkNick")
	@ResponseBody
	public JsonResult checkNick(@RequestParam(value="nick")String nick){
		userService.findByIdOrUsernameOrNick(null, null, nick);
		
		return new JsonResult("");
	}
	
	/**
     * 更新用户信息，需要登录授权
     *
     * @param newUserForm
     * @param errors
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/setting/update", method = RequestMethod.POST)
    public String updateInfomatio(User newUserForm,
                                  MultipartFile file,
                                  HttpSession session,
                                  HttpServletRequest rq,
                                  Model model) {
    	User user = (User)session.getAttribute("user");
		if(user==null){
			model.addAttribute("error", "修改个人信息操作需要登录，请检查您的登录状态。");
			return "/account/setting";
		}
		String path=session.getServletContext().getRealPath("upload");
		path=path+"/"+user.getId();
        String avatar = user.getAvatar();
        if (!file.isEmpty()) {//判断有没有上传了头像
            try {
                avatar = uploadFileService.upload(file, path);//上传了头像就更新
                String url=rq.getRequestURL()+"";
                url=url.replaceAll(rq.getServletPath(), "");
                avatar = url+"/upload/"+user.getId()+"/"+avatar;
            } catch (Exception ex) {
                model.addAttribute("error", "头像上传失败");
                return "/account/setting";
            }
        }
        newUserForm.setAvatar(avatar);
        newUserForm.setEmail(user.getEmail());
        //System.out.println(newUserForm.toString());
        try{
        	newUserForm=userService.updateUser(newUserForm);
        	//System.out.println(newUserForm);
        	session.setAttribute("user", newUserForm);
            model.addAttribute("msg", "信息已更新");
            model.addAttribute("user", newUserForm);
            return "/account/setting";
        }catch (Exception e){
        	model.addAttribute("error", e.getMessage());
        	return "/account/setting";
        }
        
    }

	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AlreadyExistingException.class)
	@ResponseBody
	public JsonResult alreadyExisting(AlreadyExistingException e){
		e.printStackTrace();
		return new JsonResult(1,e);
	}
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NullParameterException.class)
	@ResponseBody
	public JsonResult nullStr(NullParameterException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public JsonResult userNotFound(UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}
	
}
