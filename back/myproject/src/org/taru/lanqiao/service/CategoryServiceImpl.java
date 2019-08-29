package org.taru.lanqiao.service;

import java.util.List;

import org.taru.lanqiao.dao.CategoryDaoImpl;
import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.util.IntegerUtil;

/**
 * 类别业务类 负责查询类别 删除类别 添加类别
 * 
 * @author liuyue
 *
 */
public class CategoryServiceImpl {

	/**
	 * 查询所有类别
	 */
	public List<Category> queryAll() {
		CategoryDaoImpl dao = new CategoryDaoImpl();
		List<Category> list = dao.getAll();
		return list;
	}

	/**
	 * 修改类别
	 */
	public String updateCategory(String categoryId, String categoryName, String categoryOrder, String categoryStatus) {
		String fResult = null;
		CategoryDaoImpl dao = new CategoryDaoImpl();
		StringBuffer sql = new StringBuffer();
		sql.append("update  category  set  ");
		sql.append("category_Name  =" + "\"" + categoryName + "\"");
		if (categoryOrder != null) {
			sql.append("," + "category_Order  =" + IntegerUtil.toInt(categoryOrder));
		}
		if (categoryStatus != null) {
			sql.append("," + "category_Status  =" + IntegerUtil.toInt(categoryStatus));
		}
		sql.append("  " + "where   category_Id = " + "\"" + categoryId + "\"");
		String sqll = sql.toString();
		try {
			dao.updateById(sqll);
			fResult = "成功";
		} catch (Exception e) {
			fResult = "失败";
		}

		return fResult;
	}
	
	/*
	 * 	删除类别
	 */
	public void delete(String categoryId) {
		CategoryDaoImpl dao = new CategoryDaoImpl();
		dao.updateStatus(categoryId);
	}
	
	/*
	 * 	添加类别
	 */
	public void add(String categoryName ,int categoryOrder,byte categoryStatus) {
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		categoryDaoImpl.addCategory(categoryName, categoryOrder, categoryStatus);
	}
	
	public Category queryCategoryId(String categoryid) {
		CategoryDaoImpl Impl = new CategoryDaoImpl();
		return Impl.queryCategoryById(categoryid);
	}
}
