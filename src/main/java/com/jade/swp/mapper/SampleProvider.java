package com.jade.swp.mapper;

import java.util.Map;

public class SampleProvider {
	private static final String SEL = "select uname from User";

	public static String searchUser(Map<String, Object> params) {
		if (params.get("searchCol").equals("uid")) {
			return SEL + " where uid = #{searchStr}";
		
		} else if (params.get("searchCol").equals("uname")) {
			return SEL + " where uname = #{searchStr}";
			
		} else {
			return SEL + " limit 1";
		}
		
	}
}
