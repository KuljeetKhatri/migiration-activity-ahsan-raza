package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_SALES_STRUCTURE database table.
 * 
 */
@Entity
@Table(name="LKP_SALES_STRUCTURE")
@NamedQuery(name="LkpSalesStructure.findAll", query="SELECT l FROM LkpSalesStructure l")
public class LkpSalesStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_SALES_STRUCTURE_SALESSTRUCTUREID_GENERATOR", sequenceName="LKP_SALES_STRUCTURE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_SALES_STRUCTURE_SALESSTRUCTUREID_GENERATOR")
	@Column(name="SALES_STRUCTURE_ID")
	private long salesStructureId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SALES_STRUCTURE_CODE")
	private String salesStructureCode;

	@Column(name="SALES_STRUCTURE_DESCR")
	private String salesStructureDescr;

	@Column(name="SALES_STRUCTURE_NAME")
	private String salesStructureName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblSalesForce
	@JsonIgnore
	@OneToMany(mappedBy="lkpSalesStructure")
	private List<TblSalesForce> tblSalesForces;

	//bi-directional many-to-one association to TblSalesHierarchy
	@JsonIgnore
	@OneToMany(mappedBy="lkpSalesStructure")
	private List<TblSalesHierarchy> tblSalesHierarchies;

	//bi-directional many-to-one association to TblSalesRole
	@JsonIgnore
	@OneToMany(mappedBy="lkpSalesStructure")
	private List<TblSalesRole> tblSalesRoles;

	public LkpSalesStructure() {
	}

	public long getSalesStructureId() {
		return this.salesStructureId;
	}

	public void setSalesStructureId(long salesStructureId) {
		this.salesStructureId = salesStructureId;
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

	public String getSalesStructureCode() {
		return this.salesStructureCode;
	}

	public void setSalesStructureCode(String salesStructureCode) {
		this.salesStructureCode = salesStructureCode;
	}

	public String getSalesStructureDescr() {
		return this.salesStructureDescr;
	}

	public void setSalesStructureDescr(String salesStructureDescr) {
		this.salesStructureDescr = salesStructureDescr;
	}

	public String getSalesStructureName() {
		return this.salesStructureName;
	}

	public void setSalesStructureName(String salesStructureName) {
		this.salesStructureName = salesStructureName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblSalesForce> getTblSalesForces() {
		return this.tblSalesForces;
	}

	public void setTblSalesForces(List<TblSalesForce> tblSalesForces) {
		this.tblSalesForces = tblSalesForces;
	}

	public TblSalesForce addTblSalesForce(TblSalesForce tblSalesForce) {
		getTblSalesForces().add(tblSalesForce);
		tblSalesForce.setLkpSalesStructure(this);

		return tblSalesForce;
	}

	public TblSalesForce removeTblSalesForce(TblSalesForce tblSalesForce) {
		getTblSalesForces().remove(tblSalesForce);
		tblSalesForce.setLkpSalesStructure(null);

		return tblSalesForce;
	}

	public List<TblSalesHierarchy> getTblSalesHierarchies() {
		return this.tblSalesHierarchies;
	}

	public void setTblSalesHierarchies(List<TblSalesHierarchy> tblSalesHierarchies) {
		this.tblSalesHierarchies = tblSalesHierarchies;
	}

	public TblSalesHierarchy addTblSalesHierarchy(TblSalesHierarchy tblSalesHierarchy) {
		getTblSalesHierarchies().add(tblSalesHierarchy);
		tblSalesHierarchy.setLkpSalesStructure(this);

		return tblSalesHierarchy;
	}

	public TblSalesHierarchy removeTblSalesHierarchy(TblSalesHierarchy tblSalesHierarchy) {
		getTblSalesHierarchies().remove(tblSalesHierarchy);
		tblSalesHierarchy.setLkpSalesStructure(null);

		return tblSalesHierarchy;
	}

	public List<TblSalesRole> getTblSalesRoles() {
		return this.tblSalesRoles;
	}

	public void setTblSalesRoles(List<TblSalesRole> tblSalesRoles) {
		this.tblSalesRoles = tblSalesRoles;
	}

	public TblSalesRole addTblSalesRole(TblSalesRole tblSalesRole) {
		getTblSalesRoles().add(tblSalesRole);
		tblSalesRole.setLkpSalesStructure(this);

		return tblSalesRole;
	}

	public TblSalesRole removeTblSalesRole(TblSalesRole tblSalesRole) {
		getTblSalesRoles().remove(tblSalesRole);
		tblSalesRole.setLkpSalesStructure(null);

		return tblSalesRole;
	}

}