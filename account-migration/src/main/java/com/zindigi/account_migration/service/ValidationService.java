package com.zindigi.account_migration.service;

import com.mfs.commonservice.dto.AdditionalInformation;
import com.mfs.commonservice.dto.Error;
import com.mfs.commonservice.dto.Request;
import com.zindigi.account_migration.dto.*;

import java.util.List;

public interface ValidationService {

    List<Error> validateCheckaccountstatus(CheckAccountStatusRequest checkAccountStatusRequest, List<AdditionalInformation> additionalInformationList);


    List<Error> validateCheckBlacklistingRequest(CheckBlacklistingRequest checkBlacklistingRequest, List<AdditionalInformation> additionalInformationList);


    List<Error> getKycAttributesByAccountType(SaveCustomerRequest saveCustomerRequest, List<AdditionalInformation> additionalInformationList);

    List<Error> validateBasicUpdateAccountRequest(UpdateAccountRequest updateAccountRequest, Request jsonRequest, List<AdditionalInformation> additionalInformation);
    List<Error> validateUpdateAccountRequestL1(UpdateAccountRequest updateAccountRequest, List<AdditionalInformation> additionalInformationList);

    List<Error> validateUpdateAccountLevelRequest(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList);

    List<Error> validateUltraStep1(UpdateAccountRequest updateAccountRequest, List<AdditionalInformation> additionalInformationList);

}
