package org.taru.lanqiao.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.service.UserServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;


/**
 * 	前台用户登陆servlet    POST
 * @author 72810
 *
 */
@WebServlet("/api/user/login")
public class UserLoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserServiceImpl impl = new UserServiceImpl();
		User user = impl.login(username, password);
	
		String userId = user.getUserId();
		
		JsonResult jsonResult = null;
		try {
			if(user != null) {
				
				req.getSession().setAttribute("userId", userId);
				HttpSession session = req.getSession(); 
				String userId1 = (String) session.getAttribute("userId");
				System.out.println(userId1);
				
				jsonResult = new JsonResult("200", "登陆成功", user);
			}else {
				jsonResult = new JsonResult("404","用户名或密码错误",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","登陆异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
