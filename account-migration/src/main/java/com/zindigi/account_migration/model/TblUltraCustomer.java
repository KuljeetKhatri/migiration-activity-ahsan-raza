package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ULTRA_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="TBL_ULTRA_CUSTOMER")
@NamedQuery(name="TblUltraCustomer.findAll", query="SELECT t FROM TblUltraCustomer t")
public class TblUltraCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ULTRA_CUSTOMER_ULTRACUSTOMERID_GENERATOR", sequenceName="TBL_ULTRA_CUSTOMER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ULTRA_CUSTOMER_ULTRACUSTOMERID_GENERATOR")
	@Column(name="ULTRA_CUSTOMER_ID")
	private long ultraCustomerId;

	@Column(name="ACCOUNT_LEVEL_ID")
	private BigDecimal accountLevelId;

	@Column(name="CHEQUE_BOOK")
	private String chequeBook;

	@Column(name="CLS_CASE_ID")
	private String clsCaseId;

	@Column(name="CLS_RESPONSE_CODE")
	private String clsResponseCode;

	@Column(name="CLS_STATUS")
	private String clsStatus;

	private String comments;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DECLARATION")
	private String declaration;

	@Column(name="DUAL_NATIONALITY")
	private String dualNationality;

	private String email;

	@Column(name="FOREIGN_TAX_NUMBER")
	private String foreignTaxNumber;

	@Column(name="GREEN_CARD_HOLDER")
	private String greenCardHolder;

	@Column(name="HOUSE_NO")
	private String houseNo;

	@Column(name="IS_ACCOUNT_DETAIL_VERIFIED")
	private String isAccountDetailVerified;

	@Column(name="IS_POI_VERIFIED")
	private String isPoiVerified;

	@Column(name="KYC_VERIFIED")
	private String kycVerified;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String pmd;

	@Column(name="RESIDENCE_ADDRESS")
	private String residenceAddress;

	@Column(name="SIGNATURE_VERIFIED")
	private String signatureVerified;

	private String status;

	@Column(name="STATUS_DESCR")
	private String statusDescr;

	@Column(name="STREET_NO")
	private String streetNo;

	@Column(name="TAX_IDENTIFICATION_NUMBER")
	private String taxIdentificationNumber;

	@Column(name="ULTRA_ACCOUNT_TYPE")
	private String ultraAccountType;

	private BigDecimal updateindex;

	@Column(name="UTILITY_BILL_UPLOADED")
	private String utilityBillUploaded;

	//bi-directional many-to-one association to LkpArea
	@ManyToOne
	@JoinColumn(name="AREA_ID")
	private LkpArea lkpArea;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_OF_TAX_RESIDENCE_ID")
	private LkpCountry lkpCountry1;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_OF_BIRTH_ID")
	private LkpCountry lkpCountry2;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry3;

	//bi-directional many-to-one association to LkpFederalTaxClassification
	@ManyToOne
	@JoinColumn(name="FEDERAL_TAX_CLASSIFICATION_ID")
	private LkpFederalTaxClassification lkpFederalTaxClassification;

	//bi-directional many-to-one association to LkpMonthlySpending
	@ManyToOne
	@JoinColumn(name="EXPECTED_MONTHLY_CASHFLOW_ID")
	private LkpMonthlySpending lkpMonthlySpending;

	//bi-directional many-to-one association to LkpProfession
	@ManyToOne
	@JoinColumn(name="OCCUPATION_ID")
	private LkpOccupation lkpOccupation;

	//bi-directional many-to-one association to LkpUltraUsage
	@ManyToOne
	@JoinColumn(name="ULTRA_USAGE_ID")
	private LkpUltraUsage lkpUltraUsage;

	@ManyToOne
	@JoinColumn(name="DOC_STATUS_ID")
	private LkpDocStatus lkpDocStatus;

	//bi-directional many-to-one association to TblCustomer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private TblCustomer tblCustomer;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="VIDEO_ID")
	private TblDocument tblDocument1;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="CNIC_FRONT_ID")
	private TblDocument tblDocument2;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="PROOF_OF_PROFESSION_ID")
	private TblDocument tblDocument3;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="PROOF_OF_INCOME_ID")
	private TblDocument tblDocument4;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="SIGNATURE_ID")
	private TblDocument tblDocument5;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="CNIC_BACK_ID")
	private TblDocument tblDocument6;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="SELFIE_ID")
	private TblDocument tblDocument7;
	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="B_FORM_ID")
	private TblDocument tblDocument8;
	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="PARENT_CNIC_FRONT_ID")
	private TblDocument tblDocument9;
	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="PARENT_CNIC_BACK_ID")
	private TblDocument tblDocument10;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="UTILITY_BILL_ID")
	private TblDocument tblDocument11;

	//bi-directional many-to-one association to TblUltraCustomerDoc
	@OneToMany(mappedBy="tblUltraCustomer")
	private List<TblUltraCustomerDoc> tblUltraCustomerDocs;

	//bi-directional many-to-one association to TblUltraCustomerFatca
	@OneToMany(mappedBy="tblUltraCustomer")
	private List<TblUltraCustomerFatca> tblUltraCustomerFatcas;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	@Column(name="IS_T_AND_C_ACCEPTED")
	private String isTandCAccepted;

	@Column(name="SELF_DECLARATION_ACCEPTED")
	private String selfDeclarationAccepted;

	public String getIsTandCAccepted() {
		return isTandCAccepted;
	}

	public void setIsTandCAccepted(String isTandCAccepted) {
		this.isTandCAccepted = isTandCAccepted;
	}

	public String getSelfDeclarationAccepted() {
		return selfDeclarationAccepted;
	}

	public void setSelfDeclarationAccepted(String selfDeclarationAccepted) {
		this.selfDeclarationAccepted = selfDeclarationAccepted;
	}

	public TblUltraCustomer() {
	}

	public TblDocument getTblDocument11() {
		return tblDocument11;
	}

	public void setTblDocument11(TblDocument tblDocument11) {
		this.tblDocument11 = tblDocument11;
	}

	public long getUltraCustomerId() {
		return this.ultraCustomerId;
	}

	public void setUltraCustomerId(long ultraCustomerId) {
		this.ultraCustomerId = ultraCustomerId;
	}

	public BigDecimal getAccountLevelId() {
		return this.accountLevelId;
	}

	public void setAccountLevelId(BigDecimal accountLevelId) {
		this.accountLevelId = accountLevelId;
	}

	public String getChequeBook() {
		return this.chequeBook;
	}

	public void setChequeBook(String chequeBook) {
		this.chequeBook = chequeBook;
	}

	public String getClsCaseId() {
		return this.clsCaseId;
	}

	public void setClsCaseId(String clsCaseId) {
		this.clsCaseId = clsCaseId;
	}

	public String getClsResponseCode() {
		return this.clsResponseCode;
	}

	public void setClsResponseCode(String clsResponseCode) {
		this.clsResponseCode = clsResponseCode;
	}

	public String getClsStatus() {
		return this.clsStatus;
	}

	public void setClsStatus(String clsStatus) {
		this.clsStatus = clsStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getDeclaration() {
		return this.declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public String getDualNationality() {
		return this.dualNationality;
	}

	public void setDualNationality(String dualNationality) {
		this.dualNationality = dualNationality;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getForeignTaxNumber() {
		return this.foreignTaxNumber;
	}

	public void setForeignTaxNumber(String foreignTaxNumber) {
		this.foreignTaxNumber = foreignTaxNumber;
	}

	public String getGreenCardHolder() {
		return this.greenCardHolder;
	}

	public void setGreenCardHolder(String greenCardHolder) {
		this.greenCardHolder = greenCardHolder;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getIsAccountDetailVerified() {
		return this.isAccountDetailVerified;
	}

	public void setIsAccountDetailVerified(String isAccountDetailVerified) {
		this.isAccountDetailVerified = isAccountDetailVerified;
	}

	public String getIsPoiVerified() {
		return this.isPoiVerified;
	}

	public void setIsPoiVerified(String isPoiVerified) {
		this.isPoiVerified = isPoiVerified;
	}

	public String getKycVerified() {
		return this.kycVerified;
	}

	public void setKycVerified(String kycVerified) {
		this.kycVerified = kycVerified;
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

	public String getPmd() {
		return this.pmd;
	}

	public void setPmd(String pmd) {
		this.pmd = pmd;
	}

	public String getResidenceAddress() {
		return this.residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getSignatureVerified() {
		return this.signatureVerified;
	}

	public void setSignatureVerified(String signatureVerified) {
		this.signatureVerified = signatureVerified;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescr() {
		return this.statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getStreetNo() {
		return this.streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getTaxIdentificationNumber() {
		return this.taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public String getUltraAccountType() {
		return this.ultraAccountType;
	}

	public void setUltraAccountType(String ultraAccountType) {
		this.ultraAccountType = ultraAccountType;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUtilityBillUploaded() {
		return this.utilityBillUploaded;
	}

	public void setUtilityBillUploaded(String utilityBillUploaded) {
		this.utilityBillUploaded = utilityBillUploaded;
	}

	public LkpArea getLkpArea() {
		return this.lkpArea;
	}

	public void setLkpArea(LkpArea lkpArea) {
		this.lkpArea = lkpArea;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpCountry getLkpCountry1() {
		return this.lkpCountry1;
	}

	public void setLkpCountry1(LkpCountry lkpCountry1) {
		this.lkpCountry1 = lkpCountry1;
	}

	public LkpCountry getLkpCountry2() {
		return this.lkpCountry2;
	}

	public void setLkpCountry2(LkpCountry lkpCountry2) {
		this.lkpCountry2 = lkpCountry2;
	}

	public LkpCountry getLkpCountry3() {
		return this.lkpCountry3;
	}

	public void setLkpCountry3(LkpCountry lkpCountry3) {
		this.lkpCountry3 = lkpCountry3;
	}

	public LkpFederalTaxClassification getLkpFederalTaxClassification() {
		return this.lkpFederalTaxClassification;
	}

	public void setLkpFederalTaxClassification(LkpFederalTaxClassification lkpFederalTaxClassification) {
		this.lkpFederalTaxClassification = lkpFederalTaxClassification;
	}

	public LkpMonthlySpending getLkpMonthlySpending() {
		return this.lkpMonthlySpending;
	}

	public void setLkpMonthlySpending(LkpMonthlySpending lkpMonthlySpending) {
		this.lkpMonthlySpending = lkpMonthlySpending;
	}

	public LkpOccupation getLkpOccupation() {
		return lkpOccupation;
	}

	public void setLkpOccupation(LkpOccupation lkpOccupation) {
		this.lkpOccupation = lkpOccupation;
	}

	public LkpUltraUsage getLkpUltraUsage() {
		return this.lkpUltraUsage;
	}

	public void setLkpUltraUsage(LkpUltraUsage lkpUltraUsage) {
		this.lkpUltraUsage = lkpUltraUsage;
	}

	public TblCustomer getTblCustomer() {
		return this.tblCustomer;
	}

	public void setTblCustomer(TblCustomer tblCustomer) {
		this.tblCustomer = tblCustomer;
	}

	public TblDocument getTblDocument1() {
		return this.tblDocument1;
	}

	public void setTblDocument1(TblDocument tblDocument1) {
		this.tblDocument1 = tblDocument1;
	}

	public TblDocument getTblDocument2() {
		return this.tblDocument2;
	}

	public void setTblDocument2(TblDocument tblDocument2) {
		this.tblDocument2 = tblDocument2;
	}

	public TblDocument getTblDocument3() {
		return this.tblDocument3;
	}

	public void setTblDocument3(TblDocument tblDocument3) {
		this.tblDocument3 = tblDocument3;
	}

	public TblDocument getTblDocument4() {
		return this.tblDocument4;
	}

	public void setTblDocument4(TblDocument tblDocument4) {
		this.tblDocument4 = tblDocument4;
	}

	public TblDocument getTblDocument5() {
		return this.tblDocument5;
	}

	public void setTblDocument5(TblDocument tblDocument5) {
		this.tblDocument5 = tblDocument5;
	}

	public TblDocument getTblDocument6() {
		return this.tblDocument6;
	}

	public void setTblDocument6(TblDocument tblDocument6) {
		this.tblDocument6 = tblDocument6;
	}

	public TblDocument getTblDocument7() {
		return this.tblDocument7;
	}

	public void setTblDocument7(TblDocument tblDocument7) {
		this.tblDocument7 = tblDocument7;
	}

	public List<TblUltraCustomerDoc> getTblUltraCustomerDocs() {
		return this.tblUltraCustomerDocs;
	}

	public void setTblUltraCustomerDocs(List<TblUltraCustomerDoc> tblUltraCustomerDocs) {
		this.tblUltraCustomerDocs = tblUltraCustomerDocs;
	}

	public TblUltraCustomerDoc addTblUltraCustomerDoc(TblUltraCustomerDoc tblUltraCustomerDoc) {
		getTblUltraCustomerDocs().add(tblUltraCustomerDoc);
		tblUltraCustomerDoc.setTblUltraCustomer(this);

		return tblUltraCustomerDoc;
	}

	public TblUltraCustomerDoc removeTblUltraCustomerDoc(TblUltraCustomerDoc tblUltraCustomerDoc) {
		getTblUltraCustomerDocs().remove(tblUltraCustomerDoc);
		tblUltraCustomerDoc.setTblUltraCustomer(null);

		return tblUltraCustomerDoc;
	}

	public List<TblUltraCustomerFatca> getTblUltraCustomerFatcas() {
		return this.tblUltraCustomerFatcas;
	}

	public void setTblUltraCustomerFatcas(List<TblUltraCustomerFatca> tblUltraCustomerFatcas) {
		this.tblUltraCustomerFatcas = tblUltraCustomerFatcas;
	}

	public TblUltraCustomerFatca addTblUltraCustomerFatca(TblUltraCustomerFatca tblUltraCustomerFatca) {
		getTblUltraCustomerFatcas().add(tblUltraCustomerFatca);
		tblUltraCustomerFatca.setTblUltraCustomer(this);

		return tblUltraCustomerFatca;
	}

	public TblUltraCustomerFatca removeTblUltraCustomerFatca(TblUltraCustomerFatca tblUltraCustomerFatca) {
		getTblUltraCustomerFatcas().remove(tblUltraCustomerFatca);
		tblUltraCustomerFatca.setTblUltraCustomer(null);

		return tblUltraCustomerFatca;
	}

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public LkpDocStatus getLkpDocStatus() {
		return lkpDocStatus;
	}

	public void setLkpDocStatus(LkpDocStatus lkpDocStatus) {
		this.lkpDocStatus = lkpDocStatus;
	}

	public TblDocument getTblDocument8() {
		return tblDocument8;
	}

	public void setTblDocument8(TblDocument tblDocument8) {
		this.tblDocument8 = tblDocument8;
	}

	public TblDocument getTblDocument9() {
		return tblDocument9;
	}

	public void setTblDocument9(TblDocument tblDocument9) {
		this.tblDocument9 = tblDocument9;
	}

	public TblDocument getTblDocument10() {
		return tblDocument10;
	}

	public void setTblDocument10(TblDocument tblDocument10) {
		this.tblDocument10 = tblDocument10;
	}
}