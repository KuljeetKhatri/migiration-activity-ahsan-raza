package com.zindigi.account_migration.dto;

import java.util.List;

public class ChartOfAccountsRequest {

    private List<Long> glTypeID ;
    private List<Long> costCenterID;
    private List<Long> organizationID;
    private List<Long> subCategoryID;
    private List<Long> branchID;
    private List<Long> currencyID;
    private List<Long> mrpCodeID;


    public List<Long> getGlTypeID() {
        return glTypeID;
    }

    public void setGlTypeID(List<Long> glTypeID) {
        this.glTypeID = glTypeID;
    }

    public List<Long> getCostCenterID() {
        return costCenterID;
    }

    public void setCostCenterID(List<Long> costCenterID) {
        this.costCenterID = costCenterID;
    }

    public List<Long> getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(List<Long> organizationID) {
        this.organizationID = organizationID;
    }

    public List<Long> getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(List<Long> subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public List<Long> getBranchID() {
        return branchID;
    }

    public void setBranchID(List<Long> branchID) {
        this.branchID = branchID;
    }

    public List<Long> getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(List<Long> currencyID) {
        this.currencyID = currencyID;
    }

    public List<Long> getMrpCodeID() {
        return mrpCodeID;
    }

    public void setMrpCodeID(List<Long> mrpCodeID) {
        this.mrpCodeID = mrpCodeID;
    }
}
