package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PRICING_SLAB database table.
 * 
 */
@Entity
@Table(name="TBL_PRICING_SLAB")
@NamedQuery(name="TblPricingSlab.findAll", query="SELECT t FROM TblPricingSlab t")
public class TblPricingSlab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRICING_SLAB_PRICINGSLABID_GENERATOR", sequenceName="TBL_PRICING_SLAB_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRICING_SLAB_PRICINGSLABID_GENERATOR")
	@Column(name="PRICING_SLAB_ID")
	private long pricingSlabId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="END_SLAB")
	private BigDecimal endSlab;

	@Column(name="FEE_AMOUNT")
	private BigDecimal feeAmount;

	@Column(name="FEE_CONDITION")
	private String feeCondition;

	@Column(name="FEE_PERCENTAGE")
	private BigDecimal feePercentage;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="START_SLAB")
	private BigDecimal startSlab;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpFeeType
	@ManyToOne
	@JoinColumn(name="FEE_TYPE_ID")
	private LkpFeeType lkpFeeType;

	//bi-directional many-to-one association to TblPricingProfile
	@ManyToOne
	@JoinColumn(name="PRICING_PROFILE_ID")
	private TblPricingProfile tblPricingProfile;

	public TblPricingSlab() {
	}

	public long getPricingSlabId() {
		return this.pricingSlabId;
	}

	public void setPricingSlabId(long pricingSlabId) {
		this.pricingSlabId = pricingSlabId;
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

	public BigDecimal getEndSlab() {
		return this.endSlab;
	}

	public void setEndSlab(BigDecimal endSlab) {
		this.endSlab = endSlab;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
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

	public BigDecimal getStartSlab() {
		return this.startSlab;
	}

	public void setStartSlab(BigDecimal startSlab) {
		this.startSlab = startSlab;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpFeeType getLkpFeeType() {
		return this.lkpFeeType;
	}

	public void setLkpFeeType(LkpFeeType lkpFeeType) {
		this.lkpFeeType = lkpFeeType;
	}

	public TblPricingProfile getTblPricingProfile() {
		return this.tblPricingProfile;
	}

	public void setTblPricingProfile(TblPricingProfile tblPricingProfile) {
		this.tblPricingProfile = tblPricingProfile;
	}

}