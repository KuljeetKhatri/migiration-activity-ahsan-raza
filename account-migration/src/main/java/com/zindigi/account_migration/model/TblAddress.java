package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpCity;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ADDRESS database table.
 * 
 */
@Entity
@Table(name="TBL_ADDRESS")
@NamedQuery(name="TblAddress.findAll", query="SELECT t FROM TblAddress t")
public class TblAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ADDRESS_ADDRESSID_GENERATOR", sequenceName="TBL_ADDRESS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ADDRESS_ADDRESSID_GENERATOR")
	@Column(name="ADDRESS_ID")
	private long addressId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FULL_ADDRESS")
	private String fullAddress;

	@Column(name="HOUSE_NO")
	private String houseNo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="POSTAL_CODE")
	private BigDecimal postalCode;

	@Column(name="STREET_ADDRESS")
	private String streetAddress;

	@Column(name="STREET_NO")
	private String streetNo;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpAddressType
	@ManyToOne
	@JoinColumn(name="ADDRESS_TYPE_ID")
	private LkpAddressType lkpAddressType;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpDistrict
	@ManyToOne
	@JoinColumn(name="DISTRICT_ID")
	private LkpDistrict lkpDistrict;

	//bi-directional many-to-one association to LkpProvince
	@ManyToOne
	@JoinColumn(name="PROVINCE_ID")
	private LkpProvince lkpProvince;


	//bi-directional many-to-one association to TblAgent
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private TblAgent tblAgent;

	//bi-directional many-to-one association to TblCustomer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private TblCustomer tblCustomer;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	public TblAddress() {
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
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

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
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

	public BigDecimal getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(BigDecimal postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetNo() {
		return this.streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpAddressType getLkpAddressType() {
		return this.lkpAddressType;
	}

	public void setLkpAddressType(LkpAddressType lkpAddressType) {
		this.lkpAddressType = lkpAddressType;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpDistrict getLkpDistrict() {
		return this.lkpDistrict;
	}

	public void setLkpDistrict(LkpDistrict lkpDistrict) {
		this.lkpDistrict = lkpDistrict;
	}

	public LkpProvince getLkpProvince() {
		return this.lkpProvince;
	}

	public void setLkpProvince(LkpProvince lkpProvince) {
		this.lkpProvince = lkpProvince;
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

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}