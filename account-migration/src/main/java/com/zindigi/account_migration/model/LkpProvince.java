package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_PROVINCE database table.
 * 
 */
@Entity
@Table(name="LKP_PROVINCE")
@NamedQuery(name="LkpProvince.findAll", query="SELECT l FROM LkpProvince l")
public class LkpProvince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_PROVINCE_PROVINCEID_GENERATOR", sequenceName="LKP_PROVINCE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_PROVINCE_PROVINCEID_GENERATOR")
	@Column(name="PROVINCE_ID")
	private long provinceId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_DESCR")
	private String provinceDescr;

	@Column(name="PROVINCE_NAME")
	private String provinceName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry;

	public LkpProvince() {
	}

	public long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
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

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceDescr() {
		return this.provinceDescr;
	}

	public void setProvinceDescr(String provinceDescr) {
		this.provinceDescr = provinceDescr;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

}