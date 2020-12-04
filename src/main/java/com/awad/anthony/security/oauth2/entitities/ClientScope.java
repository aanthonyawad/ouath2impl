package com.awad.anthony.security.oauth2.entitities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="client_scope")
public class ClientScope {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
 
    @ManyToOne
    @JoinColumn(name = "scope_id")
    Scope scope;

	public ClientScope() {}

	public ClientScope(int id, Client client, Scope scope) {
		super();
		this.id = id;
		this.client = client;
		this.scope = scope;
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

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}


	
}
