package com.battcn.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_record")
public class Record implements Serializable {

	private static final long serialVersionUID = -4591926873434703389L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "institutionId")
	private String institutionId;
	@Column(name = "appId")
	private String appId;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "amount")
	private Long amount;
	@Column(name = "orderNo")
	private String orderNo;
	@Column(name = "type")
	private String type;
	@Column(name = "typeName")
	private String typeName;
	@Column(name = "createTime")
	private String createTime;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "remark")
	private String remark;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
