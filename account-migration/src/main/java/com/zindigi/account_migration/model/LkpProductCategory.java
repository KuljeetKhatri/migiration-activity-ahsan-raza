package com.zindigi.account_migration.model;

import javax.persistence.*;import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "LKP_PRODUCT_CATEGORY")
public class LkpProductCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRODUCT_CATEGORY_ID")
    private BigInteger productCategoryId;

    @Column(name = "PRODUCT_CATEGORY_CODE")
    private String productCategoryCode;

    @Column(name = "PRODUCT_CATEGORY_NAME")
    private String productCategoryName;

    @Column(name = "PRODUCT_CATEGORY_DESCR")
    private String productCategoryDescr;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "CREATEUSER")
    private BigInteger createuser;

    @Column(name = "CREATEDATE")
    private Date createdate;

    @Column(name = "LASTUPDATEUSER")
    private BigInteger lastupdateuser;

    @Column(name = "LASTUPDATEDATE")
    private Date lastupdatedate;

    @Column(name = "UPDATEINDEX")
    private BigInteger updateindex;

    public BigInteger getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(BigInteger productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryDescr() {
        return productCategoryDescr;
    }

    public void setProductCategoryDescr(String productCategoryDescr) {
        this.productCategoryDescr = productCategoryDescr;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public BigInteger getCreateuser() {
        return createuser;
    }

    public void setCreateuser(BigInteger createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigInteger getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigInteger lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigInteger getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigInteger updateindex) {
        this.updateindex = updateindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LkpProductCategory that = (LkpProductCategory) o;
        return Objects.equals(productCategoryId, that.productCategoryId) && Objects.equals(productCategoryCode, that.productCategoryCode) && Objects.equals(productCategoryName, that.productCategoryName) && Objects.equals(productCategoryDescr, that.productCategoryDescr) && Objects.equals(isActive, that.isActive) && Objects.equals(createuser, that.createuser) && Objects.equals(createdate, that.createdate) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(lastupdatedate, that.lastupdatedate) && Objects.equals(updateindex, that.updateindex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCategoryId, productCategoryCode, productCategoryName, productCategoryDescr, isActive, createuser, createdate, lastupdateuser, lastupdatedate, updateindex);
    }
}
