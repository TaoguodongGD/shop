package org.taru.lanqiao.servlet.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.CarServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


@WebServlet("/api/car/delete")
public class CarDeleteServlet extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String detailId = request.getParameter("detailId");
		String userId = request.getParameter("uid");
		
		CarServiceImpl Impl = new CarServiceImpl();

		JsonResult json = null;
		try {
			int i = Impl.deleteCar(detailId,userId);
			json = new JsonResult("200", "成功", i);
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);
	}

}