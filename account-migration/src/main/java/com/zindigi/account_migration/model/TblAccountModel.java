package com.zindigi.account_migration.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.*;
import com.mfs.commonservice.model.LkpAccountStatus;
import com.mfs.commonservice.model.LkpChannel;
import com.mfs.commonservice.model.LkpDaocode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "TBL_ACCOUNT")
@NamedQuery(name = "TblAccountModel.findAll", query = "SELECT t FROM TblAccountModel t")
public class TblAccountModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_ACCOUNT_ACCOUNTID_GENERATOR", sequenceName = "TBL_ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_ACCOUNT_ACCOUNTID_GENERATOR")
    @Column(name = "ACCOUNT_ID")
    private long accountId;

    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Column(name = "ACCOUNT_TITLE")
    private String accountTitle;

    @Column(name = "ACTUAL_BALANCE")
    private BigDecimal actualBalance;

    @Column(name = "APP_SCREEN_STATUS")
    private String appScreenStatus;

    @Column(name = "AVAILABLE_BALANCE")
    private BigDecimal availableBalance;

    @Column(name = "CBS_ACCOUNT_CODE")
    private String cbsAccountCode;

    @Column(name = "CLS_CREDIT_BLOCK")
    private String clsCreditBlock;

    @Column(name = "CLS_DEBIT_BLOCK")
    private String clsDebitBlock;

    private Date createdate;

    private BigDecimal createuser;

    @Column(name = "FIN_ACCOUNT_CODE")
    private String finAccountCode;

    private String iban;

    @Column(name = "INITIAL_DEPOSIT")
    private BigDecimal initialDeposit;

    @Column(name = "IS_GL_ACCOUNT")
    private String isGlAccount;

    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name = "LEVEL_CHANGE_BY")
    private BigDecimal levelChangeBy;

    @Column(name = "LEVEL_CHANGE_COMMENTS")
    private String levelChangeComments;

    @Temporal(TemporalType.DATE)
    @Column(name = "LEVEL_CHANGE_DATE")
    private Date levelChangeDate;

    @Column(name = "PREVIOUS_LEVEL_ID")
    private BigDecimal previousLevelId;

    @Column(name = "PREVIOUS_STATUS_ID")
    private BigDecimal previousStatusId;

    @Column(name = "SECURITY_QUESTION_RETRIES")
    private BigDecimal securityQuestionRetries;

    @Column(name = "STATUS_CHANGE_BY")
    private BigDecimal statusChangeBy;

    @Column(name = "STATUS_CHANGE_COMMENTS")
    private String statusChangeComments;

    @Temporal(TemporalType.DATE)
    @Column(name = "STATUS_CHANGE_DATE")
    private Date statusChangeDate;

    private BigDecimal updateindex;

    @Column(name = "WHT_APPLICABILITY")
    private String whtApplicability;

    @Column(name = "NTN_NUMBER")
    private String ntnNumber;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    //bi-directional many-to-one association to LkpDaocode
    @ManyToOne
    @JoinColumn(name = "DAOCODE_ID")
    private LkpDaocode lkpDaocode;

    @Transient
    private String createdBy;

    @Transient
    private String updatedBy;


    //bi-directional many-to-one association to LkpAccountLevel

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_LEVEL_ID")
    private LkpAccountLevel lkpAccountLevel;

    //bi-directional many-to-one association to LkpAccountStatus

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_STATUS_ID")
    private LkpAccountStatus lkpAccountStatus;

    //bi-directional many-to-one association to LkpAccountType

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private LkpAccountType lkpAccountType;

    //bi-directional many-to-one association to LkpChannel

    @ManyToOne
    @JoinColumn(name = "CHANNEL_ID")
    private LkpChannel lkpChannel;

    //bi-directional many-to-one association to LkpCurrency

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private LkpCurrency lkpCurrency;

    //bi-directional many-to-one association to LkpRegistrationType
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "REGISTRATION_TYPE_ID")
    private LkpRegistrationType lkpRegistrationType;

    //bi-directional many-to-one association to TblAgent
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AGENT_ID")
    private TblAgent tblAgent;

    //bi-directional many-to-one association to TblCustomer
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private TblCustomer tblCustomer;

    //bi-directional many-to-one association to TblGlCodeCombination

    @ManyToOne
    @JoinColumn(name = "GL_CODE_COMBINATION_ID")
    private TblGlCodeCombination tblGlCodeCombination;

    //bi-directional many-to-one association to TblAccountDailyStat
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel")
    private List<TblAccountDailyStat> tblAccountDailyStats;

    //bi-directional many-to-one association to TblCommissionProfile
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel1")
    private List<TblCommissionProfile> tblCommissionProfiles1;

    //bi-directional many-to-one association to TblCommissionProfile
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel2")
    private List<TblCommissionProfile> tblCommissionProfiles2;

    //bi-directional many-to-one association to TblPricingIncomeSharing
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel1")
    private List<TblPricingIncomeSharing> tblPricingIncomeSharings1;

    //bi-directional many-to-one association to TblPricingIncomeSharing
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel2")
    private List<TblPricingIncomeSharing> tblPricingIncomeSharings2;

    //bi-directional many-to-one association to TblPricingProfile
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel1")
    private List<TblPricingProfile> tblPricingProfiles1;

    //bi-directional many-to-one association to TblPricingProfile
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel2")
    private List<TblPricingProfile> tblPricingProfiles2;

    //bi-directional many-to-one association to TblProductGlConfig
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel")
    private List<TblProductGlConfig> tblProductGlConfigs;

    //bi-directional many-to-one association to TblTaxRegime
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel")
    private List<TblTaxRegime> tblTaxRegimes;

    //bi-directional many-to-one association to TblTransHead
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModelCr")
    private List<TblTransHead> tblTransHeads1;

    //bi-directional many-to-one association to TblTransHead
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModelDr")
    private List<TblTransHead> tblTransHeads2;

    //bi-directional many-to-one association to TblTransPatternDetail
    @JsonIgnore
    @OneToMany(mappedBy = "tblAccountModel")
    private List<TblTransPatternDetail> tblTransPatternDetails;

    //bi-directional many-to-one association to LkpGlType
    @ManyToOne
    @JoinColumn(name = "GL_TYPE_ID")
    private LkpGlType lkpGlType;

    //bi-directional many-to-one association to LkpSegment
    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private LkpStatus lkpStatus;

    @Column(name = "IS_RAAST_ID_LINK")
    private String isRaastIdLink;

    @Column(name = "IS_T_AND_C_ACCEPTED")
    private String isTAndCAccepted;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_NATURE_ID")
    private LkpAccountNature lkpAccountNature;

    @ManyToOne
    @JoinColumn(name="RAAST_ALIAS_TYPE_ID")
    private LkpRaastAliasType lkpRaastAliasType;


    @Column(name = "RAAST_ALIAS_VALUE")
    private String raastAliasValue;

    @Column(name = "T_AND_C_ACCEPTED_DATE")
    private Date tAndCAcceptedDate;

    @Column(name = "OVERDRAWN_ALLOWED")
    private String overDrawnAllowed;

    @Column(name = "OVERDRAWN_AMOUNT_THRESHOLD")
    private BigDecimal overDrawnAmountThreshold;

    @Column(name = "LIEN_AMOUNT")
    private BigDecimal lienAmount;

    @Column(name = "IS_VRG_LINKED")
    private String isVrgLinked;

    public LkpRaastAliasType getLkpRaastAliasType() {
        return lkpRaastAliasType;
    }

    public void setLkpRaastAliasType(LkpRaastAliasType lkpRaastAliasType) {
        this.lkpRaastAliasType = lkpRaastAliasType;
    }

    public String getRaastAliasValue() {
        return raastAliasValue;
    }

    public void setRaastAliasValue(String raastAliasValue) {
        this.raastAliasValue = raastAliasValue;
    }

    public TblAccountModel() {
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountTitle() {
        return this.accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public BigDecimal getActualBalance() {
        return this.actualBalance;
    }

    public void setActualBalance(BigDecimal actualBalance) {
        this.actualBalance = actualBalance;
    }

    public String getAppScreenStatus() {
        return this.appScreenStatus;
    }

    public void setAppScreenStatus(String appScreenStatus) {
        this.appScreenStatus = appScreenStatus;
    }

    public BigDecimal getAvailableBalance() {
        return this.availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCbsAccountCode() {
        return this.cbsAccountCode;
    }

    public void setCbsAccountCode(String cbsAccountCode) {
        this.cbsAccountCode = cbsAccountCode;
    }

    public String getClsCreditBlock() {
        return this.clsCreditBlock;
    }

    public void setClsCreditBlock(String clsCreditBlock) {
        this.clsCreditBlock = clsCreditBlock;
    }

    public String getClsDebitBlock() {
        return this.clsDebitBlock;
    }

    public void setClsDebitBlock(String clsDebitBlock) {
        this.clsDebitBlock = clsDebitBlock;
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

    public String getFinAccountCode() {
        return this.finAccountCode;
    }

    public void setFinAccountCode(String finAccountCode) {
        this.finAccountCode = finAccountCode;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getInitialDeposit() {
        return this.initialDeposit;
    }

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getIsGlAccount() {
        return this.isGlAccount;
    }

    public void setIsGlAccount(String isGlAccount) {
        this.isGlAccount = isGlAccount;
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

    public BigDecimal getLevelChangeBy() {
        return this.levelChangeBy;
    }

    public void setLevelChangeBy(BigDecimal levelChangeBy) {
        this.levelChangeBy = levelChangeBy;
    }

    public String getLevelChangeComments() {
        return this.levelChangeComments;
    }

    public void setLevelChangeComments(String levelChangeComments) {
        this.levelChangeComments = levelChangeComments;
    }

    public Date getLevelChangeDate() {
        return this.levelChangeDate;
    }

    public void setLevelChangeDate(Date levelChangeDate) {
        this.levelChangeDate = levelChangeDate;
    }

    public BigDecimal getPreviousLevelId() {
        return this.previousLevelId;
    }

    public void setPreviousLevelId(BigDecimal previousLevelId) {
        this.previousLevelId = previousLevelId;
    }

    public BigDecimal getPreviousStatusId() {
        return this.previousStatusId;
    }

    public void setPreviousStatusId(BigDecimal previousStatusId) {
        this.previousStatusId = previousStatusId;
    }

    public BigDecimal getSecurityQuestionRetries() {
        return this.securityQuestionRetries;
    }

    public void setSecurityQuestionRetries(BigDecimal securityQuestionRetries) {
        this.securityQuestionRetries = securityQuestionRetries;
    }

    public BigDecimal getStatusChangeBy() {
        return this.statusChangeBy;
    }

    public void setStatusChangeBy(BigDecimal statusChangeBy) {
        this.statusChangeBy = statusChangeBy;
    }

    public String getStatusChangeComments() {
        return this.statusChangeComments;
    }

    public void setStatusChangeComments(String statusChangeComments) {
        this.statusChangeComments = statusChangeComments;
    }

    public Date getStatusChangeDate() {
        return this.statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public LkpAccountLevel getLkpAccountLevel() {
        return this.lkpAccountLevel;
    }

    public void setLkpAccountLevel(LkpAccountLevel lkpAccountLevelModel) {
        this.lkpAccountLevel = lkpAccountLevelModel;
    }

    public LkpAccountStatus getLkpAccountStatus() {
        return this.lkpAccountStatus;
    }

    public void setLkpAccountStatus(LkpAccountStatus lkpAccountStatus) {
        this.lkpAccountStatus = lkpAccountStatus;
    }

    public LkpAccountType getLkpAccountType() {
        return this.lkpAccountType;
    }

    public void setLkpAccountType(LkpAccountType lkpAccountType) {
        this.lkpAccountType = lkpAccountType;
    }

    public LkpChannel getLkpChannel() {
        return this.lkpChannel;
    }

    public void setLkpChannel(LkpChannel lkpChannel) {
        this.lkpChannel = lkpChannel;
    }

    public LkpCurrency getLkpCurrency() {
        return this.lkpCurrency;
    }

    public void setLkpCurrency(LkpCurrency lkpCurrency) {
        this.lkpCurrency = lkpCurrency;
    }

    public LkpRegistrationType getLkpRegistrationType() {
        return this.lkpRegistrationType;
    }

    public void setLkpRegistrationType(LkpRegistrationType lkpRegistrationType) {
        this.lkpRegistrationType = lkpRegistrationType;
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

    public TblGlCodeCombination getTblGlCodeCombination() {
        return this.tblGlCodeCombination;
    }

    public void setTblGlCodeCombination(TblGlCodeCombination tblGlCodeCombination) {
        this.tblGlCodeCombination = tblGlCodeCombination;
    }

    public List<TblAccountDailyStat> getTblAccountDailyStats() {
        return this.tblAccountDailyStats;
    }

    public void setTblAccountDailyStats(List<TblAccountDailyStat> tblAccountDailyStats) {
        this.tblAccountDailyStats = tblAccountDailyStats;
    }

    public TblAccountDailyStat addTblAccountDailyStat(TblAccountDailyStat tblAccountDailyStat) {
        getTblAccountDailyStats().add(tblAccountDailyStat);
        tblAccountDailyStat.setTblAccountModel(this);

        return tblAccountDailyStat;
    }

    public TblAccountDailyStat removeTblAccountDailyStat(TblAccountDailyStat tblAccountDailyStat) {
        getTblAccountDailyStats().remove(tblAccountDailyStat);
        tblAccountDailyStat.setTblAccountModel(null);

        return tblAccountDailyStat;
    }

    public List<TblCommissionProfile> getTblCommissionProfiles1() {
        return this.tblCommissionProfiles1;
    }

    public void setTblCommissionProfiles1(List<TblCommissionProfile> tblCommissionProfiles1) {
        this.tblCommissionProfiles1 = tblCommissionProfiles1;
    }

    public TblCommissionProfile addTblCommissionProfiles1(TblCommissionProfile tblCommissionProfiles1) {
        getTblCommissionProfiles1().add(tblCommissionProfiles1);
        tblCommissionProfiles1.setTblAccountModel1(this);

        return tblCommissionProfiles1;
    }

    public TblCommissionProfile removeTblCommissionProfiles1(TblCommissionProfile tblCommissionProfiles1) {
        getTblCommissionProfiles1().remove(tblCommissionProfiles1);
        tblCommissionProfiles1.setTblAccountModel1(null);

        return tblCommissionProfiles1;
    }

    public List<TblCommissionProfile> getTblCommissionProfiles2() {
        return this.tblCommissionProfiles2;
    }

    public void setTblCommissionProfiles2(List<TblCommissionProfile> tblCommissionProfiles2) {
        this.tblCommissionProfiles2 = tblCommissionProfiles2;
    }

    public TblCommissionProfile addTblCommissionProfiles2(TblCommissionProfile tblCommissionProfiles2) {
        getTblCommissionProfiles2().add(tblCommissionProfiles2);
        tblCommissionProfiles2.setTblAccountModel2(this);

        return tblCommissionProfiles2;
    }

    public TblCommissionProfile removeTblCommissionProfiles2(TblCommissionProfile tblCommissionProfiles2) {
        getTblCommissionProfiles2().remove(tblCommissionProfiles2);
        tblCommissionProfiles2.setTblAccountModel2(null);

        return tblCommissionProfiles2;
    }

    public List<TblPricingIncomeSharing> getTblPricingIncomeSharings1() {
        return this.tblPricingIncomeSharings1;
    }

    public void setTblPricingIncomeSharings1(List<TblPricingIncomeSharing> tblPricingIncomeSharings1) {
        this.tblPricingIncomeSharings1 = tblPricingIncomeSharings1;
    }

    public TblPricingIncomeSharing addTblPricingIncomeSharings1(TblPricingIncomeSharing tblPricingIncomeSharings1) {
        getTblPricingIncomeSharings1().add(tblPricingIncomeSharings1);
        tblPricingIncomeSharings1.setTblAccountModel1(this);

        return tblPricingIncomeSharings1;
    }

    public TblPricingIncomeSharing removeTblPricingIncomeSharings1(TblPricingIncomeSharing tblPricingIncomeSharings1) {
        getTblPricingIncomeSharings1().remove(tblPricingIncomeSharings1);
        tblPricingIncomeSharings1.setTblAccountModel1(null);

        return tblPricingIncomeSharings1;
    }

    public List<TblPricingIncomeSharing> getTblPricingIncomeSharings2() {
        return this.tblPricingIncomeSharings2;
    }

    public void setTblPricingIncomeSharings2(List<TblPricingIncomeSharing> tblPricingIncomeSharings2) {
        this.tblPricingIncomeSharings2 = tblPricingIncomeSharings2;
    }

    public TblPricingIncomeSharing addTblPricingIncomeSharings2(TblPricingIncomeSharing tblPricingIncomeSharings2) {
        getTblPricingIncomeSharings2().add(tblPricingIncomeSharings2);
        tblPricingIncomeSharings2.setTblAccountModel2(this);

        return tblPricingIncomeSharings2;
    }

    public TblPricingIncomeSharing removeTblPricingIncomeSharings2(TblPricingIncomeSharing tblPricingIncomeSharings2) {
        getTblPricingIncomeSharings2().remove(tblPricingIncomeSharings2);
        tblPricingIncomeSharings2.setTblAccountModel2(null);

        return tblPricingIncomeSharings2;
    }

    public List<TblPricingProfile> getTblPricingProfiles1() {
        return this.tblPricingProfiles1;
    }

    public void setTblPricingProfiles1(List<TblPricingProfile> tblPricingProfiles1) {
        this.tblPricingProfiles1 = tblPricingProfiles1;
    }

    public TblPricingProfile addTblPricingProfiles1(TblPricingProfile tblPricingProfiles1) {
        getTblPricingProfiles1().add(tblPricingProfiles1);
        tblPricingProfiles1.setTblAccountModel1(this);

        return tblPricingProfiles1;
    }

    public TblPricingProfile removeTblPricingProfiles1(TblPricingProfile tblPricingProfiles1) {
        getTblPricingProfiles1().remove(tblPricingProfiles1);
        tblPricingProfiles1.setTblAccountModel1(null);

        return tblPricingProfiles1;
    }

    public List<TblPricingProfile> getTblPricingProfiles2() {
        return this.tblPricingProfiles2;
    }

    public void setTblPricingProfiles2(List<TblPricingProfile> tblPricingProfiles2) {
        this.tblPricingProfiles2 = tblPricingProfiles2;
    }

    public TblPricingProfile addTblPricingProfiles2(TblPricingProfile tblPricingProfiles2) {
        getTblPricingProfiles2().add(tblPricingProfiles2);
        tblPricingProfiles2.setTblAccountModel2(this);

        return tblPricingProfiles2;
    }

    public TblPricingProfile removeTblPricingProfiles2(TblPricingProfile tblPricingProfiles2) {
        getTblPricingProfiles2().remove(tblPricingProfiles2);
        tblPricingProfiles2.setTblAccountModel2(null);

        return tblPricingProfiles2;
    }

    public List<TblProductGlConfig> getTblProductGlConfigs() {
        return this.tblProductGlConfigs;
    }

    public void setTblProductGlConfigs(List<TblProductGlConfig> tblProductGlConfigs) {
        this.tblProductGlConfigs = tblProductGlConfigs;
    }

    public TblProductGlConfig addTblProductGlConfig(TblProductGlConfig tblProductGlConfig) {
        getTblProductGlConfigs().add(tblProductGlConfig);
        tblProductGlConfig.setTblAccount(this);

        return tblProductGlConfig;
    }

    public TblProductGlConfig removeTblProductGlConfig(TblProductGlConfig tblProductGlConfig) {
        getTblProductGlConfigs().remove(tblProductGlConfig);
        tblProductGlConfig.setTblAccount(null);

        return tblProductGlConfig;
    }

    public List<TblTaxRegime> getTblTaxRegimes() {
        return this.tblTaxRegimes;
    }

    public void setTblTaxRegimes(List<TblTaxRegime> tblTaxRegimes) {
        this.tblTaxRegimes = tblTaxRegimes;
    }

    public TblTaxRegime addTblTaxRegime(TblTaxRegime tblTaxRegime) {
        getTblTaxRegimes().add(tblTaxRegime);
        tblTaxRegime.setTblAccountModel(this);

        return tblTaxRegime;
    }

    public TblTaxRegime removeTblTaxRegime(TblTaxRegime tblTaxRegime) {
        getTblTaxRegimes().remove(tblTaxRegime);
        tblTaxRegime.setTblAccountModel(null);

        return tblTaxRegime;
    }

    public List<TblTransHead> getTblTransHeads1() {
        return this.tblTransHeads1;
    }

    public void setTblTransHeads1(List<TblTransHead> tblTransHeads1) {
        this.tblTransHeads1 = tblTransHeads1;
    }

    public TblTransHead addTblTransHeads1(TblTransHead tblTransHeads1) {
        getTblTransHeads1().add(tblTransHeads1);
        tblTransHeads1.setTblAccountModelCr(this);

        return tblTransHeads1;
    }

    public TblTransHead removeTblTransHeads1(TblTransHead tblTransHeads1) {
        getTblTransHeads1().remove(tblTransHeads1);
        tblTransHeads1.setTblAccountModelCr(null);

        return tblTransHeads1;
    }

    public List<TblTransHead> getTblTransHeads2() {
        return this.tblTransHeads2;
    }

    public void setTblTransHeads2(List<TblTransHead> tblTransHeads2) {
        this.tblTransHeads2 = tblTransHeads2;
    }

    public TblTransHead addTblTransHeads2(TblTransHead tblTransHeads2) {
        getTblTransHeads2().add(tblTransHeads2);
        tblTransHeads2.setTblAccountModelDr(this);

        return tblTransHeads2;
    }

    public TblTransHead removeTblTransHeads2(TblTransHead tblTransHeads2) {
        getTblTransHeads2().remove(tblTransHeads2);
        tblTransHeads2.setTblAccountModelDr(null);

        return tblTransHeads2;
    }

    public List<TblTransPatternDetail> getTblTransPatternDetails() {
        return this.tblTransPatternDetails;
    }

    public void setTblTransPatternDetails(List<TblTransPatternDetail> tblTransPatternDetails) {
        this.tblTransPatternDetails = tblTransPatternDetails;
    }

    public TblTransPatternDetail addTblTransPatternDetail(TblTransPatternDetail tblTransPatternDetail) {
        getTblTransPatternDetails().add(tblTransPatternDetail);
        tblTransPatternDetail.setTblAccountModel(this);

        return tblTransPatternDetail;
    }

    public TblTransPatternDetail removeTblTransPatternDetail(TblTransPatternDetail tblTransPatternDetail) {
        getTblTransPatternDetails().remove(tblTransPatternDetail);
        tblTransPatternDetail.setTblAccountModel(null);

        return tblTransPatternDetail;
    }

    public String getWhtApplicability() {
        return whtApplicability;
    }

    public void setWhtApplicability(String whtApplicability) {
        this.whtApplicability = whtApplicability;
    }

    public LkpDaocode getLkpDaocode() {
        return lkpDaocode;
    }

    public void setLkpDaocode(LkpDaocode lkpDaocode) {
        this.lkpDaocode = lkpDaocode;
    }

    public String getNtnNumber() {
        return ntnNumber;
    }

    public void setNtnNumber(String ntnNumber) {
        this.ntnNumber = ntnNumber;
    }

    public LkpGlType getLkpGlType() {
        return lkpGlType;
    }

    public void setLkpGlType(LkpGlType lkpGlType) {
        this.lkpGlType = lkpGlType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public LkpStatus getLkpStatus() {
        return lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getIsRaastIdLink() {
        return isRaastIdLink;
    }

    public void setIsRaastIdLink(String isRaastIdLink) {
        this.isRaastIdLink = isRaastIdLink;
    }

    public String getIsTAndCAccepted() {
        return isTAndCAccepted;
    }

    public void setIsTAndCAccepted(String isTAndCAccepted) {
        this.isTAndCAccepted = isTAndCAccepted;
    }

    public LkpAccountNature getLkpAccountNature() {
        return lkpAccountNature;
    }

    public void setLkpAccountNature(LkpAccountNature lkpAccountNature) {
        this.lkpAccountNature = lkpAccountNature;
    }

    public Date gettAndCAcceptedDate() {
        return tAndCAcceptedDate;
    }

    public void settAndCAcceptedDate(Date tAndCAcceptedDate) {
        this.tAndCAcceptedDate = tAndCAcceptedDate;
    }

    public String getOverDrawnAllowed() {
        return overDrawnAllowed;
    }

    public void setOverDrawnAllowed(String overDrawnAllowed) {
        this.overDrawnAllowed = overDrawnAllowed;
    }

    public BigDecimal getOverDrawnAmountThreshold() {
        return overDrawnAmountThreshold;
    }

    public void setOverDrawnAmountThreshold(BigDecimal overDrawnAmountThreshold) {
        this.overDrawnAmountThreshold = overDrawnAmountThreshold;
    }

    public BigDecimal getLienAmount() {
        return lienAmount;
    }

    public void setLienAmount(BigDecimal lienAmount) {
        this.lienAmount = lienAmount;
    }

    public String getIsVrgLinked() {
        return isVrgLinked;
    }

    public void setIsVrgLinked(String isVrgLinked) {
        this.isVrgLinked = isVrgLinked;
    }
}