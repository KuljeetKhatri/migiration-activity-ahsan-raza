package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_APP_USER_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_APP_USER_TYPE")
@NamedQuery(name="LkpAppUserType.findAll", query="SELECT l FROM LkpAppUserType l")
public class LkpAppUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_APP_USER_TYPE_APPUSERTYPEID_GENERATOR", sequenceName="LKP_APP_USER_TYPE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_APP_USER_TYPE_APPUSERTYPEID_GENERATOR")
	@Column(name="APP_USER_TYPE_ID")
	private long appUserTypeId;

	@Column(name="APP_USER_TYPE_CODE")
	private String appUserTypeCode;

	@Column(name="APP_USER_TYPE_DESCR")
	private String appUserTypeDescr;

	@Column(name="APP_USER_TYPE_NAME")
	private String appUserTypeName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpAppUserType() {
	}

	public long getAppUserTypeId() {
		return this.appUserTypeId;
	}

	public void setAppUserTypeId(long appUserTypeId) {
		this.appUserTypeId = appUserTypeId;
	}

	public String getAppUserTypeCode() {
		return this.appUserTypeCode;
	}

	public void setAppUserTypeCode(String appUserTypeCode) {
		this.appUserTypeCode = appUserTypeCode;
	}

	public String getAppUserTypeDescr() {
		return this.appUserTypeDescr;
	}

	public void setAppUserTypeDescr(String appUserTypeDescr) {
		this.appUserTypeDescr = appUserTypeDescr;
	}

	public String getAppUserTypeName() {
		return this.appUserTypeName;
	}

	public void setAppUserTypeName(String appUserTypeName) {
		this.appUserTypeName = appUserTypeName;
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