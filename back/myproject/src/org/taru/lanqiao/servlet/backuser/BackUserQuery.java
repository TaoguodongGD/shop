package org.taru.lanqiao.servlet.backuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.UserServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

/**
 * 	用户查询 多条件
 * @author 72810
 *
 */
@WebServlet("/api/backstage/user/query")
public class BackUserQuery extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");
		String telphone = req.getParameter("telphone");
		String shopName = req.getParameter("shopName");
		
		if(pageNum == "") {
			pageNum = "-1";
		}
		if(pageSize == "") {
			pageSize = "-1";
		}
		
		int pageNum2 = Integer.parseInt(pageNum);
		int pageSize2 = Integer.parseInt(pageSize);
		
		PageResult pageResult = null;
		JsonResult jsonResult = null;
		UserServiceImpl impl = new UserServiceImpl();
		try {
			pageResult = impl.query(pageNum2, pageSize2, telphone, shopName);
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
