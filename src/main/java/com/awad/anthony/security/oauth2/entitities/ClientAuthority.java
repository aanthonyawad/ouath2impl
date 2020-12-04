package com.awad.anthony.security.oauth2.entitities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="client_authority")
public class ClientAuthority {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="id")
	private int id;


    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
 
    @ManyToOne
    @JoinColumn(name = "authority_id")
    Authority authority;
 
	
	public ClientAuthority() {}


	public ClientAuthority(int id, Client client, Authority authority) {
		super();
		this.id = id;
		this.client = client;
		this.authority = authority;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Authority getAuthority() {
		return authority;
	}


	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}
