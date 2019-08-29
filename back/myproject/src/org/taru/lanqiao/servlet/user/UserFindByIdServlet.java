package org.taru.lanqiao.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.service.UserServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 	根据用户Id查询信息  POST|GET
 * @author 72810
 *
 */
@WebServlet("/api/user/findById")
public class UserFindByIdServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		
		UserServiceImpl impl = new UserServiceImpl();
		JsonResult jsonResult = null;
		User user = impl.findById(userId);
		try {
			if(user != null) {
				jsonResult = new JsonResult("200","用户查询信息成功",user);
			}else {
				jsonResult = new JsonResult("404","该用户不存在",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","用户查询异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
