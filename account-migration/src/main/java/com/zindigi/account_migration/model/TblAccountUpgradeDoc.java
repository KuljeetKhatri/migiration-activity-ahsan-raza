package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="TBL_ACCOUNT_UPGRADE_DOCS")
@NamedQuery(name="TblAccountUpgradeDoc.findAll", query="SELECT t FROM TblAccountUpgradeDoc t")
public class TblAccountUpgradeDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TBL_ACCOUNT_UPGRADE_DOCS_ACCOUNTUPGRADEDOCSID_GENERATOR", sequenceName="TBL_ACCOUNT_UPGRADE_DOCS_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ACCOUNT_UPGRADE_DOCS_ACCOUNTUPGRADEDOCSID_GENERATOR")
    @Column(name="ACCOUNT_UPGRADE_DOCS_ID")
    private long accountUpgradeDocsId;

    private String comments;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    @Column(name="FIELD_IDENTIFIER")
    private String fieldIdentifier;

    @Column(name="FIELD_NAME")
    private String fieldName;

    @Lob
    @Column(name="FIELD_VALUE")
    private String fieldValue;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    private BigDecimal updateindex;

    //bi-directional many-to-one association to LkpDocStatus
    @ManyToOne
    @JoinColumn(name="DOC_STATUS_ID")
    private LkpDocStatus lkpDocStatus;

    //bi-directional many-to-one association to TblAccountUpgrade
    @ManyToOne
    @JoinColumn(name="ACCOUNT_UPGRADE_ID")
    private TblAccountUpgrade tblAccountUpgrade;

    //bi-directional many-to-one association to TblDocument
    @ManyToOne
    @JoinColumn(name="DOCUMENT_ID")
    private TblDocument tblDocument;

    public TblAccountUpgradeDoc() {
    }

    public long getAccountUpgradeDocsId() {
        return this.accountUpgradeDocsId;
    }

    public void setAccountUpgradeDocsId(long accountUpgradeDocsId) {
        this.accountUpgradeDocsId = accountUpgradeDocsId;
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

    public String getFieldIdentifier() {
        return this.fieldIdentifier;
    }

    public void setFieldIdentifier(String fieldIdentifier) {
        this.fieldIdentifier = fieldIdentifier;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return this.fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
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

    public LkpDocStatus getLkpDocStatus() {
        return this.lkpDocStatus;
    }

    public void setLkpDocStatus(LkpDocStatus lkpDocStatus) {
        this.lkpDocStatus = lkpDocStatus;
    }

    public TblAccountUpgrade getTblAccountUpgrade() {
        return this.tblAccountUpgrade;
    }

    public void setTblAccountUpgrade(TblAccountUpgrade tblAccountUpgrade) {
        this.tblAccountUpgrade = tblAccountUpgrade;
    }

    public TblDocument getTblDocument() {
        return this.tblDocument;
    }

    public void setTblDocument(TblDocument tblDocument) {
        this.tblDocument = tblDocument;
    }

}
