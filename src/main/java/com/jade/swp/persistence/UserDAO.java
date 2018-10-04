package com.jade.swp.persistence;

import java.util.Date;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;

public interface UserDAO {

	User login(LoginDTO dto) throws Exception;

	void keepLogin(String uid, String sessionId, Date sessionLimit);

	User checkLoginBefore(String loginCookie);

}
