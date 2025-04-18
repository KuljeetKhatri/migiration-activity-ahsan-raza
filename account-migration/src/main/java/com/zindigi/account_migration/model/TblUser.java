package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_USER database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_USERID_GENERATOR", sequenceName="TBL_USER_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="CONFIRM_PASSWORD")
	private String confirmPassword;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	@Column(name="EMPLOYEE_ID")
	private String employeeId;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	private String password;

	private BigDecimal updateindex;

	@Column(name="USER_TYPE")
	private String userType;

	private String username;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	public TblUser() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

}