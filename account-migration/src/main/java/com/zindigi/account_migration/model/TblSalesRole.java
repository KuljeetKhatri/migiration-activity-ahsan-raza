package com.zindigi.account_migration.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_SALES_ROLE database table.
 * 
 */
@Entity
@Table(name="TBL_SALES_ROLE")
@NamedQuery(name="TblSalesRole.findAll", query="SELECT t FROM TblSalesRole t")
public class TblSalesRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SALES_ROLE_SALESROLEID_GENERATOR", sequenceName="TBL_SALES_ROLE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SALES_ROLE_SALESROLEID_GENERATOR")
	@Column(name="SALES_ROLE_ID")
	private long salesRoleId;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="NO_IN_HIERARCHY")
	private BigDecimal noInHierarchy;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBusinessType
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="BUSINESS_TYPE_ID")
	private LkpBusinessType lkpBusinessType;

	//bi-directional many-to-one association to LkpRegion
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REGION_ID")
	private LkpRegion lkpRegion;

	//bi-directional many-to-one association to LkpSalesStructure
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_STRUCTURE_ID")
	private LkpSalesStructure lkpSalesStructure;

	//bi-directional many-to-one association to TblSalesRoleDetail
	@OneToMany(mappedBy="tblSalesRole")
	private List<TblSalesRoleDetail> tblSalesRoleDetails;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;


	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@Transient
	private Long businessTypeId;

	@Transient
	private Long regionId;

	@Transient
	private Long salesStructureId;

	public TblSalesRole() {
	}

	public long getSalesRoleId() {
		return this.salesRoleId;
	}

	public void setSalesRoleId(long salesRoleId) {
		this.salesRoleId = salesRoleId;
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

	public BigDecimal getNoInHierarchy() {
		return this.noInHierarchy;
	}

	public void setNoInHierarchy(BigDecimal noInHierarchy) {
		this.noInHierarchy = noInHierarchy;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpBusinessType getLkpBusinessType() {
		return this.lkpBusinessType;
	}

	public void setLkpBusinessType(LkpBusinessType lkpBusinessType) {
		this.lkpBusinessType = lkpBusinessType;
	}

	public LkpRegion getLkpRegion() {
		return this.lkpRegion;
	}

	public void setLkpRegion(LkpRegion lkpRegion) {
		this.lkpRegion = lkpRegion;
	}

	public LkpSalesStructure getLkpSalesStructure() {
		return this.lkpSalesStructure;
	}

	public void setLkpSalesStructure(LkpSalesStructure lkpSalesStructure) {
		this.lkpSalesStructure = lkpSalesStructure;
	}

	public List<TblSalesRoleDetail> getTblSalesRoleDetails() {
		return this.tblSalesRoleDetails;
	}

	public void setTblSalesRoleDetails(List<TblSalesRoleDetail> tblSalesRoleDetails) {
		this.tblSalesRoleDetails = tblSalesRoleDetails;
	}

	public TblSalesRoleDetail addTblSalesRoleDetail(TblSalesRoleDetail tblSalesRoleDetail) {
		getTblSalesRoleDetails().add(tblSalesRoleDetail);
		tblSalesRoleDetail.setTblSalesRole(this);

		return tblSalesRoleDetail;
	}

	public TblSalesRoleDetail removeTblSalesRoleDetail(TblSalesRoleDetail tblSalesRoleDetail) {
		getTblSalesRoleDetails().remove(tblSalesRoleDetail);
		tblSalesRoleDetail.setTblSalesRole(null);

		return tblSalesRoleDetail;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
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

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getSalesStructureId() {
		return salesStructureId;
	}

	public void setSalesStructureId(Long salesStructureId) {
		this.salesStructureId = salesStructureId;
	}

}