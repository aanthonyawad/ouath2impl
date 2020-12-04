package com.awad.anthony.security.oauth2.service;

import java.util.Date;

import com.awad.anthony.security.oauth2.entitities.Access;
import com.awad.anthony.security.oauth2.entitities.Client;

public interface AccessTokenService {

	Client getClient(String clientId, String clientSecret);

	Access saveAccess(Access access);

	void removeExpiredTokens(Date insertDate);

}
