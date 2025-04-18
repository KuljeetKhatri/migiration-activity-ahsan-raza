package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_AREA database table.
 * 
 */
@Entity
@Table(name="LKP_AREA")
@NamedQuery(name="LkpArea.findAll", query="SELECT l FROM LkpArea l")
public class LkpArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_AREA_AREAID_GENERATOR", sequenceName="LKP_AREA_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_AREA_AREAID_GENERATOR")
	@Column(name="AREA_ID")
	private long areaId;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="AREA_DESCR")
	private String areaDescr;

	@Column(name="AREA_NAME")
	private String areaName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="lkpArea")
	private List<TblUltraCustomer> tblUltraCustomers;

	public LkpArea() {
	}

	public long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDescr() {
		return this.areaDescr;
	}

	public void setAreaDescr(String areaDescr) {
		this.areaDescr = areaDescr;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public List<TblUltraCustomer> getTblUltraCustomers() {
		return this.tblUltraCustomers;
	}

	public void setTblUltraCustomers(List<TblUltraCustomer> tblUltraCustomers) {
		this.tblUltraCustomers = tblUltraCustomers;
	}

	public TblUltraCustomer addTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().add(tblUltraCustomer);
		tblUltraCustomer.setLkpArea(this);

		return tblUltraCustomer;
	}

	public TblUltraCustomer removeTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().remove(tblUltraCustomer);
		tblUltraCustomer.setLkpArea(null);

		return tblUltraCustomer;
	}

}