package com.battcn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mp_agent")
public class Agent implements Serializable {

	private static final long serialVersionUID = 7953513582515383004L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merId")
	private String merId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "accountNumber")
	private String accountNumber;
	@Column(name = "password")
	private String password;
	@Column(name = "mailbox")
	private String mailbox;
	@Column(name = "oneMerId")
	private String oneMerId;
	@Column(name = "totalCode")
	private String totalCode;
	@Column(name = "generatedCode")
	private String generatedCode;
	@Column(name = "used")
	private String used;
	@Column(name = "notused")
	private String notused;
	@Column(name = "assign")
	private String assign;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "merChantId")
	private String merChantId;


	public Agent() {
		super();
	}

	public Agent(String merName, String merMp,
			String accountNumber, String password, String mailbox,
			String oneMerId, String creatDate) {
		super();
		this.merName = merName;
		this.merMp = merMp;
		this.accountNumber = accountNumber;
		this.password = password;
		this.mailbox = mailbox;
		this.oneMerId = oneMerId;
		this.creatDate = creatDate;
	}

	public Agent(Long id, String merId, String merName, String merMp,
			String accountNumber, String password, String mailbox,
			String oneMerId, String totalCode, String generatedCode,
			String used, String notused, String assign, String creatDate,
			String remarks) {
		super();
		this.id = id;
		this.merId = merId;
		this.merName = merName;
		this.merMp = merMp;
		this.accountNumber = accountNumber;
		this.password = password;
		this.mailbox = mailbox;
		this.oneMerId = oneMerId;
		this.totalCode = totalCode;
		this.generatedCode = generatedCode;
		this.used = used;
		this.notused = notused;
		this.assign = assign;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMerMp() {
		return merMp;
	}

	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getOneMerId() {
		return oneMerId;
	}

	public void setOneMerId(String oneMerId) {
		this.oneMerId = oneMerId;
	}

	public String getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getNotused() {
		return notused;
	}

	public void setNotused(String notused) {
		this.notused = notused;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((assign == null) ? 0 : assign.hashCode());
		result = prime * result
				+ ((creatDate == null) ? 0 : creatDate.hashCode());
		result = prime * result
				+ ((generatedCode == null) ? 0 : generatedCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mailbox == null) ? 0 : mailbox.hashCode());
		result = prime * result + ((merId == null) ? 0 : merId.hashCode());
		result = prime * result + ((merMp == null) ? 0 : merMp.hashCode());
		result = prime * result + ((merName == null) ? 0 : merName.hashCode());
		result = prime * result + ((notused == null) ? 0 : notused.hashCode());
		result = prime * result
				+ ((oneMerId == null) ? 0 : oneMerId.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result
				+ ((totalCode == null) ? 0 : totalCode.hashCode());
		result = prime * result + ((used == null) ? 0 : used.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (assign == null) {
			if (other.assign != null)
				return false;
		} else if (!assign.equals(other.assign))
			return false;
		if (creatDate == null) {
			if (other.creatDate != null)
				return false;
		} else if (!creatDate.equals(other.creatDate))
			return false;
		if (generatedCode == null) {
			if (other.generatedCode != null)
				return false;
		} else if (!generatedCode.equals(other.generatedCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mailbox == null) {
			if (other.mailbox != null)
				return false;
		} else if (!mailbox.equals(other.mailbox))
			return false;
		if (merId == null) {
			if (other.merId != null)
				return false;
		} else if (!merId.equals(other.merId))
			return false;
		if (merMp == null) {
			if (other.merMp != null)
				return false;
		} else if (!merMp.equals(other.merMp))
			return false;
		if (merName == null) {
			if (other.merName != null)
				return false;
		} else if (!merName.equals(other.merName))
			return false;
		if (notused == null) {
			if (other.notused != null)
				return false;
		} else if (!notused.equals(other.notused))
			return false;
		if (oneMerId == null) {
			if (other.oneMerId != null)
				return false;
		} else if (!oneMerId.equals(other.oneMerId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (totalCode == null) {
			if (other.totalCode != null)
				return false;
		} else if (!totalCode.equals(other.totalCode))
			return false;
		if (used == null) {
			if (other.used != null)
				return false;
		} else if (!used.equals(other.used))
			return false;
		return true;
	}
	
}
