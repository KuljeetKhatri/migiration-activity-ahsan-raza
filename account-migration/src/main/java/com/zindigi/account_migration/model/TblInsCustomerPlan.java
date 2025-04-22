package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_INS_CUSTOMER_PLAN database table.
 * 
 */
@Entity
@Table(name="TBL_INS_CUSTOMER_PLAN")
@NamedQuery(name="TblInsCustomerPlan.findAll", query="SELECT t FROM TblInsCustomerPlan t")
public class TblInsCustomerPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_INS_CUSTOMER_PLAN_INSCUSTOMERPLANID_GENERATOR", sequenceName="TBL_INS_CUSTOMER_PLAN_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_INS_CUSTOMER_PLAN_INSCUSTOMERPLANID_GENERATOR")
	@Column(name="INS_CUSTOMER_PLAN_ID")
	private long insCustomerPlanId;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTIVATION_DATE")
	private Date activationDate;

	private String beneficiary;

	@Temporal(TemporalType.DATE)
	@Column(name="CANCELLATION_DATE")
	private Date cancellationDate;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENT_STATUS")
	private String currentStatus;

	private String deduction;

	private String email;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal payment;

	private String subscribe;

	@Column(name="TRANS_HEAD_ID")
	private BigDecimal transHeadId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	@JsonIgnore
	private TblAccountModel tblAccountModel;

	//bi-directional many-to-one association to TblInsProductPlan
	@ManyToOne
	@JoinColumn(name="INS_PRODUCT_PLAN_ID")
	@JsonIgnore
	private TblInsProductPlan tblInsProductPlan;

	@Transient
	private String message;

	public TblInsCustomerPlan() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getInsCustomerPlanId() {
		return this.insCustomerPlanId;
	}

	public void setInsCustomerPlanId(long insCustomerPlanId) {
		this.insCustomerPlanId = insCustomerPlanId;
	}

	public Date getActivationDate() {
		return this.activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public Date getCancellationDate() {
		return this.cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
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

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDeduction() {
		return this.deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public BigDecimal getPayment() {
		return this.payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public String getSubscribe() {
		return this.subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public BigDecimal getTransHeadId() {
		return this.transHeadId;
	}

	public void setTransHeadId(BigDecimal transHeadId) {
		this.transHeadId = transHeadId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblInsProductPlan getTblInsProductPlan() {
		return this.tblInsProductPlan;
	}

	public void setTblInsProductPlan(TblInsProductPlan tblInsProductPlan) {
		this.tblInsProductPlan = tblInsProductPlan;
	}

	public TblAccountModel getTblAccountModel() {
		return tblAccountModel;
	}

	public void setTblAccountModel(TblAccountModel tblAccountModel) {
		this.tblAccountModel = tblAccountModel;
	}
}