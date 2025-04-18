/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.dto

Class Name: Error

Date and Time:3/13/2023 12:33 PM

Version:1.0
*/
package com.zindigi.account_migration.dto;

public class Error {

    private String errorCode;
    private String errorDescr;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescr() {
        return errorDescr;
    }

    public void setErrorDescr(String errorDescr) {
        this.errorDescr = errorDescr;
    }
}
