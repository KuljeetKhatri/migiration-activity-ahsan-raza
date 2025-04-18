package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_DOC_STATUS database table.
 * 
 */
@Entity
@Table(name="LKP_DOC_STATUS")
@NamedQuery(name="LkpDocStatus.findAll", query="SELECT l FROM LkpDocStatus l")
public class LkpDocStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_DOC_STATUS_DOCSTATUSID_GENERATOR", sequenceName="LKP_DOC_STATUS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_DOC_STATUS_DOCSTATUSID_GENERATOR")
	@Column(name="DOC_STATUS_ID")
	private long docStatusId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOC_STATUS_CODE")
	private String docStatusCode;

	@Column(name="DOC_STATUS_DESCR")
	private String docStatusDescr;

	@Column(name="DOC_STATUS_NAME")
	private String docStatusName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;


	public LkpDocStatus() {
	}

	public long getDocStatusId() {
		return this.docStatusId;
	}

	public void setDocStatusId(long docStatusId) {
		this.docStatusId = docStatusId;
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

	public String getDocStatusCode() {
		return this.docStatusCode;
	}

	public void setDocStatusCode(String docStatusCode) {
		this.docStatusCode = docStatusCode;
	}

	public String getDocStatusDescr() {
		return this.docStatusDescr;
	}

	public void setDocStatusDescr(String docStatusDescr) {
		this.docStatusDescr = docStatusDescr;
	}

	public String getDocStatusName() {
		return this.docStatusName;
	}

	public void setDocStatusName(String docStatusName) {
		this.docStatusName = docStatusName;
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



}