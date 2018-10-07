package com.jade.swp.service;

import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;
import com.jade.swp.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO dao;

	@Override
	public User login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date expire) {
		dao.keepLogin(uid, sessionId, expire);
	}

	@Override
	public User checkLoginBefore(String loginCookie) {
		return dao.checkLoginBefore(loginCookie);
	}

	@Override
	public User getBySns(User snsUser) {
		if (StringUtils.isNotEmpty(snsUser.getNaverid())) {
			return dao.getByNaverid(snsUser.getNaverid());
			
		} else {
			return dao.getByGoogleid(snsUser.getGoogleid());
		}
	}

}
