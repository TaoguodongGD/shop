package org.taru.lanqiao.servlet.backuser;

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
 * 	后台  根据Id查询用户信息
 * @author 72810
 *
 */
@WebServlet("/api/backstage/user/findById")
public class BackUserFindById extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("uid");
		JsonResult jsonResult = null;
		try {
			UserServiceImpl impl = new UserServiceImpl();
			User user = impl.findById(userId);
			if(user != null) {
				jsonResult = new JsonResult("200","查询信息成功",user);
			}else {
				jsonResult = new JsonResult("404","该用户不存在",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","查询信息异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
