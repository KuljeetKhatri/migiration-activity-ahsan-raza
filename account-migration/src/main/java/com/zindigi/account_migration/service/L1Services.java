package com.zindigi.account_migration.service;


import com.mfs.commonservice.dto.AdditionalInformation;
import com.mfs.commonservice.dto.Request;
import com.zindigi.account_migration.dto.UpdateAccountLevelRequest;
import com.zindigi.account_migration.dto.UpdateNadraRecord;
import com.zindigi.account_migration.model.TblAccountModel;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public interface L1Services {
  /*  String updateaccountlevel(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, HttpServletRequest httpServletRequest, Request jsonRequest) throws Exception;

    void updateAccountGl(TblAccountModel tblAccount, String accountLevelName);
    void updateGlBalances(long previousGlId,long nextGlId,BigDecimal balance);
*/
    String updateNadraRecord(UpdateNadraRecord updateNadraRecord, Request jsonRequest, String header, BigDecimal loggedUserDetail) throws NoSuchAlgorithmException, ParseException;
    String updateBvsStatusByMobileNumber(String mobileNumber, String bvsStatus, BigDecimal userId) throws NoSuchAlgorithmException;


}

