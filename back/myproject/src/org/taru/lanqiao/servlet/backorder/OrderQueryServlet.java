package org.taru.lanqiao.servlet.backorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.service.OrderServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


@WebServlet("/api/backstage/queryById")
public class OrderQueryServlet extends HttpServlet{
	
	/**
	 * 根据订单ID查询单个订单信息
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		
		OrderServiceImpl Impl=new OrderServiceImpl();
		JsonResult json=null;
		try {
			Order orderby = Impl.queryOrderById(orderid);
			json=new JsonResult("200","�ɹ�",orderby);
		} catch (Exception e) {
			json=new JsonResult("500","ʧ��",e.getMessage());
		}
		JsonWriter.writer(response, json);
		
	}

}
