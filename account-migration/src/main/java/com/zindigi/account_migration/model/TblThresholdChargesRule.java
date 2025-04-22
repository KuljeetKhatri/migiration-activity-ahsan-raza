package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpChannel;
import com.mfs.commonservice.model.LkpSegment;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_THRESHOLD_CHARGES_RULE database table.
 * 
 */
@Entity
@Table(name="TBL_THRESHOLD_CHARGES_RULE")
@NamedQuery(name="TblThresholdChargesRule.findAll", query="SELECT t FROM TblThresholdChargesRule t")
public class TblThresholdChargesRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_THRESHOLD_CHARGES_RULE_THRESHOLDCHARGESRULEID_GENERATOR", sequenceName="TBL_THRESHOLD_CHARGES_RULE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_THRESHOLD_CHARGES_RULE_THRESHOLDCHARGESRULEID_GENERATOR")
	@Column(name="THRESHOLD_CHARGES_RULE_ID")
	private long thresholdChargesRuleId;

	@Column(name="CHARGES_FIXED_AMOUNT")
	private BigDecimal chargesFixedAmount;

	@Column(name="CHARGES_PERCENTAGE")
	private BigDecimal chargesPercentage;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FED_INCL_EXCL")
	private String fedInclExcl;

	private String frequency;

	@Column(name="HIGHER_LOWER")
	private String higherLower;

	@Column(name="INCLUSIVE_EXCLUSIVE")
	private String inclusiveExclusive;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_DEFAULT")
	private String isDefault;

	@Column(name="IS_DELETED")
	private BigDecimal isDeleted;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="THRESHOLD_AMOUNT")
	private BigDecimal thresholdAmount;

	@Column(name="THRESHOLD_VELOCITY")
	private BigDecimal thresholdVelocity;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblThresholdCharge
	@OneToMany(mappedBy="tblThresholdChargesRule")
	private List<TblThresholdCharge> tblThresholdCharges;

	//bi-directional many-to-one association to LkpAgentNetwork
	@ManyToOne
	@JoinColumn(name="AGENT_NETWORK_ID")
	private LkpAgentNetwork lkpAgentNetwork;

	//bi-directional many-to-one association to LkpChannel
	@ManyToOne
	@JoinColumn(name="CHANNEL_ID")
	private LkpChannel lkpChannel;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	@JsonIgnore
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblThresholdChargesRule() {
	}

	public long getThresholdChargesRuleId() {
		return this.thresholdChargesRuleId;
	}

	public void setThresholdChargesRuleId(long thresholdChargesRuleId) {
		this.thresholdChargesRuleId = thresholdChargesRuleId;
	}

	public BigDecimal getChargesFixedAmount() {
		return this.chargesFixedAmount;
	}

	public void setChargesFixedAmount(BigDecimal chargesFixedAmount) {
		this.chargesFixedAmount = chargesFixedAmount;
	}

	public BigDecimal getChargesPercentage() {
		return this.chargesPercentage;
	}

	public void setChargesPercentage(BigDecimal chargesPercentage) {
		this.chargesPercentage = chargesPercentage;
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

	public String getFedInclExcl() {
		return this.fedInclExcl;
	}

	public void setFedInclExcl(String fedInclExcl) {
		this.fedInclExcl = fedInclExcl;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getHigherLower() {
		return this.higherLower;
	}

	public void setHigherLower(String higherLower) {
		this.higherLower = higherLower;
	}

	public String getInclusiveExclusive() {
		return this.inclusiveExclusive;
	}

	public void setInclusiveExclusive(String inclusiveExclusive) {
		this.inclusiveExclusive = inclusiveExclusive;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public BigDecimal getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(BigDecimal isDeleted) {
		this.isDeleted = isDeleted;
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

	public BigDecimal getThresholdAmount() {
		return this.thresholdAmount;
	}

	public void setThresholdAmount(BigDecimal thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public BigDecimal getThresholdVelocity() {
		return this.thresholdVelocity;
	}

	public void setThresholdVelocity(BigDecimal thresholdVelocity) {
		this.thresholdVelocity = thresholdVelocity;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblThresholdCharge> getTblThresholdCharges() {
		return this.tblThresholdCharges;
	}

	public void setTblThresholdCharges(List<TblThresholdCharge> tblThresholdCharges) {
		this.tblThresholdCharges = tblThresholdCharges;
	}

	public TblThresholdCharge addTblThresholdCharge(TblThresholdCharge tblThresholdCharge) {
		getTblThresholdCharges().add(tblThresholdCharge);
		tblThresholdCharge.setTblThresholdChargesRule(this);

		return tblThresholdCharge;
	}

	public TblThresholdCharge removeTblThresholdCharge(TblThresholdCharge tblThresholdCharge) {
		getTblThresholdCharges().remove(tblThresholdCharge);
		tblThresholdCharge.setTblThresholdChargesRule(null);

		return tblThresholdCharge;
	}

	public LkpAgentNetwork getLkpAgentNetwork() {
		return this.lkpAgentNetwork;
	}

	public void setLkpAgentNetwork(LkpAgentNetwork lkpAgentNetwork) {
		this.lkpAgentNetwork = lkpAgentNetwork;
	}

	public LkpChannel getLkpChannel() {
		return this.lkpChannel;
	}

	public void setLkpChannel(LkpChannel lkpChannel) {
		this.lkpChannel = lkpChannel;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}