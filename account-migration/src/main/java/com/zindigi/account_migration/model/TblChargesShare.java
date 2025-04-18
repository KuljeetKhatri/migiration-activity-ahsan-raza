package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_CHARGES_SHARE database table.
 * 
 */
@Entity
@Table(name="TBL_CHARGES_SHARE")
@NamedQuery(name="TblChargesShare.findAll", query="SELECT t FROM TblChargesShare t")
public class TblChargesShare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_CHARGES_SHARE_CHARGESSHAREID_GENERATOR", sequenceName="TBL_CHARGES_SHARE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CHARGES_SHARE_CHARGESSHAREID_GENERATOR")
	@Column(name="CHARGES_SHARE_ID")
	private long chargesShareId;

	@Column(name="CHARGES_SHARE")
	private BigDecimal chargesShare;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FROM")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_TO")
	private Date dateTo;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_DEFAULT")
	private String isDefault;

	@Column(name="IS_DELETED")
	private BigDecimal isDeleted;

	@Column(name="IS_FED_APPLICABLE")
	private BigDecimal isFedApplicable;

	@Column(name="IS_WHT_APPLICABLE")
	private BigDecimal isWhtApplicable;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SERVICE_OP_ID")
	private BigDecimal serviceOpId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpAgentNetwork
	@ManyToOne
	@JoinColumn(name="AGENT_NETWORK_ID")
	private LkpAgentNetwork lkpAgentNetwork;

	//bi-directional many-to-one association to LkpChannel
	@ManyToOne
	@JoinColumn(name="CHANNEL_ID")
	private LkpChannel lkpChannel;

	//bi-directional many-to-one association to LkpCharge
	@ManyToOne
	@JoinColumn(name="CHARGES_ID")
	private LkpCharge lkpCharge;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	@JsonIgnore
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to TblChargesShareRule
	@ManyToOne
	@JoinColumn(name="CHARGES_SHARE_RULE_ID")
	private TblChargesShareRule tblChargesShareRule;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblChargesShare() {
	}

	public long getChargesShareId() {
		return this.chargesShareId;
	}

	public void setChargesShareId(long chargesShareId) {
		this.chargesShareId = chargesShareId;
	}

	public BigDecimal getChargesShare() {
		return this.chargesShare;
	}

	public void setChargesShare(BigDecimal chargesShare) {
		this.chargesShare = chargesShare;
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

	public BigDecimal getIsFedApplicable() {
		return this.isFedApplicable;
	}

	public void setIsFedApplicable(BigDecimal isFedApplicable) {
		this.isFedApplicable = isFedApplicable;
	}

	public BigDecimal getIsWhtApplicable() {
		return this.isWhtApplicable;
	}

	public void setIsWhtApplicable(BigDecimal isWhtApplicable) {
		this.isWhtApplicable = isWhtApplicable;
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

	public LkpCharge getLkpCharge() {
		return this.lkpCharge;
	}

	public void setLkpCharge(LkpCharge lkpCharge) {
		this.lkpCharge = lkpCharge;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public TblChargesShareRule getTblChargesShareRule() {
		return this.tblChargesShareRule;
	}

	public void setTblChargesShareRule(TblChargesShareRule tblChargesShareRule) {
		this.tblChargesShareRule = tblChargesShareRule;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}