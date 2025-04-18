package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_OTP_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_OTP_TYPE")
@NamedQuery(name="LkpOtpType.findAll", query="SELECT l FROM LkpOtpType l")
public class LkpOtpType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_OTP_TYPE_OTPTYPEID_GENERATOR", sequenceName="LKP_OTP_TYPE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_OTP_TYPE_OTPTYPEID_GENERATOR")
	@Column(name="OTP_TYPE_ID")
	private long otpTypeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="EXPIRY_MINUTES")
	private BigDecimal expiryMinutes;

	@Column(name="EXPIRY_TRIES")
	private BigDecimal expiryTries;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="OTP_TYPE_CODE")
	private String otpTypeCode;

	@Column(name="OTP_TYPE_DESCR")
	private String otpTypeDescr;

	@Column(name="OTP_TYPE_NAME")
	private String otpTypeName;

	@Column(name="REGULAR_EXPRESSION")
	private String regularExpression;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblOtp
	@OneToMany(mappedBy="lkpOtpType")
	private List<TblOtp> tblOtps;

	public LkpOtpType() {
	}

	public long getOtpTypeId() {
		return this.otpTypeId;
	}

	public void setOtpTypeId(long otpTypeId) {
		this.otpTypeId = otpTypeId;
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

	public BigDecimal getExpiryMinutes() {
		return this.expiryMinutes;
	}

	public void setExpiryMinutes(BigDecimal expiryMinutes) {
		this.expiryMinutes = expiryMinutes;
	}

	public BigDecimal getExpiryTries() {
		return this.expiryTries;
	}

	public void setExpiryTries(BigDecimal expiryTries) {
		this.expiryTries = expiryTries;
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

	public String getOtpTypeCode() {
		return this.otpTypeCode;
	}

	public void setOtpTypeCode(String otpTypeCode) {
		this.otpTypeCode = otpTypeCode;
	}

	public String getOtpTypeDescr() {
		return this.otpTypeDescr;
	}

	public void setOtpTypeDescr(String otpTypeDescr) {
		this.otpTypeDescr = otpTypeDescr;
	}

	public String getOtpTypeName() {
		return this.otpTypeName;
	}

	public void setOtpTypeName(String otpTypeName) {
		this.otpTypeName = otpTypeName;
	}

	public String getRegularExpression() {
		return this.regularExpression;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblOtp> getTblOtps() {
		return this.tblOtps;
	}

	public void setTblOtps(List<TblOtp> tblOtps) {
		this.tblOtps = tblOtps;
	}

	public TblOtp addTblOtp(TblOtp tblOtp) {
		getTblOtps().add(tblOtp);
		tblOtp.setLkpOtpType(this);

		return tblOtp;
	}

	public TblOtp removeTblOtp(TblOtp tblOtp) {
		getTblOtps().remove(tblOtp);
		tblOtp.setLkpOtpType(null);

		return tblOtp;
	}

}