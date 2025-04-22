package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpCity;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_BRANCH database table.
 * 
 */
@Entity
@Table(name="LKP_BRANCH")
@NamedQuery(name="LkpBranch.findAll", query="SELECT l FROM LkpBranch l")
public class LkpBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BRANCH_BRANCHID_GENERATOR", sequenceName="LKP_BRANCH_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BRANCH_BRANCHID_GENERATOR")
	@Column(name="BRANCH_ID")
	private Long branchId;

	@Column(name="BRANCH_ADDRESS")
	private String branchAddress;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="BRANCH_DESCR")
	private String branchDescription;

	@Column(name = "COA_CODE")
	private String coaCode;

	public String getCoaCode() {
		return coaCode;
	}

	public void setCoaCode(String coaCode) {
		this.coaCode = coaCode;
	}

	@Column(name="BRANCH_NAME")
	private String branchName;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_PRINCIPAL")
	private String isPrincipal;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBank
	@ManyToOne
	@JoinColumn(name="BANK_ID")
	private LkpBank lkpBank;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;


	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}
	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}


	public LkpBranch() {
	}

	public Long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchDescription() {
		return this.branchDescription;
	}

	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public String getIsPrincipal() {
		return this.isPrincipal;
	}

	public void setIsPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
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

	public LkpBank getLkpBank() {
		return this.lkpBank;
	}

	public void setLkpBank(LkpBank lkpBank) {
		this.lkpBank = lkpBank;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

}