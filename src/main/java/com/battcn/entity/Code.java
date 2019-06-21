package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "t_mp_code")
public class Code implements Serializable {

	private static final long serialVersionUID = 530155362052703657L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codeId", unique = true, nullable = false)
	private Long codeId;
	@Column(name = "activationCode")
	private String activationCode;
	@Column(name = "status")
	private String status;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "actDate")
	private String actDate;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "remarks")
	private String remarks;

	public Code() {
		super();
	}
	
	public Code(String activationCode, String status, String creatDate,
			String agentId) {
		super();
		this.activationCode = activationCode;
		this.status = status;
		this.creatDate = creatDate;
		this.agentId = agentId;
	}

	public Code(Long codeId, String activationCode, String status,
			String creatDate, String actDate, String merChantId,
			String merName, String merMp, String agentId, String remarks) {
		super();
		this.codeId = codeId;
		this.activationCode = activationCode;
		this.status = status;
		this.creatDate = creatDate;
		this.actDate = actDate;
		this.merChantId = merChantId;
		this.merName = merName;
		this.merMp = merMp;
		this.agentId = agentId;
		this.remarks = remarks;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
