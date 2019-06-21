package com.battcn.entity;

import java.io.Serializable;

public class Statistics implements Serializable {

	private static final long serialVersionUID = -2641853543455209982L;
	
	private double trans;
	
	private double cash; 
	
	private double institution;
	
	private double reducedAmount;

	public Statistics() {
		super();
	}

	public Statistics(double trans, double cash, double institution, double reducedAmount) {
		super();
		this.trans = trans;
		this.cash = cash;
		this.institution = institution;
		this.reducedAmount = reducedAmount;
	}

	public double getTrans() {
		return trans;
	}

	public void setTrans(double trans) {
		this.trans = trans;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getInstitution() {
		return institution;
	}

	public void setInstitution(double institution) {
		this.institution = institution;
	}

	public double getReducedAmount() {
		return reducedAmount;
	}

	public void setReducedAmount(double reducedAmount) {
		this.reducedAmount = reducedAmount;
	}

}
