package com.battcn.entity;

/**
 * Created by Dada on 2018/11/28.
 */
public class Totalfee {
    private String totalfee;
    private String number;

    public Totalfee(String totalfee, String number) {
        this.totalfee = totalfee;
        this.number = number;
    }

    public String getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(String totalfee) {
        this.totalfee = totalfee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
