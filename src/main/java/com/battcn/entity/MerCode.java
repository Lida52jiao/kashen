package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "t_mp_merCode")
public class MerCode implements Serializable {

	private static final long serialVersionUID = 1775227879487651846L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "merId", unique = true, nullable = false)
	private String merId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "oneMerId")
	private String oneMerId;
	@Column(name = "totalCode")
	private int totalCode;
	@Column(name = "generatedCode")
	private int generatedCode;
	@Column(name = "used")
	private int used;
	@Column(name = "notused")
	private int notused;
	@Column(name = "assign")
	private int assign;
	
	public MerCode() {
		super();
	}

	public MerCode(String merId, int totalCode, int generatedCode,
			int used, int notused) {
		super();
		this.merId = merId;
		this.totalCode = totalCode;
		this.generatedCode = generatedCode;
		this.used = used;
		this.notused = notused;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getOneMerId() {
		return oneMerId;
	}

	public void setOneMerId(String oneMerId) {
		this.oneMerId = oneMerId;
	}

	public int getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(int totalCode) {
		this.totalCode = totalCode;
	}

	public int getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(int generatedCode) {
		this.generatedCode = generatedCode;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getNotused() {
		return notused;
	}

	public void setNotused(int notused) {
		this.notused = notused;
	}

	public int getAssign() {
		return assign;
	}

	public void setAssign(int assign) {
		this.assign = assign;
	}

}
