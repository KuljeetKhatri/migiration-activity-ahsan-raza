package com.zindigi.account_migration.dto;


import com.mfs.commonservice.model.LkpCity;
import com.mfs.commonservice.model.LkpStatus;
import com.zindigi.account_migration.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_MERCHANT database table.
 * 
 */
@Entity
@Table(name="TBL_MERCHANT")
@NamedQuery(name="TblMerchant.findAll", query="SELECT t FROM TblMerchant t")
public class TblMerchant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MERCHANT_MERCHANTID_GENERATOR", sequenceName="TBL_MERCHANT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MERCHANT_MERCHANTID_GENERATOR")
	@Column(name="MERCHANT_ID")
	private long merchantId;

	@Column(name="BUSINESS_ADDRESS")
	private String businessAddress;

	@Column(name="BUSINESS_NAME")
	private String businessName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String qr;

	@Column(name="TILL_NO")
	private String tillNo;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBusinessType
	@ManyToOne
	@JoinColumn(name="BUSINESS_TYPE_ID")
	private LkpBusinessType lkpBusinessType;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpMonthlySale
	@ManyToOne
	@JoinColumn(name="MONTHLY_SALES_EXPECTED_ID")
	private LkpMonthlySale lkpMonthlySale;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccountModel;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="PROOF_OF_BUSINESS_ID")
	private TblDocument tblDocument1;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="CNIC_FRONT_ID")
	private TblDocument tblDocument2;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="CNIC_BACK_ID")
	private TblDocument tblDocument3;

	//bi-directional many-to-one association to TblDocument
	@ManyToOne
	@JoinColumn(name="SELFIE_ID")
	private TblDocument tblDocument4;

	//bi-directional many-to-one association to TblMerchantDoc
	@OneToMany(mappedBy="tblMerchantModel")
	private List<TblMerchantDoc> tblMerchantDocs;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name = "STATUS_ID")
	private LkpStatus lkpStatus;


	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public TblMerchant() {
	}

	public long getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
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

	public String getQr() {
		return this.qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
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

	public LkpBusinessType getLkpBusinessType() {
		return this.lkpBusinessType;
	}

	public void setLkpBusinessType(LkpBusinessType lkpBusinessType) {
		this.lkpBusinessType = lkpBusinessType;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpMonthlySale getLkpMonthlySale() {
		return this.lkpMonthlySale;
	}

	public void setLkpMonthlySale(LkpMonthlySale lkpMonthlySale) {
		this.lkpMonthlySale = lkpMonthlySale;
	}

	public TblAccountModel getTblAccountModel() {
		return this.tblAccountModel;
	}

	public void setTblAccountModel(TblAccountModel tblAccountModel) {
		this.tblAccountModel = tblAccountModel;
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

	public List<TblMerchantDoc> getTblMerchantDocs() {
		return this.tblMerchantDocs;
	}

	public void setTblMerchantDocs(List<TblMerchantDoc> tblMerchantDocs) {
		this.tblMerchantDocs = tblMerchantDocs;
	}

	public TblMerchantDoc addTblMerchantDoc(TblMerchantDoc tblMerchantDoc) {
		getTblMerchantDocs().add(tblMerchantDoc);
		tblMerchantDoc.setTblMerchant(this);

		return tblMerchantDoc;
	}

	public TblMerchantDoc removeTblMerchantDoc(TblMerchantDoc tblMerchantDoc) {
		getTblMerchantDocs().remove(tblMerchantDoc);
		tblMerchantDoc.setTblMerchant(null);

		return tblMerchantDoc;
	}

}