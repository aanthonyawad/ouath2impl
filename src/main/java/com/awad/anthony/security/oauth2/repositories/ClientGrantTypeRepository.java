package com.awad.anthony.security.oauth2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awad.anthony.security.oauth2.entitities.Authority;
import com.awad.anthony.security.oauth2.entitities.ClientGrantType;

public interface ClientGrantTypeRepository extends JpaRepository<ClientGrantType, Integer> {
	
}
