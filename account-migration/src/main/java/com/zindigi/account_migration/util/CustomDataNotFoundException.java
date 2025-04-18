/*Author Name:muhammad.anas
Project Name: zconnect_backoffice
Package Name:com.mfs.zconnect_backoffice.utils
Class Name: CustomDataNotFoundException
Date and Time:11/11/2023 11:10 AM
Version:1.0*/
package com.zindigi.account_migration.util;

public class CustomDataNotFoundException extends Exception {
    private String errorMessage;

    public CustomDataNotFoundException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
