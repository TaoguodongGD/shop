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

@WebServlet("/api/backstage/product/photo")
public class BackProductUpdatePhotoServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		String photo = req.getParameter("photo");
		
		JsonResult jsonResult = null;
		ProductServiceImpl impl = new ProductServiceImpl();
		boolean bool = false;
		try {
			bool = impl.updatePhoto(productId, photo);
			if(bool) {
				jsonResult = new JsonResult("200","修改图片成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","修改图片异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
