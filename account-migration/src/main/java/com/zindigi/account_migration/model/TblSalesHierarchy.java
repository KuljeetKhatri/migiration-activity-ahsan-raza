package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_SALES_HIERARCHY database table.
 * 
 */
@Entity
@Table(name="TBL_SALES_HIERARCHY")
@NamedQuery(name="TblSalesHierarchy.findAll", query="SELECT t FROM TblSalesHierarchy t")
public class TblSalesHierarchy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SALES_HIERARCHY_SALESHIERARCHYID_GENERATOR", sequenceName="TBL_SALES_HIERARCHY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SALES_HIERARCHY_SALESHIERARCHYID_GENERATOR")
	@Column(name="SALES_HIERARCHY_ID")
	private long salesHierarchyId;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="HIERARCHY_NAME")
	private String hierarchyName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpSalesStructure
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="SALES_STRUCTURE_ID")
	private LkpSalesStructure lkpSalesStructure;

	//bi-directional many-to-one association to TblSalesHierarchyDetail
	@OneToMany(mappedBy="tblSalesHierarchy")
	private List<TblSalesHierarchyDetail> tblSalesHierarchyDetails;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;


	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;


	@Transient
	private Long salesStructureId;

	public TblSalesHierarchy() {
	}

	public long getSalesHierarchyId() {
		return this.salesHierarchyId;
	}

	public void setSalesHierarchyId(long salesHierarchyId) {
		this.salesHierarchyId = salesHierarchyId;
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

	public String getHierarchyName() {
		return this.hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
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

	public List<TblSalesHierarchyDetail> getTblSalesHierarchyDetails() {
		return this.tblSalesHierarchyDetails;
	}

	public void setTblSalesHierarchyDetails(List<TblSalesHierarchyDetail> tblSalesHierarchyDetails) {
		this.tblSalesHierarchyDetails = tblSalesHierarchyDetails;
	}

	public TblSalesHierarchyDetail addTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().add(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesHierarchy(this);

		return tblSalesHierarchyDetail;
	}

	public TblSalesHierarchyDetail removeTblSalesHierarchyDetail(TblSalesHierarchyDetail tblSalesHierarchyDetail) {
		getTblSalesHierarchyDetails().remove(tblSalesHierarchyDetail);
		tblSalesHierarchyDetail.setTblSalesHierarchy(null);

		return tblSalesHierarchyDetail;
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

	public Long getSalesStructureId() {
		return salesStructureId;
	}

	public void setSalesStructureId(Long salesStructureId) {
		this.salesStructureId = salesStructureId;
	}
}