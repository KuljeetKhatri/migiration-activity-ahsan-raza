package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_TAX_RATE database table.
 * 
 */
@Entity
@Table(name="TBL_TAX_RATE")
@NamedQuery(name="TblTaxRate.findAll", query="SELECT t FROM TblTaxRate t")
public class TblTaxRate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TAX_RATE_TAXRATEID_GENERATOR", sequenceName="TBL_TAX_RATE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAX_RATE_TAXRATEID_GENERATOR")
	@Column(name="TAX_RATE_ID")
	private long taxRateId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_FROM")
	private Date effectiveFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_TO")
	private Date effectiveTo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TAX_RATE_TYPE")
	private String taxRateType;

	@Column(name="THRESHOLD_AMOUNT")
	private BigDecimal thresholdAmount;

	private BigDecimal updateindex;


	@JsonIgnore
	//bi-directional many-to-one association to TblCustomer
	@OneToMany(mappedBy="tblTaxRate1")
	private List<TblCustomer> tblCustomers1;

	@JsonIgnore
	//bi-directional many-to-one association to TblCustomer
	@OneToMany(mappedBy="tblTaxRate2")
	private List<TblCustomer> tblCustomers2;

//	//bi-directional many-to-one association to LkpCity
//	@ManyToOne
//	@JoinColumn(name="CITY_ID")
//	private LkpCity lkpCity;

//	//bi-directional many-to-one association to LkpCountry
//	@ManyToOne
//	@JoinColumn(name="COUNTRY_ID")
//	private LkpCountry lkpCountry;

//	//bi-directional many-to-one association to LkpProvince
//	@ManyToOne
//	@JoinColumn(name="PROVINCE_ID")
//	private LkpProvince lkpProvince;

	//bi-directional many-to-one association to TblTaxRegime
//	@ManyToOne
//	@JoinColumn(name="TAX_REGIME_ID")
//	private TblTaxRegime tblTaxRegime;

	@JsonIgnore
	//bi-directional many-to-one association to TblTaxSlab
	@OneToMany(mappedBy="tblTaxRate")
	private List<TblTaxSlab> tblTaxSlabs;

	public TblTaxRate() {
	}

	public long getTaxRateId() {
		return this.taxRateId;
	}

	public void setTaxRateId(long taxRateId) {
		this.taxRateId = taxRateId;
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

	public Date getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
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

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxRateType() {
		return this.taxRateType;
	}

	public void setTaxRateType(String taxRateType) {
		this.taxRateType = taxRateType;
	}

	public BigDecimal getThresholdAmount() {
		return this.thresholdAmount;
	}

	public void setThresholdAmount(BigDecimal thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}


//	public TblAgent addTblAgent(TblAgent tblAgent) {
//		getTblAgents().add(tblAgent);
//		tblAgent.setTblTaxRate(this);
//
//		return tblAgent;
//	}
//
//	public TblAgent removeTblAgent(TblAgent tblAgent) {
//		getTblAgents().remove(tblAgent);
//		tblAgent.setTblTaxRate(null);
//
//		return tblAgent;
//	}

	public List<TblCustomer> getTblCustomers1() {
		return this.tblCustomers1;
	}

	public void setTblCustomers1(List<TblCustomer> tblCustomers1) {
		this.tblCustomers1 = tblCustomers1;
	}

	public TblCustomer addTblCustomers1(TblCustomer tblCustomers1) {
		getTblCustomers1().add(tblCustomers1);
		tblCustomers1.setTblTaxRate1(this);

		return tblCustomers1;
	}

	public TblCustomer removeTblCustomers1(TblCustomer tblCustomers1) {
		getTblCustomers1().remove(tblCustomers1);
		tblCustomers1.setTblTaxRate1(null);

		return tblCustomers1;
	}

	public List<TblCustomer> getTblCustomers2() {
		return this.tblCustomers2;
	}

	public void setTblCustomers2(List<TblCustomer> tblCustomers2) {
		this.tblCustomers2 = tblCustomers2;
	}

	public TblCustomer addTblCustomers2(TblCustomer tblCustomers2) {
		getTblCustomers2().add(tblCustomers2);
		tblCustomers2.setTblTaxRate2(this);

		return tblCustomers2;
	}

	public TblCustomer removeTblCustomers2(TblCustomer tblCustomers2) {
		getTblCustomers2().remove(tblCustomers2);
		tblCustomers2.setTblTaxRate2(null);

		return tblCustomers2;
	}


//	public LkpCountry getLkpCountry() {
//		return this.lkpCountry;
//	}
//
//	public void setLkpCountry(LkpCountry lkpCountry) {
//		this.lkpCountry = lkpCountry;
//	}

//	public LkpProvince getLkpProvince() {
//		return this.lkpProvince;
//	}
//
//	public void setLkpProvince(LkpProvince lkpProvince) {
//		this.lkpProvince = lkpProvince;
//	}

//	public TblTaxRegime getTblTaxRegime() {
//		return this.tblTaxRegime;
//	}
//
//	public void setTblTaxRegime(TblTaxRegime tblTaxRegime) {
//		this.tblTaxRegime = tblTaxRegime;
//	}

	public List<TblTaxSlab> getTblTaxSlabs() {
		return this.tblTaxSlabs;
	}

	public void setTblTaxSlabs(List<TblTaxSlab> tblTaxSlabs) {
		this.tblTaxSlabs = tblTaxSlabs;
	}

	public TblTaxSlab addTblTaxSlab(TblTaxSlab tblTaxSlab) {
		getTblTaxSlabs().add(tblTaxSlab);
		tblTaxSlab.setTblTaxRate(this);

		return tblTaxSlab;
	}

	public TblTaxSlab removeTblTaxSlab(TblTaxSlab tblTaxSlab) {
		getTblTaxSlabs().remove(tblTaxSlab);
		tblTaxSlab.setTblTaxRate(null);

		return tblTaxSlab;
	}

}