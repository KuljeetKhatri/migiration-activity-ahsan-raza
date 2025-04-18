package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ULTRA_CUSTOMER_DOCS database table.
 * 
 */
@Entity
@Table(name="TBL_ULTRA_CUSTOMER_DOCS")
@NamedQuery(name="TblUltraCustomerDoc.findAll", query="SELECT t FROM TblUltraCustomerDoc t")
public class TblUltraCustomerDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ULTRA_CUSTOMER_DOCS_ULTRACUSTOMERDOCSID_GENERATOR", sequenceName="TBL_ULTRA_CUSTOMER_DOCS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ULTRA_CUSTOMER_DOCS_ULTRACUSTOMERDOCSID_GENERATOR")
	@Column(name="ULTRA_CUSTOMER_DOCS_ID")
	private long ultraCustomerDocsId;

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

	@ManyToOne
	@JoinColumn(name="DOC_STATUS_ID")
	private LkpDocStatus lkpDocStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomer
	@ManyToOne
	@JoinColumn(name="ULTRA_CUSTOMER_ID")
	private TblUltraCustomer tblUltraCustomer;

	public TblUltraCustomerDoc() {
	}

	public long getUltraCustomerDocsId() {
		return this.ultraCustomerDocsId;
	}

	public void setUltraCustomerDocsId(long ultraCustomerDocsId) {
		this.ultraCustomerDocsId = ultraCustomerDocsId;
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

	public LkpDocStatus getLkpDocStatus() {
		return lkpDocStatus;
	}

	public void setLkpDocStatus(LkpDocStatus lkpDocStatus) {
		this.lkpDocStatus = lkpDocStatus;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblUltraCustomer getTblUltraCustomer() {
		return this.tblUltraCustomer;
	}

	public void setTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		this.tblUltraCustomer = tblUltraCustomer;
	}

}