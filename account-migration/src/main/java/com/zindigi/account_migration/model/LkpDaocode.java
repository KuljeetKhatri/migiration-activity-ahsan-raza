package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_DAOCODE database table.
 * 
 */
@Entity
@Table(name="LKP_DAOCODE")
@NamedQuery(name="LkpDaocode.findAll", query="SELECT l FROM LkpDaocode l")
public class LkpDaocode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_DAOCODE_DAOCODEID_GENERATOR", sequenceName="LKP_DAOCODE_SEQ" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_DAOCODE_DAOCODEID_GENERATOR")
	@Column(name="DAOCODE_ID")
	private long daocodeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DAOCODE_CODE")
	private String daocodeCode;

	@Column(name="DAOCODE_DESCR")
	private String daocodeDescr;

	@Column(name="DAOCODE_NAME")
	private String daocodeName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpDaocode() {
	}

	public long getDaocodeId() {
		return this.daocodeId;
	}

	public void setDaocodeId(long daocodeId) {
		this.daocodeId = daocodeId;
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

	public String getDaocodeCode() {
		return this.daocodeCode;
	}

	public void setDaocodeCode(String daocodeCode) {
		this.daocodeCode = daocodeCode;
	}

	public String getDaocodeDescr() {
		return this.daocodeDescr;
	}

	public void setDaocodeDescr(String daocodeDescr) {
		this.daocodeDescr = daocodeDescr;
	}

	public String getDaocodeName() {
		return this.daocodeName;
	}

	public void setDaocodeName(String daocodeName) {
		this.daocodeName = daocodeName;
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

}