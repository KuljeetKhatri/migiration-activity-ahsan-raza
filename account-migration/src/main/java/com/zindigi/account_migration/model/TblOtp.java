package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_OTP database table.
 * 
 */
@Entity
@Table(name="TBL_OTP")
@NamedQuery(name="TblOtp.findAll", query="SELECT t FROM TblOtp t")
public class TblOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_OTP_OTPID_GENERATOR", sequenceName="TBL_OTP_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_OTP_OTPID_GENERATOR")
	@Column(name="OTP_ID")
	private long otpId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_FROM")
	private Date effectiveFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_TO")
	private Date effectiveTo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="OTP_TRIES")
	private BigDecimal otpTries;

	private String otpin;

	private BigDecimal updateindex;

	@Column(name="VERIFICATION_REASON")
	private String verificationReason;

	private String verified;

	//bi-directional many-to-one association to LkpOtpType
	@ManyToOne
	@JoinColumn(name="OTP_TYPE_ID")
	private LkpOtpType lkpOtpType;

	//bi-directional many-to-one association to TblAgent
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private TblAgent tblAgent;

	//bi-directional many-to-one association to TblCustomer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private TblCustomer tblCustomer;

	public TblOtp() {
	}

	public long getOtpId() {
		return this.otpId;
	}

	public void setOtpId(long otpId) {
		this.otpId = otpId;
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

	public Date getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
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

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BigDecimal getOtpTries() {
		return this.otpTries;
	}

	public void setOtpTries(BigDecimal otpTries) {
		this.otpTries = otpTries;
	}

	public String getOtpin() {
		return this.otpin;
	}

	public void setOtpin(String otpin) {
		this.otpin = otpin;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getVerificationReason() {
		return this.verificationReason;
	}

	public void setVerificationReason(String verificationReason) {
		this.verificationReason = verificationReason;
	}

	public String getVerified() {
		return this.verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public LkpOtpType getLkpOtpType() {
		return this.lkpOtpType;
	}

	public void setLkpOtpType(LkpOtpType lkpOtpType) {
		this.lkpOtpType = lkpOtpType;
	}

	public TblAgent getTblAgent() {
		return this.tblAgent;
	}

	public void setTblAgent(TblAgent tblAgent) {
		this.tblAgent = tblAgent;
	}

	public TblCustomer getTblCustomer() {
		return this.tblCustomer;
	}

	public void setTblCustomer(TblCustomer tblCustomer) {
		this.tblCustomer = tblCustomer;
	}

}