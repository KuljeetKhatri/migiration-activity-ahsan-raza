package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_SALES_FORCE database table.
 * 
 */
@Entity
@Table(name="TBL_SALES_FORCE")
@NamedQuery(name="TblSalesForce.findAll", query="SELECT t FROM TblSalesForce t")
public class TblSalesForce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SALES_FORCE_SALESFORCEID_GENERATOR", sequenceName="TBL_SALES_FORCE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SALES_FORCE_SALESFORCEID_GENERATOR")
	@Column(name="SALES_FORCE_ID")
	private long salesForceId;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="EMPLOYEE_CNIC")
	private String employeeCnic;

	@Column(name="EMPLOYEE_ID")
	private String employeeId;

	@Column(name="EMPLOYEE_MOBILE_NO")
	private String employeeMobileNo;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name="ESS_DEPARTMENT")
	private String essDepartment;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpSalesStructure
	@ManyToOne
	@JoinColumn(name="SALES_STRUCTURE_ID")
	private LkpSalesStructure lkpSalesStructure;

	//bi-directional many-to-one association to TblSalesRoleDetail
	@ManyToOne
	@JoinColumn(name="SALES_ROLE_DETAIL_ID")
	@JsonIgnore
	private TblSalesRoleDetail tblSalesRoleDetail;

	//bi-directional many-to-one association to TblSalesHierarchyDetail
	@OneToMany(mappedBy="tblSalesForce")
	private List<TblSalesHierarchyDetail> tblSalesHierarchyDetails;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;



	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_ROLE_ID")
	private TblSalesRole tblSalesRole;

    @Transient
	private Long salesStructureId;
	@Transient
	private Long salesRoleDetailId;
	@Transient
	private Long salesRoleId;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@Transient
	private String roleName;
	@Transient
	private String regionName;

	@Transient
	private String salesHierarchyName;

	private String employeeEmail;

	public TblSalesForce() {
	}

	public long getSalesForceId() {
		return this.salesForceId;
	}

	public void setSalesForceId(long salesForceId) {
		this.salesForceId = salesForceId;
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

	public String getEmployeeCnic() {
		return this.employeeCnic;
	}

	public void setEmployeeCnic(String employeeCnic) {
		this.employeeCnic = employeeCnic;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeMobileNo() {
		return this.employeeMobileNo;
	}

	public void setEmployeeMobileNo(String employeeMobileNo) {
		this.employeeMobileNo = employeeMobileNo;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEssDepartment() {
		return this.essDepartment;
	}

	public void setEssDepartment(String essDepartment) {
		this.essDepartment = essDepartment;
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

	public LkpSalesStructure getLkpSalesStructure() {
		return this.lkpSalesStructure;
	}

	public void setLkpSalesStructure(LkpSalesStructure lkpSalesStructure) {
		this.lkpSalesStructure = lkpSalesStructure;
	}

	public TblSalesRoleDetail getTblSalesRoleDetail() {
		return this.tblSalesRoleDetail;
	}

	public void setTblSalesRoleDetail(TblSalesRoleDetail tblSalesRoleDetail) {
		this.tblSalesRoleDetail = tblSalesRoleDetail;
	}

	public List<TblSalesHierarchyDetail> getTblSalesHierarchyDetails() {
		return this.tblSalesHierarchyDetails;
	}

	public void setTblSalesHierarchyDetails(List<TblSalesHierarchyDetail> tblSalesHierarchyDetails) {
		this.tblSalesHierarchyDetails = tblSalesHierarchyDetails;
	}

	public TblSalesHierarchyDetail addTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().add(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesForce(this);

		return tblSalesHierarchyDetail;
	}

	public TblSalesHierarchyDetail removeTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().remove(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesForce(null);

		return tblSalesHierarchyDetail;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblSalesRole getTblSalesRole() {
		return tblSalesRole;
	}

	public void setTblSalesRole(TblSalesRole tblSalesRole) {
		this.tblSalesRole = tblSalesRole;
	}

	public Long getSalesStructureId() {
		return salesStructureId;
	}

	public void setSalesStructureId(Long salesStructureId) {
		this.salesStructureId = salesStructureId;
	}

	public Long getSalesRoleDetailId() {
		return salesRoleDetailId;
	}

	public void setSalesRoleDetailId(Long salesRoleDetailId) {
		this.salesRoleDetailId = salesRoleDetailId;
	}

	public Long getSalesRoleId() {
		return salesRoleId;
	}

	public void setSalesRoleId(Long salesRoleId) {
		this.salesRoleId = salesRoleId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getSalesHierarchyName() {
		return salesHierarchyName;
	}

	public void setSalesHierarchyName(String salesHierarchyName) {
		this.salesHierarchyName = salesHierarchyName;
	}
}