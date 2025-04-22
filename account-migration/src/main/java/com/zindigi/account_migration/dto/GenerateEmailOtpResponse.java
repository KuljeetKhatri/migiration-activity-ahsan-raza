package com.zindigi.account_migration.dto;

import java.util.Date;
public class GenerateEmailOtpResponse {
    private long otpId;

    private Date createdate;

    private Long createuser;

    private Date effectiveFrom;

    private Date effectiveTo;

    private String email;

    private Long otpTries;

    private String otpin;

    private String verificationReason;

    private String verified;

    public long getOtpId() {
        return otpId;
    }

    public void setOtpId(long otpId) {
        this.otpId = otpId;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Date getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(Date effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOtpTries() {
        return otpTries;
    }

    public void setOtpTries(Long otpTries) {
        this.otpTries = otpTries;
    }

    public String getOtpin() {
        return otpin;
    }

    public void setOtpin(String otpin) {
        this.otpin = otpin;
    }

    public String getVerificationReason() {
        return verificationReason;
    }

    public void setVerificationReason(String verificationReason) {
        this.verificationReason = verificationReason;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
}
