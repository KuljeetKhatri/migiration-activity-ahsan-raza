/*Author Name:muhammad.anas
Project Name: account
Package Name:com.mfs.account.dto
Class Name: AddressInfo
Date and Time:3/3/2024 9:42 PM
Version:1.0*/
package com.zindigi.account_migration.dto;

public class AddressInfo {
    private String addressId;
    private String fullAddress;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}