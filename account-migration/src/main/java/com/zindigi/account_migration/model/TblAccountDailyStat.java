package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ACCOUNT_DAILY_STATS database table.
 * 
 */
@Entity
@Table(name="TBL_ACCOUNT_DAILY_STATS")
@NamedQuery(name="TblAccountDailyStat.findAll", query="SELECT t FROM TblAccountDailyStat t")
public class TblAccountDailyStat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ACCOUNT_DAILY_STATS_ACCOUNTDAILYSTATSID_GENERATOR", sequenceName="TBL_ACCOUNT_DAILY_STATS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ACCOUNT_DAILY_STATS_ACCOUNTDAILYSTATSID_GENERATOR")
	@Column(name="ACCOUNT_DAILY_STATS_ID")
	private long accountDailyStatsId;

	@Column(name="BALANCE_DISBURSED")
	private BigDecimal balanceDisbursed;

	@Column(name="BALANCE_RECEIVED")
	private BigDecimal balanceReceived;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="END_DAY_BALANCE")
	private BigDecimal endDayBalance;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="START_DAY_BALANCE")
	private BigDecimal startDayBalance;

	@Temporal(TemporalType.DATE)
	@Column(name="STATS_DATE")
	private Date statsDate;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private TblAccountModel tblAccountModel;

	public TblAccountDailyStat() {
	}

	public long getAccountDailyStatsId() {
		return this.accountDailyStatsId;
	}

	public void setAccountDailyStatsId(long accountDailyStatsId) {
		this.accountDailyStatsId = accountDailyStatsId;
	}

	public BigDecimal getBalanceDisbursed() {
		return this.balanceDisbursed;
	}

	public void setBalanceDisbursed(BigDecimal balanceDisbursed) {
		this.balanceDisbursed = balanceDisbursed;
	}

	public BigDecimal getBalanceReceived() {
		return this.balanceReceived;
	}

	public void setBalanceReceived(BigDecimal balanceReceived) {
		this.balanceReceived = balanceReceived;
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

	public BigDecimal getEndDayBalance() {
		return this.endDayBalance;
	}

	public void setEndDayBalance(BigDecimal endDayBalance) {
		this.endDayBalance = endDayBalance;
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

	public BigDecimal getStartDayBalance() {
		return this.startDayBalance;
	}

	public void setStartDayBalance(BigDecimal startDayBalance) {
		this.startDayBalance = startDayBalance;
	}

	public Date getStatsDate() {
		return this.statsDate;
	}

	public void setStatsDate(Date statsDate) {
		this.statsDate = statsDate;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblAccountModel getTblAccountModel() {
		return this.tblAccountModel;
	}

	public void setTblAccountModel(TblAccountModel tblAccount) {
		this.tblAccountModel = tblAccount;
	}

}