package org.taru.lanqiao.servlet.category;

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


@WebServlet("/api/backstage/category/UpdateCategory")
public class UpdateCategoryServlet extends HttpServlet {

	/**
	 * 更新类别
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		  String categoryId = request.getParameter("categoryId");
		  String categoryName = request.getParameter("categoryName");
		  String categoryOrder = request.getParameter("categoryOrder");
		  String categoryStatus = request.getParameter("categoryStatus");
		  //System.out.println(categoryId+categoryName);
		CategoryServiceImpl impl = new CategoryServiceImpl();
		JsonResult result = null;
		String fResult = null;
		System.out.println(categoryId+categoryName+categoryOrder+categoryStatus);
		try {
			fResult =impl.updateCategory(categoryId,categoryName,categoryOrder,categoryStatus);
		    if( fResult !=null) {
		    	result = new JsonResult("200", "查询成功", fResult);
			}else {
				result = new JsonResult("500", "查询失败", fResult);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "查询失败", null);
		}
		JsonWriter.writer(response, result);
	}


}
