package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PRODUCT database table.
 * 
 */
@Entity
@Table(name="TBL_PRODUCT")
@NamedQuery(name="TblProduct.findAll", query="SELECT t FROM TblProduct t")
public class TblProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRODUCT_PRODUCTID_GENERATOR", sequenceName="TBL_PRODUCT_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRODUCT_PRODUCTID_GENERATOR")
	@Column(name="PRODUCT_ID")
	private long productId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	private BigDecimal updateindex;

	@Column(name="PRODUCT_NAME")
	private String productName;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblProductField
	@OneToMany(mappedBy="tblProduct")
	private List<TblProductField> tblProductFields;

	//bi-directional many-to-one association to TblProductLimit
	@OneToMany(mappedBy="tblProduct")
	private List<TblProductLimit> tblProductLimits;

	//bi-directional many-to-one association to TblTransPattern
	@OneToMany(mappedBy="tblProduct")
	private List<TblTransPattern> tblTransPatterns;

	//bi-directional many-to-one association to TblInsProductPlan
	@OneToMany(mappedBy="tblProduct")
	private List<TblInsProductPlan> tblInsProductPlans;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@ManyToOne
	@JoinColumn(name="PRODUCT_CATEGORY_ID")
	private LkpProductCategory lkpProductCategory;

//	//bi-directional many-to-one association to TblLienDetail
//	@OneToMany(mappedBy="tblProduct")
//	private List<TblLienDetail> tblLienDetails;

	public TblProduct() {
	}

//	public List<TblLienDetail> getTblLienDetails() {
//		return tblLienDetails;
//	}
//
//	public void setTblLienDetails(List<TblLienDetail> tblLienDetails) {
//		this.tblLienDetails = tblLienDetails;
//	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public List<TblProductField> getTblProductFields() {
		return tblProductFields;
	}

	public void setTblProductFields(List<TblProductField> tblProductFields) {
		this.tblProductFields = tblProductFields;
	}

	public List<TblProductLimit> getTblProductLimits() {
		return tblProductLimits;
	}

	public void setTblProductLimits(List<TblProductLimit> tblProductLimits) {
		this.tblProductLimits = tblProductLimits;
	}

	public List<TblTransPattern> getTblTransPatterns() {
		return tblTransPatterns;
	}

	public void setTblTransPatterns(List<TblTransPattern> tblTransPatterns) {
		this.tblTransPatterns = tblTransPatterns;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LkpProductCategory getLkpProductCategory() {
		return lkpProductCategory;
	}

	public void setLkpProductCategory(LkpProductCategory lkpProductCategory) {
		this.lkpProductCategory = lkpProductCategory;
	}

	public List<TblInsProductPlan> getTblInsProductPlans() {
		return tblInsProductPlans;
	}

	public void setTblInsProductPlans(List<TblInsProductPlan> tblInsProductPlans) {
		this.tblInsProductPlans = tblInsProductPlans;
	}
}