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
 * 	后台 添加用户
 * @author 72810
 *
 */
@WebServlet("/api/backstage/user/add")
public class BackUserAddServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("userUserName");
		String userPassword = req.getParameter("userPassword");
		String userTelphone = req.getParameter("userTelphone");
		String userAddress = req.getParameter("userAddress");
		String userShopName = req.getParameter("userShopName");
		String userComment = req.getParameter("userComment");
		
		User user = new User();
		user.setUserUserName(username);
		user.setUserPassword(userPassword);
		user.setUserTelphone(userTelphone);
		user.setUserAddress(userAddress);
		user.setUserShopName(userShopName);
		user.setUserComment(userComment);
		
		JsonResult jsonResult = null;
		UserServiceImpl impl = new UserServiceImpl();
		boolean bool = impl.register(user);
		try {
			if(bool) {
				jsonResult = new JsonResult("200","用户添加成功",null);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","添加用户异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
