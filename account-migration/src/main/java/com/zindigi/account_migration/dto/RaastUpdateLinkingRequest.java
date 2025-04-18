package com.zindigi.account_migration.dto;

public class RaastUpdateLinkingRequest {
    private String name;
    private String aliasType;
    private String aliasValue;
    private String dateOfBirth;
    private String cnicExpiryDate;
    private String cnic;
    private String  gender;
    private String iban;
    private boolean isdeLinking;
    private String type;
    private String businessName;
    private String city;
    private String stateProvinceRegion;
    private String address;
    private String country;
    private String mcc;
    private String currency;
    private String accountId;
    private String customerId;
    private String aliasId;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAliasType() {
        return aliasType;
    }

    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvinceRegion() {
        return stateProvinceRegion;
    }

    public void setStateProvinceRegion(String stateProvinceRegion) {
        this.stateProvinceRegion = stateProvinceRegion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsdeLinking() {
        return isdeLinking;
    }

    public void setIsdeLinking(boolean isdeLinking) {
        this.isdeLinking = isdeLinking;
    }


    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasValue() {
        return aliasValue;
    }

    public void setAliasValue(String aliasValue) {
        this.aliasValue = aliasValue;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCnicExpiryDate() {
        return cnicExpiryDate;
    }

    public void setCnicExpiryDate(String cnicExpiryDate) {
        this.cnicExpiryDate = cnicExpiryDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }




}
