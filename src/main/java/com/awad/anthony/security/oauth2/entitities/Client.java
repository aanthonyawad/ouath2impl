package com.awad.anthony.security.oauth2.entitities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="client_key")
	private String key;
	

	@Column(name="client_secret")
	private String secret;
	
	@Column(name="token_validity_time")
	private int tokenValidityTime;
	
	@Column(name="refresh_token_validity_time")
	private int refreshTokenValidityTime;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="insert_date")
	private Date insertDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
    @OneToMany(fetch = FetchType.LAZY,mappedBy="client")
    private List<Access> accesses;
	
	@OneToMany(mappedBy = "client")
    List<ClientAuthority> clientAuthorities;
	
	@OneToMany(mappedBy = "client")
    List<ClientScope> clientScopes;
	
	@OneToMany(mappedBy = "client")
    List<ClientGrantType> clientGrantTypes;
    
	public Client() {}

	public Client(int id, String key, String secret, int tokenValidityTime, int refreshTokenValidityTime,
			Date insertDate, Date updateDate, Customer customer, List<Access> accesses,
			List<ClientAuthority> clientAuthorities, List<ClientScope> clientScopes,
			List<ClientGrantType> clientGrantTypes) {
		super();
		this.id = id;
		this.key = key;
		this.secret = secret;
		this.tokenValidityTime = tokenValidityTime;
		this.refreshTokenValidityTime = refreshTokenValidityTime;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
		this.customer = customer;
		this.accesses = accesses;
		this.clientAuthorities = clientAuthorities;
		this.clientScopes = clientScopes;
		this.clientGrantTypes = clientGrantTypes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getTokenValidityTime() {
		return tokenValidityTime;
	}

	public void setTokenValidityTime(int tokenValidityTime) {
		this.tokenValidityTime = tokenValidityTime;
	}

	public int getRefreshTokenValidityTime() {
		return refreshTokenValidityTime;
	}

	public void setRefreshTokenValidityTime(int refreshTokenValidityTime) {
		this.refreshTokenValidityTime = refreshTokenValidityTime;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Access> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<Access> accesses) {
		this.accesses = accesses;
	}

	public List<ClientAuthority> getClientAuthorities() {
		return clientAuthorities;
	}

	public void setClientAuthorities(List<ClientAuthority> clientAuthorities) {
		this.clientAuthorities = clientAuthorities;
	}

	public List<ClientScope> getClientScopes() {
		return clientScopes;
	}

	public void setClientScopes(List<ClientScope> clientScopes) {
		this.clientScopes = clientScopes;
	}

	public List<ClientGrantType> getClientGrantTypes() {
		return clientGrantTypes;
	}

	public void setClientGrantTypes(List<ClientGrantType> clientGrantTypes) {
		this.clientGrantTypes = clientGrantTypes;
	}

	
	
}
