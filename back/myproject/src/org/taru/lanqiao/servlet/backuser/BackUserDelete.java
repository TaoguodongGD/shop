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

/**
 * 	后台 删除用户
 * @author 72810
 *
 */
@WebServlet("/api/backstage/user/del")
public class BackUserDelete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		JsonResult jsonResult = null;
		UserServiceImpl impl = new UserServiceImpl();
		try {
			boolean bool = impl.delete(uid);
			if(bool) {		
				jsonResult = new JsonResult("200","用户删除成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","用户删除异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
