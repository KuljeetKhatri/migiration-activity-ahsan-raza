package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_ACCOUNT_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_ACCOUNT_TYPE")
@NamedQuery(name="LkpAccountType.findAll", query="SELECT l FROM LkpAccountType l")
public class LkpAccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ACCOUNT_TYPE_ACCOUNTTYPEID_GENERATOR", sequenceName="LKP_ACCOUNT_TYPE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ACCOUNT_TYPE_ACCOUNTTYPEID_GENERATOR")
	@Column(name="ACCOUNT_TYPE_ID")
	private long accountTypeId;

	@Column(name="ACCOUNT_TYPE_CODE")
	private String accountTypeCode;

	@Column(name="ACCOUNT_TYPE_DESCR")
	private String accountTypeDescr;

	@Column(name="ACCOUNT_TYPE_NAME")
	private String accountTypeName;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpAccountType() {
	}

	public long getAccountTypeId() {
		return this.accountTypeId;
	}

	public void setAccountTypeId(long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountTypeCode() {
		return this.accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public String getAccountTypeDescr() {
		return this.accountTypeDescr;
	}

	public void setAccountTypeDescr(String accountTypeDescr) {
		this.accountTypeDescr = accountTypeDescr;
	}

	public String getAccountTypeName() {
		return this.accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
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