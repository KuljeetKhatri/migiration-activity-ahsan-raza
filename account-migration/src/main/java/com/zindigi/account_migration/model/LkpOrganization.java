package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LKP_ORGANIZATION")
public class LkpOrganization {
    private Long organizationId;
    private String organizationCode;
    private String organizationDescr;
    private String isActive;
    private BigDecimal createuser;
    private Date createdate;
    private BigDecimal lastupdateuser;
    private Date lastupdatedate;
    private BigDecimal updateindex;
    private String organizationName;
    private String coaCode;



    private LkpStatus lkpStatus;

    @Id
    @SequenceGenerator(name="LKP_ORGANIZATION_SEQ_GENERATOR", sequenceName="LKP_ORGANIZATION_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ORGANIZATION_SEQ_GENERATOR")
    @Column(name = "ORGANIZATION_ID")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }


    @Column(name = "ORGANIZATION_CODE")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }


    @Column(name = "ORGANIZATION_DESCR")
    public String getOrganizationDescr() {
        return organizationDescr;
    }

    public void setOrganizationDescr(String organizationDescr) {
        this.organizationDescr = organizationDescr;
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


    @Column(name = "ORGANIZATION_NAME")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }


    @Column(name = "COA_CODE")
    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
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
