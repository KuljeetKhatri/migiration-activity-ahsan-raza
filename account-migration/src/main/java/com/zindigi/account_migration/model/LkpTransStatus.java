package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_TRANS_STATUS database table.
 * 
 */
@Entity
@Table(name="LKP_TRANS_STATUS")
@NamedQuery(name="LkpTransStatus.findAll", query="SELECT l FROM LkpTransStatus l")
public class LkpTransStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_TRANS_STATUS_TRANSSTATUSID_GENERATOR", sequenceName="LKP_TRANS_STATUS_SEQ" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_TRANS_STATUS_TRANSSTATUSID_GENERATOR")
	@Column(name="TRANS_STATUS_ID")
	private long transStatusId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="TRANS_STATUS_CODE")
	private String transStatusCode;

	@Column(name="TRANS_STATUS_DESCR")
	private String transStatusDescr;

	@Column(name="TRANS_STATUS_NAME")
	private String transStatusName;

	private BigDecimal updateindex;


	public LkpTransStatus() {
	}

	public long getTransStatusId() {
		return this.transStatusId;
	}

	public void setTransStatusId(long transStatusId) {
		this.transStatusId = transStatusId;
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

	public String getTransStatusCode() {
		return this.transStatusCode;
	}

	public void setTransStatusCode(String transStatusCode) {
		this.transStatusCode = transStatusCode;
	}

	public String getTransStatusDescr() {
		return this.transStatusDescr;
	}

	public void setTransStatusDescr(String transStatusDescr) {
		this.transStatusDescr = transStatusDescr;
	}

	public String getTransStatusName() {
		return this.transStatusName;
	}

	public void setTransStatusName(String transStatusName) {
		this.transStatusName = transStatusName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}