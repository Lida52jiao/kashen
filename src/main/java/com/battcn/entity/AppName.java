package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_mp_appName")
public class AppName implements Serializable {

	private static final long serialVersionUID = -5060562121782519504L;
	@Column(name = "appId")
	private String appId;
	@Column(name = "appName")
	private String appName;
	@Column(name = "agentId")
	private String agentId;

	public AppName() {
		super();
	}

	public AppName(String appId, String appName) {
		super();
		this.appId = appId;
		this.appName = appName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

}
