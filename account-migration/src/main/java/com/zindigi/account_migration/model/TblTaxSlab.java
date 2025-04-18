package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TAX_SLAB database table.
 * 
 */
@Entity
@Table(name="TBL_TAX_SLAB")
@NamedQuery(name="TblTaxSlab.findAll", query="SELECT t FROM TblTaxSlab t")
public class TblTaxSlab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TAX_SLAB_TAXSLABID_GENERATOR", sequenceName="TBL_TAX_SLAB_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAX_SLAB_TAXSLABID_GENERATOR")
	@Column(name="TAX_SLAB_ID")
	private long taxSlabId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FROM_AMOUNT")
	private BigDecimal fromAmount;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SLAB_TYPE")
	private String slabType;

	@Column(name="TAX_AMOUNT")
	private BigDecimal taxAmount;

	@Column(name="TAX_PERCENTAGE")
	private BigDecimal taxPercentage;

	@Column(name="THRESHOLD_AMOUNT")
	private BigDecimal thresholdAmount;

	@Column(name="TO_AMOUNT")
	private BigDecimal toAmount;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblTaxRate
	@ManyToOne
	@JoinColumn(name="TAX_RATE_ID")
	private TblTaxRate tblTaxRate;

	public TblTaxSlab() {
	}

	public long getTaxSlabId() {
		return this.taxSlabId;
	}

	public void setTaxSlabId(long taxSlabId) {
		this.taxSlabId = taxSlabId;
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

	public BigDecimal getFromAmount() {
		return this.fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
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

	public String getSlabType() {
		return this.slabType;
	}

	public void setSlabType(String slabType) {
		this.slabType = slabType;
	}

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxPercentage() {
		return this.taxPercentage;
	}

	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public BigDecimal getThresholdAmount() {
		return this.thresholdAmount;
	}

	public void setThresholdAmount(BigDecimal thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public BigDecimal getToAmount() {
		return this.toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblTaxRate getTblTaxRate() {
		return this.tblTaxRate;
	}

	public void setTblTaxRate(TblTaxRate tblTaxRate) {
		this.tblTaxRate = tblTaxRate;
	}

}