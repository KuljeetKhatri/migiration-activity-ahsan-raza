package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_VALIDATOR database table.
 */
@Entity
@Table(name = "TBL_VALIDATOR")
@NamedQuery(name = "TblValidator.findAll", query = "SELECT t FROM TblValidator t")
public class TblValidator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_VALIDATOR_VALIDATORID_GENERATOR", sequenceName = "TBL_VALIDATOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_VALIDATOR_VALIDATORID_GENERATOR")
    @Column(name = "VALIDATOR_ID")
    private long validatorId;

    @Column(name = "ARGUMENT_NAME")
    private String argumentName;

    @Column(name = "ARGUMENT_VALUE")
    private String argumentValue;

    private Date createdate;

    private BigDecimal createuser;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    private BigDecimal updateindex;

    @Column(name = "VALIDATOR_NAME")
    private String validatorName;


    //bi-directional many-to-one association to TblKycAttribute
    @JsonIgnore
    @OneToMany(mappedBy = "tblValidator")
    private List<TblKycAttribute> tblKycAttributes;

    //bi-directional many-to-one association to LkpStatus
    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private LkpStatus lkpStatus;

//    //bi-directional many-to-one association to LkpValidatorType
//    @ManyToOne
//    @JoinColumn(name = "VALIDATOR_TYPE_ID")
//    private LkpValidatorType lkpValidatorType;
//
//    //bi-directional many-to-one association to TblValidatorPredef
//    @OneToMany(mappedBy = "tblValidator", cascade = CascadeType.ALL)
//    private List<TblValidatorPredef> tblValidatorPredefs;

    @Transient
    private String createdBy;

    @Transient
    private String updatedBy;


	@Transient
	private long totalRecordCount;


    public TblValidator() {
    }

    public long getValidatorId() {
        return this.validatorId;
    }

    public void setValidatorId(long validatorId) {
        this.validatorId = validatorId;
    }

    public String getArgumentName() {
        return this.argumentName;
    }

    public void setArgumentName(String argumentName) {
        this.argumentName = argumentName;
    }

    public String getArgumentValue() {
        return this.argumentValue;
    }

    public void setArgumentValue(String argumentValue) {
        this.argumentValue = argumentValue;
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

    public String getValidatorName() {
        return this.validatorName;
    }

    public void setValidatorName(String validatorName) {
        this.validatorName = validatorName;
    }

    public List<TblKycAttribute> getTblKycAttributes() {
        return this.tblKycAttributes;
    }

    public void setTblKycAttributes(List<TblKycAttribute> tblKycAttributes) {
        this.tblKycAttributes = tblKycAttributes;
    }

    public TblKycAttribute addTblKycAttribute(TblKycAttribute tblKycAttribute) {
        getTblKycAttributes().add(tblKycAttribute);
        tblKycAttribute.setTblValidator(this);

        return tblKycAttribute;
    }

    public TblKycAttribute removeTblKycAttribute(TblKycAttribute tblKycAttribute) {
        getTblKycAttributes().remove(tblKycAttribute);
        tblKycAttribute.setTblValidator(null);

        return tblKycAttribute;
    }

    public LkpStatus getLkpStatus() {
        return this.lkpStatus;
    }

    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }

//    public LkpValidatorType getLkpValidatorType() {
//        return this.lkpValidatorType;
//    }
//
//    public void setLkpValidatorType(LkpValidatorType lkpValidatorType) {
//        this.lkpValidatorType = lkpValidatorType;
//    }
//
//    public List<TblValidatorPredef> getTblValidatorPredefs() {
//        return this.tblValidatorPredefs;
//    }
//
//    public void setTblValidatorPredefs(List<TblValidatorPredef> tblValidatorPredefs) {
//        this.tblValidatorPredefs = tblValidatorPredefs;
//    }
//
//    public TblValidatorPredef addTblValidatorPredef(TblValidatorPredef tblValidatorPredef) {
//        getTblValidatorPredefs().add(tblValidatorPredef);
//        tblValidatorPredef.setTblValidator(this);
//
//        return tblValidatorPredef;
//    }
//
//    public TblValidatorPredef removeTblValidatorPredef(TblValidatorPredef tblValidatorPredef) {
//        getTblValidatorPredefs().remove(tblValidatorPredef);
//        tblValidatorPredef.setTblValidator(null);
//
//        return tblValidatorPredef;
//    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

}