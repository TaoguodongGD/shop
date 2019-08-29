package org.taru.lanqiao.servlet.backproduct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

/**
 * 	后台 查询商品
 * @author 72810
 *
 */
@WebServlet("/api/backstage/product/list")
public class BackProductFindAllServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		if(pageSize == "") {
			pageSize = "-1";
		}
		if(pageNum =="") {
			pageNum = "-1";
		}
		String categoryId = req.getParameter("categoryId");
		String productName = req.getParameter("productName");
		String isLack = req.getParameter("isLack");
		
		int pageNum2 = IntegerUtil.toInt(pageNum);
		int pageSize2 = IntegerUtil.toInt(pageSize);
		
		JsonResult jsonResult = null;
		ProductServiceImpl impl = new ProductServiceImpl();
		PageResult pageResult = null;
		try {
			pageResult = impl.findAll(pageNum2, pageSize2, categoryId, productName, isLack);
			if(pageResult != null) {
				jsonResult = new JsonResult("200","查询成功",pageResult);
			}else {
				jsonResult = new JsonResult("404","查询失败",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","查询异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
