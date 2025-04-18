package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_KYC_ATTRIBUTES database table.
 * 
 */
@Entity
@Table(name="TBL_KYC_ATTRIBUTES")
@NamedQuery(name="TblKycAttribute.findAll", query="SELECT t FROM TblKycAttribute t")
public class TblKycAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_KYC_ATTRIBUTES_KYCATTRIBUTESID_GENERATOR", sequenceName="TBL_KYC_ATTRIBUTES_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_KYC_ATTRIBUTES_KYCATTRIBUTESID_GENERATOR")
	@Column(name="KYC_ATTRIBUTES_ID")
	private long kycAttributesId;

	@Column(name="ATTRIBUTE_CODE")
	private String attributeCode;

	@Column(name="ATTRIBUTE_NAME")
	private String attributeName;

	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_EDITABLE")
	private String isEditable;

	@Column(name="IS_SEARCHABLE")
	private String isSearchable;

	@Column(name="IS_UNIQUE")
	private String isUnique;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblValidator
	@ManyToOne
	@JoinColumn(name="VALIDATOR_ID")
	private TblValidator tblValidator;


	//bi-directional many-to-one association to TblKycSetDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblKycAttribute")
	private List<TblKycSetDetail> tblKycSetDetails;

	@Column(name="IS_ACTIVE")
	private String isActive;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	@Transient
	private Boolean isMandatory;

	@Transient
	private String createdBy;

	@Transient
	private String updatedBy;

	@Transient
	private long totalRecordCount;

	public TblKycAttribute() {
	}

	public long getKycAttributesId() {
		return this.kycAttributesId;
	}

	public void setKycAttributesId(long kycAttributesId) {
		this.kycAttributesId = kycAttributesId;
	}

	public String getAttributeCode() {
		return this.attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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

	public String getIsEditable() {
		return this.isEditable;
	}

	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}

	public String getIsSearchable() {
		return this.isSearchable;
	}

	public void setIsSearchable(String isSearchable) {
		this.isSearchable = isSearchable;
	}

	public String getIsUnique() {
		return this.isUnique;
	}

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
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

	public TblValidator getTblValidator() {
		return this.tblValidator;
	}

	public void setTblValidator(TblValidator tblValidator) {
		this.tblValidator = tblValidator;
	}

	public List<TblKycSetDetail> getTblKycSetDetails() {
		return this.tblKycSetDetails;
	}

	public void setTblKycSetDetails(List<TblKycSetDetail> tblKycSetDetails) {
		this.tblKycSetDetails = tblKycSetDetails;
	}

	public TblKycSetDetail addTblKycSetDetail(TblKycSetDetail tblKycSetDetail) {
		getTblKycSetDetails().add(tblKycSetDetail);
		tblKycSetDetail.setTblKycAttribute(this);

		return tblKycSetDetail;
	}

	public TblKycSetDetail removeTblKycSetDetail(TblKycSetDetail tblKycSetDetail) {
		getTblKycSetDetails().remove(tblKycSetDetail);
		tblKycSetDetail.setTblKycAttribute(null);

		return tblKycSetDetail;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Boolean getMandatory() {
		return isMandatory;
	}

	public void setMandatory(Boolean mandatory) {
		isMandatory = mandatory;
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

	public long getTotalRecordCount() { return totalRecordCount; }

	public void setTotalRecordCount(long totalRecordCount) { this.totalRecordCount = totalRecordCount; }
}