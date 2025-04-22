package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="LKP_SEGMENT")
@NamedQuery(name="LkpSegmentModel.findAll", query="SELECT l FROM LkpSegmentModel l")
public class LkpSegmentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="LKP_SEGMENT_SEGMENTID_GENERATOR", sequenceName="LKP_SEGMENT_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_SEGMENT_SEGMENTID_GENERATOR")
    @Column(name="SEGMENT_ID")
    private long segmentId;

    @Temporal(TemporalType.DATE)
    @Column(name="AGREEMENT_SIGNING_DATE")
    private Date agreementSigningDate;

    @Column(name="CLIENT_EMAIL")
    private String clientEmail;

    @Column(name="CLIENT_MOBILE_NO")
    private String clientMobileNo;

    @Column(name="CLIENT_POC_NAME")
    private String clientPocName;

    @Column(name="COMPANY_ADDRESS")
    private String companyAddress;

    private Date createdate;

    private BigDecimal createuser;

    @Column(name="IS_ACTIVE")
    private String isActive;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name="SALARY_STATUS")
    private String salaryStatus;

    @Column(name="SEGMENT_CODE")
    private String segmentCode;

    @Column(name="SEGMENT_DESCR")
    private String segmentDescr;

    @Column(name="SEGMENT_NAME")
    private String segmentName;

    private BigDecimal updateindex;

    @Column(name="UPLOAD_AGREEMENT")
    private String uploadAgreement;

    //bi-directional many-to-one association to LkpBusinessType
    @ManyToOne
    @JoinColumn(name="BUSINESS_TYPE_ID")
    private LkpBusinessType lkpBusinessType;

    //bi-directional many-to-one association to LkpRegion
    @ManyToOne
    @JoinColumn(name="REGION_ID")
    private LkpRegion lkpRegion;

    //bi-directional many-to-one association to LkpStatus
    @ManyToOne
    @JoinColumn(name="STATUS_ID")
    private com.mfs.commonservice.model.LkpStatus lkpStatus;

    //bi-directional many-to-one association to TblSalesForce
    @ManyToOne
    @JoinColumn(name="SALES_FORCE_ID")
    @JsonIgnore
    private TblSalesForce tblSalesForce;

    //bi-directional many-to-one association to TblSalesRoleDetail
    @ManyToOne
    @JoinColumn(name="SALES_ROLE_DETAIL_ID")
    private TblSalesRoleDetail tblSalesRoleDetail;

    @JsonIgnore
    //bi-directional many-to-one association to TblChargesConfig
    @OneToMany(mappedBy="lkpSegment")
    private List<TblChargesConfig> tblChargesConfigs;

    @JsonIgnore
    //bi-directional many-to-one association to TblChargesConfigRule
    @OneToMany(mappedBy="lkpSegment")
    private List<TblChargesConfigRule> tblChargesConfigRules;

    @JsonIgnore
    //bi-directional many-to-one association to TblChargesShare
    @OneToMany(mappedBy="lkpSegment")
    private List<TblChargesShare> tblChargesShares;

    @JsonIgnore
    //bi-directional many-to-one association to TblChargesShareRule
    @OneToMany(mappedBy="lkpSegment")
    private List<TblChargesShareRule> tblChargesShareRules;

    @JsonIgnore
    //bi-directional many-to-one association to TblCustomer
    @OneToMany(mappedBy="lkpSegment")
    private List<TblCustomer> tblCustomers;

    @JsonIgnore
    //bi-directional many-to-one association to TblExcludeLimit
    @OneToMany(mappedBy="lkpSegment")
    private List<TblExcludeLimit> tblExcludeLimits;

    @JsonIgnore
    //bi-directional many-to-one association to TblOperatingHoursRule
    @OneToMany(mappedBy="lkpSegment")
    private List<TblOperatingHoursRule> tblOperatingHoursRules;

    @JsonIgnore
    //bi-directional many-to-one association to TblRequest
    @OneToMany(mappedBy="lkpSegment")
    private List<TblRequest> tblRequests;

    @JsonIgnore
    //bi-directional many-to-one association to TblThresholdCharge
    @OneToMany(mappedBy="lkpSegment")
    private List<TblThresholdCharge> tblThresholdCharges;

    @JsonIgnore
    //bi-directional many-to-one association to TblThresholdChargesRule
    @OneToMany(mappedBy="lkpSegment")
    private List<TblThresholdChargesRule> tblThresholdChargesRules;

    @JsonIgnore
    //bi-directional many-to-one association to TblTransLimitConfig
    @OneToMany(mappedBy="lkpSegment")
    private List<TblTransLimitConfig> tblTransLimitConfigs;

    @JsonIgnore
    //bi-directional many-to-one association to TblTransPatternHead
    @OneToMany(mappedBy="lkpSegment")
    private List<TblTransPatternHead> tblTransPatternHeads;

    @Transient
    private String createdBy;

    @Transient
    private String updatedBy;

    @Transient
    private String base64content;


    public long getSegmentId() {
        return this.segmentId;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }

    public Date getAgreementSigningDate() {
        return this.agreementSigningDate;
    }

    public void setAgreementSigningDate(Date agreementSigningDate) {
        this.agreementSigningDate = agreementSigningDate;
    }

    public String getClientEmail() {
        return this.clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientMobileNo() {
        return this.clientMobileNo;
    }

    public void setClientMobileNo(String clientMobileNo) {
        this.clientMobileNo = clientMobileNo;
    }

    public String getClientPocName() {
        return this.clientPocName;
    }

    public void setClientPocName(String clientPocName) {
        this.clientPocName = clientPocName;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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

    public String getSalaryStatus() {
        return this.salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public String getSegmentCode() {
        return this.segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentDescr() {
        return this.segmentDescr;
    }

    public void setSegmentDescr(String segmentDescr) {
        this.segmentDescr = segmentDescr;
    }

    public String getSegmentName() {
        return this.segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public String getUploadAgreement() {
        return this.uploadAgreement;
    }

    public void setUploadAgreement(String uploadAgreement) {
        this.uploadAgreement = uploadAgreement;
    }

    public LkpBusinessType getLkpBusinessType() {
        return this.lkpBusinessType;
    }

    public void setLkpBusinessType(LkpBusinessType lkpBusinessType) {
        this.lkpBusinessType = lkpBusinessType;
    }

    public LkpRegion getLkpRegion() {
        return this.lkpRegion;
    }

    public void setLkpRegion(LkpRegion lkpRegion) {
        this.lkpRegion = lkpRegion;
    }

    public com.mfs.commonservice.model.LkpStatus getLkpStatus() {
        return this.lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }

    public TblSalesForce getTblSalesForce() {
        return this.tblSalesForce;
    }

    public void setTblSalesForce(TblSalesForce tblSalesForce) {
        this.tblSalesForce = tblSalesForce;
    }

    public TblSalesRoleDetail getTblSalesRoleDetail() {
        return this.tblSalesRoleDetail;
    }

    public void setTblSalesRoleDetail(TblSalesRoleDetail tblSalesRoleDetail) {
        this.tblSalesRoleDetail = tblSalesRoleDetail;
    }

    public List<TblChargesConfig> getTblChargesConfigs() {
        return this.tblChargesConfigs;
    }

    public void setTblChargesConfigs(List<TblChargesConfig> tblChargesConfigs) {
        this.tblChargesConfigs = tblChargesConfigs;
    }



    public List<TblChargesConfigRule> getTblChargesConfigRules() {
        return this.tblChargesConfigRules;
    }

    public void setTblChargesConfigRules(List<TblChargesConfigRule> tblChargesConfigRules) {
        this.tblChargesConfigRules = tblChargesConfigRules;
    }



    public List<TblChargesShare> getTblChargesShares() {
        return this.tblChargesShares;
    }

    public void setTblChargesShares(List<TblChargesShare> tblChargesShares) {
        this.tblChargesShares = tblChargesShares;
    }



    public List<TblChargesShareRule> getTblChargesShareRules() {
        return this.tblChargesShareRules;
    }

    public void setTblChargesShareRules(List<TblChargesShareRule> tblChargesShareRules) {
        this.tblChargesShareRules = tblChargesShareRules;
    }



    public List<TblCustomer> getTblCustomers() {
        return this.tblCustomers;
    }

    public void setTblCustomers(List<TblCustomer> tblCustomers) {
        this.tblCustomers = tblCustomers;
    }



    public List<TblExcludeLimit> getTblExcludeLimits() {
        return this.tblExcludeLimits;
    }

    public void setTblExcludeLimits(List<TblExcludeLimit> tblExcludeLimits) {
        this.tblExcludeLimits = tblExcludeLimits;
    }



    public List<TblOperatingHoursRule> getTblOperatingHoursRules() {
        return this.tblOperatingHoursRules;
    }

    public void setTblOperatingHoursRules(List<TblOperatingHoursRule> tblOperatingHoursRules) {
        this.tblOperatingHoursRules = tblOperatingHoursRules;
    }


    public List<TblRequest> getTblRequests() {
        return this.tblRequests;
    }

    public void setTblRequests(List<TblRequest> tblRequests) {
        this.tblRequests = tblRequests;
    }



    public List<TblThresholdCharge> getTblThresholdCharges() {
        return this.tblThresholdCharges;
    }

    public void setTblThresholdCharges(List<TblThresholdCharge> tblThresholdCharges) {
        this.tblThresholdCharges = tblThresholdCharges;
    }



    public List<TblThresholdChargesRule> getTblThresholdChargesRules() {
        return this.tblThresholdChargesRules;
    }

    public void setTblThresholdChargesRules(List<TblThresholdChargesRule> tblThresholdChargesRules) {
        this.tblThresholdChargesRules = tblThresholdChargesRules;
    }



    public List<TblTransLimitConfig> getTblTransLimitConfigs() {
        return this.tblTransLimitConfigs;
    }

    public void setTblTransLimitConfigs(List<TblTransLimitConfig> tblTransLimitConfigs) {
        this.tblTransLimitConfigs = tblTransLimitConfigs;
    }



    public List<TblTransPatternHead> getTblTransPatternHeads() {
        return this.tblTransPatternHeads;
    }

    public void setTblTransPatternHeads(List<TblTransPatternHead> tblTransPatternHeads) {
        this.tblTransPatternHeads = tblTransPatternHeads;
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

    public String getBase64content() {
        return base64content;
    }

    public void setBase64content(String base64content) {
        this.base64content = base64content;
    }

    public LkpSegmentModel(long segmentId) {
        this.segmentId = segmentId;
    }

    public LkpSegmentModel() {
    }
}