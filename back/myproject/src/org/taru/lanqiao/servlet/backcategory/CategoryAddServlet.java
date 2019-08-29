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
 * 	后台 添加类别
 * @author 72810
 *
 */
@WebServlet("/api/backstage/category/add")
public class CategoryAddServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryName = req.getParameter("categoryName");
		String categoryOrder = req.getParameter("categoryOrder");
		String categoryStatus = req.getParameter("categoryStatus");
		int categoryOrder2 = 0;
		if(categoryOrder != "") {
			categoryOrder2 = Integer.parseInt(categoryOrder);
		}else {
			categoryOrder2 = -1;
		}
		
		byte categoryStatus2 = 0;
		if(categoryStatus != "") {
			categoryStatus2 = Byte.parseByte(categoryStatus);
		}else {
			categoryStatus2 = -1;
		}
		
		CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
		JsonResult jsonResult = null;
		try {
			categoryServiceImpl.add(categoryName, categoryOrder2, categoryStatus2);
			jsonResult = new JsonResult("200","添加类别成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","添加类别异常",e.getMessage());
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
