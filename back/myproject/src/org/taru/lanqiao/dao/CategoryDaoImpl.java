package org.taru.lanqiao.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;

public class CategoryDaoImpl {
	// 查询所有类别
	public List<Category> getAll() {
		List<Category> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		String sql = "select * from category ";
		try {
			list1 = SqlUtil.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				Category category = new Category();
				HashMap<String, Object> map = list1.get(i);
				category.setCategoryId(StringUtil.toString(map.get("CATEGORY_ID")));
				category.setCategoryName(StringUtil.toString(map.get("CATEGORY_NAME")));
				category.setCategoryOrder(IntegerUtil.toInt(map.get("CATEGORY_ORDER")));
				category.setCategoryStatus(ByteUtil.toByte(map.get("CATEGORY_STATUS")));
				list.add(category);
			}
		}
		return list;
	}

	// 根据类别Id查询类别
	public Category queryById(String categoryId) {
		Category category = null;
		List<HashMap<String, Object>> list1 = null;
		String sql = "select * from category where category_id =?";
		try {
			list1 = SqlUtil.executeQuery(sql, categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				category = new Category();
				HashMap<String, Object> map = list1.get(i);
				category.setCategoryId(StringUtil.toString(map.get("CATEGORY_ID")));
				category.setCategoryName(StringUtil.toString(map.get("CATEGORY_NAME")));
				category.setCategoryOrder(IntegerUtil.toInt(map.get("CATEGORY_ORDER")));
				category.setCategoryStatus(ByteUtil.toByte(map.get("CATEGORY_STATUS")));
			}
		}
		return category;
	}

	// 根据类别名查询类别
	public Category queryByName(String categoryName) {
		Category category = null;
		List<HashMap<String, Object>> list1 = null;
		String sql = "select * from category where category_name =?";
		try {
			list1 = SqlUtil.executeQuery(sql, categoryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				category = new Category();
				HashMap<String, Object> map = list1.get(i);
				category.setCategoryId(StringUtil.toString(map.get("CATEGORY_ID")));
				category.setCategoryName(StringUtil.toString(map.get("CATEGORY_NAME")));
				category.setCategoryOrder(IntegerUtil.toInt(map.get("CATEGORY_ORDER")));
				category.setCategoryStatus(ByteUtil.toByte(map.get("CATEGORY_STATUS")));
			}
		}
		return category;
	}

	// 根据类别Id更改类别
	public void updateById(String sql) {
		int list1 = 0;
		try {
			list1 = SqlUtil.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 修改类别的状态
	 */
	public void updateStatus(String categoryId) {
		String sql = "update category set CATEGORY_STATUS = 0 where CATEGORY_ID = ? ";
		SqlUtil.executeUpdate(sql, categoryId);
		SqlUtil.close();
	}

	/*
	 * 添加类别
	 */
	public void addCategory(String categoryName, int categoryOrder, byte categoryStatus) {
		if (categoryOrder < 0) {
			categoryOrder = 1;
		}
		if (categoryStatus != 1 & categoryStatus != 0) {
			categoryStatus = 1;
		}
		String sql = "insert into category (CATEGORY_ID,CATEGORY_NAME,CATEGORY_ORDER,CATEGORY_STATUS) values (uuid(),?,?,?)";
		SqlUtil.executeUpdate(sql, categoryName, categoryOrder, categoryStatus);
		SqlUtil.close();

	}

	// 根据id查询类别
	public Category queryCategoryById(String id) {
		String sql = "select CATEGORY_ID from category where CATEGORY_ID=?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, id);
		Category categoryid = null;

		if (list.size() > 0) {
			categoryid = new Category();
			categoryid.setCategoryId(list.get(0).get("CATEGORY_ID").toString());
		}
		return categoryid;
	}
}
