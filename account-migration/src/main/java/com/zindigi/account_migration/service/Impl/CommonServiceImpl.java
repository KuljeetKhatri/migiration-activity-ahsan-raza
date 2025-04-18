package com.zindigi.account_migration.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zindigi.account_migration.controller.AbstarctApi;
import com.zindigi.account_migration.dto.Request;
import com.zindigi.account_migration.dto.Response;
import com.zindigi.account_migration.dto.RiskProfileScoreAndRatingRequest;
import com.zindigi.account_migration.dto.RiskProfileScoreAndRatingResponse;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.TblAccountRepo;
import com.zindigi.account_migration.repo.TblAddressRepo;
import com.zindigi.account_migration.repo.TblCustomerRepo;
import com.zindigi.account_migration.repo.TblUltraCustomerRepo;
import com.zindigi.account_migration.service.CommonService;
import com.zindigi.account_migration.service.KycService;
import com.zindigi.account_migration.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackOn =Exception.class )
public class CommonServiceImpl extends AbstarctApi implements CommonService {

    @Autowired
    private KycService validatorsService;
    @Autowired
    private TblAccountRepo tblAccountRepo;

    @Autowired
    private TblCustomerRepo tblCustomerRepo;
    @Value("${risk.calculate.url}")
    private String calculateRiskUrl;
    @Autowired
    private TblUltraCustomerRepo tblUltraCustomerRepo;

    @Value("${account.level.0}")
    private String accountLevelNameZero;
    @Value("${account.level.1}")
    private String accountLevelNameOne;
    @Value("${account.level.ultra.basic}")
    private String accountLevelNameUltraBasic;
    @Value("${account.level.ultra.freelance}")
    private String accountLevelNameUltraFreeLance;
    @Value("${account.level.ultra.signature}")
    private String accountLevelNameUltraSignature;
    @Autowired
    private TblAddressRepo tblAddressRepo;

    @Value("${account.type.wallet}")
    private String accountTypeWallet;

    @Value("${address.type.present}")
    private String addressTypeCode;

    public void handleResponse(Object object, Response response, String successCode) {
        TblResponseMessage tblResponseMessage = validatorsService.findByResponseMessageDescr(successCode);
        response.setPayLoad(object);
        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
    }
    @Override
    public boolean updateScreenStep(String mobileNumber, String accountTypeName, String screenStep) {
        try {
            TblAccount tblAccount = tblAccountRepo.findByMobileNoHashAndAccountTypeName(mobileNumber, accountTypeName);
            tblAccount.setAppScreenStatus(screenStep);
            tblAccountRepo.saveAndFlush(tblAccount);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Async
    public void calculateRiskScoreAndRating(String mobileNumber, String accountLevelName, Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumberHash(mobileNumber);
        try {
            RiskProfileScoreAndRatingRequest riskProfileScoreAndRatingRequest = createRiskProfileRequest(tblCustomer, accountLevelName, mobileNumber);
            RiskProfileScoreAndRatingResponse riskProfileScoreAndRatingResponse = new RiskProfileScoreAndRatingResponse();
            request.setPayLoad(riskProfileScoreAndRatingRequest);
            Response riskResponse = getResponseFromPostAPIData(createHeaderMapBackOffice(httpServletRequest.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(request), calculateRiskUrl);
            if (riskResponse != null && riskResponse.getPayLoad() != null) {
                riskProfileScoreAndRatingResponse = new ObjectMapper().readValue(convertObjecttoJson(riskResponse.getPayLoad()), RiskProfileScoreAndRatingResponse.class);
                tblCustomer = tblCustomerRepo.findByMobileNumberHash(mobileNumber);
                tblCustomer.setCrpRating(riskProfileScoreAndRatingResponse.getRating());
                tblCustomer.setCrpScore(riskProfileScoreAndRatingResponse.getScore());
                tblCustomer.setCrpDate(riskProfileScoreAndRatingResponse.getCrpDate());
                tblCustomer.setCrpNextDate(riskProfileScoreAndRatingResponse.getNextCrpDate());
                tblCustomer.setUpdateindex(tblCustomer.getUpdateindex() != null ? tblCustomer.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
                tblCustomer.setLastupdatedate(new Date());
                tblCustomerRepo.updateCustomerInfo(riskProfileScoreAndRatingResponse.getRating(),riskProfileScoreAndRatingResponse.getScore(),riskProfileScoreAndRatingResponse.getCrpDate(),riskProfileScoreAndRatingResponse.getNextCrpDate(),new Date(),tblCustomer.getCustomerId());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public RiskProfileScoreAndRatingRequest createRiskProfileRequest(TblCustomer tblCustomer, String accountLevelName, String mobileNumber) {
        RiskProfileScoreAndRatingRequest riskProfileScoreAndRatingRequest = new RiskProfileScoreAndRatingRequest();
        if (accountLevelName.equals(accountLevelNameZero) || accountLevelName.equals(accountLevelNameOne)) {
            TblAccount tblAccount = tblAccountRepo.findByMobileNoHashAndAccountLevelName(mobileNumber, accountLevelName,accountTypeWallet);
            riskProfileScoreAndRatingRequest.setChannel(tblAccount.getLkpChannel().getChannelId());
            riskProfileScoreAndRatingRequest.setCustomerType(tblAccount.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());
            List<TblAddress> tblAddresses = tblAddressRepo.findByMobileNumberAndAddressTypeCode(mobileNumber,addressTypeCode);
            TblAddress tblAddress=!isNullOrEmpty(tblAddresses)?tblAddresses.get(0):null;
            LkpProvince lkpProvince = tblAddress != null ? tblAddress.getLkpProvince() : null;
            LkpCountry lkpCountry = lkpProvince != null ? lkpProvince.getLkpCountry() : null;
            if (lkpCountry != null) {
                riskProfileScoreAndRatingRequest.setCustomerCountryID(lkpCountry.getCountryId());
                riskProfileScoreAndRatingRequest.setCustomerMailingAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
                riskProfileScoreAndRatingRequest.setCustomerPermanentAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
            }

            riskProfileScoreAndRatingRequest.setCustomerType(tblAccount.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());

        }
        if (accountLevelName.equals(accountLevelNameUltraBasic) || accountLevelName.equals(accountLevelNameUltraFreeLance) || accountLevelName.equals(accountLevelNameUltraSignature)) {
            TblAccount tblAccount = tblAccountRepo.findByMobileNoHashAndAccountLevelName(mobileNumber, accountLevelName,accountTypeWallet);
            TblUltraCustomer tblUltraCustomer = tblUltraCustomerRepo.findByCustomerId(tblCustomer.getCustomerId());
            riskProfileScoreAndRatingRequest.setChannel(tblAccount.getLkpChannel().getChannelId());
            LkpCountry lkpCountry = tblUltraCustomer.getLkpCountry3() != null ? tblUltraCustomer.getLkpCountry3() : null;
            if (lkpCountry != null) {
                riskProfileScoreAndRatingRequest.setCustomerCountryID(lkpCountry.getCountryId());
                riskProfileScoreAndRatingRequest.setCustomerMailingAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
                riskProfileScoreAndRatingRequest.setCustomerPermanentAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
            }
            riskProfileScoreAndRatingRequest.setCustomerType(tblAccount.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());
            riskProfileScoreAndRatingRequest.setCustomerMailingAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
            riskProfileScoreAndRatingRequest.setCustomerPermanentAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());

            riskProfileScoreAndRatingRequest.setCustomerOccupation(tblUltraCustomer.getLkpOccupation() != null ? tblUltraCustomer.getLkpOccupation().getOccupationId() : null);
            long c1 = tblUltraCustomer.getLkpCountry1() != null ? tblUltraCustomer.getLkpCountry1().getCountryId() : 0;
            long c2 = tblUltraCustomer.getLkpCountry2() != null ? tblUltraCustomer.getLkpCountry2().getCountryId() : 0;
            long c3 = tblUltraCustomer.getLkpCountry3() != null ? tblUltraCustomer.getLkpCountry3().getCountryId() : 0;

            long c4 = 0;
            if (c1 == c2 && c2 == c3) {
                c4 = c1;
            } else if (c1 == c2 && c2 != c3) {
                c4 = c3;
            } else if (c1 == c3 && c2 != c3) {
                c4 = c2;
            }
            riskProfileScoreAndRatingRequest.setOtherNationalities(c4);
        }
        return riskProfileScoreAndRatingRequest;
    }




}
