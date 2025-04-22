package com.zindigi.account_migration.dto;

public class CheckIsTrustedRequest {
    private Long accountLevelId;
    private Long accountSegmentId;
    private Long accountChannelId;
    private String accountTitle;

    public Long getAccountLevelId() {
        return accountLevelId;
    }

    public void setAccountLevelId(Long accountLevelId) {
        this.accountLevelId = accountLevelId;
    }

    public Long getAccountSegmentId() {
        return accountSegmentId;
    }

    public void setAccountSegmentId(Long accountSegmentId) {
        this.accountSegmentId = accountSegmentId;
    }

    public Long getAccountChannelId() {
        return accountChannelId;
    }

    public void setAccountChannelId(Long accountChannelId) {
        this.accountChannelId = accountChannelId;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }
}
