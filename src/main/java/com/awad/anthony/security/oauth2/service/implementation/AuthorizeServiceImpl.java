package com.awad.anthony.security.oauth2.service.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awad.anthony.security.oauth2.entitities.Authority;
import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.entitities.ClientAuthority;
import com.awad.anthony.security.oauth2.entitities.ClientGrantType;
import com.awad.anthony.security.oauth2.entitities.ClientScope;
import com.awad.anthony.security.oauth2.entitities.GrantType;
import com.awad.anthony.security.oauth2.entitities.Scope;
import com.awad.anthony.security.oauth2.repositories.AuthorityRepository;
import com.awad.anthony.security.oauth2.repositories.ClientAuthorithyRepository;
import com.awad.anthony.security.oauth2.repositories.ClientGrantTypeRepository;
import com.awad.anthony.security.oauth2.repositories.ClientRepository;
import com.awad.anthony.security.oauth2.repositories.ClientScopeRepository;
import com.awad.anthony.security.oauth2.repositories.GrantTypeRepository;
import com.awad.anthony.security.oauth2.repositories.ScopeRepository;
import com.awad.anthony.security.oauth2.service.AuthorizeService;

@Service("AuthorizeService")
@Repository
@Transactional
public class AuthorizeServiceImpl implements AuthorizeService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	ScopeRepository scopeRepository;
	
	@Autowired
	GrantTypeRepository grantTypeRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientAuthorithyRepository clientAuthorityRepository;
	
	@Autowired
	ClientGrantTypeRepository clientGrantTypeRepository;
	
	@Autowired
	ClientScopeRepository clientScopeRepository;
	
	@Override
	public Client createClient(Client client) {
		client = clientRepository.save(client);

		List<ClientAuthority> clientAuthorities = client.getClientAuthorities();
		clientAuthorityRepository.saveAll(clientAuthorities);
		
		List<ClientGrantType> clientGrantTypes = client.getClientGrantTypes();
		clientGrantTypeRepository.saveAll(clientGrantTypes);
		
		List<ClientScope> clientScopes = client.getClientScopes();
		clientScopeRepository.saveAll(clientScopes);
		return client;
	}

	@Override
	public List<GrantType> getDefaultGrantTypes() {
		List<GrantType> grantTypes = grantTypeRepository.findAll();
		return grantTypes;
	}

	@Override
	public List<Authority> getDefaultAuthorities() {
		List<Authority> authorities = authorityRepository.findAll();
		return authorities;
	}

	@Override
	public List<Scope> getDefaultScopes() {
		List<Scope> scopes = scopeRepository.findAll();
		return scopes;
	}

	
	

}
