package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_CHANNEL database table.
 * 
 */
@Entity
@Table(name="LKP_CHANNEL")
@NamedQuery(name="LkpChannel.findAll", query="SELECT l FROM LkpChannel l")
public class LkpChannel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_CHANNEL_CHANNELID_GENERATOR", sequenceName="LKP_CHANNEL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_CHANNEL_CHANNELID_GENERATOR")
	@Column(name="CHANNEL_ID")
	private long channelId;

	@Column(name="CHANNEL_CODE")
	private String channelCode;

	@Column(name="CHANNEL_DESCR")
	private String channelDescr;

	@Column(name="CHANNEL_NAME")
	private String channelName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;


	public long getChannelId() {
		return this.channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public String getChannelCode() {
		return this.channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelDescr() {
		return this.channelDescr;
	}

	public void setChannelDescr(String channelDescr) {
		this.channelDescr = channelDescr;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}
}