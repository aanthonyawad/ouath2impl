package com.awad.anthony.security.oauth2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awad.anthony.security.oauth2.entitities.ClientScope;

public interface ClientScopeRepository extends JpaRepository<ClientScope, Integer> {
	
}
