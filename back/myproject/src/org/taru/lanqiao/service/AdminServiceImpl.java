package org.taru.lanqiao.service;

import org.taru.lanqiao.dao.AdminDaoImpl;
import org.taru.lanqiao.model.Admin;

/**
 * 管理员业务类
 * 负责查询用户 废弃用户 上架用户 
 * @author liuyue
 *
 */
public class AdminServiceImpl {
	
	/**
	 * 根据用户名查询管理员
	 */
	public Admin queryByUsername (String username) {
		AdminDaoImpl  dao = new AdminDaoImpl ();
		return dao.queryByUsername(username);
	}

}
