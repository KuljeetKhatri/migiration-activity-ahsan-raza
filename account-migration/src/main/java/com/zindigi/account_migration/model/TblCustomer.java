package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="TBL_CUSTOMER")
@NamedQuery(name="TblCustomer.findAll", query="SELECT t FROM TblCustomer t")
public class TblCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//    @SequenceGenerator(name="TBL_CUSTOMER_CUSTOMERID_GENERATOR", sequenceName="TBL_CUSTOMER_SEQ",allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CUSTOMER_CUSTOMERID_GENERATOR")
    @Column(name="CUSTOMER_ID")
    private long customerId;

    @Column(name="BIRTH_PLACE")
    private String birthPlace;

    @Column(name="CLS_VERIFIED")
    private String clsVerified;

    private String cnic;

    @Column(name="CNIC_EXPIRY_DATE")
    private String cnicExpiryDate;

    @Column(name="CNIC_HASH")
    private String cnicHash;

    @Column(name="CNIC_ISSUANCE_DATE")
    private String cnicIssuanceDate;

    @Column(name="CNIC_STATUS")
    private String cnicStatus;


    @Column(name="BIRTH_PLACE_VERIFIED")
    private String birthPlaceVerified;

    @Column(name="CNIC_EXPIRY_DATE_VERIFIED")
    private String cnicExpiryDateVerified;


    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    private String dob;

    private String email;

    @Column(name="FATHER_HUSBAND_NAME")
    private String fatherHusbandName;

    @Column(name="FULL_NAME")
    private String fullName;

    private String gender;

    @Column(name="IS_ACTIVE")
    private String isActive;

    @Column(name="IS_FILER")
    private String isFiler;

    @Lob
    private String kyc;

    @Column(name = "BVS")
    private String bvs;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name="MOBILE_NO")
    private String mobileNo;

    @Column(name="MOBILE_NO_HASH")
    private String mobileNoHash;

    @Column(name="MOTHER_MAIDEN_NAME")
    private String motherMaidenName;

    @Column(name="MOTHER_NAME_VERIFIED")
    private String motherNameVerified;

    @Column(name="MPIN_REGISTERED")
    private String mpinRegistered;

    @Column(name="NADRA_VERIFIED")
    private String nadraVerified;

    private BigDecimal updateindex;

    @Column(name="FULL_NAME_HASH")
    private String fullNameHash;

    @Column(name="EMAIL_HASH")
    private String emailHash;

    @Column(name="CRP_RATING")
    private String crpRating;

    @Column(name="CRP_SCORE")
    private BigDecimal crpScore;

    @Column(name = "CRP_DATE")
    private Date crpDate;

    @Column(name = "CRP_NEXT_DATE")
    private Date crpNextDate;

    @JsonIgnore
    //bi-directional many-to-one association to TblAccount
    @OneToMany(mappedBy="tblCustomer")
    private List<TblAccount> tblAccounts;

    @JsonIgnore
    //bi-directional many-to-one association to TblAddress
    @OneToMany(mappedBy="tblCustomer")
    private List<TblAddress> tblAddresses;

    @JsonIgnore
    //bi-directional many-to-one association to TblAppUser
    @OneToMany(mappedBy="tblCustomer")
    private List<TblAppUser> tblAppUsers;

    @JsonIgnore
    //bi-directional many-to-one association to LkpSegment
    @ManyToOne
    @JoinColumn(name="SEGMENT_ID")
    private LkpSegment lkpSegment;

    @JsonIgnore
    //bi-directional many-to-one association to TblTaxRate
    @ManyToOne
    @JoinColumn(name="FILER_TAX_RATE_ID")
    private TblTaxRate tblTaxRate1;

    @JsonIgnore
    //bi-directional many-to-one association to TblTaxRate
    @ManyToOne
    @JoinColumn(name="NON_FILER_TAX_RATE_ID")
    private TblTaxRate tblTaxRate2;

    @JsonIgnore
    //bi-directional many-to-one association to TblOtp
    @OneToMany(mappedBy="tblCustomer")
    private List<TblOtp> tblOtps;

    @JsonIgnore
    //bi-directional many-to-one association to TblUltraCustomer
    @OneToMany(mappedBy="tblCustomer")
    private List<TblUltraCustomer> tblUltraCustomers;

    @JsonIgnore
    //bi-directional many-to-one association to LkpSegment
    @ManyToOne
    @JoinColumn(name="STATUS_ID")
    private LkpStatus lkpStatus;

    @Column(name="IS_KYC_VERIFIED")
    private String isKycVerified;

    @Column(name="LAST_KYC_DATE")
    private Date lastKycDate;


    public TblCustomer() {
    }

    public String getIsKycVerified() {
        return isKycVerified;
    }

    public void setIsKycVerified(String isKycVerified) {
        this.isKycVerified = isKycVerified;
    }

    public Date getLastKycDate() {
        return lastKycDate;
    }

    public void setLastKycDate(Date lastKycDate) {
        this.lastKycDate = lastKycDate;
    }

    public String getFullNameHash() {
        return fullNameHash;
    }

    public void setFullNameHash(String fullNameHash) {
        this.fullNameHash = fullNameHash;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public LkpStatus getLkpStatus() {
        return lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }


    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getClsVerified() {
        return this.clsVerified;
    }

    public void setClsVerified(String clsVerified) {
        this.clsVerified = clsVerified;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getCnicExpiryDate() {
        return this.cnicExpiryDate;
    }

    public void setCnicExpiryDate(String cnicExpiryDate) {
        this.cnicExpiryDate = cnicExpiryDate;
    }

    public String getCnicHash() {
        return this.cnicHash;
    }

    public void setCnicHash(String cnicHash) {
        this.cnicHash = cnicHash;
    }

    public String getCnicIssuanceDate() {
        return this.cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(String cnicIssuanceDate) {
        this.cnicIssuanceDate = cnicIssuanceDate;
    }

    public String getCnicStatus() {
        return this.cnicStatus;
    }

    public void setCnicStatus(String cnicStatus) {
        this.cnicStatus = cnicStatus;
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

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherHusbandName() {
        return this.fatherHusbandName;
    }

    public void setFatherHusbandName(String fatherHusbandName) {
        this.fatherHusbandName = fatherHusbandName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getBvs() {
        return bvs;
    }

    public void setBvs(String bvs) {
        this.bvs = bvs;
    }

    public String getKyc() {
        return this.kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
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

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNoHash() {
        return this.mobileNoHash;
    }

    public void setMobileNoHash(String mobileNoHash) {
        this.mobileNoHash = mobileNoHash;
    }

    public String getMotherMaidenName() {
        return this.motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getMotherNameVerified() {
        return this.motherNameVerified;
    }

    public void setMotherNameVerified(String motherNameVerified) {
        this.motherNameVerified = motherNameVerified;
    }

    public String getMpinRegistered() {
        return this.mpinRegistered;
    }

    public void setMpinRegistered(String mpinRegistered) {
        this.mpinRegistered = mpinRegistered;
    }

    public String getNadraVerified() {
        return this.nadraVerified;
    }

    public void setNadraVerified(String nadraVerified) {
        this.nadraVerified = nadraVerified;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public List<TblAccount> getTblAccounts() {
        return this.tblAccounts;
    }

    public void setTblAccounts(List<TblAccount> tblAccounts) {
        this.tblAccounts = tblAccounts;
    }

    public TblAccount addTblAccount(TblAccount tblAccount) {
        getTblAccounts().add(tblAccount);
        tblAccount.setTblCustomer(this);

        return tblAccount;
    }

    public TblAccount removeTblAccount(TblAccount tblAccount) {
        getTblAccounts().remove(tblAccount);
        tblAccount.setTblCustomer(null);

        return tblAccount;
    }

    public List<TblAddress> getTblAddresses() {
        return this.tblAddresses;
    }

    public void setTblAddresses(List<TblAddress> tblAddresses) {
        this.tblAddresses = tblAddresses;
    }

    public TblAddress addTblAddress(TblAddress tblAddress) {
        getTblAddresses().add(tblAddress);
        tblAddress.setTblCustomer(this);

        return tblAddress;
    }

    public TblAddress removeTblAddress(TblAddress tblAddress) {
        getTblAddresses().remove(tblAddress);
        tblAddress.setTblCustomer(null);

        return tblAddress;
    }

    public List<TblAppUser> getTblAppUsers() {
        return this.tblAppUsers;
    }

    public void setTblAppUsers(List<TblAppUser> tblAppUsers) {
        this.tblAppUsers = tblAppUsers;
    }

    public TblAppUser addTblAppUser(TblAppUser tblAppUser) {
        getTblAppUsers().add(tblAppUser);
        tblAppUser.setTblCustomer(this);

        return tblAppUser;
    }

    public TblAppUser removeTblAppUser(TblAppUser tblAppUser) {
        getTblAppUsers().remove(tblAppUser);
        tblAppUser.setTblCustomer(null);

        return tblAppUser;
    }

    public LkpSegment getLkpSegment() {
        return this.lkpSegment;
    }

    public void setLkpSegment(LkpSegment lkpSegment) {
        this.lkpSegment = lkpSegment;
    }

    public TblTaxRate getTblTaxRate1() {
        return this.tblTaxRate1;
    }

    public void setTblTaxRate1(TblTaxRate tblTaxRate1) {
        this.tblTaxRate1 = tblTaxRate1;
    }

    public TblTaxRate getTblTaxRate2() {
        return this.tblTaxRate2;
    }

    public void setTblTaxRate2(TblTaxRate tblTaxRate2) {
        this.tblTaxRate2 = tblTaxRate2;
    }

    public List<TblOtp> getTblOtps() {
        return this.tblOtps;
    }

    public void setTblOtps(List<TblOtp> tblOtps) {
        this.tblOtps = tblOtps;
    }

    public TblOtp addTblOtp(TblOtp tblOtp) {
        getTblOtps().add(tblOtp);
        tblOtp.setTblCustomer(this);

        return tblOtp;
    }

    public TblOtp removeTblOtp(TblOtp tblOtp) {
        getTblOtps().remove(tblOtp);
        tblOtp.setTblCustomer(null);

        return tblOtp;
    }

    public List<TblUltraCustomer> getTblUltraCustomers() {
        return this.tblUltraCustomers;
    }

    public void setTblUltraCustomers(List<TblUltraCustomer> tblUltraCustomers) {
        this.tblUltraCustomers = tblUltraCustomers;
    }

    public TblUltraCustomer addTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
        getTblUltraCustomers().add(tblUltraCustomer);
        tblUltraCustomer.setTblCustomer(this);

        return tblUltraCustomer;
    }

    public TblUltraCustomer removeTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
        getTblUltraCustomers().remove(tblUltraCustomer);
        tblUltraCustomer.setTblCustomer(null);

        return tblUltraCustomer;
    }


    public String getBirthPlaceVerified() {
        return birthPlaceVerified;
    }

    public void setBirthPlaceVerified(String birthPlaceVerified) {
        this.birthPlaceVerified = birthPlaceVerified;
    }

    public String getCnicExpiryDateVerified() {
        return cnicExpiryDateVerified;
    }

    public void setCnicExpiryDateVerified(String cnicExpiryDateVerified) {
        this.cnicExpiryDateVerified = cnicExpiryDateVerified;
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


    public Date getCrpDate() {
        return crpDate;
    }

    public void setCrpDate(Date crpDate) {
        this.crpDate = crpDate;
    }

    public Date getCrpNextDate() {
        return crpNextDate;
    }

    public void setCrpNextDate(Date crpNextDate) {
        this.crpNextDate = crpNextDate;
    }
}