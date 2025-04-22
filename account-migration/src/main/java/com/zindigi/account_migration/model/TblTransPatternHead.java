package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpChannel;
import com.mfs.commonservice.model.LkpSegment;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_TRANS_PATTERN_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_PATTERN_HEAD")
@NamedQuery(name="TblTransPatternHead.findAll", query="SELECT t FROM TblTransPatternHead t")
public class TblTransPatternHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_PATTERN_HEAD_TRANSPATTERNHEADID_GENERATOR", sequenceName="TBL_TRANS_PATTERN_HEAD_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_PATTERN_HEAD_TRANSPATTERNHEADID_GENERATOR")
	@Column(name="TRANS_PATTERN_HEAD_ID")
	private long transPatternHeadId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCY_ID")
	private BigDecimal currencyId;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PATTERN_CODE")
	private String patternCode;

	@Column(name="PATTERN_NAME")
	private String patternName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblTransPatternDetail
	@OneToMany(mappedBy="tblTransPatternHead")
	private List<TblTransPatternDetail> tblTransPatternDetails;

	//bi-directional many-to-one association to LkpChannel
	@ManyToOne
	@JoinColumn(name="CHANNEL_ID")
	private LkpChannel lkpChannel;

	//bi-directional many-to-one association to LkpSegment
	@ManyToOne
	@JoinColumn(name="SEGMENT_ID")
	private LkpSegment lkpSegment;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblTransPatternHead() {
	}

	public long getTransPatternHeadId() {
		return this.transPatternHeadId;
	}

	public void setTransPatternHeadId(long transPatternHeadId) {
		this.transPatternHeadId = transPatternHeadId;
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

	public BigDecimal getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(BigDecimal currencyId) {
		this.currencyId = currencyId;
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

	public String getPatternCode() {
		return this.patternCode;
	}

	public void setPatternCode(String patternCode) {
		this.patternCode = patternCode;
	}

	public String getPatternName() {
		return this.patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblTransPatternDetail> getTblTransPatternDetails() {
		return this.tblTransPatternDetails;
	}

	public void setTblTransPatternDetails(List<TblTransPatternDetail> tblTransPatternDetails) {
		this.tblTransPatternDetails = tblTransPatternDetails;
	}

	public TblTransPatternDetail addTblTransPatternDetail(TblTransPatternDetail tblTransPatternDetail) {
		getTblTransPatternDetails().add(tblTransPatternDetail);
		tblTransPatternDetail.setTblTransPatternHead(this);

		return tblTransPatternDetail;
	}

	public TblTransPatternDetail removeTblTransPatternDetail(TblTransPatternDetail tblTransPatternDetail) {
		getTblTransPatternDetails().remove(tblTransPatternDetail);
		tblTransPatternDetail.setTblTransPatternHead(null);

		return tblTransPatternDetail;
	}

	public LkpChannel getLkpChannel() {
		return this.lkpChannel;
	}

	public void setLkpChannel(LkpChannel lkpChannel) {
		this.lkpChannel = lkpChannel;
	}

	public LkpSegment getLkpSegment() {
		return this.lkpSegment;
	}

	public void setLkpSegment(LkpSegment lkpSegment) {
		this.lkpSegment = lkpSegment;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}