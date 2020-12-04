package com.awad.anthony.security.oauth2.entitities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="client_grant_type")
public class ClientGrantType {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
 
    @ManyToOne
    @JoinColumn(name = "grant_type_id")
    GrantType grantType;
	public ClientGrantType() {}
	public ClientGrantType(int id, Client client, GrantType grantType) {
		super();
		this.id = id;
		this.client = client;
		this.grantType = grantType;
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
	public GrantType getGrantType() {
		return grantType;
	}
	public void setGrantType(GrantType grantType) {
		this.grantType = grantType;
	}

	
	

}
