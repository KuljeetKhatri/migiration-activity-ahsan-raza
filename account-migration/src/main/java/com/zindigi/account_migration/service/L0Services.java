package com.zindigi.account_migration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.commonservice.model.*;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.util.CustomDataNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;

public interface L0Services {

   TblNadra saveTblNardra(TblNadra tblNadra)throws JsonProcessingException;

    CheckAccountStatusResponse checkAccountStatus(CheckAccountStatusRequest checkAccountStatusRequest);

    String checkBlacklist(CheckBlacklistingRequest checkBlacklistingRequest);

    String checkAccountExistance(String cnic, String mobileNumber);

    TblNadra getTblNadraByCnic(String cnic);

    LkpSegment getLkpSegment(String segmentCode);

    LkpChannel getChannelByName(String channelCode);

    LkpRegistrationType getLkpRegistrationType(String registrationTypeCode);

    LkpAccountLevel getLkpAccountLevel(String accountLevelCode, String accountClassificationName);

    LkpAccountStatus getLkpAccountStatus(String accountStatusCode);

    LkpAccountType getLkpAccountType(String accountLevelType);

    LkpCurrency getLkpCurrency(String currencyCode);

    String saveTblUltraCustomer(CreateAccountRequest createAccountRequest, BigDecimal userId);

    String createAccount(SaveCustomerRequest saveCustomerRequest, TblCustomer tblCustomer, TblAccountModel tblAccount, TblNadra tblNadra, DeviceInfo deviceInfo, HttpServletRequest httpServletRequest) throws ParseException, CustomDataNotFoundException;

}
