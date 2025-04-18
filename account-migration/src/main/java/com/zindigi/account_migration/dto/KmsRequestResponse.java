package com.zindigi.account_migration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KmsRequestResponse {
    private String cnic;
    private String nameEn;
    private String fatherHusbandNameEn;
    private String motherNameEn;
    private String presentAddressEn;
    private String permanentAddressEn;
    private String motherName;
    private String birthPlace;
    private String dateOfBirth;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNo;
    private String accountTitle;
    private String cnicHash;

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFatherHusbandNameEn() {
        return fatherHusbandNameEn;
    }

    public void setFatherHusbandNameEn(String fatherHusbandNameEn) {
        this.fatherHusbandNameEn = fatherHusbandNameEn;
    }

    public String getMotherNameEn() {
        return motherNameEn;
    }

    public void setMotherNameEn(String motherNameEn) {
        this.motherNameEn = motherNameEn;
    }

    public String getPresentAddressEn() {
        return presentAddressEn;
    }

    public void setPresentAddressEn(String presentAddressEn) {
        this.presentAddressEn = presentAddressEn;
    }

    public String getPermanentAddressEn() {
        return permanentAddressEn;
    }

    public void setPermanentAddressEn(String permanentAddressEn) {
        this.permanentAddressEn = permanentAddressEn;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getCnicHash() {
        return cnicHash;
    }

    public void setCnicHash(String cnicHash) {
        this.cnicHash = cnicHash;
    }
}
