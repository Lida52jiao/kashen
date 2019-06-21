package com.battcn.entity;


public class Interlocution implements java.io.Serializable {
	
    private Long id;
	
    private String question;
	
    private String answer;
	
    private String type;
	
    private String forwardURL;
	
    private String institutionId;
	
    private String appId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getForwardURL() {
		return forwardURL;
	}
	
	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
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
