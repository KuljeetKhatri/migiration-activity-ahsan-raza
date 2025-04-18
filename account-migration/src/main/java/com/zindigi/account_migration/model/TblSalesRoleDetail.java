package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_SALES_ROLE_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_SALES_ROLE_DETAIL")
@NamedQuery(name="TblSalesRoleDetail.findAll", query="SELECT t FROM TblSalesRoleDetail t")
public class TblSalesRoleDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SALES_ROLE_DETAIL_SALESROLEDETAILID_GENERATOR", sequenceName="TBL_SALES_ROLE_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SALES_ROLE_DETAIL_SALESROLEDETAILID_GENERATOR")
	@Column(name="SALES_ROLE_DETAIL_ID")
	private long salesRoleDetailId;
	
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="SR_NO")
	private Integer srNo;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpSegment
	@OneToMany(mappedBy="tblSalesRoleDetail")
	@JsonIgnore
	private List<LkpSegment> lkpSegments;

	@Column(name="IS_ACTIVE")
	private String isActive;

	//bi-directional many-to-one association to TblSalesForce
	@OneToMany(mappedBy="tblSalesRoleDetail")
	private List<TblSalesForce> tblSalesForces;

	//bi-directional many-to-one association to TblSalesHierarchyDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblSalesRoleDetail")
	private List<TblSalesHierarchyDetail> tblSalesHierarchyDetails;

	//bi-directional many-to-one association to TblSalesRole
	@ManyToOne
	@JoinColumn(name="SALES_ROLE_ID")
	@JsonIgnore
	private TblSalesRole tblSalesRole;

	public TblSalesRoleDetail() {
	}

	public long getSalesRoleDetailId() {
		return this.salesRoleDetailId;
	}

	public void setSalesRoleDetailId(long salesRoleDetailId) {
		this.salesRoleDetailId = salesRoleDetailId;
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

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblSalesForce> getTblSalesForces() {
		return this.tblSalesForces;
	}

	public void setTblSalesForces(List<TblSalesForce> tblSalesForces) {
		this.tblSalesForces = tblSalesForces;
	}

	public TblSalesForce addTblSalesForce(TblSalesForce tblSalesForce) {
		getTblSalesForces().add(tblSalesForce);
		tblSalesForce.setTblSalesRoleDetail(this);

		return tblSalesForce;
	}

	public TblSalesForce removeTblSalesForce(TblSalesForce tblSalesForce) {
		getTblSalesForces().remove(tblSalesForce);
		tblSalesForce.setTblSalesRoleDetail(null);

		return tblSalesForce;
	}

	public List<TblSalesHierarchyDetail> getTblSalesHierarchyDetails() {
		return this.tblSalesHierarchyDetails;
	}

	public void setTblSalesHierarchyDetails(List<TblSalesHierarchyDetail> tblSalesHierarchyDetails) {
		this.tblSalesHierarchyDetails = tblSalesHierarchyDetails;
	}

	public TblSalesHierarchyDetail addTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().add(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesRoleDetail(this);

		return tblSalesHierarchyDetail;
	}

	public TblSalesHierarchyDetail removeTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().remove(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesRoleDetail(null);

		return tblSalesHierarchyDetail;
	}

	public TblSalesRole getTblSalesRole() {
		return this.tblSalesRole;
	}

	public void setTblSalesRole(TblSalesRole tblSalesRole) {
		this.tblSalesRole = tblSalesRole;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}


}