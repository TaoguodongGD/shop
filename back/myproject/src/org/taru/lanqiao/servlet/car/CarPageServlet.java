package org.taru.lanqiao.servlet.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.CarServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;


@WebServlet("/api/car/page")
public class CarPageServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid=request.getParameter("uid");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		CarServiceImpl Impl = new CarServiceImpl();
		JsonResult json = null;
		try {
			PageResult queryList = Impl.pageCar(uid, pageNum, pageSize);
			json = new JsonResult("200", "成功", queryList);
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);

	}

}
