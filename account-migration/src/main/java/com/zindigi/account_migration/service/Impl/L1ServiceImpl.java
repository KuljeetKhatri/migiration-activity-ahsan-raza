package com.zindigi.account_migration.service.Impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import com.mfs.commonservice.dto.AdditionalInformation;
import com.mfs.commonservice.dto.Request;
import com.mfs.commonservice.dto.Response;
import com.mfs.commonservice.model.LkpStatus;
import com.mfs.commonservice.repo.LkpCurrencyRepo;
import com.mfs.commonservice.repo.LkpStatusRepo;
import com.mfs.commonservice.util.AbstractApi;
import com.mfs.commonservice.util.ResponseCodeConstants;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.*;
import com.zindigi.account_migration.service.L1Services;
import com.zindigi.account_migration.util.Constants;
import com.zindigi.account_migration.util.CustomDataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class L1ServiceImpl extends AbstractApi implements L1Services {
    @Autowired
    private TblAccountRepo tblAccountRepo;
    @Autowired
    private LkpAccountLevelRepo lkpAccountLevelRepo;
    @Autowired
    private TblCustomerRepo tblCustomerRepo;
    @Autowired
    private TblUltraCustomerRepo tblUltraCustomerRepo;

    @Autowired
    private TblDocumentRepo tblDocumentRepo;
    @Autowired
    private TblAddressRepo tblAddressRepo;
    @Autowired
    private LkpMonthlySaleRepo lkpMonthlySaleRepo;

    @Autowired
    private TblMerchantDocRepo tblMerchantDocRepo;

    @Autowired
    private TblMerchantRepo tblMerchantRepo;
    @Autowired
    private LkpCityRepo lkpCityRepo;
    @Autowired
    private LkpCurrencyRepo lkpCurrencyRepo;
    @Autowired
    private LkpAccountTypeRepo lkpAccountTypeRepo;
    @Autowired
    private LkpStatusRepo lkpStatusRepo;

    @Autowired
    private LkpDocStatusRepo lkpDocStatusRepo;
    @Autowired
    private TblGlCodeCombinationRepo tblGlCodeCombinationRepo;

    @Autowired
    private LkpBusinessTypeRepo lkpBusinessTypeRepo;

    @Value("${kms.kyc.decrption.url}")
    private String kmsUrl;

    @Value("${kms.encryptKyc.url}")
    private String encryptCustomerKycUrl;
    @Value("${inappbvs.url}")
    private String inappbvsUrl;
    @Value("${path.url}")
    private String pathUrl;

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
    @Value("${account.level.minor.0}")
    private String accountLevelNameMinorZero;
    @Value("${account.level.minor.1}")
    private String accountLevelNameMinorOne;
    @Value("${account.level.merchant}")
    private String accountLevelNameMerchant;


    @Value("${petro.account.type}")
    private String petroAccountType;





//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public String updateaccountlevel(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList, HttpServletRequest httpServletRequest, Request jsonRequest) throws Exception {
//        String response = null;
//        TblAccountModel tblAccount;
//        LkpAccountLevelModel lkpAccountLevel = lkpAccountLevelRepo.findByAccountLevelName(updateAccountLevelRequest.getAccountLevelName(), Constants.accountClassificationName);
//        if (lkpAccountLevel != null) {
//            if (lkpAccountLevel.getAccountLevelName().equals(accountLevelNameUltraFreeLance)) {
//                tblAccount = tblAccountRepo.getAccountByMobileNumberOrCnicAndAccountType(updateAccountLevelRequest.getCnic(), updateAccountLevelRequest.getMobileNumber(), Constants.ACCOUNT_TYPE_NAME_FREELANCE);
//            } else {
//                tblAccount = tblAccountRepo.getAccountByMobileNumberOrCnicAndAccountType(updateAccountLevelRequest.getCnic(), updateAccountLevelRequest.getMobileNumber(), Constants.accountLevelTypeWallet);
//            }
//            if (tblAccount != null) {
//
//                LkpAccountStatus lkpAccountStatus = tblAccount.getLkpAccountStatus();
//                if (lkpAccountStatus.getAccountStatusCode().equals(Constants.ACCOUNT_STATUS_ACTIVE) && (tblAccount.getIsActive().equalsIgnoreCase(Constants.Y) || lkpAccountLevel.getAccountLevelName().equals(accountLevelNameUltraFreeLance))) {
//
//                    if ((tblAccount.getLkpAccountLevel() != null && tblAccount.getLkpAccountLevel() != lkpAccountLevel)
//                            || (tblAccount.getLkpAccountLevel().getAccountLevelName().equals(accountLevelNameUltraFreeLance) && tblAccount.getIsActive().equalsIgnoreCase(Constants.not))) {
//                        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumberHash(updateAccountLevelRequest.getMobileNumber());
//
//                        if (updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameOne) || updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameMinorOne)) {
//                            if (tblAccount.getLkpAccountLevel().getAccountLevelName().equalsIgnoreCase(accountLevelNameZero) && lkpAccountLevel.getAccountLevelName().equalsIgnoreCase(accountLevelNameOne)) {
//                                tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                tblAccount.setLkpStatus(lkpStatus);
//                                tblAccount.setIsActive(Constants.Y);
//                                tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                tblAccount.setLevelChangeDate(new Date());
////                                tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                tblCustomer.setBvs(Constants.yes);
//                                tblCustomer.setLastupdatedate(new Date());
////                                tblCustomer.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                if (tblCustomer.getKyc() != null && !tblCustomer.getKyc().isEmpty()) {
//                                    ObjectMapper objectMapper = new ObjectMapper();
//                                    JsonNode jsonData = objectMapper.readTree(tblCustomer.getKyc());
//                                    ArrayNode additionalInformationArray = objectMapper.createArrayNode();
//                                    for (AdditionalInformation additionalInformation : additionalInformationList) {
//                                        additionalInformationArray.add(new Gson().toJson(additionalInformation));
//                                    }
//                                    ((ObjectNode) jsonData).set(updateAccountLevelRequest.getAccountLevelName(), additionalInformationArray);
//                                    String updatedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);
//                                    tblCustomer.setKyc(updatedJson);
//                                } else {
//                                    HashMap<String, String> additionalFields = new HashMap<>();
//                                    additionalFields.put(updateAccountLevelRequest.getAccountLevelName(), additionalInformationList != null ? new Gson().toJson(additionalInformationList) : "");
//                                    tblCustomer.setKyc(new Gson().toJson(additionalFields));
//                                }
//                                updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//
//                                updatePetroAccount(updateAccountLevelRequest, tblAccount, lkpAccountLevel);
//                                tblCustomer = tblCustomerRepo.saveAndFlush(tblCustomer);
//                                tblAccount = tblAccountRepo.saveAndFlush(tblAccount);
//                                response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                            } else if (tblAccount.getLkpAccountLevel().getAccountLevelName().equalsIgnoreCase(accountLevelNameMinorZero) && lkpAccountLevel.getAccountLevelName().equalsIgnoreCase(accountLevelNameMinorOne)) {
//                                tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                tblAccount.setIsActive(Constants.Y);
//                                tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                tblAccount.setLevelChangeDate(new Date());
//                                LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                tblAccount.setLkpStatus(lkpStatus);
////                                tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                tblCustomer.setBvs(Constants.yes);
//                                tblCustomer.setLastupdatedate(new Date());
////                                tblCustomer.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                TblUltraCustomer tblUltraCustomer = tblUltraCustomerRepo.findByCustomerId(tblCustomer.getCustomerId());
//                                if (tblUltraCustomer != null) {
//                                    tblUltraCustomer.setAccountLevelId(new BigDecimal(lkpAccountLevel.getAccountLevelId()));
//                                    tblUltraCustomer.setLastupdatedate(new Date());
////                                    tblUltraCustomer.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                    if (tblCustomer.getKyc() != null && !tblCustomer.getKyc().isEmpty()) {
//                                        ObjectMapper objectMapper = new ObjectMapper();
//                                        JsonNode jsonData = objectMapper.readTree(tblCustomer.getKyc());
//                                        ArrayNode additionalInformationArray = objectMapper.createArrayNode();
//                                        for (AdditionalInformation additionalInformation : additionalInformationList) {
//                                            additionalInformationArray.add(new Gson().toJson(additionalInformation));
//                                        }
//                                        ((ObjectNode) jsonData).set(accountLevelNameOne, additionalInformationArray);
//                                        String updatedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);
//                                        tblCustomer.setKyc(updatedJson);
//                                    } else {
//                                        HashMap<String, String> additionalFields = new HashMap<>();
//                                        additionalFields.put(accountLevelNameOne, additionalInformationList != null ? new Gson().toJson(additionalInformationList) : "");
//                                        tblCustomer.setKyc(new Gson().toJson(additionalFields));
//                                    }
//                                    updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//                                    tblCustomerRepo.saveAndFlush(tblCustomer);
//                                    tblAccountRepo.saveAndFlush(tblAccount);
//                                    tblUltraCustomerRepo.saveAndFlush(tblUltraCustomer);
//                                    response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                                } else {
//                                    response = Constants.noAccountFound;
//                                }
//                            } else {
//                                response = Constants.FAILED_TO_UPDATE_ACCOUNT_LEVEL;
//                            }
//
//                        } else if (updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraBasic) || updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraFreeLance) ||
//                                updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//                            TblUltraCustomer tblUltraCustomer = tblUltraCustomerRepo.findByCustomerId(tblCustomer.getCustomerId());
//                            if (tblUltraCustomer != null) {
//                                tblUltraCustomer.setPmd(Constants.yes);
//                                tblUltraCustomer.setKycVerified(Constants.yes);
//                                tblAccount.setIsActive(Constants.Y);
//                                tblUltraCustomer.setAccountLevelId(new BigDecimal(lkpAccountLevel.getAccountLevelId()));
//                                if (tblUltraCustomer.getChequeBook().equalsIgnoreCase(Constants.not) && !updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//                                    tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                    tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                    LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                    tblAccount.setLkpStatus(lkpStatus);
//                                    tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                    tblAccount.setLevelChangeDate(new Date());
////                                    tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                    updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//                                    tblAccountRepo.saveAndFlush(tblAccount);
//                                    tblUltraCustomer.setStatus("U");
//                                    tblUltraCustomerRepo.saveAndFlush(tblUltraCustomer);
//                                    response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                                    if (updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraBasic) || updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//
//                                        updatePetroAccount(updateAccountLevelRequest, tblAccount, lkpAccountLevel);
//
//                                    }
//                                } else {
//                                    if (updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//                                        if (tblUltraCustomer.getSignatureVerified() != null && tblUltraCustomer.getSignatureVerified().equalsIgnoreCase(Constants.yes) &&
//                                                tblUltraCustomer.getIsAccountDetailVerified() != null && tblUltraCustomer.getIsAccountDetailVerified().equalsIgnoreCase(Constants.yes) &&
//                                                tblUltraCustomer.getIsPoiVerified() != null && tblUltraCustomer.getIsPoiVerified().equalsIgnoreCase(Constants.yes)) {
//                                            tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                            tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                            tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                            tblAccount.setLevelChangeDate(new Date());
//                                            LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                            tblAccount.setLkpStatus(lkpStatus);
////                                            tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                            updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//                                            tblAccountRepo.saveAndFlush(tblAccount);
//                                            tblUltraCustomer.setSelfDeclarationAccepted(Constants.Y);
//                                            tblUltraCustomer.setIsTandCAccepted(Constants.Y);
//                                            tblUltraCustomer.setStatus("U");
//                                            tblUltraCustomerRepo.saveAndFlush(tblUltraCustomer);
//                                            if (updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraBasic) || updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameUltraSignature)) {
//
//                                                updatePetroAccount(updateAccountLevelRequest, tblAccount, lkpAccountLevel);
//
//                                            }
//                                            response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                                        } else {
//                                            response = Constants.ultraAccountParkedForReviewing;
//                                        }
//                                    } else {
//                                        if (tblUltraCustomer.getSignatureVerified() != null && tblUltraCustomer.getSignatureVerified().equalsIgnoreCase(Constants.yes) &&
//                                                tblUltraCustomer.getIsAccountDetailVerified() != null && tblUltraCustomer.getIsAccountDetailVerified().equalsIgnoreCase(Constants.yes)) {
//                                            tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                            tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                            tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                            tblAccount.setLevelChangeDate(new Date());
//                                            LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                            tblAccount.setLkpStatus(lkpStatus);
////                                            tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                            updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//                                            tblAccountRepo.saveAndFlush(tblAccount);
//                                            tblUltraCustomer.setStatus("U");
//                                            tblUltraCustomerRepo.saveAndFlush(tblUltraCustomer);
//                                            response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                                        } else {
//
//                                            response = Constants.ultraAccountParkedForReviewing;
//                                        }
//                                    }
//                                }
//                            } else {
//                                response = Constants.ultraAccountNotFound;
//                            }
//                        } else if (updateAccountLevelRequest.getAccountLevelName().equalsIgnoreCase(accountLevelNameMerchant)) {
//                            //Merchant Account update Code Here
//                            closePetroAccount(updateAccountLevelRequest);
//                            LkpCity lkpCity = lkpCityRepo.findByCityCode(updateAccountLevelRequest.getCityCode());
//                            if (lkpCity != null) {
//                                LkpBusinessType lkpBusinessType = lkpBusinessTypeRepo.findByBusinessTypeCode(updateAccountLevelRequest.getBusinessTypeCode());
//                                if (lkpBusinessType != null) {
//                                    LkpMonthlySale lkpMonthlySale = lkpMonthlySaleRepo.findByMonthlySaleCode(updateAccountLevelRequest.getMonthlySaleExpectedCode());
//                                    if (lkpMonthlySale != null) {
//                                        Object cnic1 = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(updateAccountLevelRequest.getMobileNumber(), Constants.docCnicFront);
//                                        if (cnic1 != null) {
//                                            Object cnic2 = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(updateAccountLevelRequest.getMobileNumber(), Constants.docCnicBack);
//                                            if (cnic2 != null) {
//                                                Object selfie = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(updateAccountLevelRequest.getMobileNumber(), Constants.CUSTOMER_PHOTO);
//                                                if (selfie != null) {
//                                                    Object proofOfBusiness = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(updateAccountLevelRequest.getMobileNumber(), Constants.PROOF_OF_BUSINESS);
//                                                    if (proofOfBusiness != null) {
//                                                        TblMerchant tblMerchant = tblMerchantRepo.getMerchantByAccountTypeAndMobileNumner(updateAccountLevelRequest.getMobileNumber(), Constants.accountLevelTypeWallet);
//                                                        tblMerchant = tblMerchant == null ? new TblMerchant() : tblMerchant;
//                                                        tblMerchant.setLkpMonthlySale(lkpMonthlySale);
//                                                        tblMerchant.setBusinessAddress(updateAccountLevelRequest.getBusinessAddress());
//                                                        tblMerchant.setBusinessName(updateAccountLevelRequest.getBusinessName());
//                                                        tblMerchant.setCreatedate(new Date());
////                                                        tblMerchant.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                                        tblMerchant.setIsActive(Constants.N);
//                                                        tblMerchant.setLkpBusinessType(lkpBusinessType);
//                                                        tblMerchant.setLkpCity(lkpCity);
//                                                        LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUS_PENDING);
//                                                        tblMerchant.setLkpStatus(lkpStatus);
//                                                        tblMerchant.setTillNo(Constants.TILL_PREFIX + tblMerchantRepo.getTillNo());
//                                                        tblMerchant.setTblAccount(tblAccount);
//                                                        TblDocument tblDocument = new TblDocument();
//                                                        //setting proof of Business
//
//                                                        Object[] row = (Object[]) proofOfBusiness;
//                                                        tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
//                                                        tblMerchant.setTblDocument1(tblDocument);
//                                                        //setting cnic front
//                                                        tblDocument = new TblDocument();
//                                                        row = (Object[]) cnic1;
//                                                        tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
//                                                        tblMerchant.setTblDocument2(tblDocument);
//                                                        //setting cnic back
//                                                        tblDocument = new TblDocument();
//                                                        row = (Object[]) cnic2;
//                                                        tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
//                                                        tblMerchant.setTblDocument3(tblDocument);
//                                                        //setting selfies
//                                                        tblDocument = new TblDocument();
//                                                        row = (Object[]) selfie;
//                                                        tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
//                                                        tblMerchant.setTblDocument4(tblDocument);
//                                                        tblMerchant = tblMerchantRepo.saveAndFlush(tblMerchant);
//                                                        LkpDocStatus lkpDocStatus = lkpDocStatusRepo.findByDocStatusCode(Constants.STATUS_PENDING);
//                                                        for (int i = 0; i <= 3; i++) {
//                                                            TblMerchantDoc tblMerchantDoc = new TblMerchantDoc();
//                                                            tblMerchantDoc = setMerchantDoc(tblMerchantDoc, i, tblMerchant);
//                                                            tblMerchantDoc.setTblMerchant(tblMerchant);
//                                                            tblMerchantDoc.setLkpDocStatus(lkpDocStatus);
////                                                            tblMerchantDoc.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                                            tblMerchantDoc.setCreatedate(new Date());
//                                                            tblMerchantDocRepo.saveAndFlush(tblMerchantDoc);
//                                                        }
//                                                        //(tblAccount.getLkpAccountLevel().getAccountLevelName().equalsIgnoreCase(accountLevelNameZero) || tblAccount.getLkpAccountLevel().getAccountLevelName().equalsIgnoreCase(accountLevelNameOne)) &&
//                                                        if (lkpAccountLevel.getAccountLevelName().equalsIgnoreCase(accountLevelNameMerchant)) {
//
//                                                            tblCustomer.setBvs(Constants.yes);
//                                                            tblCustomer.setLastupdatedate(new Date());
//                                                            RaastLinkingRequest raastLinkingRequest = new RaastLinkingRequest();
//                                                            raastLinkingRequest.setAliasValue(updateAccountLevelRequest.getMobileNumber());
//                                                            raastLinkingRequest.setIsdeLinking(false);
//                                                            raastLinkingRequest.setAccountLevelName(tblAccount.getLkpAccountLevel().getAccountLevelName());
//                                                            raastLinkingRequest.setType(Constants.MERCHANT);
//                                                            raastLinkingRequest.setMobileNumber(updateAccountLevelRequest.getMobileNumber());
//                                                            RaastUpdateLinkingResponse raastUpdateLinkingResponse = raastServices.updateRaastLinking(jsonRequest, raastLinkingRequest, loggedUserDetail, httpServletRequest);
//                                                            if (raastUpdateLinkingResponse.getResponseCode().equals(Constants.RAAST_SUCESS_CODE)) {
//                                                                tblAccount.setPreviousLevelId(new BigDecimal(tblAccount.getLkpAccountLevel().getAccountLevelId()));
//                                                                tblAccount.setLkpAccountLevel(lkpAccountLevel);
//                                                                lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
//                                                                tblAccount.setLkpStatus(lkpStatus);
//                                                                tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? tblAccount.getUpdateindex().add(new BigDecimal(1)) : new BigDecimal(1));
//                                                                tblAccount.setLevelChangeDate(new Date());
////                                                                tblAccount.setLevelChangeBy(BigDecimal.valueOf(loggedUserDetail.getUserId()));
//                                                                tblAccount.setIsRaastIdLink(Constants.yes);
//                                                                response = Constants.accountUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//
//
//                                                            } else {
//                                                                throw new RuntimeException(raastUpdateLinkingResponse.getResponseDescription().toString());
//                                                            }
////                                                            tblCustomer.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
//                                                            if (tblCustomer.getKyc() != null && !tblCustomer.getKyc().isEmpty()) {
//                                                                ObjectMapper objectMapper = new ObjectMapper();
//                                                                JsonNode jsonData = objectMapper.readTree(tblCustomer.getKyc());
//                                                                ArrayNode additionalInformationArray = objectMapper.createArrayNode();
//                                                                for (AdditionalInformation additionalInformation : additionalInformationList) {
//                                                                    additionalInformationArray.add(new Gson().toJson(additionalInformation));
//                                                                }
//                                                                ((ObjectNode) jsonData).set(accountLevelNameMerchant, additionalInformationArray);
//                                                                String updatedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);
//                                                                tblCustomer.setKyc(updatedJson);
//                                                            } else {
//                                                                HashMap<String, String> additionalFields = new HashMap<>();
//                                                                additionalFields.put(accountLevelNameMerchant, additionalInformationList != null ? new Gson().toJson(additionalInformationList) : "");
//                                                                tblCustomer.setKyc(new Gson().toJson(additionalFields));
//                                                            }
//                                                            updateAccountGl(tblAccount, lkpAccountLevel.getAccountLevelName());
//                                                            tblCustomerRepo.saveAndFlush(tblCustomer);
//                                                            tblAccountRepo.saveAndFlush(tblAccount);
//                                                            tblMerchantRepo.saveAndFlush(tblMerchant);
//
//                                                        }
//
//                                                    } else {
//                                                        response = Constants.PROOF_OF_BUSINESS_NOT_FOUND;
//                                                    }
//
//
//                                                } else {
//                                                    response = Constants.SELFIE_NOT_FOUND;
//                                                }
//
//                                            } else {
//                                                response = Constants.cnicBackMissing;
//                                            }
//                                        } else {
//                                            response = Constants.cnicFrontMissing;
//                                        }
//
//
//                                    } else {
//                                        response = Constants.MONTHLY_SALE_EXPECTED_NOT_FOUND;
//                                    }
//
//                                } else {
//                                    response = Constants.BUSINESS_TYPE_NOT_FOUND;
//                                }
//                            } else {
//                                response = Constants.cityNotFound;
//                            }
//                        }
//                    } else {
//                        response = Constants.accountAlreadyUpdatedto + updateAccountLevelRequest.getAccountLevelName();
//                    }
//
//                } else {
//                    response = Constants.ACCOUNT_NOT_ACTIVE;
//                }
//
//            } else {
//                response = Constants.noAccountFound;
//            }
//        } else {
//            response = Constants.invalidAccountLevel;
//
//        }
//        return response;
//
//    }



//    @Override
//    public void updateAccountGl(TblAccountModel tblAccount, String accountLevelName) {
//        TblGlCodeCombination tblGlCodeCombination = tblGlCodeCombinationRepo.findTblGlCodeCombinationByAccountLevelName(tblAccount.getLkpAccountLevel().getAccountLevelName());
//        if (tblGlCodeCombination != null) {
//            updateGlBalances(tblAccount.getTblGlCodeCombination().getGlCodeCombinationId(), tblGlCodeCombination.getGlCodeCombinationId(), tblAccount.getActualBalance());
//            tblAccount.setTblGlCodeCombination(tblGlCodeCombination);
//        }
//
//    }

//    @Override
//    public void updateGlBalances(long previousGlId, long nextGlId, BigDecimal balance) {
//        TblAccount previousGlAccount = tblAccountRepo.findGlAccountByGlCodeCombinationId(Long.valueOf(previousGlId));
//        if (previousGlAccount != null) {
//            TblAccount currentGlAccount = tblAccountRepo.findGlAccountByGlCodeCombinationId(Long.valueOf(nextGlId));
//            if (currentGlAccount != null) {
//                BigDecimal preBalance = previousGlAccount.getActualBalance().subtract(balance);
//                previousGlAccount.setActualBalance(preBalance);
//                previousGlAccount.setAvailableBalance(preBalance);
//                BigDecimal nextBalance = currentGlAccount.getActualBalance().add(balance);
//                currentGlAccount.setActualBalance(nextBalance);
//                currentGlAccount.setAvailableBalance(nextBalance);
//                tblAccountRepo.saveAndFlush(currentGlAccount);
//                tblAccountRepo.saveAndFlush(previousGlAccount);
//            }
//
//        }
//
//
//
//    }

//    private void updatePetroAccount(UpdateAccountLevelRequest updateAccountLevelRequest, TblAccount tblAccount, LkpAccountLevel lkpAccountLevel) {
//        TblAccount petroTblAccount = tblAccountRepo.getAccountByMobileNumberOrCnicAndAccountType(updateAccountLevelRequest.getCnic(), updateAccountLevelRequest.getMobileNumber(), petroAccountType);
//        if (petroTblAccount != null) {
//            petroTblAccount.setLkpAccountLevel(lkpAccountLevel);
//            updateAccountGl(petroTblAccount, lkpAccountLevel.getAccountLevelName());
//            tblAccountRepo.save(tblAccount);
//        }
//    }

//    private void closePetroAccount(UpdateAccountLevelRequest updateAccountLevelRequest) throws CustomDataNotFoundException {
//        TblAccount tblAccount = tblAccountRepo.findByMobileNoHashAndAccountTypeName(updateAccountLevelRequest.getMobileNumber(), petroAccountType);
//        if (tblAccount != null) {
//            if (tblAccount.getAvailableBalance().compareTo(BigDecimal.ZERO) > 0) {
//                throw new CustomDataNotFoundException(Constants.CONSUME_BALANCE_BEFORE_CLOSING_ACCOUNT, Constants.CONSUME_BALANCE_BEFORE_CLOSING_ACCOUNT);
//            } else {
//                LkpAccountStatus lkpAccountStatus1 = new LkpAccountStatus();
//                lkpAccountStatus1.setAccountStatusId(4);
//
//                tblAccount.setLkpAccountStatus(lkpAccountStatus1);
//                tblAccount.setLastupdatedate(new Date());
//                tblAccount.setUpdateindex(setUpdateIndex(tblAccount.getUpdateindex()));
//                tblAccountRepo.saveAndFlush(tblAccount);
//            }
//        }
//    }

    private TblMerchantDoc setMerchantDoc(TblMerchantDoc tblMerchantDoc, int i, TblMerchant tblMerchant) {
        if (i == 0) {
            TblDocument tblDocument = tblDocumentRepo.findById(tblMerchant.getTblDocument1().getDocumentId()).orElse(null);
            if (tblDocument != null) {
                tblMerchantDoc = tblMerchantDocRepo.findByMerchantIDAndFieldName(tblMerchant.getMerchantId(), tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc = tblMerchantDoc == null ? new TblMerchantDoc() : tblMerchantDoc;
                tblMerchantDoc.setFieldName(tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc.setFieldValue(tblDocument.getDocumentPath());

            }
        } else if (i == 1) {
            TblDocument tblDocument = tblDocumentRepo.findById(tblMerchant.getTblDocument2().getDocumentId()).orElse(null);
            if (tblDocument != null) {
                tblMerchantDoc = tblMerchantDocRepo.findByMerchantIDAndFieldName(tblMerchant.getMerchantId(), tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc = tblMerchantDoc == null ? new TblMerchantDoc() : tblMerchantDoc;
                tblMerchantDoc.setFieldName(tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc.setFieldValue(tblDocument.getDocumentPath());

            }
        } else if (i == 2) {
            TblDocument tblDocument = tblDocumentRepo.findById(tblMerchant.getTblDocument3().getDocumentId()).orElse(null);
            if (tblDocument != null) {
                tblMerchantDoc = tblMerchantDocRepo.findByMerchantIDAndFieldName(tblMerchant.getMerchantId(), tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc = tblMerchantDoc == null ? new TblMerchantDoc() : tblMerchantDoc;
                tblMerchantDoc.setFieldName(tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc.setFieldValue(tblDocument.getDocumentPath());

            }
        } else if (i == 3) {
            TblDocument tblDocument = tblDocumentRepo.findById(tblMerchant.getTblDocument4().getDocumentId()).orElse(null);
            if (tblDocument != null) {
                tblMerchantDoc = tblMerchantDocRepo.findByMerchantIDAndFieldName(tblMerchant.getMerchantId(), tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc = tblMerchantDoc == null ? new TblMerchantDoc() : tblMerchantDoc;
                tblMerchantDoc.setFieldName(tblDocument.getLkpDocumentType().getDocumentTypeName());
                tblMerchantDoc.setFieldValue(tblDocument.getDocumentPath());

            }
        }
        return tblMerchantDoc;
    }


    @Override
    public String updateNadraRecord(UpdateNadraRecord updateNadraRecord, Request jsonRequest, String header, BigDecimal loggedUserDetail) throws NoSuchAlgorithmException, ParseException {
        String response = null;

        if (updateNadraRecord.getType().equals("D")) {
            NadraVerificationRequest nadraVerificationRequest = new NadraVerificationRequest();
            nadraVerificationRequest.setMobileNumber(updateNadraRecord.getMobileNumber());
            nadraVerificationRequest.setCnic(updateNadraRecord.getCnic());

            Date cnicIssuenceDate = new SimpleDateFormat(Constants.cnicIssuenceDateFormat).parse(updateNadraRecord.getCnicIssuenceDate());
            nadraVerificationRequest.setCnicIssuanceDate(cnicIssuenceDate);
            nadraVerificationRequest.setEmail(updateNadraRecord.getEmail());
            nadraVerificationRequest.setUpdate(Constants.Y);
            jsonRequest.setPayLoad(nadraVerificationRequest);

            Response nadraVerificationResp = genericCommonService.sendRequestAndGetResponse(jsonRequest, header, Constants.nadrabioverisysUrl);
            if (nadraVerificationResp != null) {
                response = handleResponseCode(nadraVerificationResp.getResponseCode());
            }

        } else if (updateNadraRecord.getType().equals("B")) {
            VerifyFingerPrintRequest verifyFingerPrintRequest = new VerifyFingerPrintRequest();
            verifyFingerPrintRequest.setCnic(updateNadraRecord.getCnic());
            verifyFingerPrintRequest.setMobileNumber(updateNadraRecord.getMobileNumber());
            verifyFingerPrintRequest.setTemplateType(updateNadraRecord.getTemplateType());
            verifyFingerPrintRequest.setAreaName(updateNadraRecord.getAreaName());
            verifyFingerPrintRequest.setCustomerFpData(updateNadraRecord.getCustomerFpData());
            Long resp = tblCustomerRepo.checkForBvsExemption(encryptSHA256(updateNadraRecord.getCnic()));
            // temporary set Bvs Exempted
//            resp = Long.valueOf(3);
            if (resp != null) {
                response = ResponseCodeConstants.BVS_EXEMPTED;
            } else {
                jsonRequest.setPayLoad(verifyFingerPrintRequest);
                Response verifyFingerPrintResp = genericCommonService.sendRequestAndGetResponse(jsonRequest, header, inappbvsUrl);
                if (verifyFingerPrintResp != null) {
                    response = handleResponseCode(verifyFingerPrintResp.getResponseCode());

                } else {
                    response = ResponseCodeConstants.HOST_DOWN;
                }
            }

        }
        return response;
    }

    @Override
    public String updateBvsStatusByMobileNumber(String mobileNumber, String bvsStatus, BigDecimal userId) throws NoSuchAlgorithmException {
        String response = "";
        TblCustomer tblCustomer= tblCustomerRepo.findByMobileNumber(encryptSHA256(mobileNumber));
        if (tblCustomer != null) {
            tblCustomer.setUpdateindex(tblCustomer.getUpdateindex()!=null?tblCustomer.getUpdateindex().add(new BigDecimal(1)):BigDecimal.ONE);
            tblCustomer.setLastupdateuser(userId);
            tblCustomer.setLastupdatedate(new Date());
            tblCustomer.setBvsDate(new Date());
            tblCustomer.setBvs(bvsStatus);
            tblCustomerRepo.saveAndFlush(tblCustomer);
            response = ResponseCodeConstants.SUCCESS;


        } else {
            response = ResponseCodeConstants.ACCOUNT_NOT_EXIST;
        }
        return response;
    }
}

















