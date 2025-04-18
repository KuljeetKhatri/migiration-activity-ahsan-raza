package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_RAAST_RESPONSE database table.
 * 
 */
@Entity
@Table(name="TBL_RAAST_RESPONSE")
@NamedQuery(name="TblRaastResponse.findAll", query="SELECT t FROM TblRaastResponse t")
public class TblRaastResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RAAST_RESPONSE_RAASTRESPONSEID_GENERATOR", sequenceName="TBL_RAAST_RESPONSE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RAAST_RESPONSE_RAASTRESPONSEID_GENERATOR")
	@Column(name="RAAST_RESPONSE_ID")
	private long raastResponseId;

	private String address;

	@Column(name="ALIAS_ID")
	private String aliasId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOC_NUMBER")
	private String docNumber;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="DOC_VALIDITY")
	private String docValidity;

	private String name;

	@Column(name="REGISTRAIION_ID")
	private String registraiionId;

	@Column(name="REGISTRATION_STATUS")
	private String registrationStatus;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	@Column(name="RESPONSE_MESSAGE")
	private String responseMessage;

	@Column(name="UID_TYPE")
	private String uidType;

	@Column(name="UID_VALUE")
	private String uidValue;

	@Column(name="RAAST_CUSTOMER_ID")
	private String raastCustomerId;

	@Column(name="RAAST_ACCOUNT_ID")
	private String raastAccountId;

	//bi-directional many-to-one association to TblRaastRequest
	@ManyToOne
	@JoinColumn(name="RAAST_REQUEST_ID")
	private TblRaastRequest tblRaastRequest;

	public TblRaastResponse() {
	}

	public String getRaastCustomerId() {
		return raastCustomerId;
	}

	public void setRaastCustomerId(String raastCustomerId) {
		this.raastCustomerId = raastCustomerId;
	}

	public String getRaastAccountId() {
		return raastAccountId;
	}

	public void setRaastAccountId(String raastRecordId) {
		this.raastAccountId = raastRecordId;
	}

	public long getRaastResponseId() {
		return this.raastResponseId;
	}

	public void setRaastResponseId(long raastResponseId) {
		this.raastResponseId = raastResponseId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
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

	public String getDocNumber() {
		return this.docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistraiionId() {
		return this.registraiionId;
	}

	public void setRegistraiionId(String registraiionId) {
		this.registraiionId = registraiionId;
	}

	public String getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
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

	public TblRaastRequest getTblRaastRequest() {
		return this.tblRaastRequest;
	}

	public void setTblRaastRequest(TblRaastRequest tblRaastRequest) {
		this.tblRaastRequest = tblRaastRequest;
	}

}