package com.awad.anthony.security.oauth2.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.security.MD5Encoder;
import org.json.JSONException;
import org.json.JSONObject;

import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.model.Authorize;
import com.awad.anthony.security.oauth2.model.TokenError;

public class AuthorizeHelper {
	
	public static final String EMAIL = "email";
	public static final String NAME = "name";
	public static final int TOKEN_VALIDITY_TIME = 60*15;//in seconds 15 mins
	public static final int REFRESH_TOKEN_VALIDITY_TIME = 60*60*24;//in seconds 1 day

	public static void checkClientId(String clientId) throws UnsupportedOperationException {
		if(clientId== null)throw new UnsupportedOperationException("client_id can't be null.");
		
		if(clientId.length() <=3)throw new UnsupportedOperationException("client_id length must contains more than three characters.");
		
	}

	public static void checkRedirectUri(String redirectUri) throws UnsupportedOperationException {
		try {
			new URL(redirectUri);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnsupportedOperationException("invalid url");
		}
	}

	public static JSONObject checkCustomerData(String body) throws JSONException {
		JSONObject object = new JSONObject(body);
		object.getString("name");
		
		String email = object.getString("email");
		boolean validEmail = Helper.validate(email);	
		if(!validEmail) {
			throw new JSONException("invalid email.");
		}
		return object;
	}
	
	public static String generateSecrect(String clientId) {
		//TODO improve 
		byte[] salt = Helper.generateSalt(clientId);

		KeySpec spec = new PBEKeySpec(clientId.toCharArray(), salt, 65536, 128);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return MD5Encoder.encode(clientId.getBytes());
	}

	public static String buildError(Exception e) {
		TokenError error = new TokenError();
		error.setError(e.getMessage());
		return Helper.jsonSerialize(error).toString();
	}

	public static String buildSuccess(Client client) {
		Authorize authorize = new Authorize();
		authorize.setSecret(client.getSecret());
		return Helper.jsonSerialize(authorize).toString();
	}


}
