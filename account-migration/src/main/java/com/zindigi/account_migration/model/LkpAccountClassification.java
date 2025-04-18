package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_ACCOUNT_CLASSIFICATION database table.
 * 
 */
@Entity
@Table(name="LKP_ACCOUNT_CLASSIFICATION")
@NamedQuery(name="LkpAccountClassification.findAll", query="SELECT l FROM LkpAccountClassification l")
public class LkpAccountClassification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ACCOUNT_CLASSIFICATION_ACCOUNTCLASSIFICATIONID_GENERATOR", sequenceName="LKP_ACCOUNT_CLASSIFICATION_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ACCOUNT_CLASSIFICATION_ACCOUNTCLASSIFICATIONID_GENERATOR")
	@Column(name="ACCOUNT_CLASSIFICATION_ID")
	private long accountClassificationId;

	@Column(name="ACCOUNT_CLASSIFICATION_CODE")
	private String accountClassificationCode;

	@Column(name="ACCOUNT_CLASSIFICATION_DESCR")
	private String accountClassificationDescr;

	@Column(name="ACCOUNT_CLASSIFICATION_NAME")
	private String accountClassificationName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpAccountLevel
	@JsonIgnore
	@OneToMany(mappedBy="lkpAccountClassification")
	private List<LkpAccountLevel> lkpAccountLevels;

	//bi-directional many-to-one association to TblCommissionProfile
	@JsonIgnore
	@OneToMany(mappedBy="lkpAccountClassification")
	private List<TblCommissionProfile> tblCommissionProfiles;

	//bi-directional many-to-one association to TblKycSetHead
	@JsonIgnore
	@OneToMany(mappedBy="lkpAccountClassification")
	private List<TblKycSetHead> tblKycSetHeads;

//	//bi-directional many-to-one association to TblPricingProfile
//	@JsonIgnore
//	@OneToMany(mappedBy="lkpAccountClassification")
//	private List<TblPricingProfile> tblPricingProfiles;

	public LkpAccountClassification() {
	}

	public long getAccountClassificationId() {
		return this.accountClassificationId;
	}

	public void setAccountClassificationId(long accountClassificationId) {
		this.accountClassificationId = accountClassificationId;
	}

	public String getAccountClassificationCode() {
		return this.accountClassificationCode;
	}

	public void setAccountClassificationCode(String accountClassificationCode) {
		this.accountClassificationCode = accountClassificationCode;
	}

	public String getAccountClassificationDescr() {
		return this.accountClassificationDescr;
	}

	public void setAccountClassificationDescr(String accountClassificationDescr) {
		this.accountClassificationDescr = accountClassificationDescr;
	}

	public String getAccountClassificationName() {
		return this.accountClassificationName;
	}

	public void setAccountClassificationName(String accountClassificationName) {
		this.accountClassificationName = accountClassificationName;
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

	public List<LkpAccountLevel> getLkpAccountLevels() {
		return this.lkpAccountLevels;
	}

	public void setLkpAccountLevels(List<LkpAccountLevel> lkpAccountLevels) {
		this.lkpAccountLevels = lkpAccountLevels;
	}

	public LkpAccountLevel addLkpAccountLevel(LkpAccountLevel lkpAccountLevel) {
		getLkpAccountLevels().add(lkpAccountLevel);
		lkpAccountLevel.setLkpAccountClassification(this);

		return lkpAccountLevel;
	}

	public LkpAccountLevel removeLkpAccountLevel(LkpAccountLevel lkpAccountLevel) {
		getLkpAccountLevels().remove(lkpAccountLevel);
		lkpAccountLevel.setLkpAccountClassification(null);

		return lkpAccountLevel;
	}

	public List<TblCommissionProfile> getTblCommissionProfiles() {
		return this.tblCommissionProfiles;
	}

	public void setTblCommissionProfiles(List<TblCommissionProfile> tblCommissionProfiles) {
		this.tblCommissionProfiles = tblCommissionProfiles;
	}

	public TblCommissionProfile addTblCommissionProfile(TblCommissionProfile tblCommissionProfile) {
		getTblCommissionProfiles().add(tblCommissionProfile);
		tblCommissionProfile.setLkpAccountClassification(this);

		return tblCommissionProfile;
	}

	public TblCommissionProfile removeTblCommissionProfile(TblCommissionProfile tblCommissionProfile) {
		getTblCommissionProfiles().remove(tblCommissionProfile);
		tblCommissionProfile.setLkpAccountClassification(null);

		return tblCommissionProfile;
	}

	public List<TblKycSetHead> getTblKycSetHeads() {
		return this.tblKycSetHeads;
	}

	public void setTblKycSetHeads(List<TblKycSetHead> tblKycSetHeads) {
		this.tblKycSetHeads = tblKycSetHeads;
	}

	public TblKycSetHead addTblKycSetHead(TblKycSetHead tblKycSetHead) {
		getTblKycSetHeads().add(tblKycSetHead);
		tblKycSetHead.setLkpAccountClassification(this);

		return tblKycSetHead;
	}

	public TblKycSetHead removeTblKycSetHead(TblKycSetHead tblKycSetHead) {
		getTblKycSetHeads().remove(tblKycSetHead);
		tblKycSetHead.setLkpAccountClassification(null);

		return tblKycSetHead;
	}

//	public List<TblPricingProfile> getTblPricingProfiles() {
//		return this.tblPricingProfiles;
//	}
//
//	public void setTblPricingProfiles(List<TblPricingProfile> tblPricingProfiles) {
//		this.tblPricingProfiles = tblPricingProfiles;
//	}
//
//	public TblPricingProfile addTblPricingProfile(TblPricingProfile tblPricingProfile) {
//		getTblPricingProfiles().add(tblPricingProfile);
//		tblPricingProfile.setLkpAccountClassification(this);
//
//		return tblPricingProfile;
//	}
//
//	public TblPricingProfile removeTblPricingProfile(TblPricingProfile tblPricingProfile) {
//		getTblPricingProfiles().remove(tblPricingProfile);
//		tblPricingProfile.setLkpAccountClassification(null);
//
//		return tblPricingProfile;
//	}

}