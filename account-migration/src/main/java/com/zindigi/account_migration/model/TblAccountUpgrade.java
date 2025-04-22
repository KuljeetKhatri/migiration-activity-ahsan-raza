package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.*;
import com.mfs.commonservice.model.LkpCity;
import com.zindigi.account_migration.dto.LkpMonthlySale;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TBL_ACCOUNT_UPGRADE")
@NamedQuery(name="TblAccountUpgrade.findAll", query="SELECT t FROM TblAccountUpgrade t")
public class TblAccountUpgrade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TBL_ACCOUNT_UPGRADE_ACCOUNTUPGRADEID_GENERATOR", sequenceName="TBL_ACCOUNT_UPGRADE_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ACCOUNT_UPGRADE_ACCOUNTUPGRADEID_GENERATOR")
    @Column(name="ACCOUNT_UPGRADE_ID")
    private long accountUpgradeId;



    @Column(name="BUSINESS_ADDRESS")
    private String businessAddress;

    @Column(name="BUSINESS_NAME")
    private String businessName;



    @Column(name="CHEQUE_BOOK")
    private String chequeBook;

    @Column(name="CLS_CASE_ID")
    private String clsCaseId;

    @Column(name="CLS_RESPONSE_CODE")
    private String clsResponseCode;

    @Column(name="CLS_STATUS")
    private String clsStatus;

    private String comments;

    @Column(name="CONFIDENCE_LEVEL")
    private BigDecimal confidenceLevel;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

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

    @Column(name="IS_ACTIVE")
    private String isActive;

    @Column(name="IS_POI_VERIFIED")
    private String isPoiVerified;

    @Column(name="IS_T_AND_C_ACCEPTED")
    private String isTAndCAccepted;

    @Column(name="KYC_VERIFIED")
    private String kycVerified;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;



    private String pmd;

    private String qr;

    @Column(name="RESIDENCE_ADDRESS")
    private String residenceAddress;

    @Column(name="SCREEN_STATE")
    private String screenState;

    @Column(name="SELF_DECLARATION_ACCEPTED")
    private String selfDeclarationAccepted;

    @Column(name="SIGNATURE_VERIFIED")
    private String signatureVerified;

    private String status;

    @Column(name="STATUS_DESCR")
    private String statusDescr;

    @Column(name="STREET_NO")
    private String streetNo;

    @Column(name="TAX_IDENTIFICATION_NUMBER")
    private String taxIdentificationNumber;

    @Column(name="TILL_NO")
    private String tillNo;

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

    //bi-directional many-to-one association to LkpDocStatus
    @ManyToOne
    @JoinColumn(name="DOC_STATUS_ID")
    private LkpDocStatus lkpDocStatus;

    //bi-directional many-to-one association to LkpFederalTaxClassification
    @ManyToOne
    @JoinColumn(name="FEDERAL_TAX_CLASSIFICATION_ID")
    private LkpFederalTaxClassification lkpFederalTaxClassification;


    //bi-directional many-to-one association to LkpMonthlySpending
    @ManyToOne
    @JoinColumn(name="EXPECTED_MONTHLY_CASHFLOW_ID")
    private LkpMonthlySpending lkpMonthlySpending;

    //bi-directional many-to-one association to LkpMonthlySale
    @ManyToOne
    @JoinColumn(name="EXPECTED_MONTHLY_SALES_ID")
    private LkpMonthlySale lkpMonthlySale;

    //bi-directional many-to-one association to LkpOccupation
    @ManyToOne
    @JoinColumn(name="OCCUPATION_ID")
    private LkpOccupation lkpOccupation;

    //bi-directional many-to-one association to LkpStatus
    @ManyToOne
    @JoinColumn(name="STATUS_ID")
    private LkpStatus lkpStatus;

    //bi-directional many-to-one association to LkpUltraUsage
    @ManyToOne
    @JoinColumn(name="ULTRA_USAGE_ID")
    private LkpUltraUsage lkpUltraUsage;

    //bi-directional many-to-one association to TblAccount
    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    private TblAccount tblAccount;

    //bi-directional many-to-one association to TblCustomer
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private TblCustomer tblCustomer;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="CNIC_FRONT_ID")
    private TblDocument tblDocument1;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="PROOF_OF_INCOME_ID")
    private TblDocument tblDocument2;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="PROOF_OF_PROFESSION_ID")
    private TblDocument tblDocument3;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="B_FORM_ID")
    private TblDocument tblDocument4;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="SIGNATURE_ID")
    private TblDocument tblDocument5;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="VIDEO_ID")
    private TblDocument tblDocument6;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="UTILITY_BILL_ID")
    private TblDocument tblDocument7;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="CNIC_BACK_ID")
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
    @JoinColumn(name="SELFIE_ID")
    private TblDocument tblDocument11;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="PROOF_OF_BUSINESS_ID")
    private TblDocument tblDocument12;

    //bi-directional many-to-one association to TblAccountUpgradeDoc
    @OneToMany(mappedBy="tblAccountUpgrade")
    private List<TblAccountUpgradeDoc> tblAccountUpgradeDocs;

    @ManyToOne
    @JoinColumn(name="ACCOUNT_LEVEL_ID")
    private LkpAccountLevel lkpAccountLevel;


    @ManyToOne
    @JoinColumn(name="BUSINESS_TYPE_ID")
    private LkpBusinessType lkpBusinessType;

    //bi-directional many-to-one association to LkpCurrency
    @ManyToOne
    @JoinColumn(name="CURRENCY_ID")
    private LkpCurrency lkpCurrency;

    @Column(name="SECURITY_QUESTION_RETRIES")
    private BigDecimal securityQuestionRetries;

    public TblAccountUpgrade() {
    }

    public long getAccountUpgradeId() {
        return this.accountUpgradeId;
    }

    public void setAccountUpgradeId(long accountUpgradeId) {
        this.accountUpgradeId = accountUpgradeId;
    }



    public String getBusinessAddress() {
        return this.businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public BigDecimal getConfidenceLevel() {
        return this.confidenceLevel;
    }

    public void setConfidenceLevel(BigDecimal confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
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

    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsPoiVerified() {
        return this.isPoiVerified;
    }

    public void setIsPoiVerified(String isPoiVerified) {
        this.isPoiVerified = isPoiVerified;
    }

    public String getIsTAndCAccepted() {
        return this.isTAndCAccepted;
    }

    public void setIsTAndCAccepted(String isTAndCAccepted) {
        this.isTAndCAccepted = isTAndCAccepted;
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

    public String getQr() {
        return this.qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getResidenceAddress() {
        return this.residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getScreenState() {
        return this.screenState;
    }

    public void setScreenState(String screenState) {
        this.screenState = screenState;
    }

    public String getSelfDeclarationAccepted() {
        return this.selfDeclarationAccepted;
    }

    public void setSelfDeclarationAccepted(String selfDeclarationAccepted) {
        this.selfDeclarationAccepted = selfDeclarationAccepted;
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

    public String getTillNo() {
        return this.tillNo;
    }

    public void setTillNo(String tillNo) {
        this.tillNo = tillNo;
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

    public LkpDocStatus getLkpDocStatus() {
        return this.lkpDocStatus;
    }

    public void setLkpDocStatus(LkpDocStatus lkpDocStatus) {
        this.lkpDocStatus = lkpDocStatus;
    }

    public LkpFederalTaxClassification getLkpFederalTaxClassification() {
        return this.lkpFederalTaxClassification;
    }

    public void setLkpFederalTaxClassification(LkpFederalTaxClassification lkpFederalTaxClassification) {
        this.lkpFederalTaxClassification = lkpFederalTaxClassification;
    }

    public LkpMonthlySale getLkpMonthlySale() {
        return this.lkpMonthlySale;
    }

    public void setLkpMonthlySale(LkpMonthlySale lkpMonthlySale) {
        this.lkpMonthlySale = lkpMonthlySale;
    }

    public LkpMonthlySpending getLkpMonthlySpending() {
        return this.lkpMonthlySpending;
    }

    public void setLkpMonthlySpending(LkpMonthlySpending lkpMonthlySpending) {
        this.lkpMonthlySpending = lkpMonthlySpending;
    }

    public LkpOccupation getLkpOccupation() {
        return this.lkpOccupation;
    }

    public void setLkpOccupation(LkpOccupation lkpOccupation) {
        this.lkpOccupation = lkpOccupation;
    }

    public LkpStatus getLkpStatus() {
        return this.lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }

    public LkpUltraUsage getLkpUltraUsage() {
        return this.lkpUltraUsage;
    }

    public void setLkpUltraUsage(LkpUltraUsage lkpUltraUsage) {
        this.lkpUltraUsage = lkpUltraUsage;
    }

    public TblAccount getTblAccount() {
        return this.tblAccount;
    }

    public void setTblAccount(TblAccount tblAccount) {
        this.tblAccount = tblAccount;
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

    public TblDocument getTblDocument8() {
        return this.tblDocument8;
    }

    public void setTblDocument8(TblDocument tblDocument8) {
        this.tblDocument8 = tblDocument8;
    }

    public TblDocument getTblDocument9() {
        return this.tblDocument9;
    }

    public void setTblDocument9(TblDocument tblDocument9) {
        this.tblDocument9 = tblDocument9;
    }

    public TblDocument getTblDocument10() {
        return this.tblDocument10;
    }

    public void setTblDocument10(TblDocument tblDocument10) {
        this.tblDocument10 = tblDocument10;
    }

    public TblDocument getTblDocument11() {
        return this.tblDocument11;
    }

    public void setTblDocument11(TblDocument tblDocument11) {
        this.tblDocument11 = tblDocument11;
    }

    public TblDocument getTblDocument12() {
        return this.tblDocument12;
    }

    public void setTblDocument12(TblDocument tblDocument12) {
        this.tblDocument12 = tblDocument12;
    }

    public List<TblAccountUpgradeDoc> getTblAccountUpgradeDocs() {
        return this.tblAccountUpgradeDocs;
    }

    public void setTblAccountUpgradeDocs(List<TblAccountUpgradeDoc> tblAccountUpgradeDocs) {
        this.tblAccountUpgradeDocs = tblAccountUpgradeDocs;
    }

    public TblAccountUpgradeDoc addTblAccountUpgradeDoc(TblAccountUpgradeDoc tblAccountUpgradeDoc) {
        getTblAccountUpgradeDocs().add(tblAccountUpgradeDoc);
        tblAccountUpgradeDoc.setTblAccountUpgrade(this);

        return tblAccountUpgradeDoc;
    }

    public TblAccountUpgradeDoc removeTblAccountUpgradeDoc(TblAccountUpgradeDoc tblAccountUpgradeDoc) {
        getTblAccountUpgradeDocs().remove(tblAccountUpgradeDoc);
        tblAccountUpgradeDoc.setTblAccountUpgrade(null);

        return tblAccountUpgradeDoc;
    }

    public LkpAccountLevel getLkpAccountLevel() {
        return lkpAccountLevel;
    }

    public void setLkpAccountLevel(LkpAccountLevel lkpAccountLevel) {
        this.lkpAccountLevel = lkpAccountLevel;
    }

    public LkpBusinessType getLkpBusinessType() {
        return lkpBusinessType;
    }

    public void setLkpBusinessType(LkpBusinessType lkpBusinessType) {
        this.lkpBusinessType = lkpBusinessType;
    }

    public LkpCurrency getLkpCurrency() {
        return lkpCurrency;
    }

    public void setLkpCurrency(LkpCurrency lkpCurrency) {
        this.lkpCurrency = lkpCurrency;
    }

    public BigDecimal getSecurityQuestionRetries() {
        return securityQuestionRetries;
    }

    public void setSecurityQuestionRetries(BigDecimal securityQuestionRetries) {
        this.securityQuestionRetries = securityQuestionRetries;
    }
}