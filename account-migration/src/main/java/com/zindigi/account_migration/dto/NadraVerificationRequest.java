package com.zindigi.account_migration.dto;

import java.util.Date;

public class NadraVerificationRequest {

    private String cnic;
    private String mobileNumber;
    private Date cnicIssuanceDate;
    private String email;
    private String update;

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCnicIssuanceDate() {
        return cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(Date cnicIssuanceDate) {
        this.cnicIssuanceDate = cnicIssuanceDate;
    }
}
