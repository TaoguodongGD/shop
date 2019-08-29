package org.taru.lanqiao.servlet.backcategory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.service.CategoryServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 	后台 查询类别列表
 * @author 72810
 *
 */
@WebServlet("/api/backstage/category/list")
public class CategoryQueryServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
		JsonResult jsonResult = null;
		List<Category> list = null;
		try {
			list = categoryServiceImpl.queryAll();
			if(list.size() > 0) {
				jsonResult = new JsonResult("200","查询成功",list);
			}else {
				jsonResult = new JsonResult("404","查询失败",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","查询异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
