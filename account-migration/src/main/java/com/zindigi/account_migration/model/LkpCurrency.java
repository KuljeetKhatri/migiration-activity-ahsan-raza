package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_CURRENCY database table.
 * 
 */
@Entity
@Table(name="LKP_CURRENCY")
@NamedQuery(name="LkpCurrency.findAll", query="SELECT l FROM LkpCurrency l")
public class LkpCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_CURRENCY_CURRENCYID_GENERATOR", sequenceName="LKP_CURRENCY_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_CURRENCY_CURRENCYID_GENERATOR")
	@Column(name="CURRENCY_ID")
	private Long currencyId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="CURRENCY_DESCR")
	private String currencyDescr;

	@Column(name="CURRENCY_NAME")
	private String currencyName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ISO_CODE")
	private String isoCode;

	@Column(name = "COA_CODE")
	private String coaCode;

	public String getCoaCode() {
		return coaCode;
	}

	public void setCoaCode(String coaCode) {
		this.coaCode = coaCode;
	}

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;


	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;



	public LkpCurrency() {
	}

	public Long getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
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

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyDescr() {
		return this.currencyDescr;
	}

	public void setCurrencyDescr(String currencyDescr) {
		this.currencyDescr = currencyDescr;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
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

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}
}