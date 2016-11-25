package com.fong2.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fong2.dao.EmailCodeDao;
import com.fong2.dao.UserDao;
import com.fong2.entity.EmailCode;
import com.fong2.entity.User;
import com.fong2.service.UserService;
import com.fong2.serviceRtException.AlreadyExistingException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.PasswordException;
import com.fong2.serviceRtException.UserNotFoundException;
import com.fong2.utils.Md5Utils;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	UserDao userDao;
	@Resource
	EmailCodeDao emailCodeDao;
	
	/**
	 * 检测用户是否存在
	 * @param email username
	 * 建议每次只能传入一个值分别检测是否存在用户
	 * @return 存在则返回该User 否则返回null
	 */
	public User countByEmailOrUsername(String email, String username) 
	throws NullParameterException,AlreadyExistingException{
		if(email==null||email.trim().isEmpty())
			email="";
		if(username==null||username.trim().isEmpty())
			username="";
		if("".equals(username)&&"".equals(email)){
			//throw new RuntimeException("email与username不能同时为空");
			throw new NullParameterException("参数错误");
		}
		User user=userDao.countByEmailOrUsername(email, username);
		if(user!=null)
			throw new AlreadyExistingException("该信息已被占用");
		return user;
	}
	/**
	 * @param
	 * 	username 用户名
	 * 	email 邮箱
	 * 	password  密码
	 * 	matchpwd  确认密码
	 * @return 注册成功则返回该User 否则抛出异常
	 */
	public User saveUser(String username, String email, String password, String matchpwd) 
		throws NullParameterException,AlreadyExistingException,UserNotFoundException{
		if(email==null||email.trim().isEmpty())
			throw new NullParameterException("email地址不能为空");
		User countEmail=userDao.countByEmailOrUsername(email, null);
		if(countEmail!=null)
			throw new AlreadyExistingException("该email已被注册");
		if(username==null||username.trim().isEmpty())
			throw new NullParameterException("username不能为空");
		User countUsername=userDao.countByEmailOrUsername(null, username);
		if(countUsername!=null)
			throw new AlreadyExistingException("该用户名已被注册");
		String reg="^\\s*[^\\s\\u4e00-\u9fa5]{6,16}\\s*$";
		if(password==null||!password.equals(matchpwd)||!password.matches(reg)){
			throw new NullParameterException("password异常：不能为空/两次不一致/密码长度有误");
		}
		password=Md5Utils.crypt(password);
		User user =new User();
		user.setCreateAt(new Date());
		user.setEmail(email);
		user.setNick(username);
		user.setPassword(password);
		user.setPoints(0);
		user.setUsername(username);
		int i=userDao.saveUser(user);
		if(i!=1){
			throw new UserNotFoundException("注册失败");
		}
		return user;
	}
	public User findByEmailOrUsername(String nameOrEmail,String password) 
			throws NullParameterException, UserNotFoundException, PasswordException {
		if(nameOrEmail==null||nameOrEmail.trim().isEmpty()){
			throw new NullParameterException("参数错误:用户名/邮箱必须正确输入");
		}
		if(password==null||password.length()<6)
			throw new NullParameterException("密码长度有误");
		User user=null;
		if(userDao.countByEmailOrUsername(nameOrEmail, null)!=null){
			user=userDao.countByEmailOrUsername(nameOrEmail, null);
		}else if(userDao.countByEmailOrUsername(null,nameOrEmail)!=null){
			user=userDao.countByEmailOrUsername(null,nameOrEmail);
		}
		if(user==null)
			throw new UserNotFoundException("用户名/邮箱不存在");
		password=Md5Utils.crypt(password);
		if(!user.getPassword().equals(password))
			throw new PasswordException("您输入的密码有误");
		return user;
	}
	public List<User> findAllUserOrderByNick() {
		return userDao.findAllUser();
	}
	public User findByIdOrUsernameOrNick(Integer id,String username,String nick) throws NullParameterException, UserNotFoundException {
		if(id==null&&username==null&&nick==null)
			throw new NullParameterException("查询参数不能为空");
		User user=new User();
		user.setId(id);
		user.setUsername(username);
		user.setNick(nick);
		User user1=userDao.findByIdOrUsernameOrNick(user);
		if(user1==null)
			throw new UserNotFoundException("没找到该用户信息");
		return user1;
	}
	public User updateUser(User user) throws UserNotFoundException,
			NullParameterException {
		if(user==null)
			throw new NullParameterException("传入参数User不能为空");
		Integer rs=userDao.updateUser(user);
		if(rs!=1){
			throw new UserNotFoundException("用户不存在/用户值参数有误");
		}
		
		User newUser=new User();
		newUser.setId(user.getId());
		//System.out.println(newUser.getId());
		newUser=userDao.findByIdOrUsernameOrNick(newUser);
		//System.out.println(newUser);
		return newUser;
	}
	public int findPwd(String email,String code, String password) throws NullParameterException,
			UserNotFoundException, PasswordException {
		if(email==null||email.trim().isEmpty()||code==null||code.trim().isEmpty()){
			throw new NullParameterException("参数错误:必须正确输入");
		}
		if(userDao.countByEmailOrUsername(email, null)==null){
			throw new UserNotFoundException("用户不存在");
		}
		EmailCode ec=emailCodeDao.findByEmail(email);
		if(ec==null){
			throw new UserNotFoundException("参数异常，请重新发起取回密码操作");
		}
		if(!code.equals(ec.getCode())){
			throw new UserNotFoundException("参数异常，请重新发起取回密码操作");
		}
		long t1=ec.getTime().getTime();
		long t2=System.currentTimeMillis();
		if((t2-t1)>1800000){
			throw new UserNotFoundException("操作超时，请重新发起取回密码操作");
		}
		if(password==null||password.length()<6)
			throw new NullParameterException("密码长度有误");
		User user=new User();
		user.setEmail(email);
		password=Md5Utils.crypt(password);
		user.setPassword(password);
		int i=userDao.updateUser(user);
		return i;
	}
	

}
