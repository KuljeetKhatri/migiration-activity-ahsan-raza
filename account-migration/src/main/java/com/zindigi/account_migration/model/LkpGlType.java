package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_GL_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_GL_TYPE")
@NamedQuery(name="LkpGlType.findAll", query="SELECT l FROM LkpGlType l")
public class LkpGlType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_GL_TYPE_GLTYPEID_GENERATOR", sequenceName="LKP_GL_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_GL_TYPE_GLTYPEID_GENERATOR")
	@Column(name="GL_TYPE_ID")
	private Long glTypeId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="GL_TYPE_CODE")
	private String glTypeCode;

	@Column(name="GL_TYPE_DESCR")
	private String glTypeDescr;

	@Column(name="GL_TYPE_NAME")
	private String glTypeName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name = "COA_CODE")
	private String coaCode;

	public String getCoaCode() {
		return coaCode;
	}

	public void setCoaCode(String coaCode) {
		this.coaCode = coaCode;
	}

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;

	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}
	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public LkpGlType() {
	}

	public Long getGlTypeId() {
		return this.glTypeId;
	}

	public void setGlTypeId(Long glTypeId) {
		this.glTypeId = glTypeId;
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

	public String getGlTypeCode() {
		return this.glTypeCode;
	}

	public void setGlTypeCode(String glTypeCode) {
		this.glTypeCode = glTypeCode;
	}

	public String getGlTypeDescr() {
		return this.glTypeDescr;
	}

	public void setGlTypeDescr(String glTypeDescr) {
		this.glTypeDescr = glTypeDescr;
	}

	public String getGlTypeName() {
		return this.glTypeName;
	}

	public void setGlTypeName(String glTypeName) {
		this.glTypeName = glTypeName;
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