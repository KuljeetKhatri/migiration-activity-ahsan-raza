package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpAccountLevel;
import com.mfs.commonservice.model.LkpSegment;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_OPERATING_HOURS_RULE database table.
 * 
 */
@Entity
@Table(name="TBL_OPERATING_HOURS_RULE")
@NamedQuery(name="TblOperatingHoursRule.findAll", query="SELECT t FROM TblOperatingHoursRule t")
public class TblOperatingHoursRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_OPERATING_HOURS_RULE_OPERATINGHOURSRULEID_GENERATOR", sequenceName="TBL_OPERATING_HOURS_RULE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_OPERATING_HOURS_RULE_OPERATINGHOURSRULEID_GENERATOR")
	@Column(name="OPERATING_HOURS_RULE_ID")
	private long operatingHoursRuleId;

	@Column(name="ALLOWED_ENDING_HOURS")
	private String allowedEndingHours;

	@Column(name="ALLOWED_ENDING_MINS")
	private BigDecimal allowedEndingMins;

	@Column(name="ALLOWED_STARTING_HOURS")
	private String allowedStartingHours;

	@Column(name="ALLOWED_STARTING_MINS")
	private BigDecimal allowedStartingMins;

	@Column(name="CHANNEL_ID")
	private BigDecimal channelId;

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
	@ManyToOne
	@JoinColumn(name="ACCOUNT_LEVEL_ID")
	private LkpAccountLevel lkpAccountLevel;

	//bi-directional many-to-one association to LkpAgentNetwork
	@ManyToOne
	@JoinColumn(name="AGENT_NETWORK_ID")
	private LkpAgentNetwork lkpAgentNetwork;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	@JsonIgnore
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to TblAgent
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private TblAgent tblAgent;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblOperatingHoursRule() {
	}

	public long getOperatingHoursRuleId() {
		return this.operatingHoursRuleId;
	}

	public void setOperatingHoursRuleId(long operatingHoursRuleId) {
		this.operatingHoursRuleId = operatingHoursRuleId;
	}

	public String getAllowedEndingHours() {
		return this.allowedEndingHours;
	}

	public void setAllowedEndingHours(String allowedEndingHours) {
		this.allowedEndingHours = allowedEndingHours;
	}

	public BigDecimal getAllowedEndingMins() {
		return this.allowedEndingMins;
	}

	public void setAllowedEndingMins(BigDecimal allowedEndingMins) {
		this.allowedEndingMins = allowedEndingMins;
	}

	public String getAllowedStartingHours() {
		return this.allowedStartingHours;
	}

	public void setAllowedStartingHours(String allowedStartingHours) {
		this.allowedStartingHours = allowedStartingHours;
	}

	public BigDecimal getAllowedStartingMins() {
		return this.allowedStartingMins;
	}

	public void setAllowedStartingMins(BigDecimal allowedStartingMins) {
		this.allowedStartingMins = allowedStartingMins;
	}

	public BigDecimal getChannelId() {
		return this.channelId;
	}

	public void setChannelId(BigDecimal channelId) {
		this.channelId = channelId;
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

	public LkpAccountLevel getLkpAccountLevel() {
		return this.lkpAccountLevel;
	}

	public void setLkpAccountLevel(LkpAccountLevel lkpAccountLevel) {
		this.lkpAccountLevel = lkpAccountLevel;
	}

	public LkpAgentNetwork getLkpAgentNetwork() {
		return this.lkpAgentNetwork;
	}

	public void setLkpAgentNetwork(LkpAgentNetwork lkpAgentNetwork) {
		this.lkpAgentNetwork = lkpAgentNetwork;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public TblAgent getTblAgent() {
		return this.tblAgent;
	}

	public void setTblAgent(TblAgent tblAgent) {
		this.tblAgent = tblAgent;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}