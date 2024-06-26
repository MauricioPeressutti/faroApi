package com.project.faro.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.project.security")
public class JwtSettings {
	public final static String TOKEN_HEADER_PARAM = "X-Authorization";

	public static final String SIGNING_KEY = "rvV691Ew4U0ETZXREGTeU5hg00UqYh";

	public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

	public static String HEADER_PREFIX = "Bearer ";

	private Integer tokenExpirationTime;

	private String tokenIssuer;

	private Integer refreshTokenExpTime;

	private Integer personTokenExpirationTime;

	public Integer getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Integer tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public String getTokenIssuer() {
		return tokenIssuer;
	}

	public void setTokenIssuer(String tokenIssuer) {
		this.tokenIssuer = tokenIssuer;
	}

	public Integer getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}

	public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

	public Integer getPersonTokenExpirationTime() {
		return personTokenExpirationTime;
	}

	public void setPersonTokenExpirationTime(Integer personTokenExpirationTime) {
		this.personTokenExpirationTime = personTokenExpirationTime;
	}
}
