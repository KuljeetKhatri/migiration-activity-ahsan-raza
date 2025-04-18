package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_MONTHLY_SPENDING database table.
 * 
 */
@Entity
@Table(name="LKP_MONTHLY_SPENDING")
@NamedQuery(name="LkpMonthlySpending.findAll", query="SELECT l FROM LkpMonthlySpending l")
public class LkpMonthlySpending implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_MONTHLY_SPENDING_MONTHLYSPENDINGID_GENERATOR", sequenceName="LKP_MONTHLY_SPENDING_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_MONTHLY_SPENDING_MONTHLYSPENDINGID_GENERATOR")
	@Column(name="MONTHLY_SPENDING_ID")
	private long monthlySpendingId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MONTHLY_SPENDING_CODE")
	private String monthlySpendingCode;

	@Column(name="MONTHLY_SPENDING_DESCR")
	private String monthlySpendingDescr;

	@Column(name="MONTHLY_SPENDING_NAME")
	private String monthlySpendingName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomer
	@OneToMany(mappedBy="lkpMonthlySpending")
	private List<TblUltraCustomer> tblUltraCustomers;

	public LkpMonthlySpending() {
	}

	public long getMonthlySpendingId() {
		return this.monthlySpendingId;
	}

	public void setMonthlySpendingId(long monthlySpendingId) {
		this.monthlySpendingId = monthlySpendingId;
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

	public String getMonthlySpendingCode() {
		return this.monthlySpendingCode;
	}

	public void setMonthlySpendingCode(String monthlySpendingCode) {
		this.monthlySpendingCode = monthlySpendingCode;
	}

	public String getMonthlySpendingDescr() {
		return this.monthlySpendingDescr;
	}

	public void setMonthlySpendingDescr(String monthlySpendingDescr) {
		this.monthlySpendingDescr = monthlySpendingDescr;
	}

	public String getMonthlySpendingName() {
		return this.monthlySpendingName;
	}

	public void setMonthlySpendingName(String monthlySpendingName) {
		this.monthlySpendingName = monthlySpendingName;
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
		tblUltraCustomer.setLkpMonthlySpending(this);

		return tblUltraCustomer;
	}

	public TblUltraCustomer removeTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		getTblUltraCustomers().remove(tblUltraCustomer);
		tblUltraCustomer.setLkpMonthlySpending(null);

		return tblUltraCustomer;
	}

}