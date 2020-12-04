package com.awad.anthony.security.oauth2.controller.oauth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awad.anthony.security.oauth2.entitities.Access;
import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.helper.AccessTokenHelper;
import com.awad.anthony.security.oauth2.service.AccessTokenService;

/**
 * 
 * @author Aanthony
 * @category REST APIs
 * @version 1.0
 * <p>//TODO</p>
 */
@RestController
@RequestMapping(path = "/oauth")
public class AccessTokenController {
	
	@Autowired
	AccessTokenService accessTokenService;
	/**
	 * 
	 * @param authorization sent as header in the HTTP request as Accept plus base64encode(key:secret)
	 * @param code should be equal to token to request a token
	 * @return
	 */
	@PostMapping(path = "/access_token", produces="application/json")
	public String generateToken(
			@RequestHeader(name="Authorization",required=true) String authorization) {
		String clientId = null;
		String clientSecret = null;
		Client client = null;
		try {
			AccessTokenHelper.validateAuthorization(authorization);
		}catch (UnsupportedOperationException e) {
				return AccessTokenHelper.buildError(e);
		}
		
		try {
			clientId = AccessTokenHelper.getClientIdFromAuthorization(authorization);
		}catch (UnsupportedOperationException e) {
				return AccessTokenHelper.buildError(e);
		}
		
		try {
			clientSecret = AccessTokenHelper.getClientSecretFromAuthrorization(authorization);
		}catch (UnsupportedOperationException e) {
				return AccessTokenHelper.buildError(e);
		}
		try {
			// a stored procedure will clean tokens into another table for logging TODO this
			// this we only have working tokens
			client = accessTokenService.getClient(clientId,clientSecret);
			
			if(client==null) {
				throw new UnsupportedOperationException(AccessTokenHelper.NOT_AUTHENTICATED);
			}
		}catch (UnsupportedOperationException e) {
			return AccessTokenHelper.buildError(e);
		}
		// i have the client object which contains the access list where all the tokens are stored for this customer.
		List<Access> accesses = client.getAccesses();
		if(accesses.size() > 0) {
			// has a valid token return it
			return AccessTokenHelper.buildTokenReply(client);
		}
		//else  generate a new token
		Access access= new Access();
		access.setAccessToken(AccessTokenHelper.createRandomAccessToken());
		access.setRefreshToken(AccessTokenHelper.createRandomRefreshToken());
		access.setClient(client);
//		save it in db 
		access = accessTokenService.saveAccess(access);
//		refresh client list
		try {
			// we query to get only the working tokens
			client = accessTokenService.getClient(clientId,clientSecret);
			
			if(client==null) {
				throw new UnsupportedOperationException(AccessTokenHelper.NOT_AUTHENTICATED);
			}
		}catch (UnsupportedOperationException e) {
			return AccessTokenHelper.buildError(e);
		}
//		return it
		return AccessTokenHelper.buildTokenReply(client);
		
	}
	
}
