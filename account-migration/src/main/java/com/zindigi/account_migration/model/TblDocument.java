package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_DOCUMENT database table.
 * 
 */
@Entity
@Table(name="TBL_DOCUMENT")
@NamedQuery(name="TblDocument.findAll", query="SELECT t FROM TblDocument t")
public class TblDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_DOCUMENT_DOCUMENTID_GENERATOR", sequenceName="TBL_DOCUMENT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_DOCUMENT_DOCUMENTID_GENERATOR")
	@Column(name="DOCUMENT_ID")
	private long documentId;

	@Column(name="APP_USER_ID")
	private BigDecimal appUserId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOCUMENT_EXT")
	private String documentExt;

	@Column(name="DOCUMENT_PATH")
	private String documentPath;

	@Column(name="IS_DISCREPANT")
	private String isDiscrepant;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpDocumentType
	@ManyToOne
	@JoinColumn(name="DOCUMENT_TYPE_ID")
	private LkpDocumentType lkpDocumentType;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument1")
	private List<TblUltraCustomer> tblUltraCustomers1;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument2")
	private List<TblUltraCustomer> tblUltraCustomers2;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument3")
	private List<TblUltraCustomer> tblUltraCustomers3;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument4")
	private List<TblUltraCustomer> tblUltraCustomers4;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument5")
	private List<TblUltraCustomer> tblUltraCustomers5;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument6")
	private List<TblUltraCustomer> tblUltraCustomers6;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="tblDocument7")
	private List<TblUltraCustomer> tblUltraCustomers7;

	public TblDocument() {
	}

	public long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public BigDecimal getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(BigDecimal appUserId) {
		this.appUserId = appUserId;
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

	public String getDocumentExt() {
		return this.documentExt;
	}

	public void setDocumentExt(String documentExt) {
		this.documentExt = documentExt;
	}

	public String getDocumentPath() {
		return this.documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
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

	public LkpDocumentType getLkpDocumentType() {
		return this.lkpDocumentType;
	}

	public void setLkpDocumentType(LkpDocumentType lkpDocumentType) {
		this.lkpDocumentType = lkpDocumentType;
	}

	public List<TblUltraCustomer> getTblUltraCustomers1() {
		return this.tblUltraCustomers1;
	}

	public void setTblUltraCustomers1(List<TblUltraCustomer> tblUltraCustomers1) {
		this.tblUltraCustomers1 = tblUltraCustomers1;
	}

	public TblUltraCustomer addTblUltraCustomers1(TblUltraCustomer tblUltraCustomers1) {
		getTblUltraCustomers1().add(tblUltraCustomers1);
		tblUltraCustomers1.setTblDocument1(this);

		return tblUltraCustomers1;
	}

	public TblUltraCustomer removeTblUltraCustomers1(TblUltraCustomer tblUltraCustomers1) {
		getTblUltraCustomers1().remove(tblUltraCustomers1);
		tblUltraCustomers1.setTblDocument1(null);

		return tblUltraCustomers1;
	}

	public List<TblUltraCustomer> getTblUltraCustomers2() {
		return this.tblUltraCustomers2;
	}

	public void setTblUltraCustomers2(List<TblUltraCustomer> tblUltraCustomers2) {
		this.tblUltraCustomers2 = tblUltraCustomers2;
	}

	public TblUltraCustomer addTblUltraCustomers2(TblUltraCustomer tblUltraCustomers2) {
		getTblUltraCustomers2().add(tblUltraCustomers2);
		tblUltraCustomers2.setTblDocument2(this);

		return tblUltraCustomers2;
	}

	public TblUltraCustomer removeTblUltraCustomers2(TblUltraCustomer tblUltraCustomers2) {
		getTblUltraCustomers2().remove(tblUltraCustomers2);
		tblUltraCustomers2.setTblDocument2(null);

		return tblUltraCustomers2;
	}

	public List<TblUltraCustomer> getTblUltraCustomers3() {
		return this.tblUltraCustomers3;
	}

	public void setTblUltraCustomers3(List<TblUltraCustomer> tblUltraCustomers3) {
		this.tblUltraCustomers3 = tblUltraCustomers3;
	}

	public TblUltraCustomer addTblUltraCustomers3(TblUltraCustomer tblUltraCustomers3) {
		getTblUltraCustomers3().add(tblUltraCustomers3);
		tblUltraCustomers3.setTblDocument3(this);

		return tblUltraCustomers3;
	}

	public TblUltraCustomer removeTblUltraCustomers3(TblUltraCustomer tblUltraCustomers3) {
		getTblUltraCustomers3().remove(tblUltraCustomers3);
		tblUltraCustomers3.setTblDocument3(null);

		return tblUltraCustomers3;
	}

	public List<TblUltraCustomer> getTblUltraCustomers4() {
		return this.tblUltraCustomers4;
	}

	public void setTblUltraCustomers4(List<TblUltraCustomer> tblUltraCustomers4) {
		this.tblUltraCustomers4 = tblUltraCustomers4;
	}

	public TblUltraCustomer addTblUltraCustomers4(TblUltraCustomer tblUltraCustomers4) {
		getTblUltraCustomers4().add(tblUltraCustomers4);
		tblUltraCustomers4.setTblDocument4(this);

		return tblUltraCustomers4;
	}

	public TblUltraCustomer removeTblUltraCustomers4(TblUltraCustomer tblUltraCustomers4) {
		getTblUltraCustomers4().remove(tblUltraCustomers4);
		tblUltraCustomers4.setTblDocument4(null);

		return tblUltraCustomers4;
	}

	public List<TblUltraCustomer> getTblUltraCustomers5() {
		return this.tblUltraCustomers5;
	}

	public void setTblUltraCustomers5(List<TblUltraCustomer> tblUltraCustomers5) {
		this.tblUltraCustomers5 = tblUltraCustomers5;
	}

	public TblUltraCustomer addTblUltraCustomers5(TblUltraCustomer tblUltraCustomers5) {
		getTblUltraCustomers5().add(tblUltraCustomers5);
		tblUltraCustomers5.setTblDocument5(this);

		return tblUltraCustomers5;
	}

	public TblUltraCustomer removeTblUltraCustomers5(TblUltraCustomer tblUltraCustomers5) {
		getTblUltraCustomers5().remove(tblUltraCustomers5);
		tblUltraCustomers5.setTblDocument5(null);

		return tblUltraCustomers5;
	}

	public List<TblUltraCustomer> getTblUltraCustomers6() {
		return this.tblUltraCustomers6;
	}

	public void setTblUltraCustomers6(List<TblUltraCustomer> tblUltraCustomers6) {
		this.tblUltraCustomers6 = tblUltraCustomers6;
	}

	public TblUltraCustomer addTblUltraCustomers6(TblUltraCustomer tblUltraCustomers6) {
		getTblUltraCustomers6().add(tblUltraCustomers6);
		tblUltraCustomers6.setTblDocument6(this);

		return tblUltraCustomers6;
	}

	public TblUltraCustomer removeTblUltraCustomers6(TblUltraCustomer tblUltraCustomers6) {
		getTblUltraCustomers6().remove(tblUltraCustomers6);
		tblUltraCustomers6.setTblDocument6(null);

		return tblUltraCustomers6;
	}

	public List<TblUltraCustomer> getTblUltraCustomers7() {
		return this.tblUltraCustomers7;
	}

	public void setTblUltraCustomers7(List<TblUltraCustomer> tblUltraCustomers7) {
		this.tblUltraCustomers7 = tblUltraCustomers7;
	}

	public TblUltraCustomer addTblUltraCustomers7(TblUltraCustomer tblUltraCustomers7) {
		getTblUltraCustomers7().add(tblUltraCustomers7);
		tblUltraCustomers7.setTblDocument7(this);

		return tblUltraCustomers7;
	}

	public TblUltraCustomer removeTblUltraCustomers7(TblUltraCustomer tblUltraCustomers7) {
		getTblUltraCustomers7().remove(tblUltraCustomers7);
		tblUltraCustomers7.setTblDocument7(null);

		return tblUltraCustomers7;
	}

	public String getIsDiscrepant() {
		return isDiscrepant;
	}

	public void setIsDiscrepant(String isDiscrepant) {
		this.isDiscrepant = isDiscrepant;
	}
}