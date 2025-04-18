package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_INQUIRY database table.
 */
@Entity
@Table(name = "TBL_TRANS_INQUIRY")
@NamedQuery(name = "TblTransInquiry.findAll", query = "SELECT t FROM TblTransInquiry t")
public class TblTransInquiry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_TRANS_INQUIRY_TRANSINQUIRYID_GENERATOR", sequenceName = "TBL_TRANS_INQUIRY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_TRANS_INQUIRY_TRANSINQUIRYID_GENERATOR")
    @Column(name = "TRANS_INQUIRY_ID")
    private long transInquiryId;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    @Column(name = "INQUIRY_ID")
    private String inquiryId;

    @Column(name = "PRODUCT_ID")
    private BigDecimal productId;

    //bi-directional many-to-one association to LkpTransStatus
    @ManyToOne
    @JoinColumn(name = "TRANS_STATUS_ID")
    private LkpTransStatus lkpTransStatus;

    @Column(name = "RECEIVER_ACCOUNT_NO")
    private String recieverAccountNo;

    @Column(name = "RECEIVER_ACCOUNT_TITLE")
    private String recieverAccountTitle;

    @Column(name = "SENDER_ACCOUNT_NO")
    private String senderAccountNo;

    @Column(name = "SENDER_ACCOUNT_TITLE")
    private String senderAccountTitle;

    @Column(name = "TRANSACTION_AMOUNT")
    private String transactionAmount;

    public TblTransInquiry() {
    }

    public long getTransInquiryId() {
        return this.transInquiryId;
    }

    public void setTransInquiryId(long transInquiryId) {
        this.transInquiryId = transInquiryId;
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

    public String getInquiryId() {
        return this.inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public BigDecimal getProductId() {
        return this.productId;
    }

    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public LkpTransStatus getLkpTransStatus() {
        return this.lkpTransStatus;
    }

    public void setLkpTransStatus(LkpTransStatus lkpTransStatus) {
        this.lkpTransStatus = lkpTransStatus;
    }

    public String getRecieverAccountNo() {
        return recieverAccountNo;
    }

    public void setRecieverAccountNo(String recieverAccountNo) {
        this.recieverAccountNo = recieverAccountNo;
    }

    public String getRecieverAccountTitle() {
        return recieverAccountTitle;
    }

    public void setRecieverAccountTitle(String recieverAccountTitle) {
        this.recieverAccountTitle = recieverAccountTitle;
    }

    public String getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(String senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public String getSenderAccountTitle() {
        return senderAccountTitle;
    }

    public void setSenderAccountTitle(String senderAccountTitle) {
        this.senderAccountTitle = senderAccountTitle;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}