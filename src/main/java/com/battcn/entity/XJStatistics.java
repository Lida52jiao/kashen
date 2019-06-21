package com.battcn.entity;

public class XJStatistics {
	
	private double total;
	
	private double fee;
	
	private double balanceProfit;
	
	private double profit;
	
	private String cost;

	public XJStatistics() {
		super();
	}

	public XJStatistics(double total, double fee, double balanceProfit,
			double profit, String cost) {
		super();
		this.total = total;
		this.fee = fee;
		this.balanceProfit = balanceProfit;
		this.profit = profit;
		this.cost = cost;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getBalanceProfit() {
		return balanceProfit;
	}

	public void setBalanceProfit(double balanceProfit) {
		this.balanceProfit = balanceProfit;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
}
