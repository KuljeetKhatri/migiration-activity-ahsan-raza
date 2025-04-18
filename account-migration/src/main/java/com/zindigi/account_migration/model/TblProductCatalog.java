package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PRODUCT_CATALOG database table.
 * 
 */
@Entity
@Table(name="TBL_PRODUCT_CATALOG")
@NamedQuery(name="TblProductCatalog.findAll", query="SELECT t FROM TblProductCatalog t")
public class TblProductCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRODUCT_CATALOG_PRODUCTCATALOGID_GENERATOR", sequenceName="TBL_PRODUCT_CATALOG_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRODUCT_CATALOG_PRODUCTCATALOGID_GENERATOR")
	@Column(name="PRODUCT_CATALOG_ID")
	private long productCatalogId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String description;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PRODUCT_CATALOG_NAME")
	private String projectCatalogName;

	private BigDecimal updateindex;


	public TblProductCatalog() {
	}

	public long getProductCatalogId() {
		return this.productCatalogId;
	}

	public void setProductCatalogId(long productCatalogId) {
		this.productCatalogId = productCatalogId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProjectCatalogName() {
		return this.projectCatalogName;
	}

	public void setProjectCatalogName(String projectCatalogName) {
		this.projectCatalogName = projectCatalogName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}


}