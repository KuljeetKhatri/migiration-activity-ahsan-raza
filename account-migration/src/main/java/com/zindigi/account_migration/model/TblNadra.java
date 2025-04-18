package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_NADRA database table.
 * 
 */
@Entity
@Table(name="TBL_NADRA")
@NamedQuery(name="TblNadra.findAll", query="SELECT t FROM TblNadra t")
public class TblNadra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_NADRA_NADRAID_GENERATOR", sequenceName="TBL_NADRA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_NADRA_NADRAID_GENERATOR")
	@Column(name="NADRA_ID")
	private long nadraId;

	@Column(name="BIRTH_PLACE")
	private String birthPlace;

	@Column(name="BIRTH_PLACE_EN")
	private String birthPlaceEn;

	@Column(name="CARD_TYPE")
	private String cardType;

	private String cnic;

	@Column(name="CNIC_HASH")
	private String cnicHash;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DATE_OF_BIRTH")
	private String dateOfBirth;

	private String email;

	@Column(name="EXPIRY_DATE")
	private String expiryDate;

	@Column(name="FATHER_HUSBAND_NAME_EN")
	private String fatherHusbandNameEn;

	private String gender;

	private BigDecimal id;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUANCE_DATE")
	private Date issuanceDate;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="MOTHER_NAME")
	private String motherName;

	@Column(name="MOTHER_NAME_EN")
	private String motherNameEn;

	private String name;

	@Column(name="NAME_EN")
	private String nameEn;

	private String nationality;

	@Column(name="PERMANENT_ADDRESS_EN")
	private String permanentAddressEn;

	@Column(name="PRESENT_ADDRESS")
	private String presentAddress;

	@Column(name="PRESENT_ADDRESS_EN")
	private String presentAddressEn;

	@Column(name="RESIDENCE_COUNTRY")
	private String residenceCountry;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="STATUS_MESSAGE")
	private String statusMessage;
	@Column(name="CITY")
	private String city;
	private BigDecimal updateindex;

	public TblNadra() {
	}

	public long getNadraId() {
		return this.nadraId;
	}

	public void setNadraId(long nadraId) {
		this.nadraId = nadraId;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthPlaceEn() {
		return this.birthPlaceEn;
	}

	public void setBirthPlaceEn(String birthPlaceEn) {
		this.birthPlaceEn = birthPlaceEn;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCnic() {
		return this.cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public String getCnicHash() {
		return this.cnicHash;
	}

	public void setCnicHash(String cnicHash) {
		this.cnicHash = cnicHash;
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

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFatherHusbandNameEn() {
		return this.fatherHusbandNameEn;
	}

	public void setFatherHusbandNameEn(String fatherHusbandNameEn) {
		this.fatherHusbandNameEn = fatherHusbandNameEn;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getIssuanceDate() {
		return this.issuanceDate;
	}

	public void setIssuanceDate(Date issuanceDate) {
		this.issuanceDate = issuanceDate;
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

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherNameEn() {
		return this.motherNameEn;
	}

	public void setMotherNameEn(String motherNameEn) {
		this.motherNameEn = motherNameEn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPermanentAddressEn() {
		return this.permanentAddressEn;
	}

	public void setPermanentAddressEn(String permanentAddressEn) {
		this.permanentAddressEn = permanentAddressEn;
	}

	public String getPresentAddress() {
		return this.presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPresentAddressEn() {
		return this.presentAddressEn;
	}

	public void setPresentAddressEn(String presentAddressEn) {
		this.presentAddressEn = presentAddressEn;
	}

	public String getResidenceCountry() {
		return this.residenceCountry;
	}

	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return this.statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}