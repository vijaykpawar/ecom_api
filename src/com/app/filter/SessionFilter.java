package com.app.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.ILoginDao;

public class SessionFilter implements Filter {

	@Autowired
	ILoginDao loginDao;

	private ArrayList<String> urlList;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();

		System.out.println("In filter URL is ::" + url);
		System.out.println("session Toke is ::"+request.getParameter("sessionToken"));

		boolean allowedRequest = false;
		if (urlList.contains(url)) {
			allowedRequest = true;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		urlList = new ArrayList<>();
		urlList.add("/user/valid");

	}

}
