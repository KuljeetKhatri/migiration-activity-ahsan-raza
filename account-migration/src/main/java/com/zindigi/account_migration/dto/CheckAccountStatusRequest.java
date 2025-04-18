package com.zindigi.account_migration.dto;

public class CheckAccountStatusRequest {
    private String cnic;
    private String mobileNumber;
    private String accountClassificationName;
    private String accountLevelName;

    public String getAccountLevelName() {
        return accountLevelName;
    }

    public void setAccountLevelName(String accountLevelName) {
        this.accountLevelName = accountLevelName;
    }

    public String getAccountClassificationName() {
        return accountClassificationName;
    }

    public void setAccountClassificationName(String accountClassificationName) {
        this.accountClassificationName = accountClassificationName;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }







}
