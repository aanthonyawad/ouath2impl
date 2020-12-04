package com.awad.anthony.security.oauth2.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awad.anthony.security.oauth2.entitities.Access;
import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.helper.AuthorizeHelper;
import com.awad.anthony.security.oauth2.repositories.AccessRepository;
import com.awad.anthony.security.oauth2.repositories.AuthorityRepository;
import com.awad.anthony.security.oauth2.repositories.ClientRepository;
import com.awad.anthony.security.oauth2.service.AccessTokenService;

@Service("AccessTokenService")
@Repository
@Transactional
public class AccessTokenServiceImpl implements AccessTokenService {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccessRepository accessRepository;
/**
 * @param clientId the key parameter from Client object
 * @param clientSecrect the secret parameter from Client object
 * returns a client object based from client key and client secret passed in parameters	
 */
	@Override
	public Client getClient(String clientId, String clientSecret) {
		// when i say select client it will also select it will select all the object even with the forgein key constraints
		String query = "SELECT client from Client client where client.key = ?1 and client.secret = ?2";
		List<Client> clients = em.createQuery(query).setParameter(1, clientId)
				.setParameter(2, clientSecret).getResultList();
		if(clients.size() > 0) {
			return clients.get(0);
		}
		return null;
	}

	@Override
	public Access saveAccess(Access access) {
		access = accessRepository.save(access);
		return access;
	}

	@Override
	public void removeExpiredTokens(Date insertDate) {
		Date queryDate = new Date(insertDate.getTime()-AuthorizeHelper.REFRESH_TOKEN_VALIDITY_TIME*1000);//*1000 because refresh token time is in seconds 
		List<Access> accesses = em.createQuery("SELECT acccess FROM Access acccess " +
                "WHERE acccess.insertDate < :start")
   .setParameter("start",queryDate , TemporalType.DATE)
   .getResultList();
		
	if(accesses.size()> 0) {
		accessRepository.deleteAll(accesses);
	}
		
	}

}
