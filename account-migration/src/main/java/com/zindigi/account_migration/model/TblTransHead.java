package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_TRANS_HEAD database table.
 */
@Entity
@Table(name = "TBL_TRANS_HEAD")
@NamedQuery(name = "TblTransHead.findAll", query = "SELECT t FROM TblTransHead t")
public class TblTransHead implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_TRANS_HEAD_TRANSHEADID_GENERATOR", sequenceName = "TBL_TRANS_HEAD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_TRANS_HEAD_TRANSHEADID_GENERATOR")
    @Column(name = "TRANS_HEAD_ID")
    private long transHeadId;

    @Column(name = "CARD_ACCEPTOR_NAME_LOCATION")
    private String cardAcceptorNameLocation;

    @Column(name = "CARD_ACCEPTOR_TERMINAL_ID")
    private String cardAcceptorTerminalId;

    @Column(name = "COMMISSION_AMOUNT")
    private BigDecimal commissionAmount;

    @Column(name = "CR_ACCOUNT_CNIC")
    private String crAccountCnic;

    @Column(name = "CR_ACCOUNT_NO")
    private String crAccountNo;

    @Column(name = "CR_ACCOUNT_TITLE")
    private String crAccountTitle;

    @Column(name = "CR_ACCOUNT_TYPE")
    private String crAccountType;

    @Column(name = "CR_BANK_NAME")
    private String crBankName;

    private Date createdate;

    private BigDecimal createuser;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private LkpCurrency lkpCurrency;

    @Column(name = "DEBIT_CARD_NO")
    private String debitCardNo;

    @Column(name = "DR_ACCOUNT_CNIC")
    private String drAccountCnic;

    @Column(name = "DR_ACCOUNT_NO")
    private String drAccountNo;

    @Column(name = "DR_ACCOUNT_TITLE")
    private String drAccountTitle;

    @Column(name = "DR_ACCOUNT_TYPE")
    private String drAccountType;

    @Column(name = "DR_BANK_NAME")
    private String drBankName;

    private BigDecimal fed;

    private BigDecimal fee;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "RRN")
    private String rrn;

    private String stan;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;

    @Column(name = "TRANSACTION_CODE")
    private String transactionCode;

    private BigDecimal updateindex;

    @Column(name = "UTIILITY_COMPANY_CODE")
    private String utiilityCompanyCode;

    @Column(name = "UTILITY_CONSUMER_NUMBER")
    private String utilityConsumerNumber;

    @Column(name = "WHTF")
    private BigDecimal whtf;

    //bi-directional many-to-one association to TblTransDetail
    @OneToMany(mappedBy = "tblTransHead", cascade = CascadeType.ALL)
    private List<TblTransDetail> tblTransDetails;

    //bi-directional many-to-one association to LkpChannel
    @ManyToOne
    @JoinColumn(name = "CHANNEL_ID")
    private LkpChannel lkpChannel;

    //bi-directional many-to-one association to TblAccount
    @ManyToOne
    @JoinColumn(name = "CR_ACCOUNT_ID")
    private TblAccount tblAccountCr;

    //bi-directional many-to-one association to TblAccount
    @ManyToOne
    @JoinColumn(name = "DR_ACCOUNT_ID")
    private TblAccount tblAccountDr;

    @Column(name = "PRODUCT_ID")
    private long productId;

    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "TRANS_STATUS_ID")
    private LkpTransStatus lkpTransStatus;

    @ManyToOne
    @JoinColumn(name = "TRANS_INQUIRY_ID")
    private TblTransInquiry tblTransInquiry;

    private String terminalId;

    @Column(name = "TRANS_REVERSIBLE_ID")
    private BigDecimal transReversibleId;

    private Date valueDate;

    @Column(name = "DR_EXCLUDE_LIMIT")
    private String drLimitsExclude;

    @Column(name = "CR_EXCLUDE_LIMIT")
    private String crLimitsExclude;

    @Column(name = "FEE_INCLUSIVE_EXCLUSIVE")
    private String feeInclusiveExclusive;

    @Column(name = "FED_INCLUSIVE_EXCLUSIVE")
    private String fedInclusiveExclusive;

    @Column(name = "WHTC")
    private BigDecimal whtc;

    @Column(name = "DR_MOBILE_NO")
    private String drMobileNo;

    @Column(name = "CR_MOBILE_NO")
    private String crMobileNo;

    private Date transactionCompleteDate;

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_PURPOSE_ID")
    private LkpTransactionPurpose lkpTransactionPurpose;

    public long getTransHeadId() {
        return this.transHeadId;
    }

    public void setTransHeadId(long transHeadId) {
        this.transHeadId = transHeadId;
    }

    public String getCardAcceptorNameLocation() {
        return this.cardAcceptorNameLocation;
    }

    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
    }

    public String getCardAcceptorTerminalId() {
        return this.cardAcceptorTerminalId;
    }

    public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {
        this.cardAcceptorTerminalId = cardAcceptorTerminalId;
    }

    public BigDecimal getCommissionAmount() {
        return this.commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getCrAccountCnic() {
        return this.crAccountCnic;
    }

    public void setCrAccountCnic(String crAccountCnic) {
        this.crAccountCnic = crAccountCnic;
    }

    public String getCrAccountNo() {
        return this.crAccountNo;
    }

    public void setCrAccountNo(String crAccountNo) {
        this.crAccountNo = crAccountNo;
    }

    public String getCrAccountTitle() {
        return this.crAccountTitle;
    }

    public void setCrAccountTitle(String crAccountTitle) {
        this.crAccountTitle = crAccountTitle;
    }

    public String getCrAccountType() {
        return this.crAccountType;
    }

    public void setCrAccountType(String crAccountType) {
        this.crAccountType = crAccountType;
    }

    public String getCrBankName() {
        return this.crBankName;
    }

    public void setCrBankName(String crBankName) {
        this.crBankName = crBankName;
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

    public String getDebitCardNo() {
        return this.debitCardNo;
    }

    public void setDebitCardNo(String debitCardNo) {
        this.debitCardNo = debitCardNo;
    }

    public String getDrAccountCnic() {
        return this.drAccountCnic;
    }

    public void setDrAccountCnic(String drAccountCnic) {
        this.drAccountCnic = drAccountCnic;
    }

    public String getDrAccountNo() {
        return this.drAccountNo;
    }

    public void setDrAccountNo(String drAccountNo) {
        this.drAccountNo = drAccountNo;
    }

    public String getDrAccountTitle() {
        return this.drAccountTitle;
    }

    public void setDrAccountTitle(String drAccountTitle) {
        this.drAccountTitle = drAccountTitle;
    }

    public String getDrAccountType() {
        return this.drAccountType;
    }

    public void setDrAccountType(String drAccountType) {
        this.drAccountType = drAccountType;
    }

    public String getDrBankName() {
        return this.drBankName;
    }

    public void setDrBankName(String drBankName) {
        this.drBankName = drBankName;
    }

    public BigDecimal getFed() {
        return this.fed;
    }

    public void setFed(BigDecimal fed) {
        this.fed = fed;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRrn() {
        return this.rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getStan() {
        return this.stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTransactionAmount() {
        return this.transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public String getUtiilityCompanyCode() {
        return this.utiilityCompanyCode;
    }

    public void setUtiilityCompanyCode(String utiilityCompanyCode) {
        this.utiilityCompanyCode = utiilityCompanyCode;
    }

    public String getUtilityConsumerNumber() {
        return this.utilityConsumerNumber;
    }

    public void setUtilityConsumerNumber(String utilityConsumerNumber) {
        this.utilityConsumerNumber = utilityConsumerNumber;
    }

    public BigDecimal getWhtf() {
        return whtf;
    }

    public void setWhtf(BigDecimal whtf) {
        this.whtf = whtf;
    }

    public List<TblTransDetail> getTblTransDetails() {
        return this.tblTransDetails;
    }

    public void setTblTransDetails(List<TblTransDetail> tblTransDetails) {
        this.tblTransDetails = tblTransDetails;
    }

    public TblTransDetail addTblTransDetail(TblTransDetail tblTransDetail) {
        getTblTransDetails().add(tblTransDetail);
        tblTransDetail.setTblTransHead(this);

        return tblTransDetail;
    }

    public TblTransDetail removeTblTransDetail(TblTransDetail tblTransDetail) {
        getTblTransDetails().remove(tblTransDetail);
        tblTransDetail.setTblTransHead(null);

        return tblTransDetail;
    }

    public LkpChannel getLkpChannel() {
        return this.lkpChannel;
    }

    public void setLkpChannel(LkpChannel lkpChannel) {
        this.lkpChannel = lkpChannel;
    }

    public TblAccount getTblAccountCr() {
        return this.tblAccountCr;
    }

    public void setTblAccountCr(TblAccount tblAccount1) {
        this.tblAccountCr = tblAccount1;
    }

    public TblAccount getTblAccountDr() {
        return this.tblAccountDr;
    }

    public void setTblAccountDr(TblAccount tblAccount2) {
        this.tblAccountDr = tblAccount2;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public LkpTransStatus getLkpTransStatus() {
        return lkpTransStatus;
    }

    public void setLkpTransStatus(LkpTransStatus lkpTransStatus) {
        this.lkpTransStatus = lkpTransStatus;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LkpCurrency getLkpCurrency() {
        return lkpCurrency;
    }

    public void setLkpCurrency(LkpCurrency lkpCurrency) {
        this.lkpCurrency = lkpCurrency;
    }

    public TblTransInquiry getTblTransInquiry() {
        return tblTransInquiry;
    }

    public void setTblTransInquiry(TblTransInquiry tblTransInquiry) {
        this.tblTransInquiry = tblTransInquiry;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public BigDecimal getTransReversibleId() {
        return transReversibleId;
    }

    public void setTransReversibleId(BigDecimal transReversibleId) {
        this.transReversibleId = transReversibleId;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getDrLimitsExclude() {
        return drLimitsExclude;
    }

    public void setDrLimitsExclude(String drLimitsExclude) {
        this.drLimitsExclude = drLimitsExclude;
    }

    public String getCrLimitsExclude() {
        return crLimitsExclude;
    }

    public void setCrLimitsExclude(String crLimitsExclude) {
        this.crLimitsExclude = crLimitsExclude;
    }

    public String getFeeInclusiveExclusive() {
        return feeInclusiveExclusive;
    }

    public void setFeeInclusiveExclusive(String feeInclusiveExclusive) {
        this.feeInclusiveExclusive = feeInclusiveExclusive;
    }

    public String getFedInclusiveExclusive() {
        return fedInclusiveExclusive;
    }

    public void setFedInclusiveExclusive(String fedInclusiveExclusive) {
        this.fedInclusiveExclusive = fedInclusiveExclusive;
    }

    public BigDecimal getWhtc() {
        return whtc;
    }

    public void setWhtc(BigDecimal whtc) {
        this.whtc = whtc;
    }

    public String getDrMobileNo() {
        return drMobileNo;
    }

    public void setDrMobileNo(String drMobileNo) {
        this.drMobileNo = drMobileNo;
    }

    public String getCrMobileNo() {
        return crMobileNo;
    }

    public void setCrMobileNo(String crMobileNo) {
        this.crMobileNo = crMobileNo;
    }

    public Date getTransactionCompleteDate() {
        return transactionCompleteDate;
    }

    public void setTransactionCompleteDate(Date transactionCompleteDate) {
        this.transactionCompleteDate = transactionCompleteDate;
    }

    public LkpTransactionPurpose getLkpTransactionPurpose() {
        return lkpTransactionPurpose;
    }

    public void setLkpTransactionPurpose(LkpTransactionPurpose lkpTransactionPurpose) {
        this.lkpTransactionPurpose = lkpTransactionPurpose;
    }
}