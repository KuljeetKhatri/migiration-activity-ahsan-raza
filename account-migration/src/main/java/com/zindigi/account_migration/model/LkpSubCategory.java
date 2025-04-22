package com.zindigi.account_migration.model;

import com.mfs.commonservice.model.LkpStatus;

import javax.persistence.*;import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LKP_SUB_CATEGORY")
public class LkpSubCategory {
    private Long subCategoryId;
    private String subCategoryCode;
    private String subCategoryDescr;
    private String subCategoryName;
    private String coaCode;
    private String isActive;
    private BigDecimal createuser;
    private Date createdate;
    private BigDecimal lastupdateuser;
    private Date lastupdatedate;
    private BigDecimal updateindex;
    private LkpStatus lkpStatus;





    @Id
    @SequenceGenerator(name="LKP_SUB_CATEGORY_SEQ_GENERATOR", sequenceName="LKP_SUB_CATEGORY_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_SUB_CATEGORY_SEQ_GENERATOR")
    @Column(name = "SUB_CATEGORY_ID")
    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }


    @Column(name = "SUB_CATEGORY_CODE")
    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }


    @Column(name = "SUB_CATEGORY_DESCR")
    public String getSubCategoryDescr() {
        return subCategoryDescr;
    }

    public void setSubCategoryDescr(String subCategoryDescr) {
        this.subCategoryDescr = subCategoryDescr;
    }


    @Column(name = "SUB_CATEGORY_NAME")
    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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

    @ManyToOne
    @JoinColumn(name="STATUS_ID")
    public LkpStatus getLkpStatus() {
        return lkpStatus;
    }
    public void setLkpStatus(LkpStatus lkpStatus) {
        this.lkpStatus = lkpStatus;
    }
}
