package com.oracle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.entity.Users;


public class MyFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String str=request.getRequestURI();
		if(str.contains(".css")||str.contains(".js")){
			chain.doFilter(request, response);
		}else if(str.contains(".jsp")){
			HttpSession session=request.getSession();
			Users user=(Users) session.getAttribute("user");
			if(user!=null){
				chain.doFilter(request, response);
			}else{
				response.sendRedirect("jsp/login.jsp");
				System.out.println("¹ýÂËÆ÷Ö´ÐÐ");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}



	
}
