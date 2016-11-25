package com.fong2.service;

import java.util.List;

import com.fong2.entity.User;
import com.fong2.serviceRtException.AlreadyExistingException;
import com.fong2.serviceRtException.NullParameterException;
import com.fong2.serviceRtException.PasswordException;
import com.fong2.serviceRtException.UserNotFoundException;

public interface UserService {
	/**
	 * 检测用户是否存在
	 * @param email 邮箱 
	 * @param username 用户名
	 * @category 建议每次只能传入一个值分别检测是否存在用户
	 * @return 存在则抛出异常 否则返回null
	 */
	public User countByEmailOrUsername(String email,String username)
		throws NullParameterException,AlreadyExistingException;
	/**
	 * @param username 用户名
	 * @param email 邮箱
	 * @param password  密码
	 * @param matchpwd  确认密码
	 * @return 注册成功则返回该User 否则抛出异常
	 */
	public User saveUser(String username,String email,String password,String matchpwd)
			throws NullParameterException,AlreadyExistingException,UserNotFoundException;
	/**
	 * 使用邮箱/用户名查找用户
	 * @param email 邮箱 
	 * @param username 用户名
	 * @category 建议每次只能传入一个值分别检测是否存在用户
	 * @return 存在则返回该User 否则抛出异常
	 */
	public User findByEmailOrUsername(String nameOrEmail,String password)
			throws NullParameterException,UserNotFoundException,PasswordException;
	
	public List<User> findAllUserOrderByNick();
	public User findByIdOrUsernameOrNick(Integer id,String username,String nick)
			throws NullParameterException,UserNotFoundException;
	public User updateUser(User user) 
			throws UserNotFoundException,NullParameterException;
	public int findPwd(String email,String code,String password)
			throws NullParameterException,UserNotFoundException,PasswordException;
}
