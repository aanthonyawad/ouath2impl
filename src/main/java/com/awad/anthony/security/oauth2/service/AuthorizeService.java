package com.awad.anthony.security.oauth2.service;

import java.util.List;

import com.awad.anthony.security.oauth2.entitities.Authority;
import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.entitities.GrantType;
import com.awad.anthony.security.oauth2.entitities.Scope;

public interface AuthorizeService {

	Client createClient(Client client);

	List<GrantType> getDefaultGrantTypes();

	List<Authority> getDefaultAuthorities();

	List<Scope> getDefaultScopes();

}
