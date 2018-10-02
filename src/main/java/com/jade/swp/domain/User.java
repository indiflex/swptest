package com.jade.swp.domain;

import lombok.Data;

@Data
//@Builder
public class User {
	private String uid;
	private String upw;
	private String uname;
	private Integer upoint;
	
}
