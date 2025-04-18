package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LKP_COST_CENTER")
@NamedQuery(name="LkpCostCenter.findAll", query="SELECT l FROM LkpCostCenter l")
public class LkpCostCenter {

    @Id
    @SequenceGenerator(name="LKP_COST_CENTER_SEQ_GENERATOR", sequenceName="LKP_COST_CENTER_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_COST_CENTER_SEQ_GENERATOR")
    @Column(name = "COST_CENTER_ID")
    private Long costCenterId;

    @Column(name = "COST_CENTER_CODE")
    private String costCenterCode;

    @Column(name = "COST_CENTER_DESCR")
    private String costCenterDescr;

    @Column(name = "COST_CENTER_NAME")
    private String costCenterName;

    @Column(name = "COA_CODE")
    private String coaCode;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "CREATEUSER")
    private BigDecimal createuser;


    private Date createdate;

    private BigDecimal lastupdateuser;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

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


    public Long getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(Long costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getCostCenterDescr() {
        return costCenterDescr;
    }

    public void setCostCenterDescr(String costCenterDescr) {
        this.costCenterDescr = costCenterDescr;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public BigDecimal getCreateuser() {
        return createuser;
    }

    public void setCreateuser(BigDecimal createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }


    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
