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


@WebServlet("/api/Category/CategoryList")
public class CategoryListServlet extends HttpServlet {

	/**
	 * 查询类别列表
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		CategoryServiceImpl impl = new CategoryServiceImpl();
		JsonResult result = null;
		List<Category> list = null;
		try {
		    list =impl.queryAll();
		    if( list !=null) {
		    	result = new JsonResult("200", "查询成功", list);
			}else {
				result = new JsonResult("500", "查询失败", null);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "查询失败", null);
		}
		JsonWriter.writer(response, result);
	}


}
