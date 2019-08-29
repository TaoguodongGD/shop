package org.taru.lanqiao.servlet.backorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.OrderServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 * Servlet implementation class ServletDownload
 */
@WebServlet("/api/backstage/order/Download")
public class OrderDownloadServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderid = request.getParameter("orderid");

		OrderServiceImpl Impl = new OrderServiceImpl();

		JsonResult json = null;
		try {
			String i = Impl.downloadByIdtxt(orderid);
			response.setHeader("Content-Disposition", "attachment; filename=" + orderid+".txt");
			response.setContentType("multipart/form-data; charset=UTF-8");
			json = new JsonResult("200","成功", i);
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);
	}
}