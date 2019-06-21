package com.battcn.entity;

import java.math.BigDecimal;
import java.util.List;

public class HYUserWithdrawCount implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5815795963665505268L;

	private List<BigDecimal> userFeeList;
	
	private List<BigDecimal> userWithdrawList;

	public List<BigDecimal> getUserFeeList() {
		return userFeeList;
	}

	public void setUserFeeList(List<BigDecimal> userFeeList) {
		this.userFeeList = userFeeList;
	}

	public List<BigDecimal> getUserWithdrawList() {
		return userWithdrawList;
	}

	public void setUserWithdrawList(List<BigDecimal> userWithdrawList) {
		this.userWithdrawList = userWithdrawList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
