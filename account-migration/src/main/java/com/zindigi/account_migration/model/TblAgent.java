package com.zindigi.account_migration.model;


import com.zindigi.account_migration.dto.AddressInfo;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_AGENT database table.
 */
@Entity
@Table(name = "TBL_AGENT")
@NamedQuery(name = "TblAgent.findAll", query = "SELECT t FROM TblAgent t")
public class TblAgent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_AGENT_AGENTID_GENERATOR", sequenceName = "TBL_AGENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_AGENT_AGENTID_GENERATOR")
    @Column(name = "AGENT_ID")
    private long agentId;

    @Column(name = "FRANCHISE_NAME")
    private String franchiseName;

    @Column(name = "AGENT_EMAIL")
    private String agentEmail;

    @Column(name = "AGENT_NAME")
    private String agentName;

    @Column(name = "MOBILE_NO")
    private String agentPhoneNo;

    private String cnic;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "IS_FILER")
    private String isFiler;

//	@Column(name="IS_PARENT_AGENT")
//	private BigDecimal isParentAgent;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name = "PARENT_AGENT_ID")
    private BigDecimal parentAgentId;

    private BigDecimal updateindex;

    //bi-directional many-to-one association to TblCommissionProfile
    @ManyToOne
    @JoinColumn(name = "COMMISSION_PROFILE_ID")
    private TblCommissionProfile tblCommissionProfile;


    @ManyToOne
    @JoinColumn(name = "TRANS_LIMIT_ID")
    private TblTransLimit tblTransLimit;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private LkpStatus lkpStatus;

    @ManyToOne
    @JoinColumn(name = "PRICING_PROFILE_ID")
    private TblPricingProfile tblPricingProfile;

    @ManyToOne
    @JoinColumn(name = "SALES_HIERARCHY_ID")
    private TblSalesHierarchy tblSalesHierarchy;

    @ManyToOne
    @JoinColumn(name = "COMMISSION_HIERARCHY_ID")
    private TblCommissionHierarchy tblCommissionHierarchy;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_CLASSIFICATION_ID")
    private LkpAccountClassification lkpAccountClassification;

    @ManyToOne
    @JoinColumn(name = "KYC_SET_HEAD_ID")
    private TblKycSetHead tblKycSetHead;

    @ManyToOne
    @JoinColumn(name = "SALES_FORCE_ID")
    private TblSalesForce tblSalesForce;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private LkpBranch lkpBranch;

    @OneToMany(mappedBy = "tblAgent", cascade = CascadeType.ALL)
    private List<TblAgentChannel> tblAgentChannelList;

    @OneToMany(mappedBy = "tblAgent", cascade = CascadeType.ALL)
    private List<TblAgentDirector> tblAgentDirectorList;

    private String chequeBook;

    private String debitCard;

    private String debitCardName;

    private String debitCardAddress;

    private String atmWaiver;

    private String chequeWaiver;

    private String noOfLeaves;

    private String franchiseAddress;

    private String bvs;

    private String kyc;

    private String isMasterAgent;

    private String cnicHash;

    private String mobileNoHash;

    private String ntn;

    private String ntnHash;

    private String cnicIssuanceDate;

    private String ecib;

    private String clsVerified;

    private String gender;

    @Transient
    private String createdBy;

    @Transient
    private String updatedBy;

    @Transient
    private String parentName;

    @Transient
    private String kycView;

    @Transient
    private String accountNumber;

    @Transient
    private String accountTitle;

    @Transient
    private BigDecimal accountStatus;

    @Transient
    private String remarks;

    @Transient
    private String motherName;

    @Transient
    private String fatherName;

    @Transient
    private String birthPlace;

    @Transient
    private List<AddressInfo> addressInfos;

    @Transient
    private TblEcibResponse tblEcibResponse;

    @Column(name = "CRP_RATING")
    private String crpRating;

    @Column(name = "CRP_SCORE")
    private BigDecimal crpScore;

    private BigDecimal segmentId;

    @Transient
    private BigDecimal totalCount;

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public long getAgentId() {
        return this.agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getFranchiseName() {
        return this.franchiseName;
    }

    public void setFranchiseName(String agentContactName) {
        this.franchiseName = agentContactName;
    }

    public String getAgentEmail() {
        return this.agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhoneNo() {
        return this.agentPhoneNo;
    }

    public void setAgentPhoneNo(String agentPhoneNo) {
        this.agentPhoneNo = agentPhoneNo;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
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

    public String getIsFiler() {
        return this.isFiler;
    }

    public void setIsFiler(String isFiler) {
        this.isFiler = isFiler;
    }

//	public BigDecimal getIsParentAgent() {
//		return this.isParentAgent;
//	}
//
//	public void setIsParentAgent(BigDecimal isParentAgent) {
//		this.isParentAgent = isParentAgent;
//	}

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

    public BigDecimal getParentAgentId() {
        return this.parentAgentId;
    }

    public void setParentAgentId(BigDecimal parentAgentId) {
        this.parentAgentId = parentAgentId;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }


    public TblTransLimit getTblTransLimit() {
        return tblTransLimit;
    }

    public void setTblTransLimit(TblTransLimit tblTransLimit) {
        this.tblTransLimit = tblTransLimit;
    }

    public LkpStatus getLkpStatus() {
        return lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }

    public TblPricingProfile getTblPricingProfile() {
        return tblPricingProfile;
    }

    public void setTblPricingProfile(TblPricingProfile tblPricingProfile) {
        this.tblPricingProfile = tblPricingProfile;
    }

    public TblSalesHierarchy getTblSalesHierarchy() {
        return tblSalesHierarchy;
    }

    public void setTblSalesHierarchy(TblSalesHierarchy tblSalesHierarchy) {
        this.tblSalesHierarchy = tblSalesHierarchy;
    }

    public LkpAccountClassification getLkpAccountClassification() {
        return lkpAccountClassification;
    }

    public void setLkpAccountClassification(LkpAccountClassification lkpAccountClassification) {
        this.lkpAccountClassification = lkpAccountClassification;
    }

    public TblKycSetHead getTblKycSetHead() {
        return tblKycSetHead;
    }

    public void setTblKycSetHead(TblKycSetHead tblKycSetHead) {
        this.tblKycSetHead = tblKycSetHead;
    }

    public String getChequeBook() {
        return chequeBook;
    }

    public void setChequeBook(String chequeBook) {
        this.chequeBook = chequeBook;
    }

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCard) {
        this.debitCard = debitCard;
    }

    public String getDebitCardName() {
        return debitCardName;
    }

    public void setDebitCardName(String debitCardName) {
        this.debitCardName = debitCardName;
    }

    public String getDebitCardAddress() {
        return debitCardAddress;
    }

    public void setDebitCardAddress(String debitCardAddress) {
        this.debitCardAddress = debitCardAddress;
    }

    public String getAtmWaiver() {
        return atmWaiver;
    }

    public void setAtmWaiver(String waiver) {
        this.atmWaiver = waiver;
    }

    public String getNoOfLeaves() {
        return noOfLeaves;
    }

    public void setNoOfLeaves(String noOfLeaves) {
        this.noOfLeaves = noOfLeaves;
    }


    public String getFranchiseAddress() {
        return franchiseAddress;
    }

    public void setFranchiseAddress(String agentParentHusbandName) {
        this.franchiseAddress = agentParentHusbandName;
    }

    public String getBvs() {
        return bvs;
    }

    public void setBvs(String bvs) {
        this.bvs = bvs;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public String getIsMasterAgent() {
        return isMasterAgent;
    }

    public void setIsMasterAgent(String isMasterAgent) {
        this.isMasterAgent = isMasterAgent;
    }

    public List<TblAgentChannel> getTblAgentChannelList() {
        return tblAgentChannelList;
    }

    public void setTblAgentChannelList(List<TblAgentChannel> tblAgentChannelList) {
        this.tblAgentChannelList = tblAgentChannelList;
    }


    public TblCommissionProfile getTblCommissionProfile() {
        return this.tblCommissionProfile;
    }

    public void setTblCommissionProfile(TblCommissionProfile tblCommissionProfile) {
        this.tblCommissionProfile = tblCommissionProfile;
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


    public String getCnicHash() {
        return cnicHash;
    }

    public void setCnicHash(String cnicHash) {
        this.cnicHash = cnicHash;
    }

    public String getMobileNoHash() {
        return mobileNoHash;
    }

    public void setMobileNoHash(String mobileNoHash) {
        this.mobileNoHash = mobileNoHash;
    }

    public TblSalesForce getTblSalesForce() {
        return tblSalesForce;
    }

    public void setTblSalesForce(TblSalesForce tblSalesForce) {
        this.tblSalesForce = tblSalesForce;
    }

    public LkpBranch getLkpBranch() {
        return lkpBranch;
    }

    public void setLkpBranch(LkpBranch lkpBranch) {
        this.lkpBranch = lkpBranch;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getNtn() {
        return ntn;
    }

    public void setNtn(String ntn) {
        this.ntn = ntn;
    }

    public String getNtnHash() {
        return ntnHash;
    }

    public void setNtnHash(String ntnHash) {
        this.ntnHash = ntnHash;
    }

    public String getCnicIssuanceDate() {
        return cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(String cnicIssuanceDate) {
        this.cnicIssuanceDate = cnicIssuanceDate;
    }

    public List<TblAgentDirector> getTblAgentDirectorList() {
        return tblAgentDirectorList;
    }

    public void setTblAgentDirectorList(List<TblAgentDirector> tblAgentDirectorList) {
        this.tblAgentDirectorList = tblAgentDirectorList;
    }

    public String getKycView() {
        return kycView;
    }

    public void setKycView(String kycView) {
        this.kycView = kycView;
    }

    public String getEcib() {
        return ecib;
    }

    public void setEcib(String ecib) {
        this.ecib = ecib;
    }

    public String getChequeWaiver() {
        return chequeWaiver;
    }

    public void setChequeWaiver(String chequeWaiver) {
        this.chequeWaiver = chequeWaiver;
    }

    public TblCommissionHierarchy getTblCommissionHierarchy() {
        return tblCommissionHierarchy;
    }

    public void setTblCommissionHierarchy(TblCommissionHierarchy tblCommissionHierarchy) {
        this.tblCommissionHierarchy = tblCommissionHierarchy;
    }

    public String getClsVerified() {
        return clsVerified;
    }

    public void setClsVerified(String clsVerified) {
        this.clsVerified = clsVerified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public BigDecimal getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(BigDecimal accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public List<AddressInfo> getAddressInfos() {
        return addressInfos;
    }

    public void setAddressInfos(List<AddressInfo> addressInfos) {
        this.addressInfos = addressInfos;
    }

    public String getCrpRating() {
        return crpRating;
    }

    public void setCrpRating(String crpRating) {
        this.crpRating = crpRating;
    }

    public BigDecimal getCrpScore() {
        return crpScore;
    }

    public void setCrpScore(BigDecimal crpScore) {
        this.crpScore = crpScore;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public TblEcibResponse getTblEcibResponse() {
        return tblEcibResponse;
    }

    public void setTblEcibResponse(TblEcibResponse tblEcibResponse) {
        this.tblEcibResponse = tblEcibResponse;
    }

    public BigDecimal getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(BigDecimal segmentId) {
        this.segmentId = segmentId;
    }
}