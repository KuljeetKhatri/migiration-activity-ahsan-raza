package com.zindigi.account_migration.model;

import javax.persistence.*;import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LKP_MRP_CODE")
public class LkpMrpCode {
    private long mrpCodeId;
    private String mrpCodeCode;
    private String mrpCodeDescr;
    private String mrpCodeName;
    private String coaCode;
    private String isActive;
    private BigDecimal createuser;
    private Date createdate;
    private BigDecimal lastupdateuser;
    private Date lastupdatedate;
    private BigDecimal updateindex;
    private LkpStatus lkpStatus;

    @Id
    @SequenceGenerator(name="LKP_MRP_CODE_SEQ_GENERATOR", sequenceName="LKP_MRP_CODE_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_MRP_CODE_SEQ_GENERATOR")
    @Column(name = "MRP_CODE_ID")
    public Long getMrpCodeId() {
        return mrpCodeId;
    }

    public void setMrpCodeId(Long mrpCodeId) {
        this.mrpCodeId = mrpCodeId;
    }


    @Column(name = "MRP_CODE_CODE")
    public String getMrpCodeCode() {
        return mrpCodeCode;
    }

    public void setMrpCodeCode(String mrpCodeCode) {
        this.mrpCodeCode = mrpCodeCode;
    }


    @Column(name = "MRP_CODE_DESCR")
    public String getMrpCodeDescr() {
        return mrpCodeDescr;
    }

    public void setMrpCodeDescr(String mrpCodeDescr) {
        this.mrpCodeDescr = mrpCodeDescr;
    }


    @Column(name = "MRP_CODE_NAME")
    public String getMrpCodeName() {
        return mrpCodeName;
    }

    public void setMrpCodeName(String mrpCodeName) {
        this.mrpCodeName = mrpCodeName;
    }


    @Column(name = "COA_CODE")
    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }


    @Column(name = "IS_ACTIVE")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }


    @Column(name = "CREATEUSER")
    public BigDecimal getCreateuser() {
        return createuser;
    }

    public void setCreateuser(BigDecimal createuser) {
        this.createuser = createuser;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "CREATEDATE")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }


    @Column(name = "LASTUPDATEUSER")
    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }


    @Column(name = "LASTUPDATEDATE")
    @Temporal(TemporalType.DATE)
    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }


    @Column(name = "UPDATEINDEX")
    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    @ManyToOne
    @JoinColumn(name="STATUS_ID")
    public LkpStatus getLkpStatus() {
        return lkpStatus;
    }
    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }
}
