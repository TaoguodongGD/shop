package org.taru.lanqiao.servlet.car;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.CarServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


@WebServlet("/api/car/querylist")
public class CarQueryListServlet extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		CarServiceImpl Impl = new CarServiceImpl();
		JsonResult json = null;
		try {
			List<Product> list = Impl.queryCarList(uid);
			if(list!=null) {
				json = new JsonResult("200", "成功", list);
			}else {
				json = new JsonResult("200", "成功", null);
			}
			
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);
	}

}

		

