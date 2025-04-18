package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_ULTRA_USAGE database table.
 * 
 */
@Entity
@Table(name="LKP_ULTRA_USAGE")
@NamedQuery(name="LkpUltraUsage.findAll", query="SELECT l FROM LkpUltraUsage l")
public class LkpUltraUsage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ULTRA_USAGE_ULTRAUSAGEID_GENERATOR", sequenceName="LKP_ULTRA_USAGE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ULTRA_USAGE_ULTRAUSAGEID_GENERATOR")
	@Column(name="ULTRA_USAGE_ID")
	private long ultraUsageId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="ULTRA_USAGE_CODE")
	private String ultraUsageCode;

	@Column(name="ULTRA_USAGE_DESCR")
	private String ultraUsageDescr;

	@Column(name="ULTRA_USAGE_NAME")
	private String ultraUsageName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="lkpUltraUsage")
	private List<TblUltraCustomer> tblUltraCustomers;

	public LkpUltraUsage() {
	}

	public long getUltraUsageId() {
		return this.ultraUsageId;
	}

	public void setUltraUsageId(long ultraUsageId) {
		this.ultraUsageId = ultraUsageId;
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

	public String getUltraUsageCode() {
		return this.ultraUsageCode;
	}

	public void setUltraUsageCode(String ultraUsageCode) {
		this.ultraUsageCode = ultraUsageCode;
	}

	public String getUltraUsageDescr() {
		return this.ultraUsageDescr;
	}

	public void setUltraUsageDescr(String ultraUsageDescr) {
		this.ultraUsageDescr = ultraUsageDescr;
	}

	public String getUltraUsageName() {
		return this.ultraUsageName;
	}

	public void setUltraUsageName(String ultraUsageName) {
		this.ultraUsageName = ultraUsageName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblUltraCustomer> getTblUltraCustomers() {
		return this.tblUltraCustomers;
	}

	public void setTblUltraCustomers(List<TblUltraCustomer> tblUltraCustomers) {
		this.tblUltraCustomers = tblUltraCustomers;
	}

	public TblUltraCustomer addTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().add(tblUltraCustomer);
		tblUltraCustomer.setLkpUltraUsage(this);

		return tblUltraCustomer;
	}

	public TblUltraCustomer removeTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().remove(tblUltraCustomer);
		tblUltraCustomer.setLkpUltraUsage(null);

		return tblUltraCustomer;
	}

}