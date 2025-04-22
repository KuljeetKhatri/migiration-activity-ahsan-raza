package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpChannel;
import com.mfs.commonservice.model.LkpSegment;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_CHARGES_CONFIG_RULE database table.
 * 
 */
@Entity
@Table(name="TBL_CHARGES_CONFIG_RULE")
@NamedQuery(name="TblChargesConfigRule.findAll", query="SELECT t FROM TblChargesConfigRule t")
public class TblChargesConfigRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_CHARGES_CONFIG_RULE_CHARGESCONFIGRULEID_GENERATOR", sequenceName="TBL_CHARGES_CONFIG_RULE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CHARGES_CONFIG_RULE_CHARGESCONFIGRULEID_GENERATOR")
	@Column(name="CHARGES_CONFIG_RULE_ID")
	private long chargesConfigRuleId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FROM")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_TO")
	private Date dateTo;

	@Column(name="EXCLUSIVE_FIX_AMOUNT")
	private BigDecimal exclusiveFixAmount;

	@Column(name="EXCLUSIVE_PERCENT_AMOUNT")
	private BigDecimal exclusivePercentAmount;

	@Column(name="FED_INCL_EXCL")
	private String fedInclExcl;

	@Column(name="INCLUSIVE_FIX_AMOUNT")
	private BigDecimal inclusiveFixAmount;

	@Column(name="INCLUSIVE_PERCENT_AMOUNT")
	private BigDecimal inclusivePercentAmount;

	@Column(name="IS_DEFAULT")
	private String isDefault;

	@Column(name="IS_DELETED")
	private BigDecimal isDeleted;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="RANGE_ENDS")
	private BigDecimal rangeEnds;

	@Column(name="RANGE_STARTS")
	private BigDecimal rangeStarts;

	@Column(name="SERVICE_OP_ID")
	private BigDecimal serviceOpId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblChargesConfig
	@OneToMany(mappedBy="tblChargesConfigRule")
	private List<TblChargesConfig> tblChargesConfigs;

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

	public TblChargesConfigRule() {
	}

	public long getChargesConfigRuleId() {
		return this.chargesConfigRuleId;
	}

	public void setChargesConfigRuleId(long chargesConfigRuleId) {
		this.chargesConfigRuleId = chargesConfigRuleId;
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

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getExclusiveFixAmount() {
		return this.exclusiveFixAmount;
	}

	public void setExclusiveFixAmount(BigDecimal exclusiveFixAmount) {
		this.exclusiveFixAmount = exclusiveFixAmount;
	}

	public BigDecimal getExclusivePercentAmount() {
		return this.exclusivePercentAmount;
	}

	public void setExclusivePercentAmount(BigDecimal exclusivePercentAmount) {
		this.exclusivePercentAmount = exclusivePercentAmount;
	}

	public String getFedInclExcl() {
		return this.fedInclExcl;
	}

	public void setFedInclExcl(String fedInclExcl) {
		this.fedInclExcl = fedInclExcl;
	}

	public BigDecimal getInclusiveFixAmount() {
		return this.inclusiveFixAmount;
	}

	public void setInclusiveFixAmount(BigDecimal inclusiveFixAmount) {
		this.inclusiveFixAmount = inclusiveFixAmount;
	}

	public BigDecimal getInclusivePercentAmount() {
		return this.inclusivePercentAmount;
	}

	public void setInclusivePercentAmount(BigDecimal inclusivePercentAmount) {
		this.inclusivePercentAmount = inclusivePercentAmount;
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

	public BigDecimal getRangeEnds() {
		return this.rangeEnds;
	}

	public void setRangeEnds(BigDecimal rangeEnds) {
		this.rangeEnds = rangeEnds;
	}

	public BigDecimal getRangeStarts() {
		return this.rangeStarts;
	}

	public void setRangeStarts(BigDecimal rangeStarts) {
		this.rangeStarts = rangeStarts;
	}

	public BigDecimal getServiceOpId() {
		return this.serviceOpId;
	}

	public void setServiceOpId(BigDecimal serviceOpId) {
		this.serviceOpId = serviceOpId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblChargesConfig> getTblChargesConfigs() {
		return this.tblChargesConfigs;
	}

	public void setTblChargesConfigs(List<TblChargesConfig> tblChargesConfigs) {
		this.tblChargesConfigs = tblChargesConfigs;
	}

	public TblChargesConfig addTblChargesConfig(TblChargesConfig tblChargesConfig) {
		getTblChargesConfigs().add(tblChargesConfig);
		tblChargesConfig.setTblChargesConfigRule(this);

		return tblChargesConfig;
	}

	public TblChargesConfig removeTblChargesConfig(TblChargesConfig tblChargesConfig) {
		getTblChargesConfigs().remove(tblChargesConfig);
		tblChargesConfig.setTblChargesConfigRule(null);

		return tblChargesConfig;
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