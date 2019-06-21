package com.battcn.entity;

public class Title implements java.io.Serializable {
	
    private Long id;
	
    private String type;
	
    private String institutionId;
	
    private String appId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getInstitutionId() {
		return institutionId;
	}
	
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
