package com.zindigi.account_migration.dto;

public class UpdateAccountLevelRequest {
    private String mobileNumber;
    private String cnic;
    private String accountLevelName;
    private String accountClassificationName;
    private String businessName;
    private String businessTypeCode;
    private String businessAddress;
    private String monthlySaleExpectedCode;
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getMonthlySaleExpectedCode() {
        return monthlySaleExpectedCode;
    }

    public void setMonthlySaleExpectedCode(String monthlySaleExpectedCode) {
        this.monthlySaleExpectedCode = monthlySaleExpectedCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

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
}
