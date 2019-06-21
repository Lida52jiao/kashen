package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mp_income")
public class Income implements Serializable {

	private static final long serialVersionUID = -8892596013972471761L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "level")
	private String level;
	@Column(name = "first")
	private String first;
	@Column(name = "second")
	private String second;
	@Column(name = "third")
	private String third;
	@Column(name = "brushFirst")
	private String brushFirst;
	@Column(name = "brushSecond")
	private String brushSecond;
	@Column(name = "brushThird")
	private String brushThird;
	
	public Income() {
		super();
	}

	public Income(Long id, String level, String first, String second) {
		super();
		this.id = id;
		this.level = level;
		this.first = first;
		this.second = second;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getBrushFirst() {
		return brushFirst;
	}

	public void setBrushFirst(String brushFirst) {
		this.brushFirst = brushFirst;
	}

	public String getBrushSecond() {
		return brushSecond;
	}

	public void setBrushSecond(String brushSecond) {
		this.brushSecond = brushSecond;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public String getBrushThird() {
		return brushThird;
	}

	public void setBrushThird(String brushThird) {
		this.brushThird = brushThird;
	}
	
}
