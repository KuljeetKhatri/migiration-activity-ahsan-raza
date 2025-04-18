package com.zindigi.account_migration.dto;

public class RaastUpdateLinkingResponse {
    private String responseCode;
    private Object responseDescription;
    private String accountId;
    private String customerId;
    private String aliasId;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(Object responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }
}
