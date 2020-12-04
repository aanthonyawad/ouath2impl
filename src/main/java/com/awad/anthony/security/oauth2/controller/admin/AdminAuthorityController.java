package com.awad.anthony.security.oauth2.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awad.anthony.security.oauth2.entitities.Authority;
import com.awad.anthony.security.oauth2.repositories.AuthorityRepository;


/**
 * 
 * @author Aanthony
 * @category REST APIs
 * @version 1.0
 * <p>Used by administrators to CRUD predefined values into the oauth server database authority table</p>
 */
@RestController
@RequestMapping(path = "/admin/")
public class AdminAuthorityController {
	
	@Autowired
	AuthorityRepository authorityRepository;
	@GetMapping(path = "/authority", produces="application/json")
	public List<Authority> read() {
		List<Authority> authorities = authorityRepository.findAll();
		if(authorities != null && authorities.size() > 0) {
			return authorities;
		}
		//TODO handle error on empty and null
		return null;
	}
	
	@GetMapping(path = "/authority/{id}", produces="application/json")
	public List<Authority> read(@PathParam(value = "id") int id) {

		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(id);
		List<Authority> authorities = authorityRepository.findAllById(ids);
		if(authorities != null && authorities.size() > 0) {
			return authorities;
		}
		//TODO handle error on empty and null
		return null;
	}
	
	@PostMapping(path = "/authority", produces="application/json")
	public List<Authority> create(@RequestBody String body) {
		Authority authority = new Authority();
		
		try {
			JSONObject object = new JSONObject(body);
			String name = object.getString("name");
			authority.setName(name);
			authority = authorityRepository.save(authority);
			List<Authority> authorities = new ArrayList<Authority>();
			if(authority.getId() <=0)throw new UnsupportedOperationException("CREATE Error");
			authorities.add(authority);
			return authorities ;
		}catch(JSONException e) {
			e.printStackTrace();
		}
		catch(UnsupportedOperationException e) {
			e.printStackTrace();
		}
		
		//TODO handle error on empty and null
		return null;
	}
	
	@PutMapping(path = "/authority", produces="application/json")
	public List<Authority> update(@RequestBody String body) {
		Authority authority = new Authority();
		try {
			JSONObject object = new JSONObject(body);
			String name = object.getString("name");
			int id = object.getInt("id");
			authority.setName(name);
			authority.setId(id);
			//TODO check if exists
			authority = authorityRepository.save(authority);
			List<Authority> authorities = new ArrayList<Authority>();
			if(authority.getId() <=0)throw new UnsupportedOperationException("UPDATE Error");
			authorities.add(authority);
			return authorities ;
		}catch(JSONException e) {
			e.printStackTrace();
		}
		catch(UnsupportedOperationException e) {
			e.printStackTrace();
		}
		
		//TODO handle error on empty and null
		return null;
	}
	
}
