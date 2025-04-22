package com.zindigi.account_migration.dto;

public class GenerateEmailOtpRequest {
    private String otpTypeCode;
    private String email;

    public String getOtpTypeCode() {
        return otpTypeCode;
    }

    public void setOtpTypeCode(String otpTypeCode) {
        this.otpTypeCode = otpTypeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
