package org.taru.lanqiao.servlet.backorder;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.OrderServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/backstage/order/price")
public class OrderPriceServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String year = request.getParameter("year");
		OrderServiceImpl Impl = new OrderServiceImpl();
		JsonResult json = null;
		try {
			List priceYear = Impl.priceYear(year);
			if (priceYear != null) {
				json = new JsonResult("200", "成功", priceYear);
			} else {
				json = new JsonResult("200", "失败", null);
			}

		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);

	}

}