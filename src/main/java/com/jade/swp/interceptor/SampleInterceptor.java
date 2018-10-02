package com.jade.swp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jade.swp.domain.Board;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiii-pre!!>> preHandler: " + request.getParameter("page"));
		
		HandlerMethod method = (HandlerMethod) handler;
		System.out.println("MMMM>> Bean: " + method.getBean() + ", Method: " + method.getMethod());
		
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiii-pre!!>> listSize=" + request.getSession().getAttribute("listSize"));
		
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiii-post!!>> postHandler: " + handler);
		
//		Object list = modelAndView.getModel().get("list");
		@SuppressWarnings("unchecked")
		List<Board> list = (List)modelAndView.getModel().get("list");
		System.out.println("iiiiiii-list>>" + list.get(0));
		
		request.getSession().setAttribute("listSize", list.size());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiii>> afterCompletion: " + handler.toString());
	}
}
