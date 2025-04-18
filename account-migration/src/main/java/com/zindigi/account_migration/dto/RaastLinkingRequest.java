package com.zindigi.account_migration.dto;

public class RaastLinkingRequest {
    private String mobileNumber;
    private String accountLevelName;
    private String aliasValue;
    private boolean isdeLinking;
    private String type;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAccountLevelName() {
        return accountLevelName;
    }

    public void setAccountLevelName(String accountLevelName) {
        this.accountLevelName = accountLevelName;
    }

    public String getAliasValue() {
        return aliasValue;
    }

    public void setAliasValue(String aliasValue) {
        this.aliasValue = aliasValue;
    }

    public boolean isIsdeLinking() {
        return isdeLinking;
    }

    public void setIsdeLinking(boolean isdeLinking) {
        this.isdeLinking = isdeLinking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
