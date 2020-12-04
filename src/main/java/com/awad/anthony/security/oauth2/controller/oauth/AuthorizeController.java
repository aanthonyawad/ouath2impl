package com.awad.anthony.security.oauth2.controller.oauth;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awad.anthony.security.oauth2.entitities.Access;
import com.awad.anthony.security.oauth2.entitities.Authority;
import com.awad.anthony.security.oauth2.entitities.Client;
import com.awad.anthony.security.oauth2.entitities.ClientAuthority;
import com.awad.anthony.security.oauth2.entitities.ClientGrantType;
import com.awad.anthony.security.oauth2.entitities.ClientScope;
import com.awad.anthony.security.oauth2.entitities.Customer;
import com.awad.anthony.security.oauth2.entitities.GrantType;
import com.awad.anthony.security.oauth2.entitities.Scope;
import com.awad.anthony.security.oauth2.helper.AuthorizeHelper;
import com.awad.anthony.security.oauth2.service.AuthorizeService;

/**
 * 
 * @author Aanthony
 * @category REST APIs
 * @version 1.0
 * <p>//TODO</p>
 */
@RestController
@RequestMapping(path = "/oauth")
public class AuthorizeController {
	
	@Autowired
	AuthorizeService authorizeService;
	
	@PostMapping(path = "/authorize", produces="application/json")
	public String authorize(@RequestParam(name="client_id", required = true) String clientId,
			@RequestParam(name="redirect_uri", required = true) String redirectUri,
			@RequestBody String body) {
			JSONObject customerData= null;
			
			try {
			AuthorizeHelper.checkClientId(clientId);
			}catch (UnsupportedOperationException e) {
				return AuthorizeHelper.buildError(e);
			}
			
			try {
			AuthorizeHelper.checkRedirectUri(redirectUri);
			}catch(Exception e) {
				return AuthorizeHelper.buildError(e);
			}
				
			try {
			customerData = AuthorizeHelper.checkCustomerData(body);
			}catch(JSONException e) {
				return AuthorizeHelper.buildError(e);
			}
			
			//create the customer and set the data fetched from the json body
			Customer customer = new Customer();
			customer.setActive(1);
			try {
				customer.setEmail(customerData.getString(AuthorizeHelper.EMAIL));
				customer.setName(customerData.getString(AuthorizeHelper.NAME));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			customer.setRedirectUrl(redirectUri);
			
			
			Client client = new Client();
			//set the customer create in the client object
			client.setCustomer(customer);
			//set the client id as key in the client object
			client.setKey(clientId);
			//generate the secret from a function which takes the client id
			client.setSecret(AuthorizeHelper.generateSecrect(clientId));
			//set token and resfresh token validity times
			client.setTokenValidityTime(AuthorizeHelper.TOKEN_VALIDITY_TIME);
			client.setRefreshTokenValidityTime(AuthorizeHelper.REFRESH_TOKEN_VALIDITY_TIME);
			

			//set the accesses that client can use
			List<GrantType> grantTypes= authorizeService.getDefaultGrantTypes();
			List<ClientGrantType> clientGrantTypes = new ArrayList<>();
			for(GrantType grantType : grantTypes) {
				ClientGrantType clientGrantType = new ClientGrantType();
				clientGrantType.setClient(client);
				clientGrantType.setGrantType(grantType);
				clientGrantTypes.add(clientGrantType);
			}
			client.setClientGrantTypes(clientGrantTypes);
			
			//set the authorities that client can use
			List<Authority> authorities= authorizeService.getDefaultAuthorities();
			List<ClientAuthority> clientAuthorities = new ArrayList<>();
			for(Authority authority : authorities) {
				ClientAuthority clientAuthority = new ClientAuthority();
				clientAuthority.setClient(client);
				clientAuthority.setAuthority(authority);
				clientAuthorities.add(clientAuthority);
			}
			client.setClientAuthorities(clientAuthorities);
			
			//set the scopes that client can use
			List<Scope> scopes= authorizeService.getDefaultScopes();
			List<ClientScope> clientScopes = new ArrayList<>();
			for(Scope scope : scopes) {
				ClientScope clientScope = new ClientScope();
				clientScope.setClient(client);
				clientScope.setScope(scope);
				clientScopes.add(clientScope);
			}
			client.setClientScopes(clientScopes);
			
			
			
			//insert everything in the database
			client = authorizeService.createClient(client);
			
			return AuthorizeHelper.buildSuccess(client);
		
	}

}
