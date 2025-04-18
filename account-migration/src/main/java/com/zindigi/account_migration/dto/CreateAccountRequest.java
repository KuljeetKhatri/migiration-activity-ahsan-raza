package com.zindigi.account_migration.dto;

import java.util.List;

public class CreateAccountRequest {
    private String cnic;
    private String mobileNumber;
    private String cnicIssuanceDate;
    private String email;
    private String fullName;
    private String segmentName;
    private String currencyCode;
    private String channelName;
    private String presentAddress;
    private String cnicExpiry;
    private String birthPlace;
    private String motherMaiden;
    private String trackingId;
    private String pin;
    private String confirmMpin;
    private String step;
    private String updateStatusComments;
    private DeviceInfo deviceInfo;
    private String name;
    private String fatherName;
    private String dob;
    private String accountNo;
    private String accountPurposeName;
    private String occupationName;
    private String sourceOfIncomeName;
    private List<OriginatorInfo> originatorInfo;
    private String nokName;
    private String nokCnic;
    private String nokMobileNo;
    private String nokRelationshipName;
    private String accounttype;
    private String message;
    private String parentMobileNumber;
    private String raastLinking;
    private String parentCnic;
    private String tAndCAccepted;
    private String accountLevelName;
    private String accountClassificationName;
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

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getCnicExpiry() {
        return cnicExpiry;
    }

    public void setCnicExpiry(String cnicExpiry) {
        this.cnicExpiry = cnicExpiry;
    }

    public String getMotherMaiden() {
        return motherMaiden;
    }

    public void setMotherMaiden(String motherMaiden) {
        this.motherMaiden = motherMaiden;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
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

    public String gettAndCAccepted() {
        return tAndCAccepted;
    }

    public void settAndCAccepted(String tAndCAccepted) {
        this.tAndCAccepted = tAndCAccepted;
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

    public String getCnicIssuanceDate() {
        return cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(String cnicIssuanceDate) {
        this.cnicIssuanceDate = cnicIssuanceDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getOtpPin() {
//        return otpPin;
//    }

//    public void setOtpPin(String otpPin) {
//        this.otpPin = otpPin;
//    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

//    public String getVerifyOtpPinId() {
//        return verifyOtpPinId;
//    }
//
//    public void setVerifyOtpPinId(String verifyOtpPinId) {
//        this.verifyOtpPinId = verifyOtpPinId;
//    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConfirmMpin() {
        return confirmMpin;
    }

    public void setConfirmMpin(String confirmMpin) {
        this.confirmMpin = confirmMpin;
    }


    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getUpdateStatusComments() {
        return updateStatusComments;
    }

    public void setUpdateStatusComments(String updateStatusComments) {
        this.updateStatusComments = updateStatusComments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAccountPurposeName() {
        return accountPurposeName;
    }

    public void setAccountPurposeName(String accountPurposeName) {
        this.accountPurposeName = accountPurposeName;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getSourceOfIncomeName() {
        return sourceOfIncomeName;
    }

    public void setSourceOfIncomeName(String sourceOfIncomeName) {
        this.sourceOfIncomeName = sourceOfIncomeName;
    }

    public List<OriginatorInfo> getOriginatorInfo() {
        return originatorInfo;
    }

    public void setOriginatorInfo(List<OriginatorInfo> originatorInfo) {
        this.originatorInfo = originatorInfo;
    }

    public String getNokName() {
        return nokName;
    }

    public void setNokName(String nokName) {
        this.nokName = nokName;
    }

    public String getNokCnic() {
        return nokCnic;
    }

    public void setNokCnic(String nokCnic) {
        this.nokCnic = nokCnic;
    }

    public String getNokMobileNo() {
        return nokMobileNo;
    }

    public void setNokMobileNo(String nokMobileNo) {
        this.nokMobileNo = nokMobileNo;
    }

    public String getNokRelationshipName() {
        return nokRelationshipName;
    }

    public void setNokRelationshipName(String nokRelationshipName) {
        this.nokRelationshipName = nokRelationshipName;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRaastLinking() {
        return raastLinking;
    }

    public void setRaastLinking(String raastLinking) {
        this.raastLinking = raastLinking;
    }

    public String getParentMobileNumber() {
        return parentMobileNumber;
    }

    public void setParentMobileNumber(String parentMobileNumber) {
        this.parentMobileNumber = parentMobileNumber;
    }

    public String getParentCnic() {
        return parentCnic;
    }

    public void setParentCnic(String parentCnic) {
        this.parentCnic = parentCnic;
    }
}
