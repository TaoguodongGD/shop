package org.taru.lanqiao.servlet.backcategory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.service.CategoryServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


@WebServlet("/api/backstage/category/querybyid")
public class CategoryByIdServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  categoryId= request.getParameter("categoryId");
		CategoryServiceImpl Impl = new CategoryServiceImpl();
		JsonResult json = null;
		try {
			Category  category= Impl.queryCategoryId(categoryId);
			json = new JsonResult("200", "成功", category);
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);
	}

}
