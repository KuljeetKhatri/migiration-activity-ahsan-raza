package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_FEE_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_FEE_TYPE")
@NamedQuery(name="LkpFeeType.findAll", query="SELECT l FROM LkpFeeType l")
public class LkpFeeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_FEE_TYPE_FEETYPEID_GENERATOR", sequenceName="LKP_FEE_TYPE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_FEE_TYPE_FEETYPEID_GENERATOR")
	@Column(name="FEE_TYPE_ID")
	private long feeTypeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FEE_TYPE_CODE")
	private String feeTypeCode;

	@Column(name="FEE_TYPE_DESCR")
	private String feeTypeDescr;

	@Column(name="FEE_TYPE_NAME")
	private String feeTypeName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpFeeType() {
	}

	public long getFeeTypeId() {
		return this.feeTypeId;
	}

	public void setFeeTypeId(long feeTypeId) {
		this.feeTypeId = feeTypeId;
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

	public String getFeeTypeCode() {
		return this.feeTypeCode;
	}

	public void setFeeTypeCode(String feeTypeCode) {
		this.feeTypeCode = feeTypeCode;
	}

	public String getFeeTypeDescr() {
		return this.feeTypeDescr;
	}

	public void setFeeTypeDescr(String feeTypeDescr) {
		this.feeTypeDescr = feeTypeDescr;
	}

	public String getFeeTypeName() {
		return this.feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
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