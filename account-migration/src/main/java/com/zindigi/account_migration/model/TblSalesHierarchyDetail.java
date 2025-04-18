package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_SALES_HIERARCHY_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_SALES_HIERARCHY_DETAIL")
@NamedQuery(name="TblSalesHierarchyDetail.findAll", query="SELECT t FROM TblSalesHierarchyDetail t")
public class TblSalesHierarchyDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SALES_HIERARCHY_DETAIL_SALESHIERARCHYDETAILID_GENERATOR", sequenceName="TBL_SALES_HIERARCHY_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SALES_HIERARCHY_DETAIL_SALESHIERARCHYDETAILID_GENERATOR")
	@Column(name="SALES_HIERARCHY_DETAIL_ID")
	private long salesHierarchyDetailId;

	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblSalesForce
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_FORCE_ID")
	private TblSalesForce tblSalesForce;

	//bi-directional many-to-one association to TblSalesHierarchy
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_HIERARCHY_ID")
	private TblSalesHierarchy tblSalesHierarchy;

	//bi-directional many-to-one association to TblSalesRoleDetail
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_ROLE_DETAIL_ID")
	private TblSalesRoleDetail tblSalesRoleDetail;


	@Transient
	private long salesForceId;
    @Transient
	private long salesHierarchyId;

	@Transient
	private long salesRoleDetailId;

	public TblSalesHierarchyDetail() {
	}

	public long getSalesHierarchyDetailId() {
		return this.salesHierarchyDetailId;
	}

	public void setSalesHierarchyDetailId(long salesHierarchyDetailId) {
		this.salesHierarchyDetailId = salesHierarchyDetailId;
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

	public TblSalesForce getTblSalesForce() {
		return this.tblSalesForce;
	}

	public void setTblSalesForce(TblSalesForce tblSalesForce) {
		this.tblSalesForce = tblSalesForce;
	}

	public TblSalesHierarchy getTblSalesHierarchy() {
		return this.tblSalesHierarchy;
	}

	public void setTblSalesHierarchy(TblSalesHierarchy tblSalesHierarchy) {
		this.tblSalesHierarchy = tblSalesHierarchy;
	}

	public TblSalesRoleDetail getTblSalesRoleDetail() {
		return this.tblSalesRoleDetail;
	}

	public void setTblSalesRoleDetail(TblSalesRoleDetail tblSalesRoleDetail) {
		this.tblSalesRoleDetail = tblSalesRoleDetail;
	}

	public long getSalesForceId() {
		return salesForceId;
	}

	public void setSalesForceId(long salesForceId) {
		this.salesForceId = salesForceId;
	}

	public long getSalesHierarchyId() {
		return salesHierarchyId;
	}

	public void setSalesHierarchyId(long salesHierarchyId) {
		this.salesHierarchyId = salesHierarchyId;
	}

	public long getSalesRoleDetailId() {
		return salesRoleDetailId;
	}

	public void setSalesRoleDetailId(long salesRoleDetailId) {
		this.salesRoleDetailId = salesRoleDetailId;
	}
}