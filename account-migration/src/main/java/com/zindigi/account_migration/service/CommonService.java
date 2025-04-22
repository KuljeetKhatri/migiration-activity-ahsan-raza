package com.zindigi.account_migration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.commonservice.dto.*;
import com.zindigi.account_migration.dto.GetKycResponse;
import com.zindigi.account_migration.dto.RiskProfileScoreAndRatingRequest;
import com.zindigi.account_migration.dto.UpdateAccountLevelRequest;
import com.zindigi.account_migration.model.TblCustomer;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

public interface CommonService {

    void handleResponse(Object object, Response response, String successCode);
    boolean updateScreenStep(String mobileNumber, String accountTypeName, String screenStep);
    void calculateRiskScoreAndRating(String mobileNumber, String accountLevelCode, Request request, String token) throws JsonProcessingException;
    RiskProfileScoreAndRatingRequest createRiskProfileRequest(TblCustomer tblCustomer, String accountLevelName, String mobileNumber) throws NoSuchAlgorithmException;
    CheckCustomerAccountStatusResponse checkCustomerAccountStatus(CheckCustomerAccountStatusRequest customerAccountStatusRequest, Request jsonRequest, HttpServletRequest request) throws JsonProcessingException, NoSuchAlgorithmException;
    GetKycResponse getKyc(GetKycRequest getKycRequest, String headerValue) throws NoSuchAlgorithmException;
    GetKycResponse getUserDecryptedDataByMobileNumber(String mobileNumber,String cnic,  Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException, NoSuchAlgorithmException;
    HashMap<String, Object> updateaccountlevel(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, BigDecimal loggedUserDetail, String header, Request jsonRequest) throws Exception;
    void checkAccountIsTrusted(String mobileNumber, String accountLevelName, Request request, String httpServletRequest) throws JsonProcessingException, NoSuchAlgorithmException;

}
