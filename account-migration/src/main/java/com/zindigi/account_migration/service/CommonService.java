package com.zindigi.account_migration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zindigi.account_migration.dto.Request;
import com.zindigi.account_migration.dto.Response;
import com.zindigi.account_migration.dto.RiskProfileScoreAndRatingRequest;
import com.zindigi.account_migration.model.TblCustomer;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {

    void handleResponse(Object object, Response response, String successCode);
    boolean updateScreenStep(String mobileNumber, String accountTypeName, String screenStep);
    void calculateRiskScoreAndRating(String mobileNumber, String accountLevelName, Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException;
    RiskProfileScoreAndRatingRequest createRiskProfileRequest(TblCustomer tblCustomer, String accountLevelName, String mobileNumber);

}
