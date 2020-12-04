package com.awad.anthony.security.oauth2.helper;

import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.entitities.ClientScope;
import com.awad.anthony.security.oauth2.model.AccessToken;
import com.awad.anthony.security.oauth2.model.TokenError;

public class AccessTokenHelper {

	public static final String NOT_AUTHENTICATED = "Not Authenticated.";

	public static void validateAuthorization(String authorization) throws UnsupportedOperationException{
		String accept = "Accept ";
		String rest= null;
		try {
			rest = authorization.split(accept)[1];
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		}
		byte[] decodedBytes = Base64.getUrlDecoder().decode(rest);
		rest = new String(decodedBytes);
		
		String[] array = rest.split(":");
		int length = array.length;
		if(length != 2)throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		
	}


	public static String getClientIdFromAuthorization(String authorization) throws UnsupportedOperationException{
		String accept = "Accept ";
		String rest= null;
		try {
			rest = authorization.split(accept)[1];
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		}
		byte[] decodedBytes = Base64.getUrlDecoder().decode(rest);
		rest = new String(decodedBytes);
		
		String[] array = rest.split(":");
		int length = array.length;
		if(length != 2)throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		return array[0];
		
	}

	public static String getClientSecretFromAuthrorization(String authorization) throws UnsupportedOperationException{
		String accept = "Accept ";
		String rest= null;
		try {
			rest = authorization.split(accept)[1];
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		}
		byte[] decodedBytes = Base64.getUrlDecoder().decode(rest);
		rest = new String(decodedBytes);
		
		String[] array = rest.split(":");
		int length = array.length;
		if(length != 2)throw new UnsupportedOperationException(NOT_AUTHENTICATED);
		return array[1];
		
	}

	public static String createRandomAccessToken() {
		 String generatedString = RandomStringUtils.randomAlphabetic(16,32);
		return generatedString;
	}

	public static String createRandomRefreshToken() {
		 String generatedString = RandomStringUtils.randomAlphabetic(16,32);
		return generatedString;
	}
	
	public static String buildTokenReply(Client client) {
		AccessToken accessToken = new AccessToken();
		accessToken.setAccess_token(client.getAccesses().get(0).getAccessToken());
		accessToken.setRefresh_token(client.getAccesses().get(0).getRefreshToken());
		accessToken.setAccess_token_expires_in(client.getTokenValidityTime());
		accessToken.setRefresh_token_expires_in(client.getRefreshTokenValidityTime());
		String scopes="";
		List<ClientScope> clientScopes = client.getClientScopes();
		for(ClientScope clientScope: clientScopes) {
			scopes+=clientScope.getScope().getName()+",";
		}
		scopes=scopes.substring(0, scopes.length()-1);
		accessToken.setScopes(scopes);
		return Helper.jsonSerialize(accessToken).toString();
	}
	
	public static String buildError(Exception e) {
		TokenError error = new TokenError();
		error.setError(e.getMessage());
		return Helper.jsonSerialize(error).toString();
	}


}
