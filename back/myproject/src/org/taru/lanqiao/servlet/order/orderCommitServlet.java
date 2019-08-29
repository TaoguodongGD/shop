package org.taru.lanqiao.servlet.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.service.CategoryServiceImpl;
import org.taru.lanqiao.service.OrderServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


@WebServlet("/api/order/commit")
public class orderCommitServlet extends HttpServlet {

	/**
	 * 提交订单
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		  String orderUserId = request.getParameter("orderUserId");
		  String orderTotalPrice = request.getParameter("orderTotalPrice");
		  String orderComment = request.getParameter("orderComment");
		 
		OrderServiceImpl impl = new OrderServiceImpl();
		JsonResult result = null;
		String fResult = null;
		try {
			fResult =impl.orderCommit(orderUserId, orderTotalPrice, orderComment);
		    if( fResult !=null) {
		    	result = new JsonResult("200", "更改成功", fResult);
			}else {
				result = new JsonResult("500", "更改失败", fResult);
			}
			
		} catch (Exception e) {
			result = new JsonResult("500", "更改失败", null);
		}
		JsonWriter.writer(response, result);
	}


}
