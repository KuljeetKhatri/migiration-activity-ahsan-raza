package com.zindigi.account_migration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAccountLevelRequest {
    private String mobileNumber;
    private String cnic;
    private String fromAccountLevelCode;
    private String toAccountLevelCode;
    private String accountClassificationCode;

}