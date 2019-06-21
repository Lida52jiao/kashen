package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "t_mp_xj")
public class XJ implements Serializable {

	private static final long serialVersionUID = 3058160175703496442L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "fee0")
	private String fee0;
	@Column(name = "d0fee")
	private String d0fee;
	@Column(name = "fee1")
	private String fee1;
	@Column(name = "d1fee")
	private String d1fee;
	public XJ() {
		super();
	}

	public XJ(String fee0, String d0fee) {
		super();
		this.fee0 = fee0;
		this.d0fee = d0fee;
	}

	public XJ(Long id, String fee0, String d0fee) {
		super();
		this.id = id;
		this.fee0 = fee0;
		this.d0fee = d0fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFee1() {
		return fee1;
	}

	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}

	public String getD1fee() {
		return d1fee;
	}

	public void setD1fee(String d1fee) {
		this.d1fee = d1fee;
	}
	
}
