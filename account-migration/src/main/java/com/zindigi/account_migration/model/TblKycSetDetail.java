package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_KYC_SET_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_KYC_SET_DETAIL")
@NamedQuery(name="TblKycSetDetail.findAll", query="SELECT t FROM TblKycSetDetail t")
public class TblKycSetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_KYC_SET_DETAIL_KYCSETDETAILID_GENERATOR", sequenceName="TBL_KYC_SET_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_KYC_SET_DETAIL_KYCSETDETAILID_GENERATOR")
	@Column(name="KYC_SET_DETAIL_ID")
	private long kycSetDetailId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_MANDATORY")
	private String isMandatory;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblKycAttribute
	@ManyToOne
	@JoinColumn(name="KYC_ATTRIBUTES_ID")
	private TblKycAttribute tblKycAttribute;


	//bi-directional many-to-one association to TblKycSetHead
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="KYC_SET_HEAD_ID")
	private TblKycSetHead tblKycSetHead;

	@Column(name="IS_ACTIVE")
	private String isActive;

	public TblKycSetDetail() {
	}

	public long getKycSetDetailId() {
		return this.kycSetDetailId;
	}

	public void setKycSetDetailId(long kycSetDetailId) {
		this.kycSetDetailId = kycSetDetailId;
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

	public String getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
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

	public TblKycAttribute getTblKycAttribute() {
		return this.tblKycAttribute;
	}

	public void setTblKycAttribute(TblKycAttribute tblKycAttribute) {
		this.tblKycAttribute = tblKycAttribute;
	}

	public TblKycSetHead getTblKycSetHead() {
		return this.tblKycSetHead;
	}

	public void setTblKycSetHead(TblKycSetHead tblKycSetHead) {
		this.tblKycSetHead = tblKycSetHead;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}