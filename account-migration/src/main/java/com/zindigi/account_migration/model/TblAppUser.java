package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_APP_USER database table.
 * 
 */
@Entity
@Table(name="TBL_APP_USER")
@NamedQuery(name="TblAppUser.findAll", query="SELECT t FROM TblAppUser t")
public class TblAppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="TBL_APP_USER_APPUSERID_GENERATOR", sequenceName="TBL_APP_USER_SEQ",allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_APP_USER_APPUSERID_GENERATOR")
	@Column(name="APP_USER_ID")
	private long appUserId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String password;

	private BigDecimal updateindex;

	private String username;

	private String passwordPin;

	@Column(name = "PASSWORD_EXPIRY_DATE")
	private Date passwordExpiryDate;

	@JsonIgnore
	//bi-directional many-to-one association to LkpAppUserType
	@ManyToOne
	@JoinColumn(name="APP_USER_TYPE_ID")
	private LkpAppUserType lkpAppUserType;

	@JsonIgnore
	//bi-directional many-to-one association to TblAgent
	@OneToOne
	@JoinColumn(name="AGENT_ID")
	private TblAgent tblAgent;

	@JsonIgnore
	//bi-directional many-to-one association to TblCustomer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private TblCustomer tblCustomer;

	@JsonIgnore
	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	@JsonIgnore
	//bi-directional many-to-one association to TblDeviceInfo
	@OneToMany(mappedBy="tblAppUser")
	private List<TblDeviceInfo> tblDeviceInfos;

	public TblAppUser() {
	}

	public long getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(long appUserId) {
		this.appUserId = appUserId;
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

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public String getPasswordPin() {
		return passwordPin;
	}

	public void setPasswordPin(String passwordPin) {
		this.passwordPin = passwordPin;
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

	public LkpAppUserType getLkpAppUserType() {
		return this.lkpAppUserType;
	}

	public void setLkpAppUserType(LkpAppUserType lkpAppUserType) {
		this.lkpAppUserType = lkpAppUserType;
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

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public List<TblDeviceInfo> getTblDeviceInfos() {
		return this.tblDeviceInfos;
	}

	public void setTblDeviceInfos(List<TblDeviceInfo> tblDeviceInfos) {
		this.tblDeviceInfos = tblDeviceInfos;
	}

	public TblDeviceInfo addTblDeviceInfo(TblDeviceInfo tblDeviceInfo) {
		getTblDeviceInfos().add(tblDeviceInfo);
		tblDeviceInfo.setTblAppUser(this);

		return tblDeviceInfo;
	}

	public TblDeviceInfo removeTblDeviceInfo(TblDeviceInfo tblDeviceInfo) {
		getTblDeviceInfos().remove(tblDeviceInfo);
		tblDeviceInfo.setTblAppUser(null);

		return tblDeviceInfo;
	}

	public Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}

	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}
}