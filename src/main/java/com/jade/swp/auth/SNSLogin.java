package com.jade.swp.auth;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.jade.swp.domain.User;

public class SNSLogin {
	private boolean isNaver;
	private OAuth20Service oauthService;
	private String profileApiUrl;

	public SNSLogin(SnsValue sns) {
		this.oauthService = new ServiceBuilder(sns.getClientId())
				.apiSecret(sns.getClientSecret())
				.callback(sns.getRedirectUrl())
				.scope("profile")
				.build(sns.getApi20());
		
		this.isNaver = sns.isNaver();
		this.profileApiUrl = sns.getProfileApiUrl();
	}

	public OAuth2AccessToken getAccessToken(String code) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		return accessToken;
	}

	public String getUserProfile(OAuth2AccessToken oauthToken) throws Exception {
		OAuthRequest request = new OAuthRequest(Verb.GET, this.profileApiUrl);
		oauthService.signRequest(oauthToken, request);
		Response response = oauthService.execute(request);
		return response.getBody();
	}
	
	public User parseProfile(String json) throws Exception {
		User user = new User();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode rootNode = mapper.readTree(json);
		
		if (isNaver) {
			JsonNode resNode = rootNode.path("response");
			System.out.println("resNode: " + resNode);
			user.setNaverid(resNode.get("id").asText());
			user.setUname(resNode.get("name").asText());
			
		} else {
			JsonNode idNode = rootNode.path("id");
			System.out.println("id = " + idNode.asText());
			
			user.setGoogleid(idNode.asText());
			user.setUname(rootNode.path("displayName").asText());
			
//		JsonNode nameNode = rootNode.path("name");
//		System.out.println("name = " + nameNode.get("familyName") + " " + nameNode.get("givenName"));
			
			Iterator<JsonNode> emails = rootNode.path("emails").elements();
			while(emails.hasNext()) {
				JsonNode tmpNode = emails.next();
				if (StringUtils.equalsIgnoreCase("account", tmpNode.get("type").asText())) {
					System.out.println("User.email=" + tmpNode.get("value").asText());
				}
			}
		}
		
		return user;
	}
	
	public String getNaverAuthURL() throws Exception {
		return this.oauthService.getAuthorizationUrl();
	}
	
}
