package com.jade.swp.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.jade.swp.auth.SnsValue;
import com.jade.swp.auth.SNSLogin;
import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;
import com.jade.swp.interceptor.SessionNames;
import com.jade.swp.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Inject
	private SnsValue naverSns;
	
	@Inject
	private SnsValue googleSns;
	
	@RequestMapping(value = "/auth/naver/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverCallback(Model model, @RequestParam String code, HttpSession session) throws Exception {
		System.out.println("NNNNNN>> 여기는 naverCallback: " + code);
		
		return loginCallback(new SNSLogin(naverSns), model, code, session);
	}
	
	// 구글 Callback호출 메소드
	@RequestMapping(value = "/auth/google/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(Model model, 
			@RequestParam String code, @RequestParam String scope,
			HttpSession session) throws Exception {
		System.out.println("GGGGGGG>> 여기는 googleCallback: " + code);
		System.out.println("Scope: " + scope);
		
		model.addAttribute("code", code);
		model.addAttribute("scope", scope);
		
		return loginCallback(new SNSLogin(googleSns), model, code, session);
	}

	private String loginCallback(SNSLogin snsLogin, Model model, String code, HttpSession session) throws Exception {
		OAuth2AccessToken accessToken = snsLogin.getAccessToken(code);
        System.out.println("(The Token.raw response looks like this: " + accessToken.getRawResponse() + "')");
        
        String userProfile = snsLogin.getUserProfile(accessToken);
        System.out.println("GoogleUserProfile:" + userProfile);
        model.addAttribute("profile", userProfile);
        
        User snsUser = snsLogin.parseProfile(userProfile);
        System.out.println("UUUUUUUUU>>" + snsUser);
        
        User user = service.getBySns(snsUser);
        if (user != null) {
        	session.setAttribute(SessionNames.LOGIN, user);
        	model.addAttribute("result", user.getUname() + "님 반갑습니다! ");
        } else {
        	model.addAttribute("result", snsUser.getUname() + "님께서는 가입하지 않는 사용자입니다. 가입해 주세요!");
        }
        
        return "/loginResult";
	}
	

	// ---------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("logout GET .....");
		session.removeAttribute(SessionNames.LOGIN);
		session.invalidate();
		
		Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN);
		if (loginCookie != null) {
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			
			response.addCookie(loginCookie);
			
			User user = (User)session.getAttribute(SessionNames.LOGIN);
			service.keepLogin(user.getUid(), session.getId(), new Date());
		}
		
		return "/logout";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model) throws Exception {
		logger.info("login GET .....");
		
		SNSLogin snsLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		
		/* 구글code 발행을 위한 URL 생성 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

		model.addAttribute("google_url", url);
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO dto, Model model, HttpSession session) throws Exception {
		logger.info("loginPost...LoginDTO={}", dto); 
		
		try {
			User user = service.login(dto);
			if (user != null) {
				Date expire = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000);
				service.keepLogin(user.getUid(), session.getId(), expire);
				model.addAttribute("user", user);
				
			} else {
				model.addAttribute("loginResult", "Login Fail!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/logoutAjax", method=RequestMethod.GET)
	public ResponseEntity<String> logoutAjax(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session) {
		logger.info("Logout Ajax>> " + session.getAttribute("loginUser"));
		session.removeAttribute("loginUser");
		
		User user = (User)session.getAttribute(SessionNames.LOGIN);
		if (user != null) {
			session.removeAttribute(SessionNames.LOGIN);
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		
		return new ResponseEntity<>("logouted", HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginAjax", method = RequestMethod.POST)
	public ResponseEntity<User> loginAjax(@RequestBody LoginDTO dto, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("loginPost...LoginDTO={}", dto); 
		
		try {
			User user = service.login(dto);
			if (user != null) { // login success
				user.setUpw(null);
				
				session.setAttribute("loginUser", user);
				
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(7 * 24 * 60 * 60);
				
				response.addCookie(loginCookie);
				
				return new ResponseEntity<>(user, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
}
