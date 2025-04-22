package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_COMMISSION_HIERARCHY database table.
 * 
 */
@Entity
@Table(name="TBL_COMMISSION_HIERARCHY")
@NamedQuery(name="TblCommissionHierarchy.findAll", query="SELECT t FROM TblCommissionHierarchy t")
public class TblCommissionHierarchy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMMISSION_PROFILE_COMMISSION_HIERARCHY_GENERATOR", sequenceName="TBL_COMMISSION_HIERARCHY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMMISSION_PROFILE_COMMISSION_HIERARCHY_GENERATOR")
	@Column(name="COMMISSION_HIERARCHY_ID")
	private long commissionHierarchyId;

	@Column(name="CHILD_SHARE")
	private BigDecimal childShare;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="HIERARCHY_NAME")
	private String hierarchyName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PARENT_SHARE")
	private BigDecimal parentShare;

	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	private BigDecimal updateindex;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	public TblCommissionHierarchy() {
	}

	public long getCommissionHierarchyId() {
		return this.commissionHierarchyId;
	}

	public void setCommissionHierarchyId(long commissionHierarchyId) {
		this.commissionHierarchyId = commissionHierarchyId;
	}

	public BigDecimal getChildShare() {
		return this.childShare;
	}

	public void setChildShare(BigDecimal childShare) {
		this.childShare = childShare;
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

	public BigDecimal getParentShare() {
		return this.parentShare;
	}

	public void setParentShare(BigDecimal parentShare) {
		this.parentShare = parentShare;
	}

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
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
}