package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_TRANSACTION_PURPOSE database table.
 * 
 */
@Entity
@Table(name="LKP_TRANSACTION_PURPOSE")
@NamedQuery(name="LkpTransactionPurpose.findAll", query="SELECT l FROM LkpTransactionPurpose l")
public class LkpTransactionPurpose implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_TRANSACTION_PURPOSE_TRANSACTIONPURPOSEID_GENERATOR", sequenceName="LKP_TRANSACTION_PURPOSE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_TRANSACTION_PURPOSE_TRANSACTIONPURPOSEID_GENERATOR")
	@Column(name="TRANSACTION_PURPOSE_ID")
	private long transactionPurposeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="TRANSACTION_PURPOSE_CODE")
	private String transactionPurposeCode;

	@Column(name="TRANSACTION_PURPOSE_DESCR")
	private String transactionPurposeDescr;

	@Column(name="TRANSACTION_PURPOSE_NAME")
	private String transactionPurposeName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblRequest
	@OneToMany(mappedBy="lkpTransactionPurpose")
	private List<TblRequest> tblRequests;

	public LkpTransactionPurpose() {
	}

	public long getTransactionPurposeId() {
		return this.transactionPurposeId;
	}

	public void setTransactionPurposeId(long transactionPurposeId) {
		this.transactionPurposeId = transactionPurposeId;
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

	public String getTransactionPurposeCode() {
		return this.transactionPurposeCode;
	}

	public void setTransactionPurposeCode(String transactionPurposeCode) {
		this.transactionPurposeCode = transactionPurposeCode;
	}

	public String getTransactionPurposeDescr() {
		return this.transactionPurposeDescr;
	}

	public void setTransactionPurposeDescr(String transactionPurposeDescr) {
		this.transactionPurposeDescr = transactionPurposeDescr;
	}

	public String getTransactionPurposeName() {
		return this.transactionPurposeName;
	}

	public void setTransactionPurposeName(String transactionPurposeName) {
		this.transactionPurposeName = transactionPurposeName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblRequest> getTblRequests() {
		return this.tblRequests;
	}

	public void setTblRequests(List<TblRequest> tblRequests) {
		this.tblRequests = tblRequests;
	}

	public TblRequest addTblRequest(TblRequest tblRequest) {
		getTblRequests().add(tblRequest);
		tblRequest.setLkpTransactionPurpose(this);

		return tblRequest;
	}

	public TblRequest removeTblRequest(TblRequest tblRequest) {
		getTblRequests().remove(tblRequest);
		tblRequest.setLkpTransactionPurpose(null);

		return tblRequest;
	}

}