package com.fong2.dao;


import java.util.List;

import com.fong2.entity.User;



public interface UserDao {
	/**
	 * 利用ID或username查找用户
	 * @param user 用法 setuserId或Username传入参数
	 * @return
	 */
	User findByIdOrUsernameOrNick(User user);
	
	
	User getLastCommentUserByTopicId(Integer id);
	/**
	 * @param username 用户名
	 * @param email	邮箱
	 * @return 返回值存在用户个数
	 */
	User countByEmailOrUsername(String email, String username);
	Integer saveUser(User user);
	List<User> findAllUser();
	Integer updateUser(User user);
	

}
