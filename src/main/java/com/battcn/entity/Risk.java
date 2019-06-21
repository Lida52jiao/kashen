package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mp_risk")
public class Risk implements Serializable {

	private static final long serialVersionUID = -9091064925202389483L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "withdrawalsStarttime")
	private String withdrawalsStarttime;
	@Column(name = "withdrawalsFinishtime")
	private String withdrawalsFinishtime;
	@Column(name = "transactionStarttime")
	private String transactionStarttime;
	@Column(name = "transactionFinishtime")
	private String transactionFinishtime;
	@Column(name = "submitplanStarttime")
	private String submitplanStarttime;
	@Column(name = "submitplanFinishtime")
	private String submitplanFinishtime;
	@Column(name = "maximumRepayment")
	private String maximumRepayment;
	@Column(name = "averageRepayment")
	private String averageRepayment;
	@Column(name = "consumptionAmount")
	private String consumptionAmount;
	@Column(name = "withdrawalAmount")
	private String withdrawalAmount;
	@Column(name = "minimumAmount")
	private String minimumAmount;
	@Column(name = "commissionFee")
	private String commissionFee;
	@Column(name = "number")
	private String number;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "remarks")
	private String remarks;
	
	public Risk() {
		super();
	}

	public Risk(String agentId, String withdrawalsStarttime,
			String withdrawalsFinishtime, String transactionStarttime,
			String transactionFinishtime, String submitplanStarttime,
			String submitplanFinishtime, String maximumRepayment,
			String averageRepayment, String consumptionAmount,
			String withdrawalAmount, String minimumAmount,
			String commissionFee, String number, String creatDate,
			String remarks) {
		super();
		this.agentId = agentId;
		this.withdrawalsStarttime = withdrawalsStarttime;
		this.withdrawalsFinishtime = withdrawalsFinishtime;
		this.transactionStarttime = transactionStarttime;
		this.transactionFinishtime = transactionFinishtime;
		this.submitplanStarttime = submitplanStarttime;
		this.submitplanFinishtime = submitplanFinishtime;
		this.maximumRepayment = maximumRepayment;
		this.averageRepayment = averageRepayment;
		this.consumptionAmount = consumptionAmount;
		this.withdrawalAmount = withdrawalAmount;
		this.minimumAmount = minimumAmount;
		this.commissionFee = commissionFee;
		this.number = number;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public Risk(Long id, String agentId, String withdrawalsStarttime,
			String withdrawalsFinishtime, String transactionStarttime,
			String transactionFinishtime, String submitplanStarttime,
			String submitplanFinishtime, String maximumRepayment,
			String averageRepayment, String consumptionAmount,
			String withdrawalAmount, String minimumAmount,
			String commissionFee, String number, String creatDate,
			String remarks) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.withdrawalsStarttime = withdrawalsStarttime;
		this.withdrawalsFinishtime = withdrawalsFinishtime;
		this.transactionStarttime = transactionStarttime;
		this.transactionFinishtime = transactionFinishtime;
		this.submitplanStarttime = submitplanStarttime;
		this.submitplanFinishtime = submitplanFinishtime;
		this.maximumRepayment = maximumRepayment;
		this.averageRepayment = averageRepayment;
		this.consumptionAmount = consumptionAmount;
		this.withdrawalAmount = withdrawalAmount;
		this.minimumAmount = minimumAmount;
		this.commissionFee = commissionFee;
		this.number = number;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getWithdrawalsStarttime() {
		return withdrawalsStarttime;
	}

	public void setWithdrawalsStarttime(String withdrawalsStarttime) {
		this.withdrawalsStarttime = withdrawalsStarttime;
	}

	public String getWithdrawalsFinishtime() {
		return withdrawalsFinishtime;
	}

	public void setWithdrawalsFinishtime(String withdrawalsFinishtime) {
		this.withdrawalsFinishtime = withdrawalsFinishtime;
	}

	public String getTransactionStarttime() {
		return transactionStarttime;
	}

	public void setTransactionStarttime(String transactionStarttime) {
		this.transactionStarttime = transactionStarttime;
	}

	public String getTransactionFinishtime() {
		return transactionFinishtime;
	}

	public void setTransactionFinishtime(String transactionFinishtime) {
		this.transactionFinishtime = transactionFinishtime;
	}

	public String getSubmitplanStarttime() {
		return submitplanStarttime;
	}

	public void setSubmitplanStarttime(String submitplanStarttime) {
		this.submitplanStarttime = submitplanStarttime;
	}

	public String getSubmitplanFinishtime() {
		return submitplanFinishtime;
	}

	public void setSubmitplanFinishtime(String submitplanFinishtime) {
		this.submitplanFinishtime = submitplanFinishtime;
	}

	public String getMaximumRepayment() {
		return maximumRepayment;
	}

	public void setMaximumRepayment(String maximumRepayment) {
		this.maximumRepayment = maximumRepayment;
	}

	public String getAverageRepayment() {
		return averageRepayment;
	}

	public void setAverageRepayment(String averageRepayment) {
		this.averageRepayment = averageRepayment;
	}

	public String getConsumptionAmount() {
		return consumptionAmount;
	}

	public void setConsumptionAmount(String consumptionAmount) {
		this.consumptionAmount = consumptionAmount;
	}

	public String getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(String withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(String minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getCommissionFee() {
		return commissionFee;
	}

	public void setCommissionFee(String commissionFee) {
		this.commissionFee = commissionFee;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
