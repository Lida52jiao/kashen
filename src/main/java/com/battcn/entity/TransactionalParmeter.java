package com.battcn.entity;

import java.math.BigDecimal;

/**
 * Created by bin on 2017/12/3.
 */
public class TransactionalParmeter implements java.io.Serializable{
    
	private static final long serialVersionUID = 254674726516870975L;
	private BigDecimal day;
    private BigDecimal month;
    private BigDecimal year;

    public BigDecimal getDay() {
        return day;
    }

    public void setDay(BigDecimal day) {
        this.day = day;
    }

    public BigDecimal getMonth() {
        return month;
    }

    public void setMonth(BigDecimal month) {
        this.month = month;
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }
}
