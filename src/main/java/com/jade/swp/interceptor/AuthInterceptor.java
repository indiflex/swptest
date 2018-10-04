package com.jade.swp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) == null) {
			saveAttemptedLocation(request);
			response.sendRedirect("/login");
			return false;
		}
		
		return true;
	}

	private void saveAttemptedLocation(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		System.out.println("AuthInterceptor.saveAttemp>>" + uri + ", " + query);
		if (StringUtils.isNotBlank(query)) {
			uri += "?" + query;
		}
		
		request.getSession().setAttribute(ATTEMPTED, uri);
	}

}
