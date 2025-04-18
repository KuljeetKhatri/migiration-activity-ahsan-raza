package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PRICING_PRODUCT database table.
 * 
 */
@Entity
@Table(name="TBL_PRICING_PRODUCT")
@NamedQuery(name="TblPricingProduct.findAll", query="SELECT t FROM TblPricingProduct t")
public class TblPricingProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRICING_PRODUCT_PRICINGPRODUCTID_GENERATOR", sequenceName="TBL_PRICING_PRODUCT_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRICING_PRODUCT_PRICINGPRODUCTID_GENERATOR")
	@Column(name="PRICING_PRODUCT_ID")
	private long pricingProductId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblPricingProfile
	@ManyToOne
	@JoinColumn(name="PRICING_PROFILE_ID")
	private TblPricingProfile tblPricingProfile;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblPricingProduct() {
	}

	public long getPricingProductId() {
		return this.pricingProductId;
	}

	public void setPricingProductId(long pricingProductId) {
		this.pricingProductId = pricingProductId;
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

	public TblPricingProfile getTblPricingProfile() {
		return this.tblPricingProfile;
	}

	public void setTblPricingProfile(TblPricingProfile tblPricingProfile) {
		this.tblPricingProfile = tblPricingProfile;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}