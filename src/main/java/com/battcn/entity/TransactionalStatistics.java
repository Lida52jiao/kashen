package com.battcn.entity;

import java.math.BigDecimal;

/**
 * Created by bin on 2017/12/3.
 */
public class TransactionalStatistics  implements java.io.Serializable{
    
	private static final long serialVersionUID = -9191139840997756694L;
	private String dateStr;
    private BigDecimal totalAmount;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
