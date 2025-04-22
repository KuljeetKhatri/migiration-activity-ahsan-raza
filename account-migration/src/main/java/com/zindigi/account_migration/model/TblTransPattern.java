package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpCurrency;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_PATTERN database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_PATTERN")
@NamedQuery(name="TblTransPattern.findAll", query="SELECT t FROM TblTransPattern t")
public class TblTransPattern implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_PATTERN_TRANSPATTERNID_GENERATOR", sequenceName="TBL_TRANS_PATTERN_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_PATTERN_TRANSPATTERNID_GENERATOR")
	@Column(name="TRANS_PATTERN_ID")
	private long transPatternId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String id;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="TARGET_ACCOUNT_TYPE")
	private String targetAccountType;

	@Column(name="TRANS_CLASSIFICATION")
	private String transClassification;

	@Column(name="TRANS_SIGN")
	private String transSign;

	private String type;

	private BigDecimal updateindex;

	@JsonIgnore
	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	//bi-directional many-to-one association to LkpCurrency
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private LkpCurrency lkpCurrency;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccount;

	public TblTransPattern() {
	}

	public long getTransPatternId() {
		return this.transPatternId;
	}

	public void setTransPatternId(long transPatternId) {
		this.transPatternId = transPatternId;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTargetAccountType() {
		return this.targetAccountType;
	}

	public void setTargetAccountType(String targetAccountType) {
		this.targetAccountType = targetAccountType;
	}

	public String getTransClassification() {
		return this.transClassification;
	}

	public void setTransClassification(String transClassification) {
		this.transClassification = transClassification;
	}

	public String getTransSign() {
		return this.transSign;
	}

	public void setTransSign(String transSign) {
		this.transSign = transSign;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

	public LkpCurrency getLkpCurrency() {
		return lkpCurrency;
	}

	public void setLkpCurrency(LkpCurrency lkpCurrency) {
		this.lkpCurrency = lkpCurrency;
	}

	public TblAccountModel getTblAccountModel() {
		return tblAccount;
	}

	public void setTblAccountModel(TblAccountModel tblAccount) {
		this.tblAccount = tblAccount;
	}
}