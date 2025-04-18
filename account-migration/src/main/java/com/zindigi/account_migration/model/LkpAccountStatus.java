package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_ACCOUNT_STATUS database table.
 * 
 */
@Entity
@Table(name="LKP_ACCOUNT_STATUS")
@NamedQuery(name="LkpAccountStatus.findAll", query="SELECT l FROM LkpAccountStatus l")
public class LkpAccountStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ACCOUNT_STATUS_ACCOUNTSTATUSID_GENERATOR", sequenceName="LKP_ACCOUNT_STATUS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ACCOUNT_STATUS_ACCOUNTSTATUSID_GENERATOR")
	@Column(name="ACCOUNT_STATUS_ID")
	private long accountStatusId;

	@Column(name="ACCOUNT_STATUS_CODE")
	private String accountStatusCode;

	@Column(name="ACCOUNT_STATUS_DESCR")
	private String accountStatusDescr;

	@Column(name="ACCOUNT_STATUS_NAME")
	private String accountStatusName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CREDIT_ALLOWED")
	private String creditAllowed;

	@Column(name="DEBIT_ALLOWED")
	private String debitAllowed;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpAccountStatus() {
	}

	public long getAccountStatusId() {
		return this.accountStatusId;
	}

	public void setAccountStatusId(long accountStatusId) {
		this.accountStatusId = accountStatusId;
	}

	public String getAccountStatusCode() {
		return this.accountStatusCode;
	}

	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}

	public String getAccountStatusDescr() {
		return this.accountStatusDescr;
	}

	public void setAccountStatusDescr(String accountStatusDescr) {
		this.accountStatusDescr = accountStatusDescr;
	}

	public String getAccountStatusName() {
		return this.accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getCreditAllowed() {
		return this.creditAllowed;
	}

	public void setCreditAllowed(String creditAllowed) {
		this.creditAllowed = creditAllowed;
	}

	public String getDebitAllowed() {
		return this.debitAllowed;
	}

	public void setDebitAllowed(String debitAllowed) {
		this.debitAllowed = debitAllowed;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}