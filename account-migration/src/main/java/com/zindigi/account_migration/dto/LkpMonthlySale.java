package com.zindigi.account_migration.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_MONTHLY_SALE database table.
 * 
 */
@Entity
@Table(name="LKP_MONTHLY_SALE")
@NamedQuery(name="LkpMonthlySale.findAll", query="SELECT l FROM LkpMonthlySale l")
public class LkpMonthlySale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_MONTHLY_SALE_MONTHLYSALEID_GENERATOR", sequenceName="LKP_MONTHLY_SALE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_MONTHLY_SALE_MONTHLYSALEID_GENERATOR")
	@Column(name="MONTHLY_SALE_ID")
	private long monthlySaleId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MONTHLY_SALE_CODE")
	private String monthlySaleCode;

	@Column(name="MONTHLY_SALE_DESCR")
	private String monthlySaleDescr;

	@Column(name="MONTHLY_SALE_NAME")
	private String monthlySaleName;

	private BigDecimal updateindex;


	public LkpMonthlySale() {
	}

	public long getMonthlySaleId() {
		return this.monthlySaleId;
	}

	public void setMonthlySaleId(long monthlySaleId) {
		this.monthlySaleId = monthlySaleId;
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

	public String getMonthlySaleCode() {
		return this.monthlySaleCode;
	}

	public void setMonthlySaleCode(String monthlySaleCode) {
		this.monthlySaleCode = monthlySaleCode;
	}

	public String getMonthlySaleDescr() {
		return this.monthlySaleDescr;
	}

	public void setMonthlySaleDescr(String monthlySaleDescr) {
		this.monthlySaleDescr = monthlySaleDescr;
	}

	public String getMonthlySaleName() {
		return this.monthlySaleName;
	}

	public void setMonthlySaleName(String monthlySaleName) {
		this.monthlySaleName = monthlySaleName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}





}