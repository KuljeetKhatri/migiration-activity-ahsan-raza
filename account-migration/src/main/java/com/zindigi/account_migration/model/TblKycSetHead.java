package com.zindigi.account_migration.model;


import com.mfs.commonservice.model.LkpAccountClassification;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_KYC_SET_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_KYC_SET_HEAD")
@NamedQuery(name="TblKycSetHead.findAll", query="SELECT t FROM TblKycSetHead t")
public class TblKycSetHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_KYC_SET_HEAD_KYCSETHEADID_GENERATOR", sequenceName="TBL_KYC_SET_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_KYC_SET_HEAD_KYCSETHEADID_GENERATOR")
	@Column(name="KYC_SET_HEAD_ID")
	private long kycSetHeadId;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="KYC_SET_NAME")
	private String kycSetName;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblKycSetDetail
	@OneToMany(mappedBy="tblKycSetHead")
	private List<TblKycSetDetail> tblKycSetDetails;

	//bi-directional many-to-one association to LkpAccountClassification
	@ManyToOne
	@JoinColumn(name="ACCOUNT_CLASSIFICATION_ID")
	private LkpAccountClassification lkpAccountClassification;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Transient
	private List<TblKycAttribute> excludeKycAttributes;

	@Transient
	private List<TblKycAttribute> includeKycAttributes;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@Transient
	private String accountLevel;
	@Transient
	private long totalRecordCount;

	public TblKycSetHead() {
	}

	public long getKycSetHeadId() {
		return this.kycSetHeadId;
	}

	public void setKycSetHeadId(long kycSetHeadId) {
		this.kycSetHeadId = kycSetHeadId;
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

	public String getKycSetName() {
		return this.kycSetName;
	}

	public void setKycSetName(String kycSetName) {
		this.kycSetName = kycSetName;
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

	public List<TblKycSetDetail> getTblKycSetDetails() {
		return this.tblKycSetDetails;
	}

	public void setTblKycSetDetails(List<TblKycSetDetail> tblKycSetDetails) {
		this.tblKycSetDetails = tblKycSetDetails;
	}

	public TblKycSetDetail addTblKycSetDetail(TblKycSetDetail tblKycSetDetail) {
		getTblKycSetDetails().add(tblKycSetDetail);
		tblKycSetDetail.setTblKycSetHead(this);

		return tblKycSetDetail;
	}

	public TblKycSetDetail removeTblKycSetDetail(TblKycSetDetail tblKycSetDetail) {
		getTblKycSetDetails().remove(tblKycSetDetail);
		tblKycSetDetail.setTblKycSetHead(null);

		return tblKycSetDetail;
	}

	public LkpAccountClassification getLkpAccountClassification() {
		return this.lkpAccountClassification;
	}

	public void setLkpAccountClassification(LkpAccountClassification lkpAccountClassification) {
		this.lkpAccountClassification = lkpAccountClassification;
	}

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<TblKycAttribute> getExcludeKycAttributes() {
		return excludeKycAttributes;
	}

	public void setExcludeKycAttributes(List<TblKycAttribute> excludeKycAttributes) {
		this.excludeKycAttributes = excludeKycAttributes;
	}

	public List<TblKycAttribute> getIncludeKycAttributes() {
		return includeKycAttributes;
	}

	public void setIncludeKycAttributes(List<TblKycAttribute> includeKycAttributes) {
		this.includeKycAttributes = includeKycAttributes;
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

	public String getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	}

	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
}