package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_INS_CUSTOMER_BENEFICIARY database table.
 * 
 */
@Entity
@Table(name="TBL_INS_CUSTOMER_BENEFICIARY")
@NamedQuery(name="TblInsCustomerBeneficiary.findAll", query="SELECT t FROM TblInsCustomerBeneficiary t")
public class TblInsCustomerBeneficiary implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="TBL_INS_CUSTOMER_BENEFICIARY_INSCUSTOMERBENEFICIARYID_GENERATOR", sequenceName="TBL_INS_CUSTOMER_BENEFICIARY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_INS_CUSTOMER_BENEFICIARY_INSCUSTOMERBENEFICIARYID_GENERATOR")
	@Column(name="INS_CUSTOMER_BENEFICIARY_ID")
	private long insCustomerBeneficiaryId;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String email;

	@Column(name="GUARDIAN_MOBILE")
	private String guardianMobile;

	@Column(name="GUARDIAN_NAME")
	private String guardianName;

	@Column(name="GUARDIAN_RELATIONSHIP_ID")
	private BigDecimal guardianRelationshipId;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_UNDER_AGE")
	private String isUnderAge;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	@Column(name="RELATIONSHIP_ID")
	private BigDecimal relationshipId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccount;

	//bi-directional many-to-one association to TblInsProductPlan
	@ManyToOne
	@JoinColumn(name="INS_PRODUCT_PLAN_ID")
	private TblInsProductPlan tblInsProductPlan;

	public TblInsCustomerBeneficiary() {
	}

	public long getInsCustomerBeneficiaryId() {
		return this.insCustomerBeneficiaryId;
	}

	public void setInsCustomerBeneficiaryId(long insCustomerBeneficiaryId) {
		this.insCustomerBeneficiaryId = insCustomerBeneficiaryId;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGuardianMobile() {
		return this.guardianMobile;
	}

	public void setGuardianMobile(String guardianMobile) {
		this.guardianMobile = guardianMobile;
	}

	public String getGuardianName() {
		return this.guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public BigDecimal getGuardianRelationshipId() {
		return this.guardianRelationshipId;
	}

	public void setGuardianRelationshipId(BigDecimal guardianRelationshipId) {
		this.guardianRelationshipId = guardianRelationshipId;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsUnderAge() {
		return this.isUnderAge;
	}

	public void setIsUnderAge(String isUnderAge) {
		this.isUnderAge = isUnderAge;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getRelationshipId() {
		return this.relationshipId;
	}

	public void setRelationshipId(BigDecimal relationshipId) {
		this.relationshipId = relationshipId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblAccountModel getTblAccount() {
		return this.tblAccount;
	}

	public void setTblAccount(TblAccountModel tblAccount) {
		this.tblAccount = tblAccount;
	}

	public TblInsProductPlan getTblInsProductPlan() {
		return this.tblInsProductPlan;
	}

	public void setTblInsProductPlan(TblInsProductPlan tblInsProductPlan) {
		this.tblInsProductPlan = tblInsProductPlan;
	}

}