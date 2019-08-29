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

@WebServlet("/api/backstage/detail/queryId")
public class OrderQueryDetailServlet extends HttpServlet{
	
	/**
	 * 	根据订单ID查询订单购买详情
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		
		OrderServiceImpl Impl=new OrderServiceImpl();
		JsonResult json=null;
		try {
			List detail = Impl.queryDetailId(orderid);
			json=new JsonResult("200","成功",detail);
		} catch (Exception e) {
			json=new JsonResult("500","失败",e.getMessage());
		}
		JsonWriter.writer(response, json);
		
	}

}