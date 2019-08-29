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


//按年度统计商品销售排行
@WebServlet("/api/backstage/sale")
public class OrderSaleServlet extends HttpServlet{
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String year=request.getParameter("year");
		OrderServiceImpl Impl = new OrderServiceImpl();
		JsonResult json = null;
		try {
			List sale = Impl.priceSale(year);
			if(sale!=null) {
				json = new JsonResult("200", "查询成功", sale);
			}
			else {
				System.out.println("锟斤拷锟斤拷锟斤拷锟斤拷确锟斤拷锟�");
			}
			
		} catch (Exception e) {
			json = new JsonResult("500", "查询失败", e.getMessage());
		}
		JsonWriter.writer(response, json);

	}

}
