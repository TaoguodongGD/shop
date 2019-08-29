package org.taru.lanqiao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.taru.lanqiao.dao.UserDaoImpl;
import org.taru.lanqiao.model.User;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.vo.PageResult;

/**
 * User的具体业务
 * @author 72810
 *
 */
public class UserServiceImpl {
	
	/*
	 * 	前台用户登陆
	 */
	public User login(String username , String password) {
		UserDaoImpl daoImpl = new UserDaoImpl();
		return daoImpl.queryByUAndP(username, password);
	}
	
	/*
	 *	根据Id查询用户信息
	 */
	public User findById(String userId) {
		UserDaoImpl daoImpl = new UserDaoImpl();
		return daoImpl.queryById(userId);
	}
	
	/*
	 * 	添加用户
	 */
	public boolean register(User user) {
		boolean bool = false;
		UserDaoImpl daoImpl = new UserDaoImpl();
		if(daoImpl.addUser(user) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 	修改用户信息
	 */
	public boolean update(User user) {
		boolean bool = false;
		UserDaoImpl daoImpl = new UserDaoImpl();
		if(daoImpl.updateUser(user) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 	删除用户
	 */
	public boolean delete(String uid) {
		boolean bool = false;
		UserDaoImpl daoImpl = new UserDaoImpl();
		if(daoImpl.updateStatus(uid) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 查询用户
	 */
	public PageResult query(int pageNum,int pageSize,String telphone , String shopName) {
		UserDaoImpl daoImpl = new UserDaoImpl();
		return daoImpl.query(pageNum, pageSize, telphone, shopName);
	}

}
