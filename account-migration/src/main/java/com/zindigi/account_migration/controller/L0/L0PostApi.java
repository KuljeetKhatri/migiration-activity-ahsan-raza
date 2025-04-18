package com.zindigi.account_migration.controller.L0;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zindigi.account_migration.controller.AbstarctApi;
import com.zindigi.account_migration.dto.Error;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.TblMotherNameRepo;
import com.zindigi.account_migration.repo.TblNadraRepo;
import com.zindigi.account_migration.service.*;
import com.zindigi.account_migration.util.Constants;
import com.zindigi.account_migration.util.CustomDataNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = Constants.s1, allowedHeaders = Constants.s1)
@RestController
@RestControllerAdvice
@RequestMapping(Constants.version1)
public class L0PostApi extends AbstarctApi {

    @Value("${account.level.0}")
    private String accountLevelNameZero;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private L0Services l0Services;
    @Autowired
    private ResponseService responseService;


    @Autowired
    private TblMotherNameRepo tblMotherNameRepo;
    @Autowired
    private TblNadraRepo tblNadraRepo;
    @Autowired
    private AccountDetailsService accountDetailsService;

    @Value("${moduleId}")
    private String moduleId;
    @Value("${account.level.1}")
    private String accountLevelNameOne;

    @Value("${kms.url}")
    private String kmsUrl;
    @Value("${account.level.minor.0}")
    private String accountLevelNameMinorZero;
    @Value("${account.level.ultra.basic}")
    private String accountLevelNameUltraBasic;
    @Value("${account.level.ultra.freelance}")
    private String accountLevelNameUltraFreeLance;
    @Value("${account.level.ultra.signature}")
    private String accountLevelNameUltraSignature;
    @Value("${account.level.merchant}")
    private String accountLevelNameMerchant;
    @Autowired
    private L1Services l1Services;

    @Autowired
    private CommonService commonService;

    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.createAccount, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createAccount(@RequestBody String data, HttpServletRequest request) throws
            Exception {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        TblResponseMessage tblResponseMessage;
        Request jsonRequest = null;
        jsonRequest = convertStringToRequestObjectData(data);
        logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.startingMethod, response);

        CreateAccountRequest createAccountRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), CreateAccountRequest.class);

        CheckAccountStatusRequest checkAccountStatusRequest = new CheckAccountStatusRequest();
        checkAccountStatusRequest.setCnic(createAccountRequest.getCnic());
        checkAccountStatusRequest.setAccountClassificationName(Constants.accountClassificationName);
        checkAccountStatusRequest.setMobileNumber(createAccountRequest.getMobileNumber());
        checkAccountStatusRequest.setAccountLevelName(accountLevelNameZero);
        jsonRequest.setPayLoad(checkAccountStatusRequest);
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
        ResponseEntity<Response> checkAccount = checkaccountstatus(new Gson().toJson(postParam), request);
        Response checkAccountResp = checkAccount.getBody();
        if (checkAccountResp != null && checkAccountResp.getMessage().equals(Constants.success)) {
            CheckBlacklistingRequest checkBlacklistingRequest = new CheckBlacklistingRequest();
            checkBlacklistingRequest.setCnic(createAccountRequest.getCnic());
            jsonRequest.setPayLoad(checkBlacklistingRequest);
            postParam = new HashMap<String, String>();
            postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
            ResponseEntity<Response> checkBlacklisting = checkblacklisting(new Gson().toJson(postParam), request);
            Response checkBlacklistingResponse = checkBlacklisting.getBody();
            if (checkBlacklistingResponse != null && checkBlacklistingResponse.getMessage().equals(Constants.success)) {
                TblNadra tblNadra = new TblNadra();
                tblNadra = l0Services.getTblNadraByCnic(createAccountRequest.getCnic());
                if (tblNadra == null) {
                    tblNadra = new TblNadra();
                    KmsRequestResponse kmsRequestResponse = new KmsRequestResponse();

                    kmsRequestResponse.setCnic(createAccountRequest.getCnic());
                    kmsRequestResponse.setNameEn(createAccountRequest.getFullName());
                    kmsRequestResponse.setPresentAddressEn(createAccountRequest.getPresentAddress());
                    kmsRequestResponse.setDateOfBirth(createAccountRequest.getDob());
                    kmsRequestResponse.setBirthPlace(createAccountRequest.getBirthPlace());
                    kmsRequestResponse.setMotherNameEn(createAccountRequest.getMotherMaiden());
                    kmsRequestResponse.setPermanentAddressEn("");
                    kmsRequestResponse.setEmail(createAccountRequest.getEmail());
                    TblMotherName tblMotherName = new TblMotherName();
                    tblMotherName.setIsActive(Constants.yes);
                    tblMotherName.setMotherName(createAccountRequest.getMotherMaiden());
//                tblMotherName = tblMotherNameRepo.saveAndFlush(tblMotherName);
                    kmsRequestResponse.setCnicHash("");
                    kmsRequestResponse.setEmail("aa");
                    kmsRequestResponse.setFatherHusbandNameEn("");
                    kmsRequestResponse.setPresentAddressEn(createAccountRequest.getPresentAddress());
                    kmsRequestResponse.setPermanentAddressEn(createAccountRequest.getPresentAddress());
                    kmsRequestResponse.setFirstName("");
                    kmsRequestResponse.setMiddleName("");
                    kmsRequestResponse.setLastName("");
                    kmsRequestResponse.setAccountTitle(kmsRequestResponse.getNameEn());
                    kmsRequestResponse.setMotherName("");
                    kmsRequestResponse.setMobileNo(createAccountRequest.getMobileNumber());
//                    Request request1 = new Request();
//                    request1.setSecurity(jsonRequest.getSecurity());
//                    request1.setAccount(jsonRequest.getAccount());
//                    request1.setAdditionalInformation(jsonRequest.getAdditionalInformation());
//                    request1.setChannel(jsonRequest.getChannel());
//                    request1.setCheckSum(jsonRequest.getCheckSum());
//                    request1.setTerminal(jsonRequest.getTerminal());
//                    request1.setReterivalReferenceNumber(jsonRequest.getReterivalReferenceNumber());
//                    request1.setPayLoad(kmsRequestResponse);
                    Request request1 = new Request();
                    request1.setPayLoad(kmsRequestResponse);

                    String kmsResponse = getResponseFromPostAPI(createHeaderMapBackOffice(request.getHeader("Authorization")), createPostParamBackOffice(request1), kmsUrl);
                    Response response1 = new Gson().fromJson(kmsResponse, Response.class);
                    if (response1.getResponseCode().equals(Constants.kmsSuccessCode)) {
                        kmsRequestResponse = new Gson().fromJson(new Gson().toJson(response1.getPayLoad()), KmsRequestResponse.class);
                        tblNadra.setCnic(kmsRequestResponse.getCnic());
                        tblNadra.setNameEn(kmsRequestResponse.getAccountTitle());
                        tblNadra.setPresentAddress(kmsRequestResponse.getPresentAddressEn());
                        tblNadra.setDateOfBirth(kmsRequestResponse.getDateOfBirth());
                        tblNadra.setBirthPlace(kmsRequestResponse.getBirthPlace());
                        tblNadra.setMotherNameEn(kmsRequestResponse.getMotherNameEn());
                        tblNadra.setCnicHash(kmsRequestResponse.getCnicHash());
                        Date cnicIssuenceDate = new SimpleDateFormat(Constants.cnicIssuenceDateFormat).parse(createAccountRequest.getCnicIssuanceDate());

                        tblNadra.setIssuanceDate(cnicIssuenceDate);
                        tblNadra.setCreateuser(BigDecimal.valueOf(1L));
                        tblNadra.setName(kmsRequestResponse.getAccountTitle());
                        tblNadra.setMotherName(kmsRequestResponse.getMotherName());
                        tblNadra.setFatherHusbandNameEn(kmsRequestResponse.getFatherHusbandNameEn());
                        tblNadra.setBirthPlaceEn(kmsRequestResponse.getBirthPlace());
                        tblNadra.setPermanentAddressEn(kmsRequestResponse.getPermanentAddressEn());
                        tblNadra.setPresentAddressEn(kmsRequestResponse.getPresentAddressEn());
                        tblNadra.setMobileNo(kmsRequestResponse.getMobileNo());
                        tblNadra.setEmail(kmsRequestResponse.getEmail());
                        tblNadra.setSessionId("MB" + jsonRequest.getReterivalReferenceNumber());

                        l0Services.saveTblNardra(tblNadra);
                    } else {
//                        l0Services.saveTblNardra(tblNadra);

                    }
                }
                SaveCustomerRequest saveCustomerRequest = new SaveCustomerRequest();
                saveCustomerRequest.setSegmentName(createAccountRequest.getSegmentName());
                saveCustomerRequest.setRegistrationTypeName(Constants.l0registrationType);
                saveCustomerRequest.setAccountClassificationName(Constants.accountClassificationName);
                saveCustomerRequest.setAccountLevelName(accountLevelNameZero);
                saveCustomerRequest.setMobileNumber(createAccountRequest.getMobileNumber());
                saveCustomerRequest.setEmail(createAccountRequest.getEmail());
                saveCustomerRequest.setCurrencyCode(createAccountRequest.getCurrencyCode());
                saveCustomerRequest.setCnic(createAccountRequest.getCnic());
                saveCustomerRequest.setAccountStatusName(Constants.activeWithoutPin);
                saveCustomerRequest.setAccountLevelTypeName(Constants.accountLevelTypeWallet);
                saveCustomerRequest.setChannelName(createAccountRequest.getChannelName());
                saveCustomerRequest.setCnicIssuanceDate(createAccountRequest.getCnicIssuanceDate());
                saveCustomerRequest.setFullName(createAccountRequest.getFullName());
                saveCustomerRequest.setDeviceInfo(createAccountRequest.getDeviceInfo());
                saveCustomerRequest.settAndCAccepted(createAccountRequest.gettAndCAccepted());
                saveCustomerRequest.setAccountNo(createAccountRequest.getAccountNo());
                saveCustomerRequest.setIban(jsonRequest.getAccount().getIban());
                saveCustomerRequest.setAccountId(createAccountRequest.getAccountId());
                saveCustomerRequest.setCustomerId(createAccountRequest.getCustomerId());
                saveCustomerRequest.setAppUserId(createAccountRequest.getAppUserId());
                saveCustomerRequest.setBulkAccountOpening(Constants.N);
                jsonRequest.setPayLoad(saveCustomerRequest);
                postParam = new HashMap<String, String>();
                postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
                ResponseEntity<Response> createCsutomerResp = createCustomerAndAccount(new Gson().toJson(postParam), request, createAccountRequest);
                Response createCsutomerResponse = createCsutomerResp.getBody();
                if (createCsutomerResponse.getMessage().equalsIgnoreCase(Constants.accountCreatedSucessfully)) {
                    commonService.updateScreenStep(saveCustomerRequest.getMobileNumber(), Constants.accountLevelTypeWallet, Constants.appScreenAccountCreatedStatus);
//                    commonService.calculateRiskScoreAndRating(saveCustomerRequest.getMobileNumber(), saveCustomerRequest.getAccountLevelName(), jsonRequest, request);

                }
                response = createCsutomerResponse;

                logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);

            } else {
                response = checkBlacklistingResponse;
                logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
            }
        } else {
            response = checkAccountResp;
            logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
        }
        return convertStringToResponseObject(response, response.getResponseCode());

    }

    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.checkaccountstatus, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> checkaccountstatus(@RequestBody String data, HttpServletRequest request) throws
            JsonProcessingException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObjectData(data);
        Response response = new Response();
        logs(Constants.checkaccountstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, String.valueOf(this.getClass().getPackage()), jsonRequest, Constants.startingMethod, response);
        TblResponseMessage tblResponseMessage;
        CheckAccountStatusRequest checkAccountStatusRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), CheckAccountStatusRequest.class);
        List<Error> validations = validationService.validateCheckaccountstatus(checkAccountStatusRequest, jsonRequest.getAdditionalInformation());
        if (validations != null && validations.size() == 0) {
            CheckAccountStatusResponse checkAccountStatusResponse = l0Services.checkAccountStatus(checkAccountStatusRequest);
            if (checkAccountStatusResponse != null) {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.accountAlreadyExist);
                response.setPayLoad(checkAccountStatusResponse);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.checkaccountstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
            } else {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.success);
                response.setPayLoad(checkAccountStatusResponse);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.checkaccountstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
            }
        } else {
            response.setErrors(validations);
            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
            logs(Constants.checkaccountstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
        }

        return convertStringToResponseObject(response, response.getResponseCode());
    }


    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.checkblacklisting, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> checkblacklisting(@RequestBody String data, HttpServletRequest request) throws
            JsonProcessingException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObjectData(data);
        Response response = new Response();
        logs(Constants.checkblacklisting, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.startingMethod, response);
        TblResponseMessage tblResponseMessage;


        CheckBlacklistingRequest checkBlacklistingRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), CheckBlacklistingRequest.class);
        List<Error> validations = validationService.validateCheckBlacklistingRequest(checkBlacklistingRequest, jsonRequest.getAdditionalInformation());
        if (validations != null && validations.size() == 0) {
            String exist = l0Services.checkBlacklist(checkBlacklistingRequest);
            if (exist.equals(Constants.N)) {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.success);
                response.setPayLoad(null);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.checkblacklisting, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
            } else {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.cnicBlackListed);
                response.setPayLoad(null);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.checkblacklisting, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
            }
        } else {
            response.setErrors(validations);
            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
            logs(Constants.checkblacklisting, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
        }

        return convertStringToResponseObject(response, response.getResponseCode());
    }

    @Transactional
    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.createCustomerAndAccount, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createCustomerAndAccount(@RequestBody String request, HttpServletRequest
            httpServletRequest, CreateAccountRequest createAccountRequest) throws JsonProcessingException, ParseException, CustomDataNotFoundException, NoSuchAlgorithmException {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObjectData(request);
        TblOtp tblOtp = new TblOtp();
        Response response = new Response();
        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.startingMethod, response);
        TblResponseMessage tblResponseMessage;
        SaveCustomerRequest saveCustomerRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), SaveCustomerRequest.class);
        boolean isEmailExist = accountDetailsService.isEmailExist(saveCustomerRequest.getEmail());
        if (isEmailExist) {
            commonService.handleResponse(null, response, Constants.EMAIL_ALREADY_EXISTS);
            return convertStringToResponseObject(response, response.getResponseCode());
        }

        if (saveCustomerRequest.getAccountLevelName().equalsIgnoreCase(accountLevelNameMinorZero)) {
            List<Error> validationByKycAttributes = validationService.getKycAttributesByAccountType(saveCustomerRequest, jsonRequest.getAdditionalInformation());
            if (validationByKycAttributes.size() == 0) {
                String exists = l0Services.checkAccountExistance(saveCustomerRequest.getCnic(), saveCustomerRequest.getMobileNumber());
                if (exists.equals(Constants.not)) {

                    TblNadra tblNadra = l0Services.getTblNadraByCnic(saveCustomerRequest.getCnic());
                    if (tblNadra != null) {
                        TblCustomer tblCustomer = new TblCustomer();
                        tblCustomer.setCnicHash(saveCustomerRequest.getCnic());
                        tblCustomer.setMobileNoHash(saveCustomerRequest.getMobileNumber());
                        HashMap<String, String> additionalFields = new HashMap<>();
                        additionalFields.put("MINOR", jsonRequest.getAdditionalInformation() != null ? new Gson().toJson(jsonRequest.getAdditionalInformation()) : "");
                        tblCustomer.setKyc(new Gson().toJson(additionalFields));
                        LkpSegment lkpSegment;
                        //finding Segment by Segment name
                        lkpSegment = l0Services.getLkpSegment(saveCustomerRequest.getSegmentName());
                        if (lkpSegment != null) {
                            tblCustomer.setLkpSegment(lkpSegment);
                            TblAccount tblAccount = new TblAccount();
                            tblAccount.setIsTAndCAccepted(saveCustomerRequest.gettAndCAccepted());
                            LkpChannel lkpChannel = l0Services.getChannelByName(saveCustomerRequest.getChannelName());
                            if (lkpChannel != null) {
                                //Account of TblCustomer to be set as tblAccountID
                                tblAccount.setLkpChannel(lkpChannel);
                                tblAccount.setAccountTitle(tblNadra.getName());

                                LkpRegistrationType lkpRegistrationType;
                                lkpRegistrationType = l0Services.getLkpRegistrationType(saveCustomerRequest.getRegistrationTypeName());
                                if (lkpRegistrationType != null) {
                                    tblAccount.setLkpRegistrationType(lkpRegistrationType);

                                    LkpAccountLevel lkpAccountLevel;
                                    lkpAccountLevel = l0Services.getLkpAccountLevel(saveCustomerRequest.getAccountLevelName(), saveCustomerRequest.getAccountClassificationName());
                                    if (lkpAccountLevel != null) {
                                        tblAccount.setLkpAccountLevel(lkpAccountLevel);

                                        LkpAccountStatus lkpAccountStatus;
                                        lkpAccountStatus = l0Services.getLkpAccountStatus(saveCustomerRequest.getAccountStatusName());
                                        if (lkpAccountStatus != null) {
                                            tblAccount.setLkpAccountStatus(lkpAccountStatus);
                                            LkpAccountType lkpAccountType;
                                            lkpAccountType = l0Services.getLkpAccountType(saveCustomerRequest.getAccountLevelTypeName());
                                            if (lkpAccountType != null) {
                                                tblAccount.setLkpAccountType(lkpAccountType);
                                                LkpCurrency lkpCurrency;
                                                lkpCurrency = l0Services.getLkpCurrency(saveCustomerRequest.getCurrencyCode());
                                                if (lkpCurrency != null) {
                                                    tblAccount.setLkpCurrency(lkpCurrency);

                                                    String createAccountResponse = l0Services.createAccount(saveCustomerRequest, tblCustomer, tblAccount, tblNadra, saveCustomerRequest.getDeviceInfo(), httpServletRequest);

                                                    if (createAccountResponse != null) {

                                                        // GET MESSAGES FROM DATABSE
                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(createAccountResponse);
                                                        response.setPayLoad(null);
                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);


                                                    } else {

                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.recordNotSaved);
                                                        response.setPayLoad(null);
                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                    }
                                                    //
                                                } else {
                                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidCurrencyCode);
                                                    response.setPayLoad(null);
                                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                }
                                            } else {
                                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountType);
                                                response.setPayLoad(null);
                                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                            }
                                        } else {
                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountStatus);
                                            response.setPayLoad(null);
                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                        }
                                    } else {
                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountLevel);
                                        response.setPayLoad(null);
                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                    }
                                } else {
                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidRegistrationType);
                                    response.setPayLoad(null);
                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                }
                            } else {
                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidChannelName);
                                response.setPayLoad(null);
                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                            }
                        } else {
                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidSegmentName);
                            response.setPayLoad(null);
                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                        }
                    } else {

                        TblCustomer tblCustomer = new TblCustomer();
                        tblCustomer.setCnicHash(saveCustomerRequest.getCnic());
                        tblCustomer.setMobileNoHash(saveCustomerRequest.getMobileNumber());
                        HashMap<String, String> additionalFields = new HashMap<>();
                        additionalFields.put("MINOR", jsonRequest.getAdditionalInformation() != null ? new Gson().toJson(jsonRequest.getAdditionalInformation()) : "");
                        tblCustomer.setKyc(new Gson().toJson(additionalFields));
                        LkpSegment lkpSegment;
                        //finding Segment by Segment name
                        lkpSegment = l0Services.getLkpSegment(saveCustomerRequest.getSegmentName());
                        if (lkpSegment != null) {
                            tblCustomer.setLkpSegment(lkpSegment);
                            TblAccount tblAccount = new TblAccount();
                            LkpChannel lkpChannel = l0Services.getChannelByName(saveCustomerRequest.getChannelName());
                            if (lkpChannel != null) {
                                //Account of TblCustomer to be set as tblAccountID
//                                saveCustomerRequest = minorService.encryptData(saveCustomerRequest, httpServletRequest);
                                if (saveCustomerRequest != null && saveCustomerRequest.getCnic() != null) {
                                    tblAccount.setLkpChannel(lkpChannel);

                                    tblAccount.setAccountTitle(saveCustomerRequest.getMinorName());

                                    LkpRegistrationType lkpRegistrationType;
                                    lkpRegistrationType = l0Services.getLkpRegistrationType(saveCustomerRequest.getRegistrationTypeName());
                                    if (lkpRegistrationType != null) {
                                        tblAccount.setLkpRegistrationType(lkpRegistrationType);

                                        LkpAccountLevel lkpAccountLevel;
                                        lkpAccountLevel = l0Services.getLkpAccountLevel(saveCustomerRequest.getAccountLevelName(), saveCustomerRequest.getAccountClassificationName());
                                        if (lkpAccountLevel != null) {
                                            tblAccount.setLkpAccountLevel(lkpAccountLevel);

                                            LkpAccountStatus lkpAccountStatus;
                                            lkpAccountStatus = l0Services.getLkpAccountStatus(saveCustomerRequest.getAccountStatusName());
                                            if (lkpAccountStatus != null) {
                                                tblAccount.setLkpAccountStatus(lkpAccountStatus);

                                                LkpAccountType lkpAccountType;
                                                lkpAccountType = l0Services.getLkpAccountType(saveCustomerRequest.getAccountLevelTypeName());
                                                if (lkpAccountType != null) {
                                                    tblAccount.setLkpAccountType(lkpAccountType);
                                                    LkpCurrency lkpCurrency;
                                                    lkpCurrency = l0Services.getLkpCurrency(saveCustomerRequest.getCurrencyCode());
                                                    if (lkpCurrency != null) {
                                                        tblAccount.setLkpCurrency(lkpCurrency);
////                                                        String createAccountResponse = minorService.createAccount(tblCustomer, tblAccount, saveCustomerRequest.getDeviceInfo(), , saveCustomerRequest, httpServletRequest);
//
//                                                        if (createAccountResponse != null) {
//
//                                                            // GET MESSAGES FROM DATABSE
//                                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(createAccountResponse);
//                                                            response.setPayLoad(null);
//                                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
//
//
//                                                        } else {
//
//                                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.recordNotSaved);
//                                                            response.setPayLoad(null);
//                                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
//                                                        }
                                                        //
                                                    } else {
                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidCurrencyCode);
                                                        response.setPayLoad(null);
                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                    }
                                                } else {
                                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountType);
                                                    response.setPayLoad(null);
                                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                }
                                            } else {
                                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountStatus);
                                                response.setPayLoad(null);
                                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                            }
                                        } else {
                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountLevel);
                                            response.setPayLoad(null);
                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                        }
                                    } else {
                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidRegistrationType);
                                        response.setPayLoad(null);
                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                    }
                                } else {
                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.kmsLayer);
                                    response.setPayLoad(null);
                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                }
                            } else {
                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidChannelName);
                                response.setPayLoad(null);
                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                            }
                        } else {
                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidSegmentName);
                            response.setPayLoad(null);
                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                        }
                    }

                } else {
                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.accountAlreadyExist);
                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                    response.setErrors(validationByKycAttributes);
                    logs(Constants.getscreenstate, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                }

            } else {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                response.setErrors(validationByKycAttributes);
                logs(Constants.getscreenstate, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);

            }
        } else {
            List<Error> validationByKycAttributes = validationService.getKycAttributesByAccountType(saveCustomerRequest, jsonRequest.getAdditionalInformation());
            if (validationByKycAttributes.size() == 0) {
                String exists = l0Services.checkAccountExistance(saveCustomerRequest.getCnic(), saveCustomerRequest.getMobileNumber());
                if (exists.equals(Constants.not)) {
//                    if (saveCustomerRequest.getBulkAccountOpening().equalsIgnoreCase(Constants.N)) {
////                        tblOtp = l0Services.verifyOtpPin(Long.valueOf(saveCustomerRequest.getVerifyOtpPinId()));
//                    }
                    if (saveCustomerRequest.getBulkAccountOpening().equalsIgnoreCase(Constants.N)) {
                        TblNadra tblNadra = l0Services.getTblNadraByCnic(saveCustomerRequest.getCnic());
                        if (tblNadra != null) {

                            TblCustomer tblCustomer = new TblCustomer();
                            tblCustomer.setCnicHash(saveCustomerRequest.getCnic());
                            tblCustomer.setMobileNoHash(saveCustomerRequest.getMobileNumber());
                            HashMap<String, String> additionalFields = new HashMap<>();
                            additionalFields.put(accountLevelNameZero, jsonRequest.getAdditionalInformation() != null ? new Gson().toJson(jsonRequest.getAdditionalInformation()) : "");
                            tblCustomer.setKyc(new Gson().toJson(additionalFields));
                            LkpSegment lkpSegment;
                            //finding Segment by Segment name
                            lkpSegment = l0Services.getLkpSegment(saveCustomerRequest.getSegmentName());
                            if (lkpSegment != null) {
                                tblCustomer.setLkpSegment(lkpSegment);
                                TblAccount tblAccount = new TblAccount();
                                LkpChannel lkpChannel = l0Services.getChannelByName(saveCustomerRequest.getChannelName());
                                if (lkpChannel != null) {
                                    //Account of TblCustomer to be set as tblAccountID
                                    tblAccount.setLkpChannel(lkpChannel);
                                    tblAccount.setAccountNo(tblNadra.getCnic());
                                    tblAccount.setAccountTitle(tblNadra.getName());
                                    tblAccount.setIsTAndCAccepted(saveCustomerRequest.gettAndCAccepted());
                                    LkpRegistrationType lkpRegistrationType;
                                    lkpRegistrationType = l0Services.getLkpRegistrationType(saveCustomerRequest.getRegistrationTypeName());
                                    if (lkpRegistrationType != null) {
                                        tblAccount.setLkpRegistrationType(lkpRegistrationType);

                                        LkpAccountLevel lkpAccountLevel;
                                        lkpAccountLevel = l0Services.getLkpAccountLevel(saveCustomerRequest.getAccountLevelName(), saveCustomerRequest.getAccountClassificationName());
                                        if (lkpAccountLevel != null) {
                                            tblAccount.setLkpAccountLevel(lkpAccountLevel);

                                            LkpAccountStatus lkpAccountStatus;
                                            lkpAccountStatus = l0Services.getLkpAccountStatus(saveCustomerRequest.getAccountStatusName());
                                            if (lkpAccountStatus != null) {
                                                tblAccount.setLkpAccountStatus(lkpAccountStatus);

                                                LkpAccountType lkpAccountType;
                                                lkpAccountType = l0Services.getLkpAccountType(saveCustomerRequest.getAccountLevelTypeName());
                                                if (lkpAccountType != null) {
                                                    tblAccount.setLkpAccountType(lkpAccountType);
                                                    LkpCurrency lkpCurrency;
                                                    lkpCurrency = l0Services.getLkpCurrency(saveCustomerRequest.getCurrencyCode());
//                                                    if (lkpCurrency != null) {
//                                                        tblAccount.setLkpCurrency(lkpCurrency);
                                                    String createAccountResponse = l0Services.createAccount(saveCustomerRequest, tblCustomer, tblAccount, tblNadra, saveCustomerRequest.getDeviceInfo(), httpServletRequest);

                                                    if (createAccountResponse != null) {

                                                        // GET MESSAGES FROM DATABSE
                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(createAccountResponse);
                                                        response.setPayLoad(null);
                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);

                                                    } else {

                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.recordNotSaved);
                                                        response.setPayLoad(null);
                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                    }
                                                    //
//                                                    } else {
//                                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidCurrencyCode);
//                                                        response.setPayLoad(null);
//                                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
//                                                    }
                                                } else {
                                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountType);
                                                    response.setPayLoad(null);
                                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                                }
                                            } else {
                                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountStatus);
                                                response.setPayLoad(null);
                                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                            }
                                        } else {
                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountLevel);
                                            response.setPayLoad(null);
                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                        }
                                    } else {
                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidRegistrationType);
                                        response.setPayLoad(null);
                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                    }
                                } else {
                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidChannelName);
                                    response.setPayLoad(null);
                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                                }
                            } else {
                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidSegmentName);
                                response.setPayLoad(null);
                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                            }
                        } else {
                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.nadraRecordNotFound);
                            response.setPayLoad(null);
                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                            logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                        }
                    } else {
                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.otpNotVerified);
                        response.setPayLoad(null);
                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                        logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);
                    }
                } else {
                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.accountAlreadyExist);
                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                    response.setErrors(validationByKycAttributes);
                    logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);


                }

            } else {
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                response.setErrors(validationByKycAttributes);
                logs(Constants.createCustomerAndAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getName(), jsonRequest, Constants.endingMethod, response);

            }
        }

        return convertStringToResponseObject(response, response.getResponseCode());
    }



    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.updateaccount, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateaccount(@RequestBody String data, HttpServletRequest request) throws Exception {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();

        Request jsonRequest = convertStringToRequestObjectData(data);
        Response response = new Response();
        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.startingMethod, response);
        TblResponseMessage tblResponseMessage;
            UpdateAccountRequest updateAccountRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), UpdateAccountRequest.class);
            List<Error> basicValidations = validationService.validateBasicUpdateAccountRequest(updateAccountRequest, jsonRequest, jsonRequest.getAdditionalInformation());
            if (basicValidations != null && basicValidations.isEmpty()) {

                boolean isEmailExist = accountDetailsService.isEmailExistUpdateAccount(updateAccountRequest.getEmail(), updateAccountRequest.getMobileNumber());
                if (isEmailExist) {
                    commonService.handleResponse(null, response, Constants.EMAIL_ALREADY_EXISTS);
                    return convertStringToResponseObject(response, response.getResponseCode());
                }

                if (updateAccountRequest.getAccountLevelName() != null && updateAccountRequest.getAccountLevelName().equals(accountLevelNameOne)) {
                    List<Error> validations = validationService.validateUpdateAccountRequestL1(updateAccountRequest, jsonRequest.getAdditionalInformation());
                    if (validations != null && validations.isEmpty()) {
//                        VerifyFingerPrintRequest verifyFingerPrintRequest = new VerifyFingerPrintRequest();
//                        verifyFingerPrintRequest.setFingerIndex(updateAccountRequest.getFingerIndex());
//                        verifyFingerPrintRequest.setFingerTemplate(updateAccountRequest.getFingerTemplate());
//                        verifyFingerPrintRequest.setTemplateType(updateAccountRequest.getTemplateType());
//                        verifyFingerPrintRequest.setCnic(updateAccountRequest.getCnic());
//                        verifyFingerPrintRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                        jsonRequest.setPayLoad(verifyFingerPrintRequest);
//                        Response verifyFingerPrintResp = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), inappbvsUrl);
//                        if (verifyFingerPrintResp.getMessage().equals(Constants.success)) {
                            UpdateAccountLevelRequest updateAccountLevelRequest = new UpdateAccountLevelRequest();
                            updateAccountLevelRequest.setCnic(updateAccountRequest.getCnic());
                            updateAccountLevelRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
                            updateAccountLevelRequest.setAccountClassificationName(Constants.accountClassificationName);
                            updateAccountLevelRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
                            jsonRequest.setPayLoad(updateAccountLevelRequest);
                            Map<String, String> postParam = new HashMap<String, String>();
                            postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
                            Response updateAccountLevelResponse = updateaccountlevel(new Gson().toJson(postParam), request).getBody();
                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(updateAccountLevelResponse != null ? updateAccountLevelResponse.getMessage() : Constants.hostDown);
                            response.setPayLoad(updateAccountLevelResponse != null ? updateAccountLevelResponse.getPayLoad() : null);
                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        } else {
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(verifyFingerPrintResp.getMessage());
//                            response.setPayLoad(verifyFingerPrintResp.getPayLoad());
//                            response.setErrors(verifyFingerPrintResp.getErrors());
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        }
                    } else {
                        response.setErrors(validations);
                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                    }
                }

//                else if (updateAccountRequest.getAccountLevelName() != null && (updateAccountRequest.getAccountLevelName().equals(accountLevelNameUltraBasic) || updateAccountRequest.getAccountLevelName().equals(accountLevelNameUltraFreeLance)
//                        || updateAccountRequest.getAccountLevelName().equals(accountLevelNameUltraSignature))) {
//                    if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u1)) {
//                        updateAccountRequest.setAccountClassificationName(Constants.accountClassificationName);
////                        List<Error> validations = validationService.validateUltraStep1(updateAccountRequest, jsonRequest.getAdditionalInformation());
//////                        if (validations != null && validations.size() == 0) {
//////
//////                            GenerateEmailOtpRequest generateEmailOtpRequest = new GenerateEmailOtpRequest();
//////                            generateEmailOtpRequest.setEmail(updateAccountRequest.getEmail());
//////                            generateEmailOtpRequest.setOtpTypeCode(Constants.l0OtpType);
//////                            jsonRequest.setPayLoad(generateEmailOtpRequest);
//////                            RequestData requestData = new RequestData();
//////                            requestData.setData(jsonRequest);
//////                            Response generateEmailOtpResponse1 = getStringFromRestApiWithMultipleHeader(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), requestData, generateEmailOtpUrl);
//////                            GenerateEmailOtpResponse generateEmailOtpResponse = objectMapper.readValue(convertObjecttoJson(generateEmailOtpResponse1.getPayLoad()), GenerateEmailOtpResponse.class);
//////                            response = generateEmailOtpResponse1;
//////                            response.setPayLoad(generateEmailOtpResponse);
//////                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//////
//////
//////                        } else {
//////                            response.setErrors(validations);
//////                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
//////                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//////                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//////                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//////                        }
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u2)) {
//                        updateAccountRequest.setAccountClassificationName(Constants.accountClassificationName);
//                        List<Error> validations = validationService.validateUltraStep1(updateAccountRequest, jsonRequest.getAdditionalInformation());
//                        if (validations != null && validations.size() == 0) {
//                            GenerateEmailOtpRequest generateEmailOtpRequest = new GenerateEmailOtpRequest();
//                            generateEmailOtpRequest.setEmail(updateAccountRequest.getEmail());
//                            generateEmailOtpRequest.setOtpTypeCode(Constants.l0OtpType);
//                            jsonRequest.setPayLoad(generateEmailOtpRequest);
//                            RequestData requestData = new RequestData();
//                            requestData.setData(jsonRequest);
//                            Response generateEmailOtpResponse1 = getStringFromRestApiWithMultipleHeader(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), requestData, generateEmailOtpUrl);
//                            GenerateEmailOtpResponse generateEmailOtpResponse = objectMapper.readValue(convertObjecttoJson(generateEmailOtpResponse1.getPayLoad()), GenerateEmailOtpResponse.class);
//                            response = generateEmailOtpResponse1;
//                            response.setPayLoad(generateEmailOtpResponse);
//                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        } else {
//                            response.setErrors(validations);
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        }
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u3)) {
//                        updateAccountRequest.setAccountClassificationName(Constants.accountClassificationName);
//                        List<Error> validations = validationService.validateUltraStep3(updateAccountRequest, jsonRequest.getAdditionalInformation());
//                        if (validations != null && validations.size() == 0) {
//                            VerifyEmailOtpRequest verifyEmailOtpRequest = new VerifyEmailOtpRequest();
//                            verifyEmailOtpRequest.setEmail(updateAccountRequest.getEmail());
//                            verifyEmailOtpRequest.setOtpTypeCode(Constants.l0OtpType);
//                            verifyEmailOtpRequest.setOtpPin(updateAccountRequest.getEmailOtpPin());
//                            verifyEmailOtpRequest.setOtpId(updateAccountRequest.getEmailOtpId());
//                            jsonRequest.setPayLoad(verifyEmailOtpRequest);
//                            Response verifyEmailOtpResponse1 = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), verifyEmailOtpUrl);
//                            if (verifyEmailOtpResponse1.getMessage().equals(Constants.success)) {
//                                GetKycRequest getKycRequest = new GetKycRequest();
//                                getKycRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                getKycRequest.setAccountClassificationName(Constants.accountClassificationName);
//                                getKycRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                                jsonRequest.setPayLoad(getKycRequest);
//                                Map<String, String> postParam = new HashMap<String, String>();
//                                postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                                ResponseEntity<Response> getKycDetails = l0PostApis.getKyc(new Gson().toJson(postParam), request);
//                                Response getKycDetailsResp = getKycDetails != null ? getKycDetails.getBody() : null;
//                                Object getKycDetailsRespPayload = getKycDetailsResp != null ? getKycDetailsResp.getPayLoad() : null;
//                                if (getKycDetailsRespPayload != null) {
//
//                                    GetKycResponse getKycResponse = objectMapper.readValue(convertObjecttoJson(getKycDetailsRespPayload), GetKycResponse.class);
//                                    Response clsResponse = commonService.clsScreening(jsonRequest, getKycResponse, request);
//                                    if (clsResponse != null && clsResponse.getMessage().equals(Constants.success)) {
//                                        if (getKycDetailsResp != null && getKycDetailsResp.getMessage().equals(Constants.success) && getKycResponse.getEmail().equals(updateAccountRequest.getEmail())) {
//                                            GetScreenStatusRequest getScreenStatusRequest = new GetScreenStatusRequest();
//                                            getScreenStatusRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                            getScreenStatusRequest.setAccountType(Constants.accountLevelTypeWallet);
//                                            getScreenStatusRequest.setAccountClassificationName(Constants.accountClassificationName);
//                                            getScreenStatusRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//
//                                            jsonRequest.setPayLoad(getScreenStatusRequest);
//                                            postParam = new HashMap<String, String>();
//                                            postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                                            ResponseEntity<Response> getScreenState = l0PostApis.getscreenstate(new Gson().toJson(postParam), request);
//                                            response = objectMapper.readValue(convertObjecttoJson(getScreenState.getBody()), Response.class);
//                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(response.getMessage());
//                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                        } else {
//                                            String updateEmailResponse = l1Services.updateEmail(updateAccountRequest.getMobileNumber(), updateAccountRequest.getEmail(), request, loggedUserDetail);
//                                            if (updateEmailResponse.equals(Constants.success)) {
//                                                GetScreenStatusRequest getScreenStatusRequest = new GetScreenStatusRequest();
//                                                getScreenStatusRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                                getScreenStatusRequest.setAccountType(Constants.accountLevelTypeWallet);
//                                                getScreenStatusRequest.setAccountClassificationName(Constants.accountClassificationName);
//                                                getScreenStatusRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//
//                                                jsonRequest.setPayLoad(getScreenStatusRequest);
//                                                postParam = new HashMap<String, String>();
//                                                postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                                                ResponseEntity<Response> getScreenState = l0PostApis.getscreenstate(new Gson().toJson(postParam), request);
//                                                response = objectMapper.readValue(convertObjecttoJson(getScreenState.getBody()), Response.class);
//                                                logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                            } else {
//                                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(updateEmailResponse);
//                                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                                logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                            }
//                                        }
//                                    } else {
//                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(clsResponse.getMessage());
//                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                                    }
//                                } else {
//                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.hostDown);
//                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                    logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                }
//                            } else {
//                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(verifyEmailOtpResponse1.getMessage());
//                                response.setErrors(verifyEmailOtpResponse1.getErrors());
//                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                            }
//
//
//                        } else {
//                            response.setErrors(validations);
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        }
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u6)) {
//                        UltraCheckDocumentStatus ultraCheckDocumentStatus = new UltraCheckDocumentStatus();
//                        ultraCheckDocumentStatus.setMobileNumber(updateAccountRequest.getMobileNumber());
//                        ultraCheckDocumentStatus.setCountryOfBirth(updateAccountRequest.getCountryOfBirth());
//                        ultraCheckDocumentStatus.setCountryOfTaxResidence(updateAccountRequest.getCountryOfTaxResidence());
//                        ultraCheckDocumentStatus.setDualNationality(updateAccountRequest.getDualNationality());
//                        ultraCheckDocumentStatus.setUsBorn(updateAccountRequest.getUsBorn());
//                        ultraCheckDocumentStatus.setUsTeleNumber(updateAccountRequest.getUsTeleNumber());
//                        ultraCheckDocumentStatus.setUsSignAuth(updateAccountRequest.getUsSignAuth());
//                        ultraCheckDocumentStatus.setUsOtherInfoIndicator(updateAccountRequest.getUsOtherInfoIndicator());
//                        ultraCheckDocumentStatus.setFedralTaxClassificationName(updateAccountRequest.getFedralTaxClassificationName());
//                        ultraCheckDocumentStatus.setAddress(updateAccountRequest.getAddress());
//                        ultraCheckDocumentStatus.setTaxIdentificationNumber(updateAccountRequest.getTaxIdentificationNumber());
//                        ultraCheckDocumentStatus.setDocumentUploaded(updateAccountRequest.getDocumentUploaded());
//                        ultraCheckDocumentStatus.setCountry(updateAccountRequest.getCountry());
//                        ultraCheckDocumentStatus.setGreenCardStatus(updateAccountRequest.getGreenCardStatus());
//                        ultraCheckDocumentStatus.setAccountClassificationName(Constants.individual);
//                        ultraCheckDocumentStatus.setStep(updateAccountRequest.getStep());
//                        ultraCheckDocumentStatus.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                        ultraCheckDocumentStatus.setDeclaration(updateAccountRequest.getDeclaration());
//                        jsonRequest.setPayLoad(ultraCheckDocumentStatus);
//                        Map<String, String> postParam = new HashMap<String, String>();
//                        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                        ResponseEntity<Response> ultraCheckDocumentResp = checkultradocumentstatus(new Gson().toJson(postParam), request);
//                        Response ultraCheckDocumentResponse = ultraCheckDocumentResp.getBody();
//                        response = ultraCheckDocumentResponse;
//
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u8)) {
//                        UltraCheckDocumentStatus ultraCheckDocumentStatus = new UltraCheckDocumentStatus();
//                        ultraCheckDocumentStatus.setMobileNumber(updateAccountRequest.getMobileNumber());
//                        ultraCheckDocumentStatus.setMonthlySpending(updateAccountRequest.getMonthlySpending());
//                        ultraCheckDocumentStatus.setUltraUsage(updateAccountRequest.getUltraUsage());
//                        ultraCheckDocumentStatus.setProfession(updateAccountRequest.getProfession());
//                        ultraCheckDocumentStatus.setChequeBook(updateAccountRequest.getChequeBook());
//                        ultraCheckDocumentStatus.setCurrencyCode(updateAccountRequest.getCurrencyCode());
//                        ultraCheckDocumentStatus.setAccountClassificationName(Constants.individual);
//                        ultraCheckDocumentStatus.setStep(updateAccountRequest.getStep());
//                        ultraCheckDocumentStatus.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                        jsonRequest.setPayLoad(ultraCheckDocumentStatus);
//                        Map<String, String> postParam = new HashMap<String, String>();
//                        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                        ResponseEntity<Response> ultraCheckDocumentResp = checkultradocumentstatus(new Gson().toJson(postParam), request);
//                        Response ultraCheckDocumentResponse = ultraCheckDocumentResp.getBody();
//                        response = ultraCheckDocumentResponse;
//
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && (updateAccountRequest.getStep().equals(Constants.u9) || updateAccountRequest.getStep().equals(Constants.u11))) {
//                        List<Error> validations = validationService.validateUpdateUltraSignatureDeclarations(updateAccountRequest);
//                        if (validations != null && validations.size() == 0) {
//                            VerifyKycQuestionRequest verifyKycQuestionRequest = new VerifyKycQuestionRequest();
//                            verifyKycQuestionRequest.setQuestions(updateAccountRequest.getQuestions());
//                            verifyKycQuestionRequest.setSegmentName(updateAccountRequest.getSegmentName());
//                            verifyKycQuestionRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                            verifyKycQuestionRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                            verifyKycQuestionRequest.setAccountClassificationName(Constants.individual);
//                            verifyKycQuestionRequest.setAccountTypeName(Constants.accountLevelTypeWallet);
//                            jsonRequest.setPayLoad(verifyKycQuestionRequest);
//                            Map<String, String> postParam = new HashMap<String, String>();
//                            postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                            ResponseEntity<Response> verifyKycResp = l0PostApis.verifykycquestions(new Gson().toJson(postParam), request);
//                            Response verifyKycResponse = verifyKycResp.getBody();
//                            if (verifyKycResponse != null && verifyKycResponse.getMessage().equals(Constants.success)) {
//                                commonService.updateScreenStep(verifyKycQuestionRequest.getMobileNumber(), verifyKycQuestionRequest.getAccountTypeName(), Constants.appScreenKycStatusUltra);
//                                PairMatchingRequest pairMatchingRequest = new PairMatchingRequest();
//                                pairMatchingRequest.setMsisdn(updateAccountRequest.getMobileNumber().substring(1));
//                                pairMatchingRequest.setCnic(updateAccountRequest.getCnic());
//                                jsonRequest.setPayLoad(pairMatchingRequest);
//                                Response pairMatchingResp = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), pairMatchingUrl);
//                                PairMatchingResponse pairMatchingResponse = objectMapper.readValue(convertObjecttoJson(pairMatchingResp.getPayLoad()), PairMatchingResponse.class);
//                                boolean updatePmdStatus = l1Services.upDatePmdStatus(updateAccountRequest.getMobileNumber(), pairMatchingResp.getResponseCode());
//                                if (pairMatchingResp.getMessage().equals(Constants.success) && updatePmdStatus == true) {
//                                    String chequeBookStatus = l1Services.getChequeBookStatusByMobileNumber(updateAccountRequest.getMobileNumber());
//                                    if (chequeBookStatus.equals(Constants.not) && !updateAccountRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//                                        UpdateAccountLevelRequest updateAccountLevelRequest = new UpdateAccountLevelRequest();
//                                        updateAccountLevelRequest.setAccountClassificationName(Constants.individual);
//                                        updateAccountLevelRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                                        updateAccountLevelRequest.setCnic(updateAccountRequest.getCnic());
//                                        updateAccountLevelRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                        jsonRequest.setPayLoad(updateAccountLevelRequest);
//                                        postParam = new HashMap<String, String>();
//                                        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                                        Response updateAccountLevelResponse = updateaccountlevel(new Gson().toJson(postParam), request).getBody();
//                                        if (updateAccountLevelResponse != null && updateAccountLevelResponse.getMessage().contains(Constants.accountUpdatedto + " " + updateAccountLevelRequest.getAccountLevelName())) {
//                                            GenerateNotificationRequest generateNotificationRequest = new GenerateNotificationRequest();
//                                            generateNotificationRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                            generateNotificationRequest.setSms(updateAccountRequest.getMessage());
//                                            jsonRequest.setPayLoad(generateNotificationRequest);
//                                            Response generateNotificationResponse = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), generateNotificationUrl);
//                                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(generateNotificationResponse.getMessage());
//                                            response = generateNotificationResponse;
//                                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                            logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//
//                                        } else {
//                                            response = updateAccountLevelResponse;
//                                        }
//                                    } else {
//                                        ////////////////////////////////////////
//                                        String accountPendingStatusResponse = l1Services.parkAccountInPendingStatus(updateAccountRequest, loggedUserDetail);
//                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(accountPendingStatusResponse != null ? accountPendingStatusResponse : Constants.hostDown);
//                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                        logs(Constants.createAccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//
//                                    }
//
//                                } else {
//                                    response = pairMatchingResp;
//
//                                }
//                            } else {
//                                response = verifyKycResponse;
//                            }
//                        } else {
//                            response.setErrors(validations);
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
//                            response.setErrors(validations);
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.checkdocumentstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                        }
//                    } else if (updateAccountRequest.getStep() != null && !updateAccountRequest.getStep().equals(Constants.empty) && updateAccountRequest.getStep().equals(Constants.u10)) {
//                        // step for ultra signature
//                        UltraCheckDocumentStatus ultraCheckDocumentStatus = new UltraCheckDocumentStatus();
//                        ultraCheckDocumentStatus.setMobileNumber(updateAccountRequest.getMobileNumber());
//                        ultraCheckDocumentStatus.setCityCode(updateAccountRequest.getCityCode());
//                        ultraCheckDocumentStatus.setAreaCode(updateAccountRequest.getAreaCode());
//                        ultraCheckDocumentStatus.setStreetNo(updateAccountRequest.getStreetNo());
//                        ultraCheckDocumentStatus.setHouseNo(updateAccountRequest.getHouseNo());
//                        ultraCheckDocumentStatus.setAccountClassificationName(Constants.individual);
//                        ultraCheckDocumentStatus.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                        ultraCheckDocumentStatus.setUtilityBillUploaded(updateAccountRequest.getUtilityBillUploaded());
//                        ultraCheckDocumentStatus.setStep(updateAccountRequest.getStep());
//                        jsonRequest.setPayLoad(ultraCheckDocumentStatus);
//                        Map<String, String> postParam = new HashMap<String, String>();
//                        postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                        ResponseEntity<Response> ultraCheckDocumentResp = checkultradocumentstatus(new Gson().toJson(postParam), request);
//                        Response ultraCheckDocumentResponse = ultraCheckDocumentResp.getBody();
//                        response = ultraCheckDocumentResponse;
//
//                    } else {
//                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidStep);
//                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                    }
//
//                }
//                else if (updateAccountRequest.getAccountLevelName() != null && updateAccountRequest.getAccountLevelName().equalsIgnoreCase(accountLevelNameMerchant)) {
//                    if (updateAccountRequest.getStep().equalsIgnoreCase(Constants.step1)) {
//                        VerifyFingerPrintRequest verifyFingerPrintRequest = new VerifyFingerPrintRequest();
//                        verifyFingerPrintRequest.setFingerIndex(updateAccountRequest.getFingerIndex());
//                        verifyFingerPrintRequest.setFingerTemplate(updateAccountRequest.getFingerTemplate());
//                        verifyFingerPrintRequest.setTemplateType(updateAccountRequest.getTemplateType());
//                        verifyFingerPrintRequest.setCnic(updateAccountRequest.getCnic());
//                        verifyFingerPrintRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                        jsonRequest.setPayLoad(verifyFingerPrintRequest);
//                        Response verifyFingerPrintResp = getResponseFromPostAPIData(createHeaderMapBackOffice(request.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), inappbvsUrl);
//                        if (verifyFingerPrintResp.getMessage().equals(Constants.success)) {
//                            String updateBvsStatus = l1Services.updateBvsStatusByMobileNumberAndAccountTypeName(updateAccountRequest.getMobileNumber(), Constants.accountLevelTypeWallet, Constants.yes, loggedUserDetail.getUserId());
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(updateBvsStatus);
//                            response.setPayLoad(null);
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//
//                        }
//                    }
//                    else if (updateAccountRequest.getStep().equalsIgnoreCase(Constants.step4)) {
//                        List<Error> validations = validationService.validateMerchantAccountUpdateRequest(updateAccountRequest, jsonRequest.getAdditionalInformation());
//                        if (validations != null && validations.size() == 0) {
//                            VerifyKycQuestionRequest verifyKycQuestionRequest = new VerifyKycQuestionRequest();
//                            verifyKycQuestionRequest.setQuestions(updateAccountRequest.getQuestions());
//                            verifyKycQuestionRequest.setSegmentName(updateAccountRequest.getSegmentName());
//                            verifyKycQuestionRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                            verifyKycQuestionRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                            verifyKycQuestionRequest.setAccountClassificationName(Constants.individual);
//                            verifyKycQuestionRequest.setAccountTypeName(Constants.accountLevelTypeWallet);
//                            jsonRequest.setPayLoad(verifyKycQuestionRequest);
//                            Map<String, String> postParam = new HashMap<String, String>();
//                            postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                            ResponseEntity<Response> verifyKycResp = l0PostApis.verifykycquestions(new Gson().toJson(postParam), request);
//                            Response verifyKycResponse = verifyKycResp.getBody();
//                            if (verifyKycResponse != null && verifyKycResponse.getMessage().equals(Constants.success)) {
//                                commonService.updateScreenStep(verifyKycQuestionRequest.getMobileNumber(), verifyKycQuestionRequest.getAccountTypeName(), Constants.MERCHANT_KYC_CLEARED);
//                                //checking for BVS Verified or Not
//                                String bvsVerified = l1Services.checkBvsVerification(updateAccountRequest.getMobileNumber(), Constants.accountLevelTypeWallet);
//                                //if BVS Verified UpdateAccountLevel
//                                if (bvsVerified.equalsIgnoreCase(Constants.yes)) {
//                                    UpdateAccountLevelRequest updateAccountLevelRequest = new UpdateAccountLevelRequest();
//                                    updateAccountLevelRequest.setMobileNumber(updateAccountRequest.getMobileNumber());
//                                    updateAccountLevelRequest.setAccountLevelName(updateAccountRequest.getAccountLevelName());
//                                    updateAccountLevelRequest.setAccountClassificationName(Constants.individual);
//                                    updateAccountLevelRequest.setBusinessAddress(updateAccountRequest.getBusinessAddress());
//                                    updateAccountLevelRequest.setBusinessName(updateAccountRequest.getBusinessName());
//                                    updateAccountLevelRequest.setBusinessTypeCode(updateAccountRequest.getBusinessTypeCode());
//                                    updateAccountLevelRequest.setMonthlySaleExpectedCode(updateAccountRequest.getMonthlySaleExpectedCode());
//                                    updateAccountLevelRequest.setCityCode(updateAccountRequest.getCityCode());
//                                    updateAccountLevelRequest.setCnic(updateAccountRequest.getCnic());
//                                    jsonRequest.setPayLoad(updateAccountLevelRequest);
//                                    postParam = new HashMap<String, String>();
//                                    postParam.put(Constants.requestData, convertObjecttoJson(jsonRequest));
//                                    Response updateAccountLevelResponse = updateaccountlevel(new Gson().toJson(postParam), request).getBody();
//                                    if (updateAccountLevelResponse.getMessage().equalsIgnoreCase(Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName()) || updateAccountLevelResponse.getMessage().equalsIgnoreCase(Constants.accountAlreadyUpdatedto + updateAccountLevelRequest.getAccountLevelName())) {
//                                        //call the QR  API HERE
//                                        String deviceInfoResponse = deviceServices.updateDeviceInfo(updateAccountRequest.getDeviceInfo(), updateAccountRequest.getMobileNumber(), loggedUserDetail.getUserId());
//                                        MerchantDetailResponse merchantDetailResponse = merchantService.generateQrForMerchant(updateAccountLevelRequest.getMobileNumber(), request);
//                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.accountUpdatedto + updateAccountRequest.getAccountLevelName());
//                                        response.setPayLoad(merchantDetailResponse != null ? merchantDetailResponse : null);
//                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                                    } else {
//                                        tblResponseMessage = responseService.getResponseMessageByResponseDescr(updateAccountLevelResponse != null ? updateAccountLevelResponse.getMessage() : Constants.hostDown);
//                                        response.setErrors(updateAccountLevelResponse.getErrors());
//                                        response.setPayLoad(updateAccountLevelResponse != null ? updateAccountLevelResponse.getPayLoad() : null);
//                                        response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                        response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                        logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                    }
//
//                                } else {
//                                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.BVS_NOT_VERIFIED);
//                                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                    logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                                }
//                            } else {
//                                tblResponseMessage = responseService.getResponseMessageByResponseDescr(verifyKycResponse.getMessage());
//                                response.setErrors(verifyKycResponse.getErrors());
//                                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                                logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                            }
//                        } else {
//                            response.setErrors(validations);
//                            tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
//                            response.setErrors(validations);
//                            response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                            response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                            logs(Constants.checkdocumentstatus, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//                        }
//                    }
//                } else {
//                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.invalidAccountLevel);
//                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
//                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
//                    logs(Constants.updateaccount, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
//
//                }
            } else {
                response.setErrors(basicValidations);
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
                response.setErrors(basicValidations);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.updateaccountlevel, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
            }

        return convertStringToResponseObject(response, response != null ? response.getResponseCode() : Constants.generalProcessingCode);
    }


    @SecurityRequirement(name = Constants.securityRequirement)
    @RequestMapping(value = Constants.updateaccountlevel, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateaccountlevel(@RequestBody String data, HttpServletRequest request) throws Exception {
        String methodName = getCurrentMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        Request jsonRequest = convertStringToRequestObjectData(data);
        Response response = new Response();
        logs(Constants.updateaccountlevel, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.startingMethod, response);
        TblResponseMessage tblResponseMessage;

            UpdateAccountLevelRequest updateAccountLevelRequest = objectMapper.readValue(convertObjecttoJson(jsonRequest.getPayLoad()), UpdateAccountLevelRequest.class);
            List<Error> validations = validationService.validateUpdateAccountLevelRequest(updateAccountLevelRequest, jsonRequest.getAdditionalInformation());
            if (validations != null && validations.size() == 0) {
                String responseData = l1Services.updateaccountlevel(updateAccountLevelRequest, jsonRequest.getAdditionalInformation(), loggedUserDetail, request, jsonRequest);
                if (responseData != null) {
                    if (responseData.contains(Constants.accountUpdatedto) || responseData.contains(Constants.accountAlreadyUpdatedto)) {
                        commonService.calculateRiskScoreAndRating(updateAccountLevelRequest.getMobileNumber(), updateAccountLevelRequest.getAccountLevelName(), jsonRequest, request);
                    }
                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(responseData);
                    response.setPayLoad(null);
                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                    logs(Constants.updateaccountlevel, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                } else {
                    tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.recordNotUpdated);
                    response.setPayLoad(null);
                    response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                    response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                    logs(Constants.updateaccountlevel, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
                }
            } else {
                response.setErrors(validations);
                tblResponseMessage = responseService.getResponseMessageByResponseDescr(Constants.validationFailed);
                response.setErrors(validations);
                response.setResponseCode(tblResponseMessage != null ? moduleId + tblResponseMessage.getResponseMessageCode() : moduleId + Constants.generalProcessingCode);
                response.setMessage(tblResponseMessage != null ? tblResponseMessage.getResponseMessageDescr() : Constants.generalProcessingError);
                logs(Constants.updateaccountlevel, Constants.logLevelInfo, this.getClass().getSimpleName(), methodName, this.getClass().getPackageName(), jsonRequest, Constants.endingMethod, response);
            }

        return convertStringToResponseObject(response, response.getResponseCode());
    }


}
