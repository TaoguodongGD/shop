package org.taru.lanqiao.servlet.backcategory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.CategoryServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 	后台 类别删除
 * @author 72810
 *
 */
@WebServlet("/api/backstage/category/del")
public class CategoryDelServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryId = req.getParameter("categoryId");
		
		JsonResult jsonResult = null;
		CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
		try {
			categoryServiceImpl.delete(categoryId);
			jsonResult = new JsonResult("200","删除成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","删除异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
