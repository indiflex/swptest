package com.jade.swp.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jade.swp.domain.User;
import com.jade.swp.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession session;

	private static final String NS = "com.jade.swp.persistence.UserMapper";
	private static final String LOGIN = NS + ".login";
	private static final String KEEP_LOGIN = NS + ".keepLogin";
	private static final String CHECK_LOGIN_BEFORE = NS + ".checkLoginBefore";
	private static final String GET_BY_NAVERID = NS + ".getByNaverid";
	private static final String GET_BY_GOOGLEID = NS + ".getByGoogleid";

	@Override
	public User login(LoginDTO dto) throws Exception {
		return session.selectOne(LOGIN, dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date expire) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("sessionkey", sessionId);
		paramMap.put("sessionlimit", expire);
		session.update(KEEP_LOGIN, paramMap);
	}

	@Override
	public User checkLoginBefore(String loginCookie) {
		return session.selectOne(CHECK_LOGIN_BEFORE, loginCookie);
	}

	@Override
	public User getByNaverid(String naverid) {
		return session.selectOne(GET_BY_NAVERID, naverid);
	}

	@Override
	public User getByGoogleid(String googleid) {
		return session.selectOne(GET_BY_GOOGLEID, googleid);
	}

}
