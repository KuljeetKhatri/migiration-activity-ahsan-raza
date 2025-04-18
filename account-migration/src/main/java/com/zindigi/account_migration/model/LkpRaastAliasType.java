package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_RAAST_ALIAS_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_RAAST_ALIAS_TYPE")
@NamedQuery(name="LkpRaastAliasType.findAll", query="SELECT l FROM LkpRaastAliasType l")
public class LkpRaastAliasType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_RAAST_ALIAS_TYPE_RAASTALIASTYPEID_GENERATOR", sequenceName="LKP_RAAST_ALIAS_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_RAAST_ALIAS_TYPE_RAASTALIASTYPEID_GENERATOR")
	@Column(name="RAAST_ALIAS_TYPE_ID")
	private long raastAliasTypeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="RAAST_ALIAS_TYPE_CODE")
	private String raastAliasTypeCode;

	@Column(name="RAAST_ALIAS_TYPE_DESCR")
	private String raastAliasTypeDescr;

	@Column(name="RAAST_ALIAS_TYPE_NAME")
	private String raastAliasTypeName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblRaastRequest
	@JsonIgnore
	@OneToMany(mappedBy="lkpRaastAliasType")
	private List<TblRaastRequest> tblRaastRequests;

	public LkpRaastAliasType() {
	}

	public long getRaastAliasTypeId() {
		return this.raastAliasTypeId;
	}

	public void setRaastAliasTypeId(long raastAliasTypeId) {
		this.raastAliasTypeId = raastAliasTypeId;
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

	public String getRaastAliasTypeCode() {
		return this.raastAliasTypeCode;
	}

	public void setRaastAliasTypeCode(String raastAliasTypeCode) {
		this.raastAliasTypeCode = raastAliasTypeCode;
	}

	public String getRaastAliasTypeDescr() {
		return this.raastAliasTypeDescr;
	}

	public void setRaastAliasTypeDescr(String raastAliasTypeDescr) {
		this.raastAliasTypeDescr = raastAliasTypeDescr;
	}

	public String getRaastAliasTypeName() {
		return this.raastAliasTypeName;
	}

	public void setRaastAliasTypeName(String raastAliasTypeName) {
		this.raastAliasTypeName = raastAliasTypeName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblRaastRequest> getTblRaastRequests() {
		return this.tblRaastRequests;
	}

	public void setTblRaastRequests(List<TblRaastRequest> tblRaastRequests) {
		this.tblRaastRequests = tblRaastRequests;
	}

	public TblRaastRequest addTblRaastRequest(TblRaastRequest tblRaastRequest) {
		getTblRaastRequests().add(tblRaastRequest);
		tblRaastRequest.setLkpRaastAliasType(this);

		return tblRaastRequest;
	}

	public TblRaastRequest removeTblRaastRequest(TblRaastRequest tblRaastRequest) {
		getTblRaastRequests().remove(tblRaastRequest);
		tblRaastRequest.setLkpRaastAliasType(null);

		return tblRaastRequest;
	}

}