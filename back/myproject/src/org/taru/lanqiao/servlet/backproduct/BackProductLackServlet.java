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

@WebServlet("/api/backstage/lack")
public class BackProductLackServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("id");
		
		JsonResult jsonResult = null;
		ProductServiceImpl impl = new ProductServiceImpl();
		boolean bool = false;
		try {
			bool = impl.lack(productId);
			if(bool) {
				jsonResult = new JsonResult("200","设置缺货成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","设置缺货异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
