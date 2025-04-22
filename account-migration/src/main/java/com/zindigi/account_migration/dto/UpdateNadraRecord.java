package com.zindigi.account_migration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateNadraRecord {
    private String type;
    private String mobileNumber;
    private String cnic;
    private String email;
    private String cnicIssuenceDate;
    private String templateType;
    private String areaName;
    private List<CustomerFpData> customerFpData;
}
