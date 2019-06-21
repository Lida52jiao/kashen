package com.battcn.entity;


public class XJWithdrawals {
	
	private Long id;
	
	private String generationFee;
	
	private String generationFeeRepayment;
	
	private String fee0;

	private String d0fee;

	public XJWithdrawals() {
		super();
	}
	
	public XJWithdrawals(Long id, String generationFee,
			String generationFeeRepayment, String fee0, String d0fee) {
		super();
		this.id = id;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.fee0 = fee0;
		this.d0fee = d0fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
