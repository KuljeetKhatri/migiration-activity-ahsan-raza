package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_RAAST_REQUEST database table.
 * 
 */
@Entity
@Table(name="TBL_RAAST_REQUEST")
@NamedQuery(name="TblRaastRequest.findAll", query="SELECT t FROM TblRaastRequest t")
public class TblRaastRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RAAST_REQUEST_RAASTREQUESTID_GENERATOR", sequenceName="TBL_RAAST_REQUEST_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RAAST_REQUEST_RAASTREQUESTID_GENERATOR")
	@Column(name="RAAST_REQUEST_ID")
	private long raastRequestId;

	@Column(name="ALIAS_ID")
	private String aliasId;

	@Column(name="ALIAS_VALUE")
	private String aliasValue;

	@Column(name="BUSINESS_NAME")
	private String businessName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String currency;

	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="DOC_VALUE")
	private String docValue;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="DOC_VALIDITY")
	private String docValidity;

	@Column(name="END_POINT")
	private String endPoint;

	private String iban;

	private String mcc;

	@Column(name="UID_TYPE")
	private String uidType;

	@Column(name="UID_VALUE")
	private String uidValue;

	//bi-directional many-to-one association to LkpRaastAliasType
	@ManyToOne
	@JoinColumn(name="RAAST_ALIAS_TYPE_ID")
	private LkpRaastAliasType lkpRaastAliasType;

	//bi-directional many-to-one association to TblRaastResponse
	@OneToMany(mappedBy="tblRaastRequest")
	private List<TblRaastResponse> tblRaastResponses;

	public TblRaastRequest() {
	}

	public long getRaastRequestId() {
		return this.raastRequestId;
	}

	public void setRaastRequestId(long raastRequestId) {
		this.raastRequestId = raastRequestId;
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	public String getAliasValue() {
		return this.aliasValue;
	}

	public void setAliasValue(String aliasValue) {
		this.aliasValue = aliasValue;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDocValue() {
		return this.docValue;
	}

	public void setDocValue(String docName) {
		this.docValue = docName;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocValidity() {
		return this.docValidity;
	}

	public void setDocValidity(String docValidity) {
		this.docValidity = docValidity;
	}

	public String getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getUidType() {
		return this.uidType;
	}

	public void setUidType(String uidType) {
		this.uidType = uidType;
	}

	public String getUidValue() {
		return this.uidValue;
	}

	public void setUidValue(String uidValue) {
		this.uidValue = uidValue;
	}

	public LkpRaastAliasType getLkpRaastAliasType() {
		return this.lkpRaastAliasType;
	}

	public void setLkpRaastAliasType(LkpRaastAliasType lkpRaastAliasType) {
		this.lkpRaastAliasType = lkpRaastAliasType;
	}

	public List<TblRaastResponse> getTblRaastResponses() {
		return this.tblRaastResponses;
	}

	public void setTblRaastResponses(List<TblRaastResponse> tblRaastResponses) {
		this.tblRaastResponses = tblRaastResponses;
	}

	public TblRaastResponse addTblRaastRespons(TblRaastResponse tblRaastRespons) {
		getTblRaastResponses().add(tblRaastRespons);
		tblRaastRespons.setTblRaastRequest(this);

		return tblRaastRespons;
	}

	public TblRaastResponse removeTblRaastRespons(TblRaastResponse tblRaastRespons) {
		getTblRaastResponses().remove(tblRaastRespons);
		tblRaastRespons.setTblRaastRequest(null);

		return tblRaastRespons;
	}

}