package com.awad.anthony.security.oauth2.model;

public class AccessToken {
	private String access_token;
	
	private String refresh_token;
	
	private int access_token_expires_in;
	
	private int refresh_token_expires_in;
	
	private String scopes;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getAccess_token_expires_in() {
		return access_token_expires_in;
	}

	public void setAccess_token_expires_in(int access_token_expires_in) {
		this.access_token_expires_in = access_token_expires_in;
	}

	public int getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}

	public void setRefresh_token_expires_in(int refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}
}
