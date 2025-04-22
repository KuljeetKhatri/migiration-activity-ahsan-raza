package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpCurrency;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_RESPONSE database table.
 * 
 */
@Entity
@Table(name="TBL_RESPONSE")
@NamedQuery(name="TblResponse.findAll", query="SELECT t FROM TblResponse t")
public class TblResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RESPONSE_RESPONSEID_GENERATOR", sequenceName="TBL_RESPONSE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RESPONSE_RESPONSEID_GENERATOR")
	@Column(name="RESPONSE_ID")
	private long responseId;

	@Column(name="COMMISSION_AMOUNT")
	private BigDecimal commissionAmount;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CUSTOMER_MOBILE_NO")
	private String customerMobileNo;

	@Column(name="DATA_HASH")
	private String dataHash;

	private BigDecimal fee;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="RECEIVER_MOBILE_NO")
	private String receiverMobileNo;

	@Column(name="RECEIVER_NAME")
	private String receiverName;

	private String reserved1;

	private String reserved10;

	private String reserved2;

	private String reserved3;

	private String reserved4;

	private String reserved5;

	private String reserved6;

	private String reserved7;

	private String reserved8;

	private String reserved9;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	@Column(name="RESPONSE_DATE_TIME")
	private String responseDateTime;

	@Column(name="RESPONSE_DESCRIPTION")
	private String responseDescription;

	@Column(name="SENDER_ACCOUNT_TITLE")
	private String senderAccountTitle;

	private BigDecimal taxes;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name="TRANSACTION_DATE_TIME")
	private String transactionDateTime;

	@Column(name="TRANSACTION_ID")
	private String transactionId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCurrency
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private LkpCurrency lkpCurrency;

	//bi-directional many-to-one association to TblRequest
	@ManyToOne
	@JoinColumn(name="REQUEST_ID")
	private TblRequest tblRequest;

	public TblResponse() {
	}

	public long getResponseId() {
		return this.responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public BigDecimal getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
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

	public String getCustomerMobileNo() {
		return this.customerMobileNo;
	}

	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getDataHash() {
		return this.dataHash;
	}

	public void setDataHash(String dataHash) {
		this.dataHash = dataHash;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
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

	public String getReceiverMobileNo() {
		return this.receiverMobileNo;
	}

	public void setReceiverMobileNo(String receiverMobileNo) {
		this.receiverMobileNo = receiverMobileNo;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved10() {
		return this.reserved10;
	}

	public void setReserved10(String reserved10) {
		this.reserved10 = reserved10;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved4() {
		return this.reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}

	public String getReserved5() {
		return this.reserved5;
	}

	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}

	public String getReserved6() {
		return this.reserved6;
	}

	public void setReserved6(String reserved6) {
		this.reserved6 = reserved6;
	}

	public String getReserved7() {
		return this.reserved7;
	}

	public void setReserved7(String reserved7) {
		this.reserved7 = reserved7;
	}

	public String getReserved8() {
		return this.reserved8;
	}

	public void setReserved8(String reserved8) {
		this.reserved8 = reserved8;
	}

	public String getReserved9() {
		return this.reserved9;
	}

	public void setReserved9(String reserved9) {
		this.reserved9 = reserved9;
	}

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDateTime() {
		return this.responseDateTime;
	}

	public void setResponseDateTime(String responseDateTime) {
		this.responseDateTime = responseDateTime;
	}

	public String getResponseDescription() {
		return this.responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getSenderAccountTitle() {
		return this.senderAccountTitle;
	}

	public void setSenderAccountTitle(String senderAccountTitle) {
		this.senderAccountTitle = senderAccountTitle;
	}

	public BigDecimal getTaxes() {
		return this.taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTransactionDateTime() {
		return this.transactionDateTime;
	}

	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpCurrency getLkpCurrency() {
		return this.lkpCurrency;
	}

	public void setLkpCurrency(LkpCurrency lkpCurrency) {
		this.lkpCurrency = lkpCurrency;
	}

	public TblRequest getTblRequest() {
		return this.tblRequest;
	}

	public void setTblRequest(TblRequest tblRequest) {
		this.tblRequest = tblRequest;
	}

}