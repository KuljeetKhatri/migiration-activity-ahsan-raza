/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: Account

Date and Time:3/13/2023 12:32 PM

Version:1.0
*/
package com.zindigi.account_migration.dto;

public class Account {


    private String msidn;
    private String iban;
    private String bban;
    private String pan;
    private String currency;


    public String getMsidn() {
        return msidn;
    }

    public void setMsidn(String msidn) {
        this.msidn = msidn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBban() {
        return bban;
    }

    public void setBban(String bban) {
        this.bban = bban;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
