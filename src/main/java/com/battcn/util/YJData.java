package com.battcn.util;

import java.io.Serializable;

public class YJData implements Serializable{

	private String repayPlanId;
	
	private Boolean status;

	public String getRepayPlanId() {
		return repayPlanId;
	}

	public void setRepayPlanId(String repayPlanId) {
		this.repayPlanId = repayPlanId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
