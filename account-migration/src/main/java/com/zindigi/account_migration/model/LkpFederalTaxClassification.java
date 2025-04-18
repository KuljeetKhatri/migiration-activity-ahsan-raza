package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_FEDERAL_TAX_CLASSIFICATION database table.
 * 
 */
@Entity
@Table(name="LKP_FEDERAL_TAX_CLASSIFICATION")
@NamedQuery(name="LkpFederalTaxClassification.findAll", query="SELECT l FROM LkpFederalTaxClassification l")
public class LkpFederalTaxClassification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_FEDERAL_TAX_CLASSIFICATION_FEDERALTAXCLASSIFICATIONID_GENERATOR", sequenceName="LKP_FEDERAL_TAX_CLASSIFICATION_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_FEDERAL_TAX_CLASSIFICATION_FEDERALTAXCLASSIFICATIONID_GENERATOR")
	@Column(name="FEDERAL_TAX_CLASSIFICATION_ID")
	private long federalTaxClassificationId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FEDERAL_TAX_CLASSIFICATION_CODE")
	private String federalTaxClassificationCode;

	@Column(name="FEDERAL_TAX_CLASSIFICATION_DESCR")
	private String federalTaxClassificationDescr;

	@Column(name="FEDERAL_TAX_CLASSIFICATION_NAME")
	private String federalTaxClassificationName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="lkpFederalTaxClassification")
	private List<TblUltraCustomer> tblUltraCustomers;

	public LkpFederalTaxClassification() {
	}

	public long getFederalTaxClassificationId() {
		return this.federalTaxClassificationId;
	}

	public void setFederalTaxClassificationId(long federalTaxClassificationId) {
		this.federalTaxClassificationId = federalTaxClassificationId;
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

	public String getFederalTaxClassificationCode() {
		return this.federalTaxClassificationCode;
	}

	public void setFederalTaxClassificationCode(String federalTaxClassificationCode) {
		this.federalTaxClassificationCode = federalTaxClassificationCode;
	}

	public String getFederalTaxClassificationDescr() {
		return this.federalTaxClassificationDescr;
	}

	public void setFederalTaxClassificationDescr(String federalTaxClassificationDescr) {
		this.federalTaxClassificationDescr = federalTaxClassificationDescr;
	}

	public String getFederalTaxClassificationName() {
		return this.federalTaxClassificationName;
	}

	public void setFederalTaxClassificationName(String federalTaxClassificationName) {
		this.federalTaxClassificationName = federalTaxClassificationName;
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

	public List<TblUltraCustomer> getTblUltraCustomers() {
		return this.tblUltraCustomers;
	}

	public void setTblUltraCustomers(List<TblUltraCustomer> tblUltraCustomers) {
		this.tblUltraCustomers = tblUltraCustomers;
	}

	public TblUltraCustomer addTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().add(tblUltraCustomer);
		tblUltraCustomer.setLkpFederalTaxClassification(this);

		return tblUltraCustomer;
	}

	public TblUltraCustomer removeTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().remove(tblUltraCustomer);
		tblUltraCustomer.setLkpFederalTaxClassification(null);

		return tblUltraCustomer;
	}

}