package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpChannel;
import com.mfs.commonservice.model.LkpCurrency;
import com.mfs.commonservice.model.LkpSegment;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_REQUEST database table.
 * 
 */
@Entity
@Table(name="TBL_REQUEST")
@NamedQuery(name="TblRequest.findAll", query="SELECT t FROM TblRequest t")
public class TblRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REQUEST_REQUESTID_GENERATOR", sequenceName="TBL_REQUEST_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REQUEST_REQUESTID_GENERATOR")
	@Column(name="REQUEST_ID")
	private long requestId;

	private BigDecimal amount;

	private String classification;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DATA_HASH")
	private String dataHash;

	private String endpoint;

	private BigDecimal fee;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAPPING_ID")
	private BigDecimal mappingId;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	private String nick;

	private BigDecimal otp;

	@Column(name="OTP_ID")
	private BigDecimal otpId;

	private String password;

	private BigDecimal pin;

	@Column(name="PIN_TYPE")
	private String pinType;

	@Column(name="RECEIVER_MOBILE_NO")
	private String receiverMobileNo;

	@Temporal(TemporalType.DATE)
	@Column(name="REQUEST_DATE_TIME")
	private Date requestDateTime;

	private String reserved1;

	private String reserved10;

	private String reserved11;

	private String reserved12;

	private String reserved13;

	private String reserved14;

	private String reserved15;

	private String reserved16;

	private String reserved17;

	private String reserved18;

	private String reserved19;

	private String reserved2;

	private String reserved20;

	private String reserved3;

	private String reserved4;

	private String reserved5;

	private String reserved6;

	private String reserved7;

	private String reserved8;

	private String reserved9;

	@Column(name="\"RRN\"")
	private String rrn;

	private BigDecimal taxes;

	@Column(name="TERMINAL_ID")
	private BigDecimal terminalId;

	@Column(name="TRANS_TYPE")
	private String transType;

	private BigDecimal updateindex;

	private String username;

	//bi-directional many-to-one association to LkpChannel
	@ManyToOne
	@JoinColumn(name="CHANNEL_ID")
	private LkpChannel lkpChannel;

	//bi-directional many-to-one association to LkpCurrency
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private LkpCurrency lkpCurrency;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	@JsonIgnore
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to LkpTransactionPurpose
	@ManyToOne
	@JoinColumn(name="PAYMENT_PURPOSE_ID")
	private LkpTransactionPurpose lkpTransactionPurpose;

	//bi-directional many-to-one association to TblResponse
	@OneToMany(mappedBy="tblRequest")
	private List<TblResponse> tblResponses;

	public TblRequest() {
	}

	public long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
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

	public String getDataHash() {
		return this.dataHash;
	}

	public void setDataHash(String dataHash) {
		this.dataHash = dataHash;
	}

	public String getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
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

	public BigDecimal getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(BigDecimal mappingId) {
		this.mappingId = mappingId;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public BigDecimal getOtp() {
		return this.otp;
	}

	public void setOtp(BigDecimal otp) {
		this.otp = otp;
	}

	public BigDecimal getOtpId() {
		return this.otpId;
	}

	public void setOtpId(BigDecimal otpId) {
		this.otpId = otpId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPin() {
		return this.pin;
	}

	public void setPin(BigDecimal pin) {
		this.pin = pin;
	}

	public String getPinType() {
		return this.pinType;
	}

	public void setPinType(String pinType) {
		this.pinType = pinType;
	}

	public String getReceiverMobileNo() {
		return this.receiverMobileNo;
	}

	public void setReceiverMobileNo(String receiverMobileNo) {
		this.receiverMobileNo = receiverMobileNo;
	}

	public Date getRequestDateTime() {
		return this.requestDateTime;
	}

	public void setRequestDateTime(Date requestDateTime) {
		this.requestDateTime = requestDateTime;
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

	public String getReserved11() {
		return this.reserved11;
	}

	public void setReserved11(String reserved11) {
		this.reserved11 = reserved11;
	}

	public String getReserved12() {
		return this.reserved12;
	}

	public void setReserved12(String reserved12) {
		this.reserved12 = reserved12;
	}

	public String getReserved13() {
		return this.reserved13;
	}

	public void setReserved13(String reserved13) {
		this.reserved13 = reserved13;
	}

	public String getReserved14() {
		return this.reserved14;
	}

	public void setReserved14(String reserved14) {
		this.reserved14 = reserved14;
	}

	public String getReserved15() {
		return this.reserved15;
	}

	public void setReserved15(String reserved15) {
		this.reserved15 = reserved15;
	}

	public String getReserved16() {
		return this.reserved16;
	}

	public void setReserved16(String reserved16) {
		this.reserved16 = reserved16;
	}

	public String getReserved17() {
		return this.reserved17;
	}

	public void setReserved17(String reserved17) {
		this.reserved17 = reserved17;
	}

	public String getReserved18() {
		return this.reserved18;
	}

	public void setReserved18(String reserved18) {
		this.reserved18 = reserved18;
	}

	public String getReserved19() {
		return this.reserved19;
	}

	public void setReserved19(String reserved19) {
		this.reserved19 = reserved19;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved20() {
		return this.reserved20;
	}

	public void setReserved20(String reserved20) {
		this.reserved20 = reserved20;
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

	public String getRrn() {
		return this.rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public BigDecimal getTaxes() {
		return this.taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public BigDecimal getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(BigDecimal terminalId) {
		this.terminalId = terminalId;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LkpChannel getLkpChannel() {
		return this.lkpChannel;
	}

	public void setLkpChannel(LkpChannel lkpChannel) {
		this.lkpChannel = lkpChannel;
	}

	public LkpCurrency getLkpCurrency() {
		return this.lkpCurrency;
	}

	public void setLkpCurrency(LkpCurrency lkpCurrency) {
		this.lkpCurrency = lkpCurrency;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public LkpTransactionPurpose getLkpTransactionPurpose() {
		return this.lkpTransactionPurpose;
	}

	public void setLkpTransactionPurpose(LkpTransactionPurpose lkpTransactionPurpose) {
		this.lkpTransactionPurpose = lkpTransactionPurpose;
	}

	public List<TblResponse> getTblResponses() {
		return this.tblResponses;
	}

	public void setTblResponses(List<TblResponse> tblResponses) {
		this.tblResponses = tblResponses;
	}

	public TblResponse addTblRespons(TblResponse tblRespons) {
		getTblResponses().add(tblRespons);
		tblRespons.setTblRequest(this);

		return tblRespons;
	}

	public TblResponse removeTblRespons(TblResponse tblRespons) {
		getTblResponses().remove(tblRespons);
		tblRespons.setTblRequest(null);

		return tblRespons;
	}

}