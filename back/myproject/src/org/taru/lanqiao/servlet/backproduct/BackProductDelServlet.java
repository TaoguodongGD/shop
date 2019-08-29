package org.taru.lanqiao.servlet.backproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 后台商品删除  更改的是状态
 * @author 72810
 *
 */
@WebServlet("/api/backstage/product/del")
public class BackProductDelServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		
		JsonResult jsonResult = null;
		boolean bool = false;
		ProductServiceImpl impl = new ProductServiceImpl();
		try {
			bool = impl.delProduct(productId);
			if(bool) {
				jsonResult = new JsonResult("200","商品删除成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","商品删除异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
