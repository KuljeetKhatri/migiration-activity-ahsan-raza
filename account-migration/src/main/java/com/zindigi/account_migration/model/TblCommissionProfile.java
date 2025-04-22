package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpAccountClassification;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_COMMISSION_PROFILE database table.
 * 
 */
@Entity
@Table(name="TBL_COMMISSION_PROFILE")
@NamedQuery(name="TblCommissionProfile.findAll", query="SELECT t FROM TblCommissionProfile t")
public class TblCommissionProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMMISSION_PROFILE_COMMISSIONPROFILEID_GENERATOR", sequenceName="TBL_COMMISSION_PROFILE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMMISSION_PROFILE_COMMISSIONPROFILEID_GENERATOR")
	@Column(name="COMMISSION_PROFILE_ID")
	private long commissionProfileId;

	@Column(name="COMMISSION_AMOUNT")
	private BigDecimal commissionAmount;

	@Column(name="COMMISSION_PERCENTAGE")
	private BigDecimal commissionPercentage;

	@Column(name="COMMISSION_PROFILE_NAME")
	private String commissionProfileName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private BigDecimal updateindex;

	@Column(name="WHT_CALCULATION_TYPE")
	private String whtCalculationType;

	//bi-directional many-to-one association to TblAgent
	@JsonIgnore
	@OneToMany(mappedBy="tblCommissionProfile")
	private List<TblAgent> tblAgents;

	//bi-directional many-to-one association to TblCommissionProduct
	@JsonIgnore
	@OneToMany(mappedBy="tblCommissionProfile")
	private List<TblCommissionProduct> tblCommissionProducts;

	//bi-directional many-to-one association to LkpAccountClassification
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ACCOUNT_CLASSIFICATION_ID")
	private LkpAccountClassification lkpAccountClassification;

	//bi-directional many-to-one association to LkpFeeType
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COMMISSION_TYPE_ID")
	private LkpFeeType lkpFeeType;

	//bi-directional many-to-one association to LkpStatus
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COMMISSION_GL_ACCOUNT_ID")
	private TblAccountModel tblAccount1;

//	bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="WHT_GL_ACCOUNT_ID")
	private TblAccountModel tblAccount2;

	//bi-directional many-to-one association to TblCommissionSlab
	@JsonIgnore
	@OneToMany(mappedBy="tblCommissionProfile")
	private List<TblCommissionSlab> tblCommissionSlabs;

	public TblCommissionProfile() {
	}

	public long getCommissionProfileId() {
		return this.commissionProfileId;
	}

	public void setCommissionProfileId(long commissionProfileId) {
		this.commissionProfileId = commissionProfileId;
	}

	public BigDecimal getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public BigDecimal getCommissionPercentage() {
		return this.commissionPercentage;
	}

	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public String getCommissionProfileName() {
		return this.commissionProfileName;
	}

	public void setCommissionProfileName(String commissionProfileName) {
		this.commissionProfileName = commissionProfileName;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getWhtCalculationType() {
		return this.whtCalculationType;
	}

	public void setWhtCalculationType(String whtCalculationType) {
		this.whtCalculationType = whtCalculationType;
	}

	public List<TblAgent> getTblAgents() {
		return this.tblAgents;
	}

	public void setTblAgents(List<TblAgent> tblAgents) {
		this.tblAgents = tblAgents;
	}

	public TblAgent addTblAgent(TblAgent tblAgent) {
		getTblAgents().add(tblAgent);
		tblAgent.setTblCommissionProfile(this);

		return tblAgent;
	}

	public TblAgent removeTblAgent(TblAgent tblAgent) {
		getTblAgents().remove(tblAgent);
		tblAgent.setTblCommissionProfile(null);

		return tblAgent;
	}

	public List<TblCommissionProduct> getTblCommissionProducts() {
		return this.tblCommissionProducts;
	}

	public void setTblCommissionProducts(List<TblCommissionProduct> tblCommissionProducts) {
		this.tblCommissionProducts = tblCommissionProducts;
	}

	public TblCommissionProduct addTblCommissionProduct(TblCommissionProduct tblCommissionProduct) {
		getTblCommissionProducts().add(tblCommissionProduct);
		tblCommissionProduct.setTblCommissionProfile(this);

		return tblCommissionProduct;
	}

	public TblCommissionProduct removeTblCommissionProduct(TblCommissionProduct tblCommissionProduct) {
		getTblCommissionProducts().remove(tblCommissionProduct);
		tblCommissionProduct.setTblCommissionProfile(null);

		return tblCommissionProduct;
	}

	public LkpAccountClassification getLkpAccountClassification() {
		return this.lkpAccountClassification;
	}

	public void setLkpAccountClassification(LkpAccountClassification lkpAccountClassification) {
		this.lkpAccountClassification = lkpAccountClassification;
	}

	public LkpFeeType getLkpFeeType() {
		return this.lkpFeeType;
	}

	public void setLkpFeeType(LkpFeeType lkpFeeType) {
		this.lkpFeeType = lkpFeeType;
	}

	public LkpStatus getLkpStatus() {
		return this.lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblAccountModel getTblAccountModel1() {
		return this.tblAccount1;
	}

	public void setTblAccountModel1(TblAccountModel tblAccount1) {
		this.tblAccount1 = tblAccount1;
	}

	public TblAccountModel getTblAccountModel2() {
		return this.tblAccount2;
	}

	public void setTblAccountModel2(TblAccountModel tblAccount2) {
		this.tblAccount2 = tblAccount2;
	}

	public List<TblCommissionSlab> getTblCommissionSlabs() {
		return this.tblCommissionSlabs;
	}

	public void setTblCommissionSlabs(List<TblCommissionSlab> tblCommissionSlabs) {
		this.tblCommissionSlabs = tblCommissionSlabs;
	}

	public TblCommissionSlab addTblCommissionSlab(TblCommissionSlab tblCommissionSlab) {
		getTblCommissionSlabs().add(tblCommissionSlab);
		tblCommissionSlab.setTblCommissionProfile(this);

		return tblCommissionSlab;
	}

	public TblCommissionSlab removeTblCommissionSlab(TblCommissionSlab tblCommissionSlab) {
		getTblCommissionSlabs().remove(tblCommissionSlab);
		tblCommissionSlab.setTblCommissionProfile(null);

		return tblCommissionSlab;
	}

}