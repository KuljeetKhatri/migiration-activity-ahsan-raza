package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_BANK database table.
 * 
 */
@Entity
@Table(name="LKP_BANK")
@NamedQuery(name="LkpBank.findAll", query="SELECT l FROM LkpBank l")
public class LkpBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BANK_BANKID_GENERATOR", sequenceName="LKP_BANK_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BANK_BANKID_GENERATOR")
	@Column(name="BANK_ID")
	private long bankId;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BANK_DESCRIPTION")
	private String bankDescription;

	@Column(name="BANK_IMD")
	private String bankImd;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="BANK_WEBSITE")
	private String bankWebsite;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="ORGANIZATION_ID")
	private BigDecimal organizationId;

	private BigDecimal updateindex;

	public LkpBank() {
	}

	public long getBankId() {
		return this.bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankDescription() {
		return this.bankDescription;
	}

	public void setBankDescription(String bankDescription) {
		this.bankDescription = bankDescription;
	}

	public String getBankImd() {
		return this.bankImd;
	}

	public void setBankImd(String bankImd) {
		this.bankImd = bankImd;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankWebsite() {
		return this.bankWebsite;
	}

	public void setBankWebsite(String bankWebsite) {
		this.bankWebsite = bankWebsite;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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

	public BigDecimal getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(BigDecimal organizationId) {
		this.organizationId = organizationId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}