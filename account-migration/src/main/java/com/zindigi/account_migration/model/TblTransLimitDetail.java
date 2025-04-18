package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_LIMIT_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_LIMIT_DETAIL")
@NamedQuery(name="TblTransLimitDetail.findAll", query="SELECT t FROM TblTransLimitDetail t")
public class TblTransLimitDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_LIMIT_DETAIL_TRANSLIMITDETAILID_GENERATOR", sequenceName="TBL_TRANS_LIMIT_DETAIL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_LIMIT_DETAIL_TRANSLIMITDETAILID_GENERATOR")
	@Column(name="TRANS_LIMIT_DETAIL_ID")
	private long transLimitDetailId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	//bi-directional many-to-one association to TblTransLimit
	@ManyToOne
	@JoinColumn(name="TRANS_LIMIT_ID")
	private TblTransLimit tblTransLimit;

	public TblTransLimitDetail() {
	}

	public long getTransLimitDetailId() {
		return this.transLimitDetailId;
	}

	public void setTransLimitDetailId(long transLimitDetailId) {
		this.transLimitDetailId = transLimitDetailId;
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

	public TblTransLimit getTblTransLimit() {
		return this.tblTransLimit;
	}

	public void setTblTransLimit(TblTransLimit tblTransLimit) {
		this.tblTransLimit = tblTransLimit;
	}

}