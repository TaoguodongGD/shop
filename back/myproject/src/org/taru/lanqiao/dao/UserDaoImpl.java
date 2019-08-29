package org.taru.lanqiao.dao;
/**
 * 	关于用户的SQL语句
 * @author 72810
 *
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

import com.google.gson.stream.JsonWriter;

public class UserDaoImpl {
	
	/*
	 * 	根据用户名密码查询用户
	 */
	public User queryByUAndP(String username , String password) {
		String sql = "select * from user where USER_USER_NAME = ? and USER_PASSWORD = ?";
		List<HashMap<String, Object>> list = null;
		try {
			list = SqlUtil.executeQuery(sql,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = null;
		if(list.size() > 0) {
			user = new User();
			user.setUserId(StringUtil.toString(list.get(0).get("USER_ID")));
			user.setUserUserName(StringUtil.toString(list.get(0).get("USER_USER_NAME")));
			user.setUserPassword(StringUtil.toString(list.get(0).get("USER_PASSWORD")));
			user.setUserTelphone(StringUtil.toString(list.get(0).get("USER_TELPHONE")));
			user.setUserAddress(StringUtil.toString(list.get(0).get("USER_ADDRESS")));
			user.setUserShopName(StringUtil.toString(list.get(0).get("USER_SHOP_NAME")));
			user.setUserComment(StringUtil.toString(list.get(0).get("USER_COMMENT")));
			user.setUserStatus(ByteUtil.toByte(list.get(0).get("USER_STATUS")));
		}
		SqlUtil.close();
		return user;
	}
	
	/*
	 * 	根据Id查询用户
	 */
	public User queryById(String userId) {
		String sql = "select * from user where USER_ID = ?";
		List<HashMap<String, Object>> list = null;
		try {
			list = SqlUtil.executeQuery(sql,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = null;
		if(list.size() > 0) {
			user = new User();
			user.setUserId(StringUtil.toString(list.get(0).get("USER_ID")));
			user.setUserUserName(StringUtil.toString(list.get(0).get("USER_USER_NAME")));
			user.setUserPassword(StringUtil.toString(list.get(0).get("USER_PASSWORD")));
			user.setUserTelphone(StringUtil.toString(list.get(0).get("USER_TELPHONE")));
			user.setUserAddress(StringUtil.toString(list.get(0).get("USER_ADDRESS")));
			user.setUserShopName(StringUtil.toString(list.get(0).get("USER_SHOP_NAME")));
			user.setUserComment(StringUtil.toString(list.get(0).get("USER_COMMENT")));
			user.setUserStatus(ByteUtil.toByte(list.get(0).get("USER_STATUS")));
		}
		SqlUtil.close();
		return user;
	}
	
	/*
	 * 	添加用户
	 */
	public int addUser(User user) {
		String sql = "insert into user (USER_ID,USER_USER_NAME,USER_PASSWORD,USER_TELPHONE,USER_ADDRESS,USER_SHOP_NAME,USER_COMMENT,USER_STATUS) values "
				+ "(uuid(),?,?,?,?,?,?,1)";
		int res = 0;
		try {
			res = SqlUtil.executeUpdate(sql, user.getUserUserName(),user.getUserPassword(),user.getUserTelphone(),user.getUserAddress(),user.getUserShopName(),user.getUserComment());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	修改用户
	 */
	public int updateUser(User user) {
		String sql = "update user set USER_USER_NAME = ? ,USER_PASSWORD = ? , USER_TELPHONE = ? ,USER_ADDRESS = ?,USER_SHOP_NAME = ? ,USER_COMMENT = ?"
				+ "where USER_ID = ?";
		int res = SqlUtil.executeUpdate(sql, user.getUserUserName(),user.getUserPassword(),user.getUserTelphone(),user.getUserAddress(),user.getUserShopName(),user.getUserComment(),user.getUserId());
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	修改用户的状态
	 */
	public int updateStatus(String uid) {
		String sql = "update user set USER_STATUS = 0 where USER_ID = ? ";
		int res = SqlUtil.executeUpdate(sql, uid);
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 查询用户
	 */
	public PageResult query(int pageNum,int pageSize,String telphone,String shopName) {
		StringBuffer sb = new StringBuffer("select * from user where 1=1");
		if(pageNum <= 0) {
			pageNum = 1;
		}
		if(pageSize <= 0) {
			pageSize = 8;
		}
		
		PageResult pageResult = null;
		List<HashMap<String, Object>> list = null;
		if(telphone != "") {
			sb.append(" and USER_TELPHONE = ? limit ?,?");
			String sql = sb.toString();
			list = SqlUtil.executeQuery(sql, telphone,(pageNum-1)*pageSize ,pageSize);
			List<User> users = new ArrayList<User>();
			if(list.size() > 0) {
				pageResult = new PageResult();
				for(HashMap<String, Object> map : list) {
					User user = new User();
					user.setUserId(StringUtil.toString(map.get("USER_ID")));
					user.setUserUserName(StringUtil.toString(map.get("USER_USER_NAME")));
					user.setUserPassword(StringUtil.toString(map.get("USER_PASSWORD")));
					user.setUserTelphone(StringUtil.toString(map.get("USER_TELPHONE")));
					user.setUserAddress(StringUtil.toString(map.get("USER_ADDRESS")));
					user.setUserShopName(StringUtil.toString(map.get("USER_SHOP_NAME")));
					user.setUserComment(StringUtil.toString(map.get("USER_COMMENT")));
					user.setUserStatus(ByteUtil.toByte(map.get("USER_STATUS")));
					users.add(user);
				}
				pageResult.setPageNum(pageNum);
				pageResult.setPageSize(pageSize);
				pageResult.setList(users);
				
				String sql2 = "select count(*) as row from user where USER_TELPHONE = ?";
				List<HashMap<String, Object>> list2 = SqlUtil.executeQuery(sql2, telphone);
				String row = StringUtil.toString(list2.get(0).get("row"));
				int count = IntegerUtil.toInt(row);
				pageResult.setTotal(count);
				pageResult.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
			}
			return pageResult;
		}
		
		if(shopName != "") {
			sb.append(" and USER_SHOP_NAME like ? limit ?,?");
			String sql = sb.toString();
			list = SqlUtil.executeQuery(sql, "%"+shopName+"%",(pageNum-1)*pageSize ,pageSize);
			List<User> users = new ArrayList<User>();
			if(list.size() > 0) {
				pageResult = new PageResult();
				for(HashMap<String, Object> map : list) {
					User user = new User();
					user.setUserId(StringUtil.toString(map.get("USER_ID")));
					user.setUserUserName(StringUtil.toString(map.get("USER_USER_NAME")));
					user.setUserPassword(StringUtil.toString(map.get("USER_PASSWORD")));
					user.setUserTelphone(StringUtil.toString(map.get("USER_TELPHONE")));
					user.setUserAddress(StringUtil.toString(map.get("USER_ADDRESS")));
					user.setUserShopName(StringUtil.toString(map.get("USER_SHOP_NAME")));
					user.setUserComment(StringUtil.toString(map.get("USER_COMMENT")));
					user.setUserStatus(ByteUtil.toByte(map.get("USER_STATUS")));
					users.add(user);
				}
				pageResult.setPageNum(pageNum);
				pageResult.setPageSize(pageSize);
				pageResult.setList(users);
				
				String sql2 = "select count(*) as row from user where USER_SHOP_NAME like ? ";
				List<HashMap<String, Object>> list2 = SqlUtil.executeQuery(sql2, "%"+shopName+"%");
				String row = StringUtil.toString(list2.get(0).get("row"));
				int count = IntegerUtil.toInt(row);
				pageResult.setTotal(count);
				pageResult.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
			}
			return pageResult;
		}
		
		if(telphone == "" && shopName == "") {
			sb.append(" limit ?,?");
			String sql = sb.toString();
			list = SqlUtil.executeQuery(sql,(pageNum-1)*pageSize ,pageSize);
			List<User> users = new ArrayList<User>();
			if(list.size() > 0) {
				pageResult = new PageResult();
				for(HashMap<String, Object> map : list) {
					User user = new User();
					user.setUserId(StringUtil.toString(map.get("USER_ID")));
					user.setUserUserName(StringUtil.toString(map.get("USER_USER_NAME")));
					user.setUserPassword(StringUtil.toString(map.get("USER_PASSWORD")));
					user.setUserTelphone(StringUtil.toString(map.get("USER_TELPHONE")));
					user.setUserAddress(StringUtil.toString(map.get("USER_ADDRESS")));
					user.setUserShopName(StringUtil.toString(map.get("USER_SHOP_NAME")));
					user.setUserComment(StringUtil.toString(map.get("USER_COMMENT")));
					user.setUserStatus(ByteUtil.toByte(map.get("USER_STATUS")));
					users.add(user);
				}
				pageResult.setPageNum(pageNum);
				pageResult.setPageSize(pageSize);
				pageResult.setList(users);
				
				String sql2 = "select count(*) as row from user";
				List<HashMap<String, Object>> list2 = SqlUtil.executeQuery(sql2);
				String row = StringUtil.toString(list2.get(0).get("row"));
				int count = IntegerUtil.toInt(row);
				pageResult.setTotal(count);
				pageResult.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
			}
			
		}
		return pageResult;
	}
	
}
