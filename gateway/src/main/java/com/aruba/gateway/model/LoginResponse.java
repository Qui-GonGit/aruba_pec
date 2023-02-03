package com.aruba.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("expires_in")
	private String expiryIn;
	@JsonProperty("refresh_expires_in")
	private String refreshExpiresIn;
	@JsonProperty("token_type")
	private String tokenType;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getExpiryIn() {
		return expiryIn;
	}
	public void setExpiryIn(String expiryIn) {
		this.expiryIn = expiryIn;
	}
	public String getRefreshExpiresIn() {
		return refreshExpiresIn;
	}
	public void setRefreshExpiresIn(String refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
