package com.awad.anthony.security.oauth2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awad.anthony.security.oauth2.entitities.Scope;

public interface ScopeRepository extends JpaRepository<Scope, Integer> {
	
}
