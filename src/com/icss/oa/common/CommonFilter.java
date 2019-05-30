package com.icss.oa.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 * @author Administrator
 *
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void destroy() {
				
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		//设置允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");	
		
		//继续向下执行没写
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
				
	}

}