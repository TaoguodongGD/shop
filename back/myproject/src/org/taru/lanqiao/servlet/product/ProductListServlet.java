package org.taru.lanqiao.servlet.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.dao.ProductDaoImpl;
import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.service.AdminServiceImpl;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

@WebServlet("/api/product/productList")
public class ProductListServlet extends HttpServlet {

	/**
	 * 查询商品列表（带分页）
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		ProductServiceImpl impl = new ProductServiceImpl();
		JsonResult result = null;
		PageResult pageResult = null;
		try {
			pageResult = impl.queryAll(request.getParameter("pageNum"), request.getParameter("pageSize"));
			if( pageResult !=null) {
				result = new JsonResult("200", "查询成功", pageResult);
			}else {
				result = new JsonResult("404", "查询失败", null);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "查询异常", null);
		}
		JsonWriter.writer(response, result);
	}

}
