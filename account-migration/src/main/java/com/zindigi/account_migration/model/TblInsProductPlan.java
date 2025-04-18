package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_INS_PRODUCT_PLAN database table.
 * 
 */
@Entity
@Table(name="TBL_INS_PRODUCT_PLAN")
@NamedQuery(name="TblInsProductPlan.findAll", query="SELECT t FROM TblInsProductPlan t")
public class TblInsProductPlan implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="TBL_INS_PRODUCT_PLAN_INSPRODUCTPLANID_GENERATOR", sequenceName="TBL_INS_PRODUCT_PLAN_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_INS_PRODUCT_PLAN_INSPRODUCTPLANID_GENERATOR")
	@Column(name="INS_PRODUCT_PLAN_ID")
	private long insProductPlanId;

	@Column(name="ANNUAL_PREMIUM")
	private BigDecimal annualPremium;

	private String category;

	@Column(name="COVERAGE_TERM")
	private String coverageTerm;

	@Column(name="COVERED_AGE")
	private String coveredAge;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="ELIMINATION_PERIOD")
	private String eliminationPeriod;

	@Column(name="ENROLLMENT_AGE")
	private String enrollmentAge;

	@Column(name="FREELOOK_PERIOD")
	private String freelookPeriod;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MORE_DETAILS")
	private String moreDetails;

	@Column(name="PLAN_NAME")
	private String planName;

	private BigDecimal price;

	private String thumbnail;

	private BigDecimal updateindex;

	@Column(name="WHATS_NOT_COVERED")
	private String whatsNotCovered;

	//bi-directional many-to-one association to TblInsBenefit
	@OneToMany(mappedBy="tblInsProductPlan")
	@JsonIgnore
	private List<TblInsBenefit> tblInsBenefits;

	//bi-directional many-to-one association to TblInsCustomerBeneficiary
	@OneToMany(mappedBy="tblInsProductPlan")
	@JsonIgnore
	private List<TblInsCustomerBeneficiary> tblInsCustomerBeneficiaries;

	//bi-directional many-to-one association to TblInsCustomerPlan
	@OneToMany(mappedBy="tblInsProductPlan")
	@JsonIgnore
	private List<TblInsCustomerPlan> tblInsCustomerPlans;

	//bi-directional many-to-one association to TblInsProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblInsProductPlan() {
	}

	public long getInsProductPlanId() {
		return this.insProductPlanId;
	}

	public void setInsProductPlanId(long insProductPlanId) {
		this.insProductPlanId = insProductPlanId;
	}

	public BigDecimal getAnnualPremium() {
		return this.annualPremium;
	}

	public void setAnnualPremium(BigDecimal annualPremium) {
		this.annualPremium = annualPremium;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCoverageTerm() {
		return this.coverageTerm;
	}

	public void setCoverageTerm(String coverageTerm) {
		this.coverageTerm = coverageTerm;
	}

	public String getCoveredAge() {
		return this.coveredAge;
	}

	public void setCoveredAge(String coveredAge) {
		this.coveredAge = coveredAge;
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

	public String getEliminationPeriod() {
		return this.eliminationPeriod;
	}

	public void setEliminationPeriod(String eliminationPeriod) {
		this.eliminationPeriod = eliminationPeriod;
	}

	public String getEnrollmentAge() {
		return this.enrollmentAge;
	}

	public void setEnrollmentAge(String enrollmentAge) {
		this.enrollmentAge = enrollmentAge;
	}

	public String getFreelookPeriod() {
		return this.freelookPeriod;
	}

	public void setFreelookPeriod(String freelookPeriod) {
		this.freelookPeriod = freelookPeriod;
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

	public String getMoreDetails() {
		return this.moreDetails;
	}

	public void setMoreDetails(String moreDetails) {
		this.moreDetails = moreDetails;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getWhatsNotCovered() {
		return this.whatsNotCovered;
	}

	public void setWhatsNotCovered(String whatsNotCovered) {
		this.whatsNotCovered = whatsNotCovered;
	}

	public List<TblInsBenefit> getTblInsBenefits() {
		return this.tblInsBenefits;
	}

	public void setTblInsBenefits(List<TblInsBenefit> tblInsBenefits) {
		this.tblInsBenefits = tblInsBenefits;
	}

	public TblInsBenefit addTblInsBenefit(TblInsBenefit tblInsBenefit) {
		getTblInsBenefits().add(tblInsBenefit);
		tblInsBenefit.setTblInsProductPlan(this);

		return tblInsBenefit;
	}

	public TblInsBenefit removeTblInsBenefit(TblInsBenefit tblInsBenefit) {
		getTblInsBenefits().remove(tblInsBenefit);
		tblInsBenefit.setTblInsProductPlan(null);

		return tblInsBenefit;
	}

	public List<TblInsCustomerBeneficiary> getTblInsCustomerBeneficiaries() {
		return this.tblInsCustomerBeneficiaries;
	}

	public void setTblInsCustomerBeneficiaries(List<TblInsCustomerBeneficiary> tblInsCustomerBeneficiaries) {
		this.tblInsCustomerBeneficiaries = tblInsCustomerBeneficiaries;
	}

	public TblInsCustomerBeneficiary addTblInsCustomerBeneficiary(TblInsCustomerBeneficiary tblInsCustomerBeneficiary) {
		getTblInsCustomerBeneficiaries().add(tblInsCustomerBeneficiary);
		tblInsCustomerBeneficiary.setTblInsProductPlan(this);

		return tblInsCustomerBeneficiary;
	}

	public TblInsCustomerBeneficiary removeTblInsCustomerBeneficiary(TblInsCustomerBeneficiary tblInsCustomerBeneficiary) {
		getTblInsCustomerBeneficiaries().remove(tblInsCustomerBeneficiary);
		tblInsCustomerBeneficiary.setTblInsProductPlan(null);

		return tblInsCustomerBeneficiary;
	}

	public List<TblInsCustomerPlan> getTblInsCustomerPlans() {
		return this.tblInsCustomerPlans;
	}

	public void setTblInsCustomerPlans(List<TblInsCustomerPlan> tblInsCustomerPlans) {
		this.tblInsCustomerPlans = tblInsCustomerPlans;
	}

	public TblInsCustomerPlan addTblInsCustomerPlan(TblInsCustomerPlan tblInsCustomerPlan) {
		getTblInsCustomerPlans().add(tblInsCustomerPlan);
		tblInsCustomerPlan.setTblInsProductPlan(this);

		return tblInsCustomerPlan;
	}

	public TblInsCustomerPlan removeTblInsCustomerPlan(TblInsCustomerPlan tblInsCustomerPlan) {
		getTblInsCustomerPlans().remove(tblInsCustomerPlan);
		tblInsCustomerPlan.setTblInsProductPlan(null);

		return tblInsCustomerPlan;
	}

	public TblProduct getTblProduct() {
		return tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}
}