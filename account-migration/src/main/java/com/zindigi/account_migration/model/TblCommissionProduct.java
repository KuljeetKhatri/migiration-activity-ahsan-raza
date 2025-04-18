package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_COMMISSION_PRODUCT database table.
 * 
 */
@Entity
@Table(name="TBL_COMMISSION_PRODUCT")
@NamedQuery(name="TblCommissionProduct.findAll", query="SELECT t FROM TblCommissionProduct t")
public class TblCommissionProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMMISSION_PRODUCT_COMMISSIONPRODUCTID_GENERATOR", sequenceName="TBL_COMMISSION_PRODUCT_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMMISSION_PRODUCT_COMMISSIONPRODUCTID_GENERATOR")
	@Column(name="COMMISSION_PRODUCT_ID")
	private long commissionProductId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblCommissionProfile
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COMMISSION_PROFILE_ID")
	private TblCommissionProfile tblCommissionProfile;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblCommissionProduct() {
	}

	public long getCommissionProductId() {
		return this.commissionProductId;
	}

	public void setCommissionProductId(long commissionProductId) {
		this.commissionProductId = commissionProductId;
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

	public TblCommissionProfile getTblCommissionProfile() {
		return this.tblCommissionProfile;
	}

	public void setTblCommissionProfile(TblCommissionProfile tblCommissionProfile) {
		this.tblCommissionProfile = tblCommissionProfile;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}