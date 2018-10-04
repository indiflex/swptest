package com.jade.swp.service;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;

public interface UserService {

	User login(LoginDTO dto) throws Exception;

}
