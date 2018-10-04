package com.jade.swp.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.jade.swp.domain.User;
import com.jade.swp.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	
	@Inject
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) == null) {
			saveAttemptedLocation(request);
			
			Cookie loginCookie = WebUtils.getCookie(request, LOGIN_COOKIE);
			if (loginCookie != null) {
				User savedUser = service.checkLoginBefore(loginCookie.getValue());
				if (savedUser != null) {
					session.setAttribute(LOGIN, savedUser);
					return true;
				}
			}
			
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
