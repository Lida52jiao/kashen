package com.battcn.entity;

import java.math.BigDecimal;
import java.util.List;

public class HYUserIncomCount implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5815795963665505268L;

	private List<Integer> intList;
	
	private List<BigDecimal> bigList;

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	public List<BigDecimal> getBigList() {
		return bigList;
	}

	public void setBigList(List<BigDecimal> bigList) {
		this.bigList = bigList;
	}
	
	

}
