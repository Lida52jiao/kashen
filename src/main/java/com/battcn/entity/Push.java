package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "t_mp_push")
public class Push implements Serializable {

	private static final long serialVersionUID = 7437285056372915425L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "appkey")
	private String appkey;
	@Column(name = "secret")
	private String secret;
	@Column(name = "appId")
	private String appId;
	
	public Push() {
		super();
	}
	
	public Push(String name, String appkey, String secret, String appId) {
		super();
		this.name = name;
		this.appkey = appkey;
		this.secret = secret;
		this.appId = appId;
	}

	public Push(Long id, String name, String appkey, String secret, String appId) {
		super();
		this.id = id;
		this.name = name;
		this.appkey = appkey;
		this.secret = secret;
		this.appId = appId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
