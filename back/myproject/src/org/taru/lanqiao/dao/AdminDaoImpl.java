package org.taru.lanqiao.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;

import com.google.gson.Gson;

public class AdminDaoImpl {
	//查询所有管理员
	public List<Admin> getAll() {
		List<Admin> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		String sql ="select * from admin ";
		try {
			list1 = SqlUtil.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				Admin admin = new Admin();
				HashMap<String, Object> map = list1.get(i);
				admin.setId(map.get("id") == null ? 0 : IntegerUtil.toInt(map.get("id")));
				admin.setUsername(map.get("username") == null ? null : map.get("username").toString());
				admin.setPassword(map.get("password") == null ? null : map.get("password").toString());
				admin.setTelphone(map.get("telphone") == null ? null : map.get("telphone").toString()); 
				admin.setEmail(map.get("email") == null ? null : map.get("email").toString());
				admin.setRealname(map.get("realname") == null ? null : map.get("realname").toString());
				list.add(admin);
			}
		}
		return list;
	}
	
	public Admin  queryByUsername(String username) {
		Admin admin =null;
		List<HashMap<String, Object>> list1 = null;
		String sql ="select * from admin where username =?";
		try {
			list1 = SqlUtil.executeQuery(sql,username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				admin = new Admin();
				HashMap<String, Object> map = list1.get(i);
				admin.setId(map.get("id") == null ? 0 : IntegerUtil.toInt(map.get("id")));
				admin.setUsername(StringUtil.toString(map.get ("username")));
				admin.setPassword(StringUtil.toString(map.get ("password")));
				admin.setTelphone( StringUtil.toString(map.get ("telphone"))); 
				admin.setEmail(  StringUtil.toString(map.get ("email")));
				admin.setRealname(  StringUtil.toString(map.get ("realname")));
			}
		}
		return admin;
	}
	
//	public static void main(String[] args) {
//		AdminDaoImpl m = new AdminDaoImpl();
//		List<Admin> a =m.getAll();
//		Gson gson = new Gson();
//		String json = gson.toJson(a);
//		System.out.println(json);
//		
//		
//	}
}
