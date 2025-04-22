package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpCurrency;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TAX_REGIME database table.
 * 
 */
@Entity
@Table(name="TBL_TAX_REGIME")
@NamedQuery(name="TblTaxRegime.findAll", query="SELECT t FROM TblTaxRegime t")
public class TblTaxRegime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TAX_REGIME_TAXREGIMEID_GENERATOR", sequenceName="TBL_TAX_REGIME_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAX_REGIME_TAXREGIMEID_GENERATOR")
	@Column(name="TAX_REGIME_ID")
	private long taxRegimeId;

	@Column(name="APPLICABLE_ON")
	private String applicableOn;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_FILER")
	private String isFiler;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

//	@Column(name="REGIME_LEVEL_ID")
//	private BigDecimal regimeLevelId;

	@Column(name="TAX_REGIME_CODE")
	private String taxRegimeCode;

	@Column(name="TAX_REGIME_NAME")
	private String taxRegimeName;

	@Column(name="TAX_TYPE_ID")
	private BigDecimal taxTypeId;

	private BigDecimal updateindex;

//	@JsonIgnore
//	//bi-directional many-to-one association to TblTaxRate
//	@OneToMany(mappedBy="tblTaxRegime")
//	private List<TblTaxRate> tblTaxRates;

	//bi-directional many-to-one association to LkpCurrency
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private LkpCurrency lkpCurrency;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccount;

	public TblTaxRegime() {
	}

	public long getTaxRegimeId() {
		return this.taxRegimeId;
	}

	public void setTaxRegimeId(long taxRegimeId) {
		this.taxRegimeId = taxRegimeId;
	}

	public String getApplicableOn() {
		return this.applicableOn;
	}

	public void setApplicableOn(String applicableOn) {
		this.applicableOn = applicableOn;
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

	public String getIsFiler() {
		return this.isFiler;
	}

	public void setIsFiler(String isFiler) {
		this.isFiler = isFiler;
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

//	public BigDecimal getRegimeLevelId() {
//		return this.regimeLevelId;
//	}
//
//	public void setRegimeLevelId(BigDecimal regimeLevelId) {
//		this.regimeLevelId = regimeLevelId;
//	}

	public String getTaxRegimeCode() {
		return this.taxRegimeCode;
	}

	public void setTaxRegimeCode(String taxRegimeCode) {
		this.taxRegimeCode = taxRegimeCode;
	}

	public String getTaxRegimeName() {
		return this.taxRegimeName;
	}

	public void setTaxRegimeName(String taxRegimeName) {
		this.taxRegimeName = taxRegimeName;
	}

	public BigDecimal getTaxTypeId() {
		return this.taxTypeId;
	}

	public void setTaxTypeId(BigDecimal taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

//	public List<TblTaxRate> getTblTaxRates() {
//		return this.tblTaxRates;
//	}
//
//	public void setTblTaxRates(List<TblTaxRate> tblTaxRates) {
//		this.tblTaxRates = tblTaxRates;
//	}
//
//	public TblTaxRate addTblTaxRate(TblTaxRate tblTaxRate) {
//		getTblTaxRates().add(tblTaxRate);
//		tblTaxRate.setTblTaxRegime(this);
//
//		return tblTaxRate;
//	}
//
//	public TblTaxRate removeTblTaxRate(TblTaxRate tblTaxRate) {
//		getTblTaxRates().remove(tblTaxRate);
//		tblTaxRate.setTblTaxRegime(null);
//
//		return tblTaxRate;
//	}

	public LkpCurrency getLkpCurrency() {
		return this.lkpCurrency;
	}

	public void setLkpCurrency(LkpCurrency lkpCurrency) {
		this.lkpCurrency = lkpCurrency;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblAccountModel getTblAccountModel() {
		return this.tblAccount;
	}

	public void setTblAccountModel(TblAccountModel tblAccount) {
		this.tblAccount = tblAccount;
	}

}