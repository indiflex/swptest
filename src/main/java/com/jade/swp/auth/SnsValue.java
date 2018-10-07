package com.jade.swp.auth;

import org.apache.commons.lang3.StringUtils;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.Data;

@Data
public class SnsValue implements SnsUrls {
	private DefaultApi20 api20;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private String profileApiUrl;
	private boolean isNaver;
	
	public SnsValue(String service, String id, String secret, String rurl) {
		this.clientId = id;
		this.clientSecret = secret;
		this.redirectUrl = rurl;
		
		this.isNaver = StringUtils.equalsIgnoreCase(service, "naver");
		this.profileApiUrl = isNaver ? NAVER_PROFILE_URL : GOOGLE_PROFILE_URL;
		this.api20 = isNaver ? NaverAPI20.getInstance() : GoogleApi20.instance();
	}
}
