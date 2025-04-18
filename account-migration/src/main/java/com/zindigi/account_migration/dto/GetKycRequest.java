package com.zindigi.account_migration.dto;

public class GetKycRequest {
    private String mobileNumber;
    private String accountClassificationName;
    private String accountLevelName;

    public String getAccountClassificationName() {
        return accountClassificationName;
    }

    public void setAccountClassificationName(String accountClassificationName) {
        this.accountClassificationName = accountClassificationName;
    }

    public String getAccountLevelName() {
        return accountLevelName;
    }

    public void setAccountLevelName(String accountLevelName) {
        this.accountLevelName = accountLevelName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
