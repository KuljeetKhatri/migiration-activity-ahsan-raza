package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_ADDRESS_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_ADDRESS_TYPE")
@NamedQuery(name="LkpAddressType.findAll", query="SELECT l FROM LkpAddressType l")
public class LkpAddressType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_ADDRESS_TYPE_ADDRESSTYPEID_GENERATOR", sequenceName="LKP_ADDRESS_TYPE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ADDRESS_TYPE_ADDRESSTYPEID_GENERATOR")
	@Column(name="ADDRESS_TYPE_ID")
	private long addressTypeId;

	@Column(name="ADDRESS_TYPE_CODE")
	private String addressTypeCode;

	@Column(name="ADDRESS_TYPE_DESCR")
	private String addressTypeDescr;

	@Column(name="ADDRESS_TYPE_NAME")
	private String addressTypeName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	public LkpAddressType() {
	}

	public long getAddressTypeId() {
		return this.addressTypeId;
	}

	public void setAddressTypeId(long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressTypeCode() {
		return this.addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getAddressTypeDescr() {
		return this.addressTypeDescr;
	}

	public void setAddressTypeDescr(String addressTypeDescr) {
		this.addressTypeDescr = addressTypeDescr;
	}

	public String getAddressTypeName() {
		return this.addressTypeName;
	}

	public void setAddressTypeName(String addressTypeName) {
		this.addressTypeName = addressTypeName;
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

}