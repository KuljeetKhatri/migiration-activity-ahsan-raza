package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ULTRA_FATCA_QUESTION database table.
 * 
 */
@Entity
@Table(name="TBL_ULTRA_FATCA_QUESTION")
@NamedQuery(name="TblUltraFatcaQuestion.findAll", query="SELECT t FROM TblUltraFatcaQuestion t")
public class TblUltraFatcaQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ULTRA_FATCA_QUESTION_ULTRAFATCAQUESTIONID_GENERATOR", sequenceName="TBL_ULTRA_FATCA_QUESTION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ULTRA_FATCA_QUESTION_ULTRAFATCAQUESTIONID_GENERATOR")
	@Column(name="ULTRA_FATCA_QUESTION_ID")
	private long ultraFatcaQuestionId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String question;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUltraCustomerFatca
	@OneToMany(mappedBy="tblUltraFatcaQuestion")
	private List<TblUltraCustomerFatca> tblUltraCustomerFatcas;

	public TblUltraFatcaQuestion() {
	}

	public long getUltraFatcaQuestionId() {
		return this.ultraFatcaQuestionId;
	}

	public void setUltraFatcaQuestionId(long ultraFatcaQuestionId) {
		this.ultraFatcaQuestionId = ultraFatcaQuestionId;
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

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblUltraCustomerFatca> getTblUltraCustomerFatcas() {
		return this.tblUltraCustomerFatcas;
	}

	public void setTblUltraCustomerFatcas(List<TblUltraCustomerFatca> tblUltraCustomerFatcas) {
		this.tblUltraCustomerFatcas = tblUltraCustomerFatcas;
	}

	public TblUltraCustomerFatca addTblUltraCustomerFatca(TblUltraCustomerFatca tblUltraCustomerFatca) {
		getTblUltraCustomerFatcas().add(tblUltraCustomerFatca);
		tblUltraCustomerFatca.setTblUltraFatcaQuestion(this);

		return tblUltraCustomerFatca;
	}

	public TblUltraCustomerFatca removeTblUltraCustomerFatca(TblUltraCustomerFatca tblUltraCustomerFatca) {
		getTblUltraCustomerFatcas().remove(tblUltraCustomerFatca);
		tblUltraCustomerFatca.setTblUltraFatcaQuestion(null);

		return tblUltraCustomerFatca;
	}

}