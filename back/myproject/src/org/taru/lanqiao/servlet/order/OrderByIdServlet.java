package org.taru.lanqiao.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.service.OrderServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


@WebServlet("/api/order/selectHistory")
public class OrderByIdServlet extends HttpServlet {

	/**
	 * 查询根据用户Id查询商品订单详情
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		String userId = request.getParameter("userId");
		OrderServiceImpl impl = new OrderServiceImpl();
		JsonResult result = null;
		List<Order> order = null;
		try {
			order= impl.queryHistoryl(userId);
		
			if( order !=null) {
				result = new JsonResult("200", "查询成功，历史订单", order);
			}else {
				result = new JsonResult("500", "查询失败", null);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "查询失败", null);
		}
		JsonWriter.writer(response, result);
	}

}
