package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PRODUCT_LIMIT database table.
 * 
 */
@Entity
@Table(name="TBL_PRODUCT_LIMIT")
@NamedQuery(name="TblProductLimit.findAll", query="SELECT t FROM TblProductLimit t")
public class TblProductLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRODUCT_LIMIT_PRODUCTLIMITID_GENERATOR", sequenceName="TBL_PRODUCT_LIMIT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRODUCT_LIMIT_PRODUCTLIMITID_GENERATOR")
	@Column(name="PRODUCT_LIMIT_ID")
	private long productLimitId;

	//bi-directional many-to-one association to LkpAccountLevel
	@ManyToOne
	@JoinColumn(name="ACCOUNT_LEVEL_ID")
	private LkpAccountLevel lkpAccountLevel;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String exclude;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAX_AMOUNT")
	private BigDecimal maxAmount;

	@Column(name="MIN_AMOUNT")
	private BigDecimal minAmount;

	private BigDecimal updateindex;

	@JsonIgnore
	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblProductLimit() {
	}

	public long getProductLimitId() {
		return this.productLimitId;
	}

	public void setProductLimitId(long productLimitId) {
		this.productLimitId = productLimitId;
	}

	public LkpAccountLevel getLkpAccountLevel() {
		return lkpAccountLevel;
	}

	public void setLkpAccountLevel(LkpAccountLevel lkpAccountLevel) {
		this.lkpAccountLevel = lkpAccountLevel;
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

	public String getExclude() {
		return this.exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
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

	public BigDecimal getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}