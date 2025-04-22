package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PRICING_INCOME_SHARING database table.
 * 
 */
@Entity
@Table(name="TBL_PRICING_INCOME_SHARING")
@NamedQuery(name="TblPricingIncomeSharing.findAll", query="SELECT t FROM TblPricingIncomeSharing t")

public class TblPricingIncomeSharing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRICING_INCOME_SHARING_PRICINGINCOMESHARINGID_GENERATOR", sequenceName="TBL_PRICING_INCOME_SHARING_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRICING_INCOME_SHARING_PRICINGINCOMESHARINGID_GENERATOR")
	@Column(name="PRICING_INCOME_SHARING_ID")
	private long pricingIncomeSharingId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SHARING_DETAILS")
	private String sharingDetails;

	@Column(name="SHARING_PERCENTAGE")
	private BigDecimal sharingPercentage;

	@Column(name="TAX_STATUS")
	private String taxStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="INCOME_GL_ACCOUNT_ID")
	private TblAccountModel tblAccountModel1;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="WHT_GL_ACCOUNT_ID")
	private TblAccountModel tblAccountModel2;

	//bi-directional many-to-one association to TblPricingProfile
	@ManyToOne
	@JoinColumn(name="PRICING_PROFILE_ID")
	private TblPricingProfile tblPricingProfile;

	public TblPricingIncomeSharing() {
	}

	public long getPricingIncomeSharingId() {
		return this.pricingIncomeSharingId;
	}

	public void setPricingIncomeSharingId(long pricingIncomeSharingId) {
		this.pricingIncomeSharingId = pricingIncomeSharingId;
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

	public String getSharingDetails() {
		return this.sharingDetails;
	}

	public void setSharingDetails(String sharingDetails) {
		this.sharingDetails = sharingDetails;
	}

	public BigDecimal getSharingPercentage() {
		return this.sharingPercentage;
	}

	public void setSharingPercentage(BigDecimal sharingPercentage) {
		this.sharingPercentage = sharingPercentage;
	}

	public String getTaxStatus() {
		return this.taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblAccountModel getTblAccountModel1() {
		return this.tblAccountModel1;
	}

	public void setTblAccountModel1(TblAccountModel tblAccountModel1) {
		this.tblAccountModel1 = tblAccountModel1;
	}

	public TblAccountModel getTblAccountModel2() {
		return this.tblAccountModel2;
	}

	public void setTblAccountModel2(TblAccountModel tblAccountModel2) {
		this.tblAccountModel2 = tblAccountModel2;
	}

	public TblPricingProfile getTblPricingProfile() {
		return this.tblPricingProfile;
	}

	public void setTblPricingProfile(TblPricingProfile tblPricingProfile) {
		this.tblPricingProfile = tblPricingProfile;
	}

}