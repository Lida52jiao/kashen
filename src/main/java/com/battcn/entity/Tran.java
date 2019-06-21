package com.battcn.entity;

import java.util.Date;
import java.util.List;

public class Tran{

	private Long id;
	private String merId;
	private String agentName;
	private String agentId;
	private String merChantId;
	private String floorNumber;
	private String generationFee;
	private String generationFeeRepayment;
	private String fee0;
	private String d0fee;
	private String fee1;
	private String d1fee;
	private Date creatDate;
	private String updateName;
	private Date updateDate;
	private String appId;
	private String appName;
	private String remarks;
	private List<String> list;
	private String agentStatus;
	public Tran() {
		super();
	}
	
	public Tran(String merId, String merChantId, String floorNumber,
			String generationFee, String generationFeeRepayment,
			Date creatDate, String remarks) {
		super();
		this.merId = merId;
		this.merChantId = merChantId;
		this.floorNumber = floorNumber;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public Tran(Long id, String merId, String merChantId,
			String floorNumber, String generationFee,
			String generationFeeRepayment, Date creatDate, String remarks) {
		super();
		this.id = id;
		this.merId = merId;
		this.merChantId = merChantId;
		this.floorNumber = floorNumber;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public Tran(Long id, String merId, String agentId,
			String merChantId, String floorNumber, String generationFee,
			String generationFeeRepayment, Date creatDate, String remarks) {
		super();
		this.id = id;
		this.merId = merId;
		this.agentId = agentId;
		this.merChantId = merChantId;
		this.floorNumber = floorNumber;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getGenerationFee() {
		return generationFee;
	}

	public void setGenerationFee(String generationFee) {
		this.generationFee = generationFee;
	}

	public String getGenerationFeeRepayment() {
		return generationFeeRepayment;
	}

	public void setGenerationFeeRepayment(String generationFeeRepayment) {
		this.generationFeeRepayment = generationFeeRepayment;
	}

	public String getFee0() {
		return fee0;
	}

	public void setFee0(String fee0) {
		this.fee0 = fee0;
	}

	public String getD0fee() {
		return d0fee;
	}

	public void setD0fee(String d0fee) {
		this.d0fee = d0fee;
	}

	public String getFee1() {
		return fee1;
	}

	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}

	public String getD1fee() {
		return d1fee;
	}

	public void setD1fee(String d1fee) {
		this.d1fee = d1fee;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}
	
	
}
