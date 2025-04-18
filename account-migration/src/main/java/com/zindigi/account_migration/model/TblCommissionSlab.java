package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_COMMISSION_SLAB database table.
 * 
 */
@Entity
@Table(name="TBL_COMMISSION_SLAB")
@NamedQuery(name="TblCommissionSlab.findAll", query="SELECT t FROM TblCommissionSlab t")
public class TblCommissionSlab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMMISSION_SLAB_COMMISSIONSLABID_GENERATOR", sequenceName="TBL_COMMISSION_SLAB_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMMISSION_SLAB_COMMISSIONSLABID_GENERATOR")
	@Column(name="COMMISSION_SLAB_ID")
	private long commissionSlabId;

	@Column(name="COMMISSION_AMOUNT")
	private BigDecimal commissionAmount;

	@Column(name="COMMISSION_PERCENTAGE")
	private BigDecimal commissionPercentage;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="END_SLAB")
	private BigDecimal endSlab;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="START_SLAB")
	private BigDecimal startSlab;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpFeeType
	@ManyToOne
	@JoinColumn(name="COMMISSION_TYPE_ID")
	private LkpFeeType lkpFeeType;

	//bi-directional many-to-one association to TblCommissionProfile
	@ManyToOne
	@JoinColumn(name="COMMISSION_PROFILE_ID")
	private TblCommissionProfile tblCommissionProfile;

	public TblCommissionSlab() {
	}

	public long getCommissionSlabId() {
		return this.commissionSlabId;
	}

	public void setCommissionSlabId(long commissionSlabId) {
		this.commissionSlabId = commissionSlabId;
	}

	public BigDecimal getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public BigDecimal getCommissionPercentage() {
		return this.commissionPercentage;
	}

	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
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

	public BigDecimal getEndSlab() {
		return this.endSlab;
	}

	public void setEndSlab(BigDecimal endSlab) {
		this.endSlab = endSlab;
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

	public BigDecimal getStartSlab() {
		return this.startSlab;
	}

	public void setStartSlab(BigDecimal startSlab) {
		this.startSlab = startSlab;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpFeeType getLkpFeeType() {
		return this.lkpFeeType;
	}

	public void setLkpFeeType(LkpFeeType lkpFeeType) {
		this.lkpFeeType = lkpFeeType;
	}

	public TblCommissionProfile getTblCommissionProfile() {
		return this.tblCommissionProfile;
	}

	public void setTblCommissionProfile(TblCommissionProfile tblCommissionProfile) {
		this.tblCommissionProfile = tblCommissionProfile;
	}

}