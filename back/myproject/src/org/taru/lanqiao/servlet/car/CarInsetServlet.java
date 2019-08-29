package org.taru.lanqiao.servlet.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.service.CarServiceImpl;
import org.taru.lanqiao.util.BigDecimalUtil;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/car/insert")
public class CarInsetServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String detailProductId = request.getParameter("detailProductId");
		String detailProductCount = request.getParameter("detailProductCount");
		String detailProductUnit = request.getParameter("detailProductUnit");
		String detailProductPrice = request.getParameter("detailProductPrice");
		String detailComment = request.getParameter("detailComment");
		String detailUserId = request.getParameter("detailUserId");

		Detail detail = new Detail();

		detail.setDetailProductId(detailProductId);
		detail.setDetailProductCount(IntegerUtil.toInt(detailProductCount));
		detail.setDetailProductUnit(detailProductUnit);
		detail.setDetailProductPrice(BigDecimalUtil.toBigDecimal(detailProductPrice));
		detail.setDetailUserId(detailUserId);
		detail.setDetailComment(detailComment);

		CarServiceImpl Impl = new CarServiceImpl();
		JsonResult json = null;
		try {
			int i = Impl.insertCar(detail);
			json = new JsonResult("200", "成功", i);
		} catch (Exception e) {
			json = new JsonResult("500", "失败", e.getMessage());
		}
		JsonWriter.writer(response, json);
	}

}
