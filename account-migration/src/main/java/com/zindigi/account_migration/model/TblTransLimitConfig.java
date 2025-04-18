package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_LIMIT_CONFIG database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_LIMIT_CONFIG")
@NamedQuery(name="TblTransLimitConfig.findAll", query="SELECT t FROM TblTransLimitConfig t")
public class TblTransLimitConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_LIMIT_CONFIG_TRANSLIMITCONFIGID_GENERATOR", sequenceName="TBL_TRANS_LIMIT_CONFIG_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_LIMIT_CONFIG_TRANSLIMITCONFIGID_GENERATOR")
	@Column(name="TRANS_LIMIT_CONFIG_ID")
	private long transLimitConfigId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpAgentNetwork
	@ManyToOne
	@JoinColumn(name="AGENT_NETWORK_ID")
	private LkpAgentNetwork lkpAgentNetwork;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	@JsonIgnore
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to TblTransLimit
	@ManyToOne
	@JoinColumn(name="TRANS_LIMIT_ID")
	private TblTransLimit tblTransLimit;

	public TblTransLimitConfig() {
	}

	public long getTransLimitConfigId() {
		return this.transLimitConfigId;
	}

	public void setTransLimitConfigId(long transLimitConfigId) {
		this.transLimitConfigId = transLimitConfigId;
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

	public TblTransLimit getTblTransLimit() {
		return this.tblTransLimit;
	}

	public void setTblTransLimit(TblTransLimit tblTransLimit) {
		this.tblTransLimit = tblTransLimit;
	}

}