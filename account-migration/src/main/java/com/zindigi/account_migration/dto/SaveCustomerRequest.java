/*Author Name:ahmed.sufyan

Project Name: accountOpening

Package Name:com.mfs.accountOpening.dto

Class Name: SaveCustomerRequest

Date and Time:2/8/2023 3:55 PM

Version:1.0*/
package com.zindigi.account_migration.dto;

public class SaveCustomerRequest {

    private String fullName;


    private String email;

    private String mobileNumber;

    private String cnic;

    private String minorName;


    private String isActive;
    private String iban;

    private String segmentName;

    private String registrationTypeName;

    private String accountLevelName;

    private String accountClassificationName;


    private String channelName;
    private String accountNo;


    private String accountStatusName;

    private String accountLevelTypeName;

    private String currencyCode;

    private String verifyOtpPinId;

    private String fatherHusbandName;


    private String cnicIssuanceDate;

    private String dob;

    private DeviceInfo deviceInfo;

    private String tAndCAccepted;

    private String bulkAccountOpening;
    private  String appUserId;
    private String customerId;
    private String accountId;

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String gettAndCAccepted() {
        return tAndCAccepted;
    }

    public void settAndCAccepted(String tAndCAccepted) {
        this.tAndCAccepted = tAndCAccepted;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public String getAccountLevelTypeName() {
        return accountLevelTypeName;
    }

    public void setAccountLevelTypeName(String accountLevelTypeName) {
        this.accountLevelTypeName = accountLevelTypeName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getVerifyOtpPinId() {
        return verifyOtpPinId;
    }

    public void setVerifyOtpPinId(String verifyOtpPinId) {
        this.verifyOtpPinId = verifyOtpPinId;
    }

    public String getFatherHusbandName() {
        return fatherHusbandName;
    }

    public void setFatherHusbandName(String fatherHusbandName) {
        this.fatherHusbandName = fatherHusbandName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCnicIssuanceDate() {
        return cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(String cnicIssuanceDate) {
        this.cnicIssuanceDate = cnicIssuanceDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getRegistrationTypeName() {
        return registrationTypeName;
    }

    public void setRegistrationTypeName(String registrationTypeName) {
        this.registrationTypeName = registrationTypeName;
    }

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


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBulkAccountOpening() {
        return bulkAccountOpening;
    }

    public void setBulkAccountOpening(String bulkAccountOpening) {
        this.bulkAccountOpening = bulkAccountOpening;
    }
}
