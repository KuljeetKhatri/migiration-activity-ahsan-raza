package com.zindigi.account_migration.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.mfs.commonservice.dto.*;
import com.mfs.commonservice.dto.GetKycRequest;
import com.mfs.commonservice.model.LkpAccountLevel;
import com.mfs.commonservice.model.LkpAccountType;
import com.mfs.commonservice.model.LkpStatus;
import com.mfs.commonservice.repo.LkpStatusRepo;
import com.mfs.commonservice.util.AbstractApi;
import com.mfs.commonservice.util.CommonConstants;
import com.mfs.commonservice.util.CustomException;
import com.mfs.commonservice.util.ResponseCodeConstants;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.dto.KmsResponse;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.*;
import com.zindigi.account_migration.repo.app.CommonServiceApp;
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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.zindigi.account_migration.util.app.Utility.generateIban;
import static com.zindigi.account_migration.util.app.Utility.setEmpty;

@Service
@Transactional(rollbackOn =Exception.class )
public class CommonServiceImpl extends AbstractApi implements CommonService {

    @Autowired
    private KycService validatorsService;
    @Autowired
    private TblAccountRepo tblAccountRepo;
    @Autowired
    private LkpAccountTypeRepo lkpAccountTypeRepo;
    @Autowired
    private TblAccountUpgradeRepo tblAccountUpgradeRepo;
    @Autowired
    private TblCustomerRepo tblCustomerRepo;
    @Value("${risk.calculate.url}")
    private String calculateRiskUrl;
    @Autowired
    private TblUltraCustomerRepo tblUltraCustomerRepo;
    @Autowired
    private TblNadraRepo tblNadraRepo;
    @Autowired
    private LkpAccountLevelRepo lkpAccountLevelRepo;
    @Autowired
    private TblGlCodeCombinationRepo tblGlCodeCombinationRepo;
    @Autowired
    private CommonServiceApp commonServiceApp;
    private LkpStatusRepo lkpStatusRepo;

    @Value("${account.level.0}")
    private String accountLevelCodeZero;
    @Value("${account.level.1}")
    private String accountLevelCodeOne;
    @Value("${account.level.ultra.basic}")
    private String accountLevelCodeUltraBasic;
    @Value("${account.level.ultra.freelance}")
    private String accountLevelCodeUltraFreeLance;
    @Value("${account.level.ultra.signature}")
    private String accountLevelNameUltraSignature;
    @Value("${account.type.freeLance}")
    private String accountTypeFreelance;
    @Value("${account.status.act}")
    private String accountStatusActive;
    @Value("${status.approved.code}")
    private String statusCodeApproved;
    @Value("${account.level.minor.1}")
    private String accountLevelCodeMinor1;
    @Value("${account.level.merchant}")
    private String accountLevelNameMerchant;
    @Autowired
    private TblAddressRepo tblAddressRepo;
    @Value("${kms.decrption.url}")
    private String kmsUrl;
    @Value("${account.type.wallet}")
    private String accountTypeWallet;
    @Value("${account.check.istrusted.url}")
    private String isAccountTrustedUrl;
    @Value("${address.type.present}")
    private String addressTypeCode;

    public CommonServiceImpl() {
    }
    public CommonServiceImpl(LkpStatusRepo lkpStatusRepo) {
        this.lkpStatusRepo = lkpStatusRepo;
    }
    public void handleResponse(Object object, Response response, String successCode) {
        TblResponseMessage tblResponseMessage = validatorsService.findByResponseMessageDescr(successCode);
        response.setPayLoad(object);
        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
    }
    @Override
    public boolean updateScreenStep(String mobileNumber, String accountTypeName, String screenStep) {
        try {
            TblAccountModel tblAccount = tblAccountRepo.findByMobileNoHashAndAccountTypeName(mobileNumber, accountTypeName);
            tblAccount.setAppScreenStatus(screenStep);
            tblAccountRepo.saveAndFlush(tblAccount);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Async
    public void calculateRiskScoreAndRating(String mobileNumber, String accountLevelCode, Request request, String token) throws JsonProcessingException {
        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumberHash(mobileNumber);
        try {
            RiskProfileScoreAndRatingRequest riskProfileScoreAndRatingRequest = createRiskProfileRequest(tblCustomer, accountLevelCode, mobileNumber);
            RiskProfileScoreAndRatingResponse riskProfileScoreAndRatingResponse = new RiskProfileScoreAndRatingResponse();
            request.setPayLoad(riskProfileScoreAndRatingRequest);
            Response riskResponse = genericCommonService.sendRequestAndGetResponse(request, token, calculateRiskUrl);
            if (riskResponse != null && riskResponse.getPayLoad() != null) {
                riskProfileScoreAndRatingResponse = new ObjectMapper().readValue(convertObjecttoJson(riskResponse.getPayLoad()), RiskProfileScoreAndRatingResponse.class);
                tblCustomer = tblCustomerRepo.findByMobileNumberHash(mobileNumber);
                tblCustomer.setCrpRating(riskProfileScoreAndRatingResponse.getRating());
                tblCustomer.setCrpScore(riskProfileScoreAndRatingResponse.getScore());
                tblCustomer.setCrpDate(riskProfileScoreAndRatingResponse.getCrpDate());
                tblCustomer.setCrpNextDate(riskProfileScoreAndRatingResponse.getNextCrpDate());
                tblCustomer.setUpdateindex(tblCustomer.getUpdateindex() != null ? tblCustomer.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
                tblCustomer.setLastupdatedate(new Date());
                tblCustomerRepo.updateCustomerInfo(riskProfileScoreAndRatingResponse.getRating(), riskProfileScoreAndRatingResponse.getScore(), riskProfileScoreAndRatingResponse.getCrpDate(), riskProfileScoreAndRatingResponse.getNextCrpDate(), new Date(), tblCustomer.getCustomerId());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public RiskProfileScoreAndRatingRequest createRiskProfileRequest(TblCustomer tblCustomer, String accountLevelCode, String mobileNumber) throws NoSuchAlgorithmException {
        RiskProfileScoreAndRatingRequest riskProfileScoreAndRatingRequest = new RiskProfileScoreAndRatingRequest();
        if (accountLevelCode.equals(accountLevelCodeZero) || accountLevelCode.equals(accountLevelCodeOne)) {

            TblAccountModel tblAccountModel = tblAccountRepo.findByMobileNoHashAndAccountLevelCodeAndAccountTypeCode(encryptSHA256(mobileNumber), accountLevelCode, accountTypeWallet);
            riskProfileScoreAndRatingRequest.setChannel(tblAccountModel.getLkpChannel().getChannelId());
            riskProfileScoreAndRatingRequest.setCustomerType(tblAccountModel.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());
            List<TblAddress> tblAddresses = tblAddressRepo.findByMobileNumberAndAddressTypeCode(mobileNumber, addressTypeCode);
            TblAddress tblAddress = !isNullOrEmpty(tblAddresses) ? tblAddresses.get(0) : null;
            LkpProvince lkpProvince = tblAddress != null ? tblAddress.getLkpProvince() : null;
            com.mfs.commonservice.model.LkpCountry lkpCountry = lkpProvince != null ? lkpProvince.getLkpCountry() : null;
            if (lkpCountry != null) {
                riskProfileScoreAndRatingRequest.setCustomerCountryID(lkpCountry.getCountryId());
                riskProfileScoreAndRatingRequest.setCustomerMailingAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
                riskProfileScoreAndRatingRequest.setCustomerPermanentAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
            }

            riskProfileScoreAndRatingRequest.setCustomerType(tblAccountModel.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());

        }
        if (accountLevelCode.equals(accountLevelCodeUltraBasic) || accountLevelCode.equals(accountLevelCodeUltraFreeLance) || accountLevelCode.equals(accountLevelNameUltraSignature)) {

            TblAccountModel tblAccountModel = tblAccountRepo.findByMobileNoHashAndAccountLevelCodeAndAccountTypeCode(encryptSHA256(mobileNumber), accountLevelCode, accountTypeWallet);
            TblAccountUpgrade tblUltraCustomer = tblAccountUpgradeRepo.findByCustomerId(tblCustomer.getCustomerId());
            riskProfileScoreAndRatingRequest.setChannel(tblAccountModel.getLkpChannel().getChannelId());
            com.mfs.commonservice.model.LkpCountry lkpCountry = tblUltraCustomer.getLkpCountry3() != null ? tblUltraCustomer.getLkpCountry3() : null;
            if (lkpCountry != null) {
                riskProfileScoreAndRatingRequest.setCustomerCountryID(lkpCountry.getCountryId());
                riskProfileScoreAndRatingRequest.setCustomerMailingAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
                riskProfileScoreAndRatingRequest.setCustomerPermanentAddress(riskProfileScoreAndRatingRequest.getCustomerCountryID());
            }
            riskProfileScoreAndRatingRequest.setCustomerType(tblAccountModel.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationId());
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

    @Override
    public CheckCustomerAccountStatusResponse checkCustomerAccountStatus(CheckCustomerAccountStatusRequest customerAccountStatusRequest, Request jsonRequest, HttpServletRequest request) throws JsonProcessingException, NoSuchAlgorithmException {
        if (isNullOrEmpty(customerAccountStatusRequest.getAccountTypeCode())) {
            customerAccountStatusRequest.setAccountTypeCode(accountTypeWallet);
        }
        CheckCustomerAccountStatusResponse checkCustomerAccountStatusResponse = new CheckCustomerAccountStatusResponse();
        TblAccountModel tblAccountModel = tblAccountRepo.getAccountByMobileNumberOrCnicAndAccountTypeCode(customerAccountStatusRequest.getCnic() != null ? encryptSHA256(customerAccountStatusRequest.getCnic()) : null, customerAccountStatusRequest.getMobileNumber() != null ? encryptSHA256(customerAccountStatusRequest.getMobileNumber()) : null, customerAccountStatusRequest.getAccountTypeCode());
        if (tblAccountModel != null) {
            GetKycResponse getKycResponse = getUserDecryptedDataByMobileNumber(customerAccountStatusRequest.getMobileNumber(), customerAccountStatusRequest.getCnic(), jsonRequest, request);
            if (getKycResponse != null) {
                checkCustomerAccountStatusResponse.setCode(tblAccountModel.getLkpAccountStatus().getAccountStatusCode().equals(accountStatusActive) ? ResponseCodeConstants.SUCCESS : ResponseCodeConstants.ACCOUNT_NOT_ACTIVE);
                checkCustomerAccountStatusResponse.setAccountClassificationCode(tblAccountModel.getLkpAccountLevel().getLkpAccountClassification().getAccountClassificationName());
                checkCustomerAccountStatusResponse.setAccountLevelCode(tblAccountModel.getLkpAccountLevel().getAccountLevelCode());
                checkCustomerAccountStatusResponse.setAccountStatusCode(tblAccountModel.getLkpAccountStatus().getAccountStatusCode());
                checkCustomerAccountStatusResponse.setAccountTypeCode(tblAccountModel.getLkpAccountType().getAccountTypeCode());
                checkCustomerAccountStatusResponse.setCnic(getKycResponse.getCnic());
                checkCustomerAccountStatusResponse.setMobileNumber(getKycResponse.getMobileNo());
                checkCustomerAccountStatusResponse.setScreenState(tblAccountModel.getAppScreenStatus());
                checkCustomerAccountStatusResponse.setTitle(getKycResponse.getAccountTitle());
                checkCustomerAccountStatusResponse.setProvinceName(tblAddressRepo.findProvinceNameByCnic(encryptSHA256(getKycResponse.getCnic())));
                checkCustomerAccountStatusResponse.setSegmentId(tblAccountModel.getTblCustomer().getLkpSegment().getSegmentId());
                checkCustomerAccountStatusResponse.setAccountNumber(tblAccountModel.getAccountNo());
                CustomerKyc customerKyc = new CustomerKyc();
                customerKyc.setAddress(getKycResponse.getPresentAddressEn());
                customerKyc.setDob(getKycResponse.getDateOfBirth());
                customerKyc.setEmail(getKycResponse.getEmail());
                customerKyc.setName(getKycResponse.getNameEn());
                customerKyc.setGender(getKycResponse.getGender());
                customerKyc.setMotherName(getKycResponse.getMotherNameEn());
                customerKyc.setFatherHusbandName(getKycResponse.getFatherHusbandNameEn());
                customerKyc.setCnicExpiryDate(getKycResponse.getCnicExpiryDate());
                customerKyc.setCnicIssuanceDate(getKycResponse.getCnicIssuanceDate().toString());
                customerKyc.setAddress(getKycResponse.getPresentAddressEn());
                customerKyc.setCustomerId(tblAccountModel.getTblCustomer().getCustomerId());
                checkCustomerAccountStatusResponse.setAccountId(tblAccountModel.getAccountId());
                checkCustomerAccountStatusResponse.setCustomerKyc(customerKyc);

                checkCustomerAccountStatusResponse.setCode(ResponseCodeConstants.SUCCESS);
            } else {
                checkCustomerAccountStatusResponse.setCode(ResponseCodeConstants.HOST_DOWN);
            }
        } else {
            checkCustomerAccountStatusResponse.setCode(ResponseCodeConstants.NO_ACCOUNT_FOUND);
        }
        return checkCustomerAccountStatusResponse;
    }

    @Override
    public GetKycResponse getUserDecryptedDataByMobileNumber(String mobileNumber, String cnic, Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException, NoSuchAlgorithmException {
        GetKycRequest getKycRequest = new GetKycRequest();
        getKycRequest.setMobileNumber(mobileNumber);
        getKycRequest.setCnic(cnic);
        request.setPayLoad(getKycRequest);
        return getKyc(getKycRequest, httpServletRequest.getHeader(CommonConstants.AUTHORIZATION));
    }
    @Override
    public GetKycResponse getKyc(GetKycRequest getKycRequest, String headerValue) throws NoSuchAlgorithmException {
        GetKycResponse getKycResponse = null;

        TblNadra tblNadra = tblNadraRepo.fetchNadraDetailsByMobileNumberORCnic(!isNullOrEmpty(getKycRequest.getMobileNumber()) ? encryptSHA256(getKycRequest.getMobileNumber()) : Constants.EMPTY,
                !isNullOrEmpty(getKycRequest.getCnic()) ? encryptSHA256(getKycRequest.getCnic()) : Constants.EMPTY);
        if (tblNadra != null) {
            KmsRequestResponse kmsRequestResponse = new KmsRequestResponse();
            kmsRequestResponse.setCnicHash(setEmpty(tblNadra.getCnicHash()));
            kmsRequestResponse.setCnic(setEmpty(tblNadra.getCnic()));
            kmsRequestResponse.setBirthPlace(setEmpty(tblNadra.getBirthPlace()));
            kmsRequestResponse.setAccountTitle(setEmpty(tblNadra.getName()));
            kmsRequestResponse.setDateOfBirth(setEmpty(tblNadra.getDateOfBirth()));
            kmsRequestResponse.setEmail(setEmpty(tblNadra.getEmail()));
            kmsRequestResponse.setFatherHusbandNameEn(setEmpty(tblNadra.getFatherHusbandNameEn()));
            kmsRequestResponse.setFirstName("");
            kmsRequestResponse.setLastName("");
            kmsRequestResponse.setMiddleName("");
            kmsRequestResponse.setMobileNo(setEmpty(tblNadra.getMobileNo()));
            kmsRequestResponse.setMotherName(setEmpty(tblNadra.getMotherName()));
            kmsRequestResponse.setMotherNameEn(setEmpty(tblNadra.getMotherNameEn()));
            kmsRequestResponse.setNameEn(setEmpty(tblNadra.getNameEn()));
            kmsRequestResponse.setPermanentAddressEn(setEmpty(tblNadra.getPermanentAddressEn()));
            kmsRequestResponse.setPresentAddressEn(setEmpty(tblNadra.getPresentAddressEn()));
            Request request = new Request();
            request.setAdditionalInformation(new ArrayList<>());
            request.setPayLoad(kmsRequestResponse);

            Response response = genericCommonService.sendRequestAndGetResponse(request, headerValue, kmsUrl);
            if (response.getResponseCode().equals(Constants.kmsDecryptSuccessCode)) {
                kmsRequestResponse = new Gson().fromJson(new Gson().toJson(response.getPayLoad()), KmsRequestResponse.class);
                getKycResponse = new GetKycResponse();
                getKycResponse.setAccountTitle(kmsRequestResponse.getAccountTitle());
                getKycResponse.setBirthPlace(kmsRequestResponse.getBirthPlace());
                getKycResponse.setCnic(kmsRequestResponse.getCnic());
                getKycResponse.setDateOfBirth(kmsRequestResponse.getDateOfBirth());
                getKycResponse.setEmail(kmsRequestResponse.getEmail());
                getKycResponse.setFatherHusbandNameEn(kmsRequestResponse.getFatherHusbandNameEn());
                getKycResponse.setFirstName(kmsRequestResponse.getFirstName());
                getKycResponse.setLastName(kmsRequestResponse.getLastName());
                getKycResponse.setMiddleName(kmsRequestResponse.getMiddleName());
                getKycResponse.setMobileNo(kmsRequestResponse.getMobileNo());
                getKycResponse.setMotherName(kmsRequestResponse.getMotherName());
                getKycResponse.setMotherNameEn(kmsRequestResponse.getMotherNameEn());
                getKycResponse.setNameEn(kmsRequestResponse.getNameEn());
                getKycResponse.setPermanentAddressEn(kmsRequestResponse.getPermanentAddressEn());
                getKycResponse.setPresentAddressEn(kmsRequestResponse.getPresentAddressEn());
                getKycResponse.setGender(tblNadra.getGender());
                getKycResponse.setCnicExpiryDate(tblNadra.getExpiryDate());
                getKycResponse.setCnicIssuanceDate(tblNadra.getIssuanceDate());

            } else {
                getKycResponse = null;
            }


        } else {
            getKycResponse = null;
        }


        return getKycResponse;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public HashMap<String, Object> updateaccountlevel(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, BigDecimal loggedUserDetail, String header, Request jsonRequest) throws Exception {
        HashMap<String, Object> response = null;
        TblAccountModel tblAccountModel;
        LkpAccountLevel lkpAccountLevelModel = lkpAccountLevelRepo.findByAccountLevelCode(updateAccountLevelRequest.getToAccountLevelCode()).orElseThrow(() -> new CustomException(ResponseCodeConstants.ACCOUNT_LEVEL_NOT_FOUND));
        tblAccountModel = tblAccountRepo.findByMobileNoHashAndAccountLevelCodeAndAccountTypeCode(encryptSHA256(updateAccountLevelRequest.getMobileNumber()), updateAccountLevelRequest.getFromAccountLevelCode(), accountTypeWallet);
        if (tblAccountModel == null) {
            throw new CustomException(ResponseCodeConstants.ACCOUNT_NOT_EXIST);
        }
        if (lkpAccountLevelModel.getAccountLevelCode().equalsIgnoreCase(accountLevelCodeUltraFreeLance)) {
            TblAccountUpgrade tblAccountUpgrade = tblAccountUpgradeRepo.findByMobileNumberAndAccountLevelCode(encryptSHA256(updateAccountLevelRequest.getMobileNumber()), updateAccountLevelRequest.getToAccountLevelCode());
            if (tblAccountUpgrade == null) {
                throw new CustomException(ResponseCodeConstants.ACCOUNT_NOT_EXIST);
            }
            parkFreeLanceAccount(updateAccountLevelRequest.getMobileNumber(), tblAccountUpgrade, tblAccountModel, loggedUserDetail);

        } else if (lkpAccountLevelModel.getAccountLevelCode().equalsIgnoreCase(accountLevelCodeMinor1)) {
            handleMinorAccountUpgrade(updateAccountLevelRequest, additionalInformationList, loggedUserDetail, header, tblAccountModel, lkpAccountLevelModel);


        } else {
            tblAccountModel.setPreviousLevelId(BigDecimal.valueOf(tblAccountModel.getLkpAccountLevel().getAccountLevelId()));
            tblAccountModel.setLkpAccountLevel(lkpAccountLevelModel);
            tblAccountModel.setUpdateindex(tblAccountModel.getUpdateindex() != null ? tblAccountModel.getUpdateindex().add(BigDecimal.ONE) : BigDecimal.ONE);
            tblAccountModel.setLastupdateuser(loggedUserDetail);
            tblAccountModel.setLastupdatedate(new Date());
            tblAccountRepo.saveAndFlush(tblAccountModel);
        }
        response = handleAccountUpdatedToResponse(lkpAccountLevelModel.getAccountLevelCode());


        return response;

    }
    @Transactional(rollbackOn = Exception.class)
    public void parkFreeLanceAccount(String mobileNumber, TblAccountUpgrade tblAccountUpgrade, TblAccountModel tblAccountModelPre, BigDecimal loggedUserDetail) throws NoSuchAlgorithmException {
        //adding a new row in tbl_account with account type
        TblAccountModel tblAccountModel = tblAccountRepo.findByMobileNoHashAndAccountTypeCode(encryptSHA256(mobileNumber), accountTypeFreelance);
        if (tblAccountModel == null) {
            tblAccountModel = new TblAccountModel();
            tblAccountModel.setLkpChannel(tblAccountModelPre.getLkpChannel());
            tblAccountModel.setLkpRegistrationType(tblAccountModelPre.getLkpRegistrationType());
            LkpAccountType lkpAccountType = lkpAccountTypeRepo.findByAccountTypeCode(accountTypeFreelance)
                    .orElseThrow(() -> new CustomException(ResponseCodeConstants.ACCOUNT_TYPE_NOT_FOUND));

            LkpAccountLevel lkpAccountLevelModel = lkpAccountLevelRepo.findByAccountLevelCode(accountLevelCodeUltraFreeLance).orElseThrow(() -> new CustomException(ResponseCodeConstants.ACCOUNT_LEVEL_NOT_FOUND));
            tblAccountModel.setLkpAccountLevel(lkpAccountLevelModel);
            tblAccountModel.setLkpAccountStatus(tblAccountModelPre.getLkpAccountStatus());
            tblAccountModel.setLkpAccountType(lkpAccountType);
            tblAccountModel.setLkpCurrency(tblAccountUpgrade.getLkpCurrency());
            tblAccountModel.setTblCustomer(tblAccountUpgrade.getTblCustomer());
            tblAccountModel.setActualBalance(BigDecimal.valueOf(0.0));
            tblAccountModel.setAvailableBalance(BigDecimal.valueOf(0.0));
            tblAccountModel.setSecurityQuestionRetries(new BigDecimal(0));
            TblGlCodeCombination tblGlCodeCombination = tblGlCodeCombinationRepo.findTblGlCodeCombinationByAccountLevelName(lkpAccountLevelModel.getAccountLevelName());
            if (tblGlCodeCombination == null) {

                throw new CustomException(ResponseCodeConstants.GL_CODE_COMBINATION_NOT_FOUND);
            }
            tblAccountModel.setIsGlAccount(Constants.N);
            tblAccountModel.setTblGlCodeCombination(tblGlCodeCombination);
            tblAccountModel.setCreatedate(new Date());
            tblAccountModel.setAppScreenStatus(Constants.ultraDocumentUploadedStatus);
            tblAccountModel.setCreateuser(loggedUserDetail);
            tblAccountModel.setCreatedate(new Date());
            String accountNumber = commonServiceApp.getAccountNumber(tblAccountUpgrade.getLkpCurrency().getCurrencyCode(), accountTypeFreelance);
            tblAccountModel.setAccountNo(accountNumber);
            tblAccountModel.setIban(generateIban(accountNumber));
            tblAccountModel.setAccountTitle(tblAccountModelPre.getAccountTitle());
            tblAccountModel.setIsActive(Constants.N);
            LkpStatus lkpStatus = lkpStatusRepo.findByStatusCode(statusCodeApproved);
            tblAccountModel.setLkpStatus(lkpStatus != null ? lkpStatus : null);
            tblAccountRepo.saveAndFlush(tblAccountModel);
        }
    }

    private void handleMinorAccountUpgrade(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, BigDecimal loggedUserDetail, String header, TblAccountModel tblAccountModel, LkpAccountLevel lkpAccountLevel) throws NoSuchAlgorithmException, JsonProcessingException {
        tblAccountModel.setPreviousLevelId(new BigDecimal(tblAccountModel.getLkpAccountLevel().getAccountLevelId()));
        tblAccountModel.setLkpAccountLevel(lkpAccountLevel);
        tblAccountModel.setIsActive(Constants.Y);
        tblAccountModel.setUpdateindex(tblAccountModel.getUpdateindex() != null ? tblAccountModel.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
        tblAccountModel.setLevelChangeDate(new Date());
        LkpStatus lkpStatus = lkpStatusRepo.findByStatusCode(Constants.STATUSAPPROVED);
        tblAccountModel.setLkpStatus(lkpStatus);
        tblAccountModel.setLevelChangeBy(loggedUserDetail);
        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumber(encryptSHA256(updateAccountLevelRequest.getMobileNumber()));
        if (tblCustomer == null) {
            throw new CustomException(ResponseCodeConstants.CUSTOMER_NOT_FOUND);
        }
        tblCustomer.setBvs(Constants.yes);
        tblCustomer.setLastupdatedate(new Date());
        tblCustomer.setLastupdateuser(loggedUserDetail);
        TblAccountUpgrade tblAccountUpgrade = tblAccountUpgradeRepo.findByCustomerId(tblCustomer.getCustomerId());
        if (tblAccountUpgrade == null) {
            throw new CustomException(ResponseCodeConstants.NO_ACCOUNT_FOUND);
        }
        tblAccountUpgrade.setLkpAccountLevel(lkpAccountLevel);
        tblAccountUpgrade.setLastupdatedate(new Date());
        tblAccountUpgrade.setLastupdateuser(loggedUserDetail);
        if (tblCustomer.getKyc() != null && !tblCustomer.getKyc().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(tblCustomer.getKyc());
            ArrayNode additionalInformationArray = objectMapper.createArrayNode();
            for (AdditionalInformation additionalInformation : additionalInformationList) {
                additionalInformationArray.add(new Gson().toJson(additionalInformation));
            }
            ((ObjectNode) jsonData).set(lkpAccountLevel.getAccountLevelName(), additionalInformationArray);
            String updatedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);
            tblCustomer.setKyc(updatedJson);
        } else {
            HashMap<String, String> additionalFields = new HashMap<>();
            additionalFields.put(lkpAccountLevel.getAccountLevelName(), additionalInformationList != null ? new Gson().toJson(additionalInformationList) : "");
            tblCustomer.setKyc(new Gson().toJson(additionalFields));
        }
        updateAccountGl(tblAccountModel, lkpAccountLevel.getAccountLevelName());
        tblCustomerRepo.saveAndFlush(tblCustomer);
        tblAccountRepo.saveAndFlush(tblAccountModel);
        tblAccountUpgradeRepo.saveAndFlush(tblAccountUpgrade);
    }

    public void updateAccountGl(TblAccountModel tblAccountModel, String accountLevelName) {
        TblGlCodeCombination tblGlCodeCombination = tblGlCodeCombinationRepo.findTblGlCodeCombinationByAccountLevelId(tblAccountModel.getLkpAccountLevel().getAccountLevelId());
        if (tblGlCodeCombination != null) {
            updateGlBalances(tblAccountModel.getTblGlCodeCombination().getGlCodeCombinationId(), tblGlCodeCombination.getGlCodeCombinationId(), tblAccountModel.getActualBalance());
            tblAccountModel.setTblGlCodeCombination(tblGlCodeCombination);
        }

    }

    public void updateGlBalances(Long previousGlId, Long nextGlId, BigDecimal balance) {
        TblAccountModel previousGlAccount = tblAccountRepo.findGlAccountByGlCodeCombinationId(previousGlId);
        if (previousGlAccount != null) {
            TblAccountModel currentGlAccount = tblAccountRepo.findGlAccountByGlCodeCombinationId(nextGlId);
            if (currentGlAccount != null) {
                BigDecimal preBalance = previousGlAccount.getActualBalance().subtract(balance);
                previousGlAccount.setActualBalance(preBalance);
                previousGlAccount.setAvailableBalance(preBalance);
                BigDecimal nextBalance = currentGlAccount.getActualBalance().add(balance);
                currentGlAccount.setActualBalance(nextBalance);
                currentGlAccount.setAvailableBalance(nextBalance);
                tblAccountRepo.saveAndFlush(currentGlAccount);
                tblAccountRepo.saveAndFlush(previousGlAccount);
            }

        }


    }

    public HashMap<String, Object> handleAccountUpdatedToResponse(String accountLevelCode) {
        HashMap<String, Object> resp = new HashMap<>();
        String response;
        boolean isSuccess = true;
        if (accountLevelCode.equals(accountLevelCodeOne)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_LEVEL_1;
        } else if (accountLevelCode.equals(accountLevelCodeUltraBasic)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_ULTRA_BASIC;
        } else if (accountLevelCode.equals(accountLevelCodeUltraFreeLance)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_ULTRA_FREELANCE;
        } else if (accountLevelCode.equals(accountLevelNameUltraSignature)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_ULTRA_SIGNATURE;
        } else if (accountLevelCode.equals(accountLevelCodeMinor1)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_MINOR_1;
        } else if (accountLevelCode.equals(accountLevelNameMerchant)) {
            response = ResponseCodeConstants.ACCOUNT_UPDATED_TO_MERCHANT;
        } else {
            response = ResponseCodeConstants.INVALID_ACCOUNT_LEVEL;
            isSuccess = false;

        }
        resp.put(Constants.MESSAGE_KEY, response);
        resp.put(Constants.IS_SUCCESS, isSuccess);
        return resp;

    }

    @Override
    @Async
    public void checkAccountIsTrusted(String mobileNumber, String accountLevelName, Request request, String token) throws JsonProcessingException, NoSuchAlgorithmException {
        TblAccountModel tblAccount = tblAccountRepo.findByMobileNoHashAndAccountLevelCodeAndAccountTypeCode(encryptSHA256(mobileNumber), accountLevelName, accountTypeWallet);
        if (tblAccount != null) {
            TblCustomer tblCustomer = tblAccount.getTblCustomer();
            CheckIsTrustedRequest checkIsTrustedRequest = new CheckIsTrustedRequest();
            checkIsTrustedRequest.setAccountChannelId(tblAccount.getLkpChannel().getChannelId());
            checkIsTrustedRequest.setAccountLevelId(tblAccount.getLkpAccountLevel().getAccountLevelId());
            checkIsTrustedRequest.setAccountSegmentId(tblCustomer.getLkpSegment().getSegmentId());
            checkIsTrustedRequest.setAccountTitle(getAccountTitle(tblCustomer.getCnicHash(), tblCustomer.getFullName(), token, request));
            request.setPayLoad(checkIsTrustedRequest);
            Response checkAccountIsTrustedResp = genericCommonService.sendRequestAndGetResponse(request, token, isAccountTrustedUrl);
            if (checkAccountIsTrustedResp != null && handleResponseCode(checkAccountIsTrustedResp.getResponseCode()).equals(ResponseCodeConstants.SUCCESS)) {
                CheckIsTrustedResponse checkIsTrustedResponse = new ObjectMapper().readValue(convertObjecttoJson(checkAccountIsTrustedResp.getPayLoad()), CheckIsTrustedResponse.class);

                tblCustomer.setIsTrusted(checkIsTrustedResponse.getIsTrusted());
                tblCustomerRepo.saveAndFlush(tblCustomer);
            } else {

                tblCustomer.setIsTrusted(Constants.N);
                tblCustomerRepo.saveAndFlush(tblCustomer);
            }
        }

    }

    private String getAccountTitle(String cnicHash, String accountTitle, String token, Request request) {
        String title = null;
        KmsResponse kmsResponse = new KmsResponse();
        kmsResponse.setCnic(cnicHash);
        kmsResponse.setReserved1(accountTitle);
        kmsResponse.setReserved2("");
        kmsResponse.setReserved3("");
        kmsResponse.setReserved4("");
        kmsResponse.setReserved5("");
        kmsResponse.setReserved6("");
        kmsResponse.setReserved7("");
        kmsResponse.setReserved8("");
        kmsResponse.setReserved9("");
        kmsResponse.setReserved10("");

        request.setPayLoad(kmsResponse);
        Response response = genericCommonService.sendRequestAndGetResponse(request, token, kmsUrl);
        if (response != null && handleResponseCode(response.getResponseCode()).equals(ResponseCodeConstants.SUCCESS)) {
            kmsResponse = new Gson().fromJson(new Gson().toJson(response.getPayLoad()), KmsResponse.class);
            title = kmsResponse.getReserved1();
        }
        return title;
    }

}
