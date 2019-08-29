package org.taru.lanqiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域过滤器
 * 设置CORS（跨域资源共享）过滤器：
 * 它使用额外的 HTTP 头来告诉浏览器  让运行在一个 origin (domain) 上的Web应用被准许访问来自不同源服务器上的指定的资源。
 * 当一个资源从与该资源本身所在的服务器不同的域、协议或端口请求一个资源时，资源会发起一个跨域 HTTP 请求。
 * @author liuyue
 *
 */
public class CorsFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  request  =(HttpServletRequest)req;
		HttpServletResponse response  =(HttpServletResponse)res;
		
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin")); //   *不允许携带凭证 （cookie） 
		//动态请求request.getHeader("Origin"))记得在vue中加入axios.defaults.withCredentials = true;让cookie可以加载，同时后台也要设置为true
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");    //
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age", "3600");  
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers", "*");  
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
