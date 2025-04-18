package com.zindigi.account_migration.dto;

import java.util.List;

public class UpdateAccountRequest {
    private String cnic;
    private String mobileNumber;
    private String fingerIndex;
    private String fingerTemplate;
    private String templateType;
    private String accountLevelName;
    private String accountNumber;
    // params for ultra
    private DeviceInfo deviceInfo;
    private String segmentName;
    private String email;
    private String emailOtpId;
    private String emailOtpPin;
    private String countryOfBirth;
    private String countryOfTaxResidence;
    private String dualNationality;
    private String usBorn;
    private String usTeleNumber;
    private String usSignAuth;
    private String usOtherInfoIndicator;
    private String fedralTaxClassificationName;
    private String address;
    private String taxIdentificationNumber;
    private String documentUploaded;
    private String accountClassificationName;
    private String ultraUsage;
    private String monthlySpending;
    private String profession;
    private String chequeBook;
    private List<VerifyQuestions> questions;
    private String fatherName;
    private String step;
    private String message;
    private String country;
    private String greenCardStatus;
    private String areaCode;
    private String cityCode;
    private String houseNo;
    private String StreetNo;
    private String utilityBillUploaded;
    private String currencyCode;
    private String declaration;
    private String isTandCAccepted;
    private String selfDeclarationAccepted;
    private String businessName;
    private String businessTypeCode;
    private String businessAddress;
    private String monthlySaleExpectedCode;

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

    public String getFingerIndex() {
        return fingerIndex;
    }

    public void setFingerIndex(String fingerIndex) {
        this.fingerIndex = fingerIndex;
    }

    public String getFingerTemplate() {
        return fingerTemplate;
    }

    public void setFingerTemplate(String fingerTemplate) {
        this.fingerTemplate = fingerTemplate;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getAccountLevelName() {
        return accountLevelName;
    }

    public void setAccountLevelName(String accountLevelName) {
        this.accountLevelName = accountLevelName;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailOtpId() {
        return emailOtpId;
    }

    public void setEmailOtpId(String emailOtpId) {
        this.emailOtpId = emailOtpId;
    }

    public String getEmailOtpPin() {
        return emailOtpPin;
    }

    public void setEmailOtpPin(String emailOtpPin) {
        this.emailOtpPin = emailOtpPin;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getCountryOfTaxResidence() {
        return countryOfTaxResidence;
    }

    public void setCountryOfTaxResidence(String countryOfTaxResidence) {
        this.countryOfTaxResidence = countryOfTaxResidence;
    }

    public String getDualNationality() {
        return dualNationality;
    }

    public void setDualNationality(String dualNationality) {
        this.dualNationality = dualNationality;
    }

    public String getUsBorn() {
        return usBorn;
    }

    public void setUsBorn(String usBorn) {
        this.usBorn = usBorn;
    }

    public String getUsTeleNumber() {
        return usTeleNumber;
    }

    public void setUsTeleNumber(String usTeleNumber) {
        this.usTeleNumber = usTeleNumber;
    }

    public String getUsSignAuth() {
        return usSignAuth;
    }

    public void setUsSignAuth(String usSignAuth) {
        this.usSignAuth = usSignAuth;
    }

    public String getUsOtherInfoIndicator() {
        return usOtherInfoIndicator;
    }

    public void setUsOtherInfoIndicator(String usOtherInfoIndicator) {
        this.usOtherInfoIndicator = usOtherInfoIndicator;
    }

    public String getFedralTaxClassificationName() {
        return fedralTaxClassificationName;
    }

    public void setFedralTaxClassificationName(String fedralTaxClassificationName) {
        this.fedralTaxClassificationName = fedralTaxClassificationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getDocumentUploaded() {
        return documentUploaded;
    }

    public void setDocumentUploaded(String documentUploaded) {
        this.documentUploaded = documentUploaded;
    }

    public String getAccountClassificationName() {
        return accountClassificationName;
    }

    public void setAccountClassificationName(String accountClassificationName) {
        this.accountClassificationName = accountClassificationName;
    }

    public String getUltraUsage() {
        return ultraUsage;
    }

    public void setUltraUsage(String ultraUsage) {
        this.ultraUsage = ultraUsage;
    }

    public String getMonthlySpending() {
        return monthlySpending;
    }

    public void setMonthlySpending(String monthlySpending) {
        this.monthlySpending = monthlySpending;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getChequeBook() {
        return chequeBook;
    }

    public void setChequeBook(String chequeBook) {
        this.chequeBook = chequeBook;
    }



    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGreenCardStatus() {
        return greenCardStatus;
    }

    public void setGreenCardStatus(String greenCardStatus) {
        this.greenCardStatus = greenCardStatus;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetNo() {
        return StreetNo;
    }

    public void setStreetNo(String streetNo) {
        StreetNo = streetNo;
    }

    public String getUtilityBillUploaded() {
        return utilityBillUploaded;
    }

    public void setUtilityBillUploaded(String utilityBillUploaded) {
        this.utilityBillUploaded = utilityBillUploaded;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getIsTandCAccepted() {
        return isTandCAccepted;
    }

    public void setIsTandCAccepted(String isTandCAccepted) {
        this.isTandCAccepted = isTandCAccepted;
    }

    public String getSelfDeclarationAccepted() {
        return selfDeclarationAccepted;
    }

    public void setSelfDeclarationAccepted(String selfDeclarationAccepted) {
        this.selfDeclarationAccepted = selfDeclarationAccepted;
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

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<VerifyQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<VerifyQuestions> questions) {
        this.questions = questions;
    }
}
