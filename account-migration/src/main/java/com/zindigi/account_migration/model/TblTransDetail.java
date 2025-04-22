package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_DETAIL")
@NamedQuery(name="TblTransDetail.findAll", query="SELECT t FROM TblTransDetail t")
public class TblTransDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_DETAIL_TRANSDETAILID_GENERATOR", sequenceName="TBL_TRANS_DETAIL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_DETAIL_TRANSDETAILID_GENERATOR")
	@Column(name="TRANS_DETAIL_ID")
	private long transDetailId;

	@Column(name="AMOUNT_CR")
	private BigDecimal amountCr;

	@Column(name="AMOUNT_DR")
	private BigDecimal amountDr;

	@Column(name="CL_BALANCE")
	private BigDecimal clBalance;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="OP_BALANCE")
	private BigDecimal opBalance;

	@Column(name="TRANS_TYPE")
	private String transType;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCharge
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="CHARGES_ID")
	private LkpCharge lkpCharge;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccount;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID")
	private TblTransHead tblTransHead;

	private String accountType;

	public TblTransDetail() {
	}

	public long getTransDetailId() {
		return this.transDetailId;
	}

	public void setTransDetailId(long transDetailId) {
		this.transDetailId = transDetailId;
	}

	public BigDecimal getAmountCr() {
		return this.amountCr;
	}

	public void setAmountCr(BigDecimal amountCr) {
		this.amountCr = amountCr;
	}

	public BigDecimal getAmountDr() {
		return this.amountDr;
	}

	public void setAmountDr(BigDecimal amountDr) {
		this.amountDr = amountDr;
	}

	public BigDecimal getClBalance() {
		return this.clBalance;
	}

	public void setClBalance(BigDecimal clBalance) {
		this.clBalance = clBalance;
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

	public BigDecimal getOpBalance() {
		return this.opBalance;
	}

	public void setOpBalance(BigDecimal opBalance) {
		this.opBalance = opBalance;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpCharge getLkpCharge() {
		return this.lkpCharge;
	}

	public void setLkpCharge(LkpCharge lkpCharge) {
		this.lkpCharge = lkpCharge;
	}

	public TblAccountModel getTblAccountModel() {
		return this.tblAccount;
	}

	public void setTblAccountModel(TblAccountModel tblAccount) {
		this.tblAccount = tblAccount;
	}

	public TblTransHead getTblTransHead() {
		return this.tblTransHead;
	}

	public void setTblTransHead(TblTransHead tblTransHead) {
		this.tblTransHead = tblTransHead;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}