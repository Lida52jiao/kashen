package com.battcn.entity;


public class Credits {
	
	private Long id;
	
	private String gateId;
	
	private String gateName;
	
	private String cost;
	
	private String transaction;

	private String merchantFee;
	
	private String generationFee;
	
	private String generationFeeRepayment;

	public Credits() {
		super();
	}

	public Credits(String gateId, String gateName, String cost,
			String transaction, String merchantFee, String generationFeeRepayment) {
		super();
		this.gateId = gateId;
		this.gateName = gateName;
		this.cost = cost;
		this.transaction = transaction;
		this.merchantFee = merchantFee;
		this.generationFeeRepayment = generationFeeRepayment;
	}

	public Credits(Long id, String gateId, String gateName, String cost,
			String transaction, String merchantFee, String generationFee,
			String generationFeeRepayment) {
		super();
		this.id = id;
		this.gateId = gateId;
		this.gateName = gateName;
		this.cost = cost;
		this.transaction = transaction;
		this.merchantFee = merchantFee;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(String merchantFee) {
		this.merchantFee = merchantFee;
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

}
