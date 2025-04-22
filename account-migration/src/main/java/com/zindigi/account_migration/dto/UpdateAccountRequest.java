package com.zindigi.account_migration.dto;

import com.mfs.commonservice.dto.DeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAccountRequest {
    private String cnic;
    private String mobileNumber;
    private String templateType;
    private String areaName;
    private List<CustomerFpData> customerFpData;
    private String accountLevelCode;
    // params for ultra
    private String accountNumber;
    private String accountLevelName;
    private DeviceInfo deviceInfo;
    private String segmentName;
    private String email;
    private String emailOtpId;
    private String emailOtpPin;
    private String countryOfBirth;
    private String countryOfTaxResidence;
    private String dualNationality;
    private String usBorn;
    private String usTeleNumber;
    private String usSignAuth;
    private String usOtherInfoIndicator;
    private String fedralTaxClassificationName;
    private String address;
    private String taxIdentificationNumber;
    private String documentUploaded;
    private String accountClassificationName;
    private String ultraUsage;
    private String monthlySpending;
    private String profession;
    private String chequeBook;
    private List<VerifyQuestions> questions;
    private String fatherName;
    private String step;
    private String message;
    private String country;
    private String greenCardStatus;
    private String areaCode;
    private String cityCode;
    private String houseNo;
    private String StreetNo;
    private String utilityBillUploaded;
    private String currencyCode;
    private String declaration;
    private String isTandCAccepted;
    private String selfDeclarationAccepted;
    private String businessName;
    private String businessTypeCode;
    private String businessAddress;
    private String monthlySaleExpectedCode;


}
