package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "LKP_ACCOUNT_NATURE")
public class LkpAccountNature {

    @Id
    @SequenceGenerator(name="LKP_ACCOUNT_NATURE_SEQ_GENERATOR", sequenceName="LKP_ACCOUNT_NATURE_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_ACCOUNT_NATURE_SEQ_GENERATOR")

    @Column(name = "ACCOUNT_NATURE_ID")
    private BigDecimal accountNatureId;

    @Column(name = "ACCOUNT_NATURE_CODE")
    private String accountNatureCode;

    @Column(name = "ACCOUNT_NATURE_NAME")
    private String accountNatureName;

    @Column(name = "ACCOUNT_NATURE_DESCR")
    private String accountNatureDescr;

    @Column(name = "IS_ACTIVE")
    private String isActive;
    @Column(name = "CREATEUSER")
    private BigDecimal createuser;

    @Column(name = "CREATEDATE")
    private Date createdate;

    @Column(name = "LASTUPDATEUSER")
    private BigDecimal lastupdateuser;

    @Column(name = "LASTUPDATEDATE")
    private Date lastupdatedate;

    @Column(name = "UPDATEINDEX")
    private BigDecimal updateindex;

    public BigDecimal getAccountNatureId() {
        return accountNatureId;
    }

    public void setAccountNatureId(BigDecimal accountNatureId) {
        this.accountNatureId = accountNatureId;
    }

    public String getAccountNatureCode() {
        return accountNatureCode;
    }

    public void setAccountNatureCode(String accountNatureCode) {
        this.accountNatureCode = accountNatureCode;
    }

    public String getAccountNatureName() {
        return accountNatureName;
    }

    public void setAccountNatureName(String accountNatureName) {
        this.accountNatureName = accountNatureName;
    }

    public String getAccountNatureDescr() {
        return accountNatureDescr;
    }

    public void setAccountNatureDescr(String accountNatureDescr) {
        this.accountNatureDescr = accountNatureDescr;
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

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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


}
