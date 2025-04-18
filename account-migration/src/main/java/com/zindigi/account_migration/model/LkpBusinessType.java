package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_BUSINESS_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_BUSINESS_TYPE")
@NamedQuery(name="LkpBusinessType.findAll", query="SELECT l FROM LkpBusinessType l")
public class LkpBusinessType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BUSINESS_TYPE_BUSINESSTYPEID_GENERATOR", sequenceName="LKP_BUSINESS_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BUSINESS_TYPE_BUSINESSTYPEID_GENERATOR")
	@Column(name="BUSINESS_TYPE_ID")
	private long businessTypeId;

	@Column(name="BUSINESS_TYPE_CODE")
	private String businessTypeCode;

	@Column(name="BUSINESS_TYPE_DESCR")
	private String businessTypeDescr;

	@Column(name="BUSINESS_TYPE_NAME")
	private String businessTypeName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpBusinessType() {
	}

	public long getBusinessTypeId() {
		return this.businessTypeId;
	}

	public void setBusinessTypeId(long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getBusinessTypeCode() {
		return this.businessTypeCode;
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getBusinessTypeDescr() {
		return this.businessTypeDescr;
	}

	public void setBusinessTypeDescr(String businessTypeDescr) {
		this.businessTypeDescr = businessTypeDescr;
	}

	public String getBusinessTypeName() {
		return this.businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
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
}