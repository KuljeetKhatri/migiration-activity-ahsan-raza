package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_INS_BENEFIT database table.
 * 
 */
@Entity
@Table(name="TBL_INS_BENEFIT")
@NamedQuery(name="TblInsBenefit.findAll", query="SELECT t FROM TblInsBenefit t")
public class TblInsBenefit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_INS_BENEFIT_INSBENEFITID_GENERATOR", sequenceName="TBL_INS_BENEFIT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_INS_BENEFIT_INSBENEFITID_GENERATOR")
	@Column(name="INS_BENEFIT_ID")
	private long insBenefitId;

	private BigDecimal amount;

	@Column(name="BENEFIT_NAME")
	private String benefitName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;


	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblInsProductPlan
	@ManyToOne
	@JoinColumn(name="INS_PRODUCT_PLAN_ID")
	private TblInsProductPlan tblInsProductPlan;

	public TblInsBenefit() {
	}

	public long getInsBenefitId() {
		return this.insBenefitId;
	}

	public void setInsBenefitId(long insBenefitId) {
		this.insBenefitId = insBenefitId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBenefitName() {
		return this.benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
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

}