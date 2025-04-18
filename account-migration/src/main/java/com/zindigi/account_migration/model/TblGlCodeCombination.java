package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_GL_CODE_COMBINATIONS database table.
 * 
 */
@Entity
@Table(name="TBL_GL_CODE_COMBINATIONS")
@NamedQuery(name="TblGlCodeCombination.findAll", query="SELECT t FROM TblGlCodeCombination t")
public class TblGlCodeCombination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_GL_CODE_COMBINATIONS_GLCODECOMBINATIONID_GENERATOR", sequenceName="TBL_GL_CODE_COMBINATIONS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_GL_CODE_COMBINATIONS_GLCODECOMBINATIONID_GENERATOR")
	@Column(name="GL_CODE_COMBINATIONS_ID")
	private long glCodeCombinationId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="GL_CODE_COMBINATION")
	private String glCodeCombination;

	@Column(name="GL_CODE_COMBINATION_NAME")
	private String glCodeCombinationName;

	@Column(name="GL_SEGMENT_CODE1")
	private String glSegmentCode1;

	@Column(name="GL_SEGMENT_CODE2")
	private String glSegmentCode2;

	@Column(name="GL_SEGMENT_CODE3")
	private String glSegmentCode3;

	@Column(name="GL_SEGMENT_CODE4")
	private String glSegmentCode4;

	@Column(name="GL_SEGMENT_CODE5")
	private String glSegmentCode5;

	@Column(name="GL_SEGMENT_CODE6")
	private String glSegmentCode6;

	@Column(name="GL_SEGMENT_CODE7")
	private String glSegmentCode7;

	@Column(name="GL_SEGMENT_DESCRIPTION1")
	private String glSegmentDescription1;

	@Column(name="GL_SEGMENT_DESCRIPTION2")
	private String glSegmentDescription2;

	@Column(name="GL_SEGMENT_DESCRIPTION3")
	private String glSegmentDescription3;

	@Column(name="GL_SEGMENT_DESCRIPTION4")
	private String glSegmentDescription4;

	@Column(name="GL_SEGMENT_DESCRIPTION5")
	private String glSegmentDescription5;

	@Column(name="GL_SEGMENT_DESCRIPTION6")
	private String glSegmentDescription6;

	@Column(name="GL_SEGMENT_DESCRIPTION7")
	private String glSegmentDescription7;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private LkpStatus lkpStatus;


	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAccount
	@JsonIgnore
	@OneToMany(mappedBy="tblGlCodeCombination")
	private List<TblAccount> tblAccounts;

	//bi-directional many-to-one association to TblGlBalance

	//bi-directional many-to-one association to LkpGlAccountType
	/*
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_ACCOUNT_TYPE_ID")
	private LkpGlAccountType lkpGlAccountType;
*/
	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID2")
	private LkpBranch tblGlSegment2;

	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID3")
	private LkpCostCenter tblGlSegment3;

	//bi-directional many-to-one association to TblGlSegment


	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID5")
	private LkpSubCategory tblGlSegment5;

	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID6")
	private LkpCurrency tblGlSegment6;


	public LkpStatus getLkpStatus() {
		return lkpStatus;
	}

	public void setLkpStatus(LkpStatus lkpStatus) {
		this.lkpStatus = lkpStatus;
	}

	public LkpBranch getTblGlSegment2() {
		return tblGlSegment2;
	}

	public void setTblGlSegment2(LkpBranch tblGlSegment2) {
		this.tblGlSegment2 = tblGlSegment2;
	}

	public LkpCostCenter getTblGlSegment3() {
		return tblGlSegment3;
	}

	public void setTblGlSegment3(LkpCostCenter tblGlSegment3) {
		this.tblGlSegment3 = tblGlSegment3;
	}



	public LkpSubCategory getTblGlSegment5() {
		return tblGlSegment5;
	}

	public void setTblGlSegment5(LkpSubCategory tblGlSegment5) {
		this.tblGlSegment5 = tblGlSegment5;
	}

	public LkpCurrency getTblGlSegment6() {
		return tblGlSegment6;
	}

	public void setTblGlSegment6(LkpCurrency tblGlSegment6) {
		this.tblGlSegment6 = tblGlSegment6;
	}

	public LkpMrpCode getTblGlSegment7() {
		return tblGlSegment7;
	}

	public void setTblGlSegment7(LkpMrpCode tblGlSegment7) {
		this.tblGlSegment7 = tblGlSegment7;
	}

	public LkpOrganization getTblGlSegment1() {
		return tblGlSegment1;
	}

	public void setTblGlSegment1(LkpOrganization tblGlSegment1) {
		this.tblGlSegment1 = tblGlSegment1;
	}

	public LkpGlType getTblGlSegment4() {
		return tblGlSegment4;
	}

	public void setTblGlSegment4(LkpGlType tblGlSegment4) {
		this.tblGlSegment4 = tblGlSegment4;
	}

	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID7")
	private LkpMrpCode tblGlSegment7;

	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID1")
	private LkpOrganization tblGlSegment1;

	//bi-directional many-to-one association to TblGlSegment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GL_SEGMENT_ID4")
	private LkpGlType tblGlSegment4;

	public TblGlCodeCombination() {
	}

	public long getGlCodeCombinationId() {
		return this.glCodeCombinationId;
	}

	public void setGlCodeCombinationId(long glCodeCombinationId) {
		this.glCodeCombinationId = glCodeCombinationId;
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

	public String getGlCodeCombination() {
		return this.glCodeCombination;
	}

	public void setGlCodeCombination(String glCodeCombination) {
		this.glCodeCombination = glCodeCombination;
	}

	public String getGlCodeCombinationName() {
		return this.glCodeCombinationName;
	}

	public void setGlCodeCombinationName(String glCodeCombinationName) {
		this.glCodeCombinationName = glCodeCombinationName;
	}

	public String getGlSegmentCode1() {
		return this.glSegmentCode1;
	}

	public void setGlSegmentCode1(String glSegmentCode1) {
		this.glSegmentCode1 = glSegmentCode1;
	}

	public String getGlSegmentCode2() {
		return this.glSegmentCode2;
	}

	public void setGlSegmentCode2(String glSegmentCode2) {
		this.glSegmentCode2 = glSegmentCode2;
	}

	public String getGlSegmentCode3() {
		return this.glSegmentCode3;
	}

	public void setGlSegmentCode3(String glSegmentCode3) {
		this.glSegmentCode3 = glSegmentCode3;
	}

	public String getGlSegmentCode4() {
		return this.glSegmentCode4;
	}

	public void setGlSegmentCode4(String glSegmentCode4) {
		this.glSegmentCode4 = glSegmentCode4;
	}

	public String getGlSegmentCode5() {
		return this.glSegmentCode5;
	}

	public void setGlSegmentCode5(String glSegmentCode5) {
		this.glSegmentCode5 = glSegmentCode5;
	}

	public String getGlSegmentCode6() {
		return this.glSegmentCode6;
	}

	public void setGlSegmentCode6(String glSegmentCode6) {
		this.glSegmentCode6 = glSegmentCode6;
	}

	public String getGlSegmentCode7() {
		return this.glSegmentCode7;
	}

	public void setGlSegmentCode7(String glSegmentCode7) {
		this.glSegmentCode7 = glSegmentCode7;
	}

	public String getGlSegmentDescription1() {
		return this.glSegmentDescription1;
	}

	public void setGlSegmentDescription1(String glSegmentDescription1) {
		this.glSegmentDescription1 = glSegmentDescription1;
	}

	public String getGlSegmentDescription2() {
		return this.glSegmentDescription2;
	}

	public void setGlSegmentDescription2(String glSegmentDescription2) {
		this.glSegmentDescription2 = glSegmentDescription2;
	}

	public String getGlSegmentDescription3() {
		return this.glSegmentDescription3;
	}

	public void setGlSegmentDescription3(String glSegmentDescription3) {
		this.glSegmentDescription3 = glSegmentDescription3;
	}

	public String getGlSegmentDescription4() {
		return this.glSegmentDescription4;
	}

	public void setGlSegmentDescription4(String glSegmentDescription4) {
		this.glSegmentDescription4 = glSegmentDescription4;
	}

	public String getGlSegmentDescription5() {
		return this.glSegmentDescription5;
	}

	public void setGlSegmentDescription5(String glSegmentDescription5) {
		this.glSegmentDescription5 = glSegmentDescription5;
	}

	public String getGlSegmentDescription6() {
		return this.glSegmentDescription6;
	}

	public void setGlSegmentDescription6(String glSegmentDescription6) {
		this.glSegmentDescription6 = glSegmentDescription6;
	}

	public String getGlSegmentDescription7() {
		return this.glSegmentDescription7;
	}

	public void setGlSegmentDescription7(String glSegmentDescription7) {
		this.glSegmentDescription7 = glSegmentDescription7;
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

	public List<TblAccount> getTblAccounts() {
		return this.tblAccounts;
	}

	public void setTblAccounts(List<TblAccount> tblAccounts) {
		this.tblAccounts = tblAccounts;
	}

	public TblAccount addTblAccount(TblAccount tblAccount) {
		getTblAccounts().add(tblAccount);
		tblAccount.setTblGlCodeCombination(this);

		return tblAccount;
	}

	public TblAccount removeTblAccount(TblAccount tblAccount) {
		getTblAccounts().remove(tblAccount);
		tblAccount.setTblGlCodeCombination(null);

		return tblAccount;
	}


}