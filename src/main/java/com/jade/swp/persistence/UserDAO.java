package com.jade.swp.persistence;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;

public interface UserDAO {

	User login(LoginDTO dto) throws Exception;

}
