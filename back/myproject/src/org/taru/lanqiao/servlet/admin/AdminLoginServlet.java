package org.taru.lanqiao.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.service.AdminServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.util.SecurityUtil;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/admin/login")
public class AdminLoginServlet extends HttpServlet {

	/**
	 * 实现登录判断
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminServiceImpl adminServlet = new AdminServiceImpl();
		System.out.println(username);
		JsonResult result = null;
		try {
			Admin admin = adminServlet.queryByUsername(username);

			if (admin.getUsername().equals(username) ) {
				result = new JsonResult("200", "登录成功", admin);
//				HttpSession session = request.getSession();&& admin.getPassword().equals(password)
//				session.setAttribute("loginUserKey", admin);
//				Cookie token = new Cookie("token",SecurityUtil.getMd5String("lanqiao"));
//				Cookie name = new Cookie("username",username);
//				token.setMaxAge(60*60);
//				name.setMaxAge(60*60);
//				token.setPath("/");
//				name.setPath("/");
//				response.addCookie(token);
//				response.addCookie(name);
			} else {
				result = new JsonResult("500", "登录失败", null);
			}
		} catch (Exception e) {
			result = new JsonResult("500", "登录失败", null);
		}
		JsonWriter.writer(response, result);
	}

}
