package org.taru.lanqiao.servlet.backproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 *	后台 根据Id查询商品的详细信息
 * @author 72810
 *
 */
@WebServlet("/api/backstage/product/findById")
public class BackProductFindById extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		ProductServiceImpl impl = new ProductServiceImpl();
		JsonResult result = null;
		Product product = null;
		try {
			product= impl.findByIdTaste(productId);
			System.out.println(product);
			if( product != null) {
				result = new JsonResult("200", "查询成功", product);
			}else {
				result = new JsonResult("404", "查询失败", null);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "查询异常", null);
		}
		JsonWriter.writer(resp, result);
	}
}
