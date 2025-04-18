package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PRICING_PROFILE database table.
 * 
 */
@Entity
@Table(name="TBL_PRICING_PROFILE")
@NamedQuery(name="TblPricingProfile.findAll", query="SELECT t FROM TblPricingProfile t")
public class TblPricingProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRICING_PROFILE_PRICINGPROFILEID_GENERATOR", sequenceName="TBL_PRICING_PROFILE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRICING_PROFILE_PRICINGPROFILEID_GENERATOR")
	@Column(name="PRICING_PROFILE_ID")
	private long pricingProfileId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="FEE_AMOUNT")
	private BigDecimal feeAmount;

	@Column(name="FEE_CALCULATION_TYPE")
	private String feeCalculationType;

	@Column(name="FEE_CONDITION")
	private String feeCondition;

	@Column(name="FEE_PERCENTAGE")
	private BigDecimal feePercentage;

	@Column(name="INCOME_SHARING")
	private String incomeSharing;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="NO_OF_PARTNERS")
	private BigDecimal noOfPartners;

	@Column(name="PRICING_PROFILE_NAME")
	private String pricingProfileName;

	@Column(name="SHARING_TYPE")
	private String sharingType;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TAX_CALCULATION_TYPE")
	private String taxCalculationType;

	private BigDecimal updateindex;

	private String velocity;

	//bi-directional many-to-one association to TblPricingIncomeSharing
	@JsonIgnore
	@OneToMany(mappedBy="tblPricingProfile")
	private List<TblPricingIncomeSharing> tblPricingIncomeSharings;


	//bi-directional many-to-one association to LkpAccountClassification
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ACCOUNT_CLASSIFICATION_ID")
	private LkpAccountClassification lkpAccountClassification;

	//bi-directional many-to-one association to LkpChannel
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CHANNEL_ID")
	private LkpChannel lkpChannel;

	//bi-directional many-to-one association to LkpFeeType
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="FEE_TYPE_ID")
	private LkpFeeType lkpFeeType;

	//bi-directional many-to-one association to LkpSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblAccount
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="INCOME_GL_ACCOUNT_ID")
	private TblAccount tblAccount1;

	//bi-directional many-to-one association to TblAccount
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PAYEE_GL_ACCOUNT_ID")
	private TblAccount tblAccount2;

	//bi-directional many-to-one association to TblPricingSlab
	@JsonIgnore
	@OneToMany(mappedBy="tblPricingProfile")
	private List<TblPricingSlab> tblPricingSlabs;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	public TblPricingProfile() {
	}

	public long getPricingProfileId() {
		return this.pricingProfileId;
	}

	public void setPricingProfileId(long pricingProfileId) {
		this.pricingProfileId = pricingProfileId;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getFeeCalculationType() {
		return this.feeCalculationType;
	}

	public void setFeeCalculationType(String feeCalculationType) {
		this.feeCalculationType = feeCalculationType;
	}

	public String getFeeCondition() {
		return this.feeCondition;
	}

	public void setFeeCondition(String feeCondition) {
		this.feeCondition = feeCondition;
	}

	public BigDecimal getFeePercentage() {
		return this.feePercentage;
	}

	public void setFeePercentage(BigDecimal feePercentage) {
		this.feePercentage = feePercentage;
	}

	public String getIncomeSharing() {
		return this.incomeSharing;
	}

	public void setIncomeSharing(String incomeSharing) {
		this.incomeSharing = incomeSharing;
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

	public BigDecimal getNoOfPartners() {
		return this.noOfPartners;
	}

	public void setNoOfPartners(BigDecimal noOfPartners) {
		this.noOfPartners = noOfPartners;
	}

	public String getPricingProfileName() {
		return this.pricingProfileName;
	}

	public void setPricingProfileName(String pricingProfileName) {
		this.pricingProfileName = pricingProfileName;
	}

	public String getSharingType() {
		return this.sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTaxCalculationType() {
		return this.taxCalculationType;
	}

	public void setTaxCalculationType(String taxCalculationType) {
		this.taxCalculationType = taxCalculationType;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getVelocity() {
		return this.velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

	public List<TblPricingIncomeSharing> getTblPricingIncomeSharings() {
		return this.tblPricingIncomeSharings;
	}

	public void setTblPricingIncomeSharings(List<TblPricingIncomeSharing> tblPricingIncomeSharings) {
		this.tblPricingIncomeSharings = tblPricingIncomeSharings;
	}

	public TblPricingIncomeSharing addTblPricingIncomeSharing(TblPricingIncomeSharing tblPricingIncomeSharing) {
		getTblPricingIncomeSharings().add(tblPricingIncomeSharing);
		tblPricingIncomeSharing.setTblPricingProfile(this);

		return tblPricingIncomeSharing;
	}

	public TblPricingIncomeSharing removeTblPricingIncomeSharing(TblPricingIncomeSharing tblPricingIncomeSharing) {
		getTblPricingIncomeSharings().remove(tblPricingIncomeSharing);
		tblPricingIncomeSharing.setTblPricingProfile(null);

		return tblPricingIncomeSharing;
	}


	public LkpAccountClassification getLkpAccountClassification() {
		return this.lkpAccountClassification;
	}

	public void setLkpAccountClassification(LkpAccountClassification lkpAccountClassification) {
		this.lkpAccountClassification = lkpAccountClassification;
	}

	public LkpChannel getLkpChannel() {
		return this.lkpChannel;
	}

	public void setLkpChannel(LkpChannel lkpChannel) {
		this.lkpChannel = lkpChannel;
	}

	public LkpFeeType getLkpFeeType() {
		return this.lkpFeeType;
	}

	public void setLkpFeeType(LkpFeeType lkpFeeType) {
		this.lkpFeeType = lkpFeeType;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblAccount getTblAccount1() {
		return this.tblAccount1;
	}

	public void setTblAccount1(TblAccount tblAccount1) {
		this.tblAccount1 = tblAccount1;
	}

	public TblAccount getTblAccount2() {
		return this.tblAccount2;
	}

	public void setTblAccount2(TblAccount tblAccount2) {
		this.tblAccount2 = tblAccount2;
	}

	public List<TblPricingSlab> getTblPricingSlabs() {
		return this.tblPricingSlabs;
	}

	public void setTblPricingSlabs(List<TblPricingSlab> tblPricingSlabs) {
		this.tblPricingSlabs = tblPricingSlabs;
	}

	public TblPricingSlab addTblPricingSlab(TblPricingSlab tblPricingSlab) {
		getTblPricingSlabs().add(tblPricingSlab);
		tblPricingSlab.setTblPricingProfile(this);
		return tblPricingSlab;
	}

	public TblPricingSlab removeTblPricingSlab(TblPricingSlab tblPricingSlab) {
		getTblPricingSlabs().remove(tblPricingSlab);
		tblPricingSlab.setTblPricingProfile(null);
		return tblPricingSlab;
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
}