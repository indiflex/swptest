package com.jade.swp.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;
import com.jade.swp.interceptor.SessionNames;
import com.jade.swp.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		logger.info("Login GET");
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
		logger.info("Login POST dto={}", dto.toString());
		
		User user = service.login(dto);
		logger.info("SelectedUser={}", user);
		if (user == null) {
			model.addAttribute("uid", dto.getUid());
			return;
		}
		
		Date sessionLimit = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000);
		service.keepLogin(user.getUid(), session.getId(), sessionLimit);
		model.addAttribute("user", user);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("dto") LoginDTO dto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Logout");
		
		User user = (User)session.getAttribute(SessionNames.LOGIN);
		if (user != null) {
			session.removeAttribute(SessionNames.LOGIN);
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(user.getUid(), session.getId(), new Date());
			}
		}

		return "login";
	}
}
