package com.battcn.entity;

public class ReturnCount implements java.io.Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6391071160819397195L;
	private Long totalAmount;//总交易金额
	 private Long totalArrivalAmount;//总到账金额
	 private Long totalPayFee;//总支付手续费
	 private Long totalRepFee;//总还款手续费
	 private Long totalInstitutionFee;//平台分润
	 private Long totalNoInstitutionFee;//平台以下分润
	 private Double payRate;//平台底价
	 private Double totalCost;//平台成本
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getTotalArrivalAmount() {
		return totalArrivalAmount;
	}
	public void setTotalArrivalAmount(Long totalArrivalAmount) {
		this.totalArrivalAmount = totalArrivalAmount;
	}
	public Long getTotalPayFee() {
		return totalPayFee;
	}
	public void setTotalPayFee(Long totalPayFee) {
		this.totalPayFee = totalPayFee;
	}
	public Long getTotalRepFee() {
		return totalRepFee;
	}
	public void setTotalRepFee(Long totalRepFee) {
		this.totalRepFee = totalRepFee;
	}
	public Long getTotalInstitutionFee() {
		return totalInstitutionFee;
	}
	public void setTotalInstitutionFee(Long totalInstitutionFee) {
		this.totalInstitutionFee = totalInstitutionFee;
	}
	public Long getTotalNoInstitutionFee() {
		return totalNoInstitutionFee;
	}
	public void setTotalNoInstitutionFee(Long totalNoInstitutionFee) {
		this.totalNoInstitutionFee = totalNoInstitutionFee;
	}
	public Double getPayRate() {
		return payRate;
	}
	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	 
}
