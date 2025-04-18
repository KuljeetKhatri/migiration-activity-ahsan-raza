package com.zindigi.account_migration.model;


import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_ACCOUNT_LEVEL database table.
 * 
 */
@Entity
@Table(name="LKP_ACCOUNT_LEVEL")
@NamedQuery(name="LkpAccountLevel.findAll", query="SELECT l FROM LkpAccountLevel l")
public class LkpAccountLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ACCOUNT_LEVEL_ACCOUNTLEVELID_GENERATOR", sequenceName="LKP_ACCOUNT_LEVEL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ACCOUNT_LEVEL_ACCOUNTLEVELID_GENERATOR")
	@Column(name="ACCOUNT_LEVEL_ID")
	private long accountLevelId;

	@Column(name="ACCOUNT_LEVEL_CODE")
	private String accountLevelCode;

	@Column(name="ACCOUNT_LEVEL_DESCR")
	private String accountLevelDescr;

	@Column(name="ACCOUNT_LEVEL_NAME")
	private String accountLevelName;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DAILY_AMT_LIMIT_CR")
	private BigDecimal dailyAmtLimitCr;

	@Column(name="DAILY_AMT_LIMIT_DR")
	private BigDecimal dailyAmtLimitDr;

	@Column(name="DAILY_TRANS_LIMIT_CR")
	private BigDecimal dailyTransLimitCr;

	@Column(name="DAILY_TRANS_LIMIT_DR")
	private BigDecimal dailyTransLimitDr;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAX_AMT_LIMIT")
	private BigDecimal maxAmtLimit;

	@Column(name="MAX_AMT_PER_TXN")
	private BigDecimal maxAmtPerTxn;

	@Column(name="MONTHLY_AMT_LIMIT_CR")
	private BigDecimal monthlyAmtLimitCr;

	@Column(name="MONTHLY_AMT_LIMIT_DR")
	private BigDecimal monthlyAmtLimitDr;

	@Column(name="MONTHLY_TRANS_LIMIT_CR")
	private BigDecimal monthlyTransLimitCr;

	@Column(name="MONTHLY_TRANS_LIMIT_DR")
	private BigDecimal monthlyTransLimitDr;

	private BigDecimal updateindex;

	@Column(name="YEARLY_AMT_LIMIT_CR")
	private BigDecimal yearlyAmtLimitCr;

	@Column(name="YEARLY_AMT_LIMIT_DR")
	private BigDecimal yearlyAmtLimitDr;

	@Column(name="YEARLY_TRANS_LIMIT_CR")
	private BigDecimal yearlyTransLimitCr;

	@Column(name="YEARLY_TRANS_LIMIT_DR")
	private BigDecimal yearlyTransLimitDr;

	//bi-directional many-to-one association to LkpAccountClassification
	@ManyToOne
	@JoinColumn(name="ACCOUNT_CLASSIFICATION_ID")
	private LkpAccountClassification lkpAccountClassification;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

//	@JsonIgnore
	//bi-directional many-to-one association to TblKycSetHead
	@ManyToOne
	@JoinColumn(name="KYC_SET_HEAD_ID")
	private TblKycSetHead tblKycSetHead;

	@Transient
	private String createdBy;
	@Transient
	private String updatedBy;

	@Transient
	private long totalRecordCount;

	public LkpAccountLevel() {
	}

	public long getAccountLevelId() {
		return this.accountLevelId;
	}

	public void setAccountLevelId(long accountLevelId) {
		this.accountLevelId = accountLevelId;
	}

	public String getAccountLevelCode() {
		return this.accountLevelCode;
	}

	public void setAccountLevelCode(String accountLevelCode) {
		this.accountLevelCode = accountLevelCode;
	}

	public String getAccountLevelDescr() {
		return this.accountLevelDescr;
	}

	public void setAccountLevelDescr(String accountLevelDescr) {
		this.accountLevelDescr = accountLevelDescr;
	}

	public String getAccountLevelName() {
		return this.accountLevelName;
	}

	public void setAccountLevelName(String accountLevelName) {
		this.accountLevelName = accountLevelName;
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

	public BigDecimal getDailyAmtLimitCr() {
		return this.dailyAmtLimitCr;
	}

	public void setDailyAmtLimitCr(BigDecimal dailyAmtLimitCr) {
		this.dailyAmtLimitCr = dailyAmtLimitCr;
	}

	public BigDecimal getDailyAmtLimitDr() {
		return this.dailyAmtLimitDr;
	}

	public void setDailyAmtLimitDr(BigDecimal dailyAmtLimitDr) {
		this.dailyAmtLimitDr = dailyAmtLimitDr;
	}

	public BigDecimal getDailyTransLimitCr() {
		return this.dailyTransLimitCr;
	}

	public void setDailyTransLimitCr(BigDecimal dailyTransLimitCr) {
		this.dailyTransLimitCr = dailyTransLimitCr;
	}

	public BigDecimal getDailyTransLimitDr() {
		return this.dailyTransLimitDr;
	}

	public void setDailyTransLimitDr(BigDecimal dailyTransLimitDr) {
		this.dailyTransLimitDr = dailyTransLimitDr;
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

	public BigDecimal getMaxAmtLimit() {
		return this.maxAmtLimit;
	}

	public void setMaxAmtLimit(BigDecimal maxAmtLimit) {
		this.maxAmtLimit = maxAmtLimit;
	}

	public BigDecimal getMaxAmtPerTxn() {
		return this.maxAmtPerTxn;
	}

	public void setMaxAmtPerTxn(BigDecimal maxAmtPerTxn) {
		this.maxAmtPerTxn = maxAmtPerTxn;
	}

	public BigDecimal getMonthlyAmtLimitCr() {
		return this.monthlyAmtLimitCr;
	}

	public void setMonthlyAmtLimitCr(BigDecimal monthlyAmtLimitCr) {
		this.monthlyAmtLimitCr = monthlyAmtLimitCr;
	}

	public BigDecimal getMonthlyAmtLimitDr() {
		return this.monthlyAmtLimitDr;
	}

	public void setMonthlyAmtLimitDr(BigDecimal monthlyAmtLimitDr) {
		this.monthlyAmtLimitDr = monthlyAmtLimitDr;
	}

	public BigDecimal getMonthlyTransLimitCr() {
		return this.monthlyTransLimitCr;
	}

	public void setMonthlyTransLimitCr(BigDecimal monthlyTransLimitCr) {
		this.monthlyTransLimitCr = monthlyTransLimitCr;
	}

	public BigDecimal getMonthlyTransLimitDr() {
		return this.monthlyTransLimitDr;
	}

	public void setMonthlyTransLimitDr(BigDecimal monthlyTransLimitDr) {
		this.monthlyTransLimitDr = monthlyTransLimitDr;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public BigDecimal getYearlyAmtLimitCr() {
		return this.yearlyAmtLimitCr;
	}

	public void setYearlyAmtLimitCr(BigDecimal yearlyAmtLimitCr) {
		this.yearlyAmtLimitCr = yearlyAmtLimitCr;
	}

	public BigDecimal getYearlyAmtLimitDr() {
		return this.yearlyAmtLimitDr;
	}

	public void setYearlyAmtLimitDr(BigDecimal yearlyAmtLimitDr) {
		this.yearlyAmtLimitDr = yearlyAmtLimitDr;
	}

	public BigDecimal getYearlyTransLimitCr() {
		return this.yearlyTransLimitCr;
	}

	public void setYearlyTransLimitCr(BigDecimal yearlyTransLimitCr) {
		this.yearlyTransLimitCr = yearlyTransLimitCr;
	}

	public BigDecimal getYearlyTransLimitDr() {
		return this.yearlyTransLimitDr;
	}

	public void setYearlyTransLimitDr(BigDecimal yearlyTransLimitDr) {
		this.yearlyTransLimitDr = yearlyTransLimitDr;
	}

	public LkpAccountClassification getLkpAccountClassification() {
		return this.lkpAccountClassification;
	}

	public void setLkpAccountClassification(LkpAccountClassification lkpAccountClassification) {
		this.lkpAccountClassification = lkpAccountClassification;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblKycSetHead getTblKycSetHead() {
		return this.tblKycSetHead;
	}

	public void setTblKycSetHead(TblKycSetHead tblKycSetHead) {
		this.tblKycSetHead = tblKycSetHead;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
}