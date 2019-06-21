package com.battcn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "t_mp_agententer")
public class AgentEnter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "entName")
	private String entName;
	@Column(name = "entMp")
	private String entMp;
	@Column(name = "entMail")
	private String entMail;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "entArea")
	private String entArea;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private String institutionId;
	@Column(name = "appId")
	private String appId;
	@Column(name = "entDate")
	private Date entDate;
	public AgentEnter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AgentEnter(Integer id, String entName, String entMp, String entMail,
			String merChantId,String entArea, String merName, String merMp, String agentId,
			String institutionId, String appId, Date entDate) {
		super();
		this.id = id;
		this.entName = entName;
		this.entMp = entMp;
		this.entMail = entMail;
		this.merChantId = merChantId;
		this.merName = merName;
		this.merMp = merMp;
		this.agentId = agentId;
		this.institutionId = institutionId;
		this.appId = appId;
		this.entDate = entDate;
		this.entArea = entArea;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getEntMp() {
		return entMp;
	}
	public void setEntMp(String entMp) {
		this.entMp = entMp;
	}
	public String getEntMail() {
		return entMail;
	}
	public void setEntMail(String entMail) {
		this.entMail = entMail;
	}
	public String getMerChantId() {
		return merChantId;
	}
	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerMp() {
		return merMp;
	}
	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
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
	public Date getEntDate() {
		return entDate;
	}
	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}
	public String getEntArea() {
		return entArea;
	}
	public void setEntArea(String entArea) {
		this.entArea = entArea;
	}

	
}
