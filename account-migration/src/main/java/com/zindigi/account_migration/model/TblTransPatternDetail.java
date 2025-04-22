package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpCharge;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;/**
 * The persistent class for the TBL_TRANS_PATTERN_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_PATTERN_DETAIL")
@NamedQuery(name="TblTransPatternDetail.findAll", query="SELECT t FROM TblTransPatternDetail t")

public class TblTransPatternDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_PATTERN_DETAIL_TRANSPATTERNDETAILID_GENERATOR", sequenceName="TBL_TRANS_PATTERN_DETAIL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_PATTERN_DETAIL_TRANSPATTERNDETAILID_GENERATOR")
	@Column(name="TRANS_PATTERN_DETAIL_ID")
	private long transPatternDetailId;

	@Column(name="ACCOUNT_ENTITY")
	private String accountEntity;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="TRANS_SIGN")
	private String transSign;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCharge
	@ManyToOne
	@JoinColumn(name="CHARGES_ID")
	private com.mfs.commonservice.model.LkpCharge lkpCharge;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccountModel;

	//bi-directional many-to-one association to TblTransPatternHead
	@ManyToOne
	@JoinColumn(name="TRANS_PATTERN_HEAD_ID")
	private TblTransPatternHead tblTransPatternHead;

	public TblTransPatternDetail() {
	}

	public long getTransPatternDetailId() {
		return this.transPatternDetailId;
	}

	public void setTransPatternDetailId(long transPatternDetailId) {
		this.transPatternDetailId = transPatternDetailId;
	}

	public String getAccountEntity() {
		return this.accountEntity;
	}

	public void setAccountEntity(String accountEntity) {
		this.accountEntity = accountEntity;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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

	public String getTransSign() {
		return this.transSign;
	}

	public void setTransSign(String transSign) {
		this.transSign = transSign;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public com.mfs.commonservice.model.LkpCharge getLkpCharge() {
		return this.lkpCharge;
	}

	public void setLkpCharge(LkpCharge lkpCharge) {
		this.lkpCharge = lkpCharge;
	}

	public TblAccountModel getTblAccountModel() {
		return this.tblAccountModel;
	}

	public void setTblAccountModel(TblAccountModel tblAccountModel) {
		this.tblAccountModel = tblAccountModel;
	}

	public TblTransPatternHead getTblTransPatternHead() {
		return this.tblTransPatternHead;
	}

	public void setTblTransPatternHead(TblTransPatternHead tblTransPatternHead) {
		this.tblTransPatternHead = tblTransPatternHead;
	}

}