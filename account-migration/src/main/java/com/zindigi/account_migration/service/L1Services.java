package com.zindigi.account_migration.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.zindigi.account_migration.dto.AdditionalInformation;
import com.zindigi.account_migration.dto.Request;
import com.zindigi.account_migration.dto.UpdateAccountLevelRequest;
import com.zindigi.account_migration.model.TblAccount;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface L1Services {
    String updateaccountlevel(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, HttpServletRequest httpServletRequest, Request jsonRequest) throws Exception;

    void updateAccountGl(TblAccount tblAccount, String accountLevelName);
    void updateGlBalances(long previousGlId,long nextGlId,BigDecimal balance);



}

