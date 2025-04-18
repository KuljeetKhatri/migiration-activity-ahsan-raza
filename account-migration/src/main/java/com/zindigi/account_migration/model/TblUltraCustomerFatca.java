package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ULTRA_CUSTOMER_FATCA database table.
 * 
 */
@Entity
@Table(name="TBL_ULTRA_CUSTOMER_FATCA")
@NamedQuery(name="TblUltraCustomerFatca.findAll", query="SELECT t FROM TblUltraCustomerFatca t")
public class TblUltraCustomerFatca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ULTRA_CUSTOMER_FATCA_ULTRACUSTOMERFATCAID_GENERATOR", sequenceName="TBL_ULTRA_CUSTOMER_FATCA_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ULTRA_CUSTOMER_FATCA_ULTRACUSTOMERFATCAID_GENERATOR")
	@Column(name="ULTRA_CUSTOMER_FATCA_ID")
	private long ultraCustomerFatcaId;

	private String answer;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String question;

	//bi-directional many-to-one association to TblUltraCustomer
	@ManyToOne
	@JoinColumn(name="ULTRA_CUSTOMER_ID")
	private TblUltraCustomer tblUltraCustomer;

	//bi-directional many-to-one association to TblUltraFatcaQuestion
	@ManyToOne
	@JoinColumn(name="ULTRA_FATCA_QUESTION_ID")
	private TblUltraFatcaQuestion tblUltraFatcaQuestion;

	public TblUltraCustomerFatca() {
	}

	public long getUltraCustomerFatcaId() {
		return this.ultraCustomerFatcaId;
	}

	public void setUltraCustomerFatcaId(long ultraCustomerFatcaId) {
		this.ultraCustomerFatcaId = ultraCustomerFatcaId;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public TblUltraCustomer getTblUltraCustomer() {
		return this.tblUltraCustomer;
	}

	public void setTblUltraCustomer(TblUltraCustomer tblUltraCustomer) {
		this.tblUltraCustomer = tblUltraCustomer;
	}

	public TblUltraFatcaQuestion getTblUltraFatcaQuestion() {
		return this.tblUltraFatcaQuestion;
	}

	public void setTblUltraFatcaQuestion(TblUltraFatcaQuestion tblUltraFatcaQuestion) {
		this.tblUltraFatcaQuestion = tblUltraFatcaQuestion;
	}

}