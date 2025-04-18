package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_COUNTRY database table.
 * 
 */
@Entity
@Table(name="LKP_COUNTRY")
@NamedQuery(name="LkpCountry.findAll", query="SELECT l FROM LkpCountry l")
public class LkpCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_COUNTRY_COUNTRYID_GENERATOR", sequenceName="LKP_COUNTRY_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_COUNTRY_COUNTRYID_GENERATOR")
	@Column(name="COUNTRY_ID")
	private long countryId;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="COUNTRY_DESCR")
	private String countryDescr;

	@Column(name="COUNTRY_NAME")
	private String countryName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpCountry() {
	}

	public long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDescr() {
		return this.countryDescr;
	}

	public void setCountryDescr(String countryDescr) {
		this.countryDescr = countryDescr;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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