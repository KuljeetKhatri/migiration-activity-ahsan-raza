package com.zindigi.account_migration.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.zindigi.account_migration.controller.AbstarctApi;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.*;
import com.zindigi.account_migration.service.L0Services;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import com.zindigi.account_migration.util.CustomDataNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service
@Transactional(rollbackOn = Exception.class)
public class L0ServiceImpl extends AbstarctApi implements L0Services {

    @Autowired
    TblAccountRepo tblAccountRepo;
    @Autowired
    TblNadraRepo tblNadraRepo;
    @Value("${kms.kyc.decrption.url}")
    private String kmsKycUrl;
    @Value("${petro.account.type}")
    private String petroAccountType;
    @Value("${petro.account.status}")
    private String petroAccountStatus;
    @Autowired
    private LkpSegmentRepo lkpSegmentRepo;
    @Value("${kms.url}")
    private String kmsUrl;
    @Autowired
    private LkpChannelRepo lkpChannelRepo;

    @Autowired
    private LkpAccountStatusRepo lkpAccountStatusRepo;

    @Autowired
    private LkpAccountTypeRepo lkpAccountTypeRepo;

    @Autowired
    private LkpCurrencyRepo lkpCurrencyRepo;

    @Autowired
    private LkpRegistrationTypeRepo lkpRegistrationTypeRepo;

    @Autowired
    private LkpAccountLevelRepo lkpAccountLevelRepo;

    @Autowired
    private TblCustomerRepo tblCustomerRepo;
    @Autowired
    private TblBlacklistCnicRepo tblBlacklistCnicRepo;


    @Autowired
    private TblDocumentRepo tblDocumentRepo;

    @Autowired
    private TblAccountDailyStatsRepo accountDailyStatsRepo;

    @Autowired
    private LkpDocStatusRepo lkpDocStatusRepo;
    @Value("${account.level.minor.0}")
    private String accountLevelNameMinorZero;

    @Autowired
    private TblUltraCustomerRepo tblUltraCustomerRepo;
    @Autowired
    private LkpStatusRepo lkpStatusRepo;
    @Autowired
    private TblAddressRepo tblAddressRepo;
    @Autowired
    private LkpCityRepo lkpCityRepo;
    @Autowired
    private TblGlCodeCombinationRepo tblGlCodeCombinationRepo;
    @Autowired
    private LkpAddressTypeRepo lkpAddressTypeRepo;
    @Autowired
    private ResponseService responseService;

    @Value("${account.level.0}")
    private String accountLevelNameZero;
    @Autowired
    private TblAppUserRepo tblAppUserRepo;
    @Autowired
    private LkpAppUserTypeRepo lkpAppUserTypeRepo;
    @Autowired
    private TblDeviceInfoRepo tblDeviceInfoRepo;

    @Override
    public CheckAccountStatusResponse checkAccountStatus(CheckAccountStatusRequest checkAccountStatusRequest) {
        List<TblAccount> tblAccount = tblAccountRepo.getAccountByMobileNumberOrCnic("checkAccountStatusRequest.getCnic()", checkAccountStatusRequest.getMobileNumber());
        CheckAccountStatusResponse checkAccountStatusResponse = null;
        if (tblAccount != null && !tblAccount.isEmpty()) {
            for (TblAccount tblAccount1 : tblAccount) {
                if (tblAccount1.getLkpAccountType().getAccountTypeName().equals(Constants.accountLevelTypeWallet)) {
                    checkAccountStatusResponse = new CheckAccountStatusResponse();
                    checkAccountStatusResponse.setCnic(checkAccountStatusRequest.getCnic());
                    checkAccountStatusResponse.setMobileNumber(checkAccountStatusRequest.getMobileNumber());
                    checkAccountStatusResponse.setStatus(tblAccount1.getLkpAccountStatus().getAccountStatusCode());
                }
            }

        }
        return checkAccountStatusResponse;
    }

    @Override
    public String checkAccountExistance(String cnic, String mobileNumber) {
        TblAccount tblAccount = tblAccountRepo.getAccountByMobileNumberOrCnicAndAccountType(cnic, mobileNumber, Constants.accountLevelTypeWallet);
        return tblAccount == null ? Constants.not : Constants.yes;
    }

    @Override
    public TblNadra getTblNadraByCnic(String cnic) {
        TblNadra tblNadra = tblNadraRepo.fetchNadraDetailsBycnic(cnic);
        return tblNadra;
    }

    @Override
    public LkpSegment getLkpSegment(String segmentName) {
        LkpSegment lkpSegment = lkpSegmentRepo.findBySegmentsName(segmentName);
        return lkpSegment;
    }


    @Override
    public LkpChannel getChannelByName(String channelName) {
        LkpChannel lkpChannel = lkpChannelRepo.getLkpChannelByChannelName(channelName);
        return lkpChannel;
    }

    @Override
    public String saveTblUltraCustomer(CreateAccountRequest createAccountRequest, BigDecimal userId) {
        String response = null;
        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumberHash(createAccountRequest.getMobileNumber());
        if (tblCustomer != null) {
            Object bform = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(createAccountRequest.getMobileNumber(), Constants.bformpic);
            Object cnicFront = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(createAccountRequest.getMobileNumber(), Constants.docCnicFront);
            Object cnicBack = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(createAccountRequest.getMobileNumber(), Constants.docCnicBack);
            if (bform != null || cnicBack != null || cnicFront != null) {
                Object parentCnicFront = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(createAccountRequest.getMobileNumber(), Constants.parentCnicFront);
                if (parentCnicFront != null) {
                    Object parentCnicBack = tblDocumentRepo.fetchDocumentDetailsByMobileNumber(createAccountRequest.getMobileNumber(), Constants.parentCnicBack);
                    if (parentCnicBack != null) {
                        TblUltraCustomer tblUltraCustomer = tblUltraCustomerRepo.findByMobileNunber(createAccountRequest.getMobileNumber());
                        if (tblUltraCustomer == null) {
                            tblUltraCustomer = new TblUltraCustomer();
                        }
                        LkpAccountLevel lkpAccountLevel = lkpAccountLevelRepo.findByAccountLevelName(accountLevelNameMinorZero, Constants.individual);
                        if (lkpAccountLevel != null) {
                            LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUS_PENDING);
                            if (lkpStatus != null) {
                                List<TblUltraCustomerFatca> tblUltraCustomerFatcaList = new ArrayList<>();
                                tblUltraCustomer.setTblCustomer(tblCustomer);
                                tblUltraCustomer.setAccountLevelId(new BigDecimal(lkpAccountLevel.getAccountLevelId()));
                                tblUltraCustomer.setLkpStatus(lkpStatus);
                                LkpDocStatus lkpDocStatus = lkpDocStatusRepo.findByDocStatusCode(Constants.STATUS_PENDING);
                                tblUltraCustomer.setLkpDocStatus(lkpDocStatus);
                                tblUltraCustomer.setEmail(tblCustomer.getEmail());
                                TblDocument tblDocument = new TblDocument();
                                Object[] row = (Object[]) parentCnicFront;
                                tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
                                tblUltraCustomer.setTblDocument9(tblDocument);
                                tblDocument = new TblDocument();
                                row = (Object[]) parentCnicBack;
                                tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
                                tblUltraCustomer.setTblDocument10(tblDocument);
                                if (bform != null) {
                                    row = (Object[]) bform;
                                    tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
                                    tblUltraCustomer.setTblDocument8(tblDocument);
                                }
                                if (cnicFront != null) {
                                    tblDocument = new TblDocument();
                                    row = (Object[]) cnicFront;
                                    tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
                                    tblUltraCustomer.setTblDocument2(tblDocument);
                                }
                                if (cnicBack != null) {
                                    tblDocument = new TblDocument();
                                    row = (Object[]) cnicBack;
                                    tblDocument.setDocumentId(((BigDecimal) row[2]).longValue());
                                    tblUltraCustomer.setTblDocument6(tblDocument);
                                }
                                tblUltraCustomer.setLkpCountry1(null);
                                tblUltraCustomer.setLkpCountry2(null);
                                LkpStatus lkpStatus1 = new LkpStatus();
                                lkpStatus1.setStatusId(1);
                                tblUltraCustomer.setLkpStatus(lkpStatus);
                                tblUltraCustomer.setStatus("M");
                                tblUltraCustomer.setCreateuser(userId);
                                tblUltraCustomer.setCreatedate(new Date());
                                tblUltraCustomer = tblUltraCustomerRepo.saveAndFlush(tblUltraCustomer);
                                if (tblUltraCustomer != null) {
                                    response = Constants.success;
                                } else {
                                    response = Constants.recordNotSaved;
                                }
                            } else {
                                response = Constants.statusNotFound;
                            }
                        } else {
                            response = Constants.invalidAccountLevel;
                        }
                    } else {
                        response = Constants.parentcnicBackMissing;
                    }
                } else {
                    response = Constants.parentcnicFrontMissing;
                }
            } else {
                response = Constants.bFormMissing;
            }
        } else {
            response = Constants.noAccountFound;
        }
        return response;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)

    public String createAccount(SaveCustomerRequest saveCustomerRequest, TblCustomer tblCustomer, TblAccount tblAccount, TblNadra tblNadra, DeviceInfo deviceInfo, HttpServletRequest httpServletRequest) throws ParseException, CustomDataNotFoundException {


        TblCustomer tblCustomer1 = tblCustomerRepo.findByMobileNumberHash(saveCustomerRequest.getMobileNumber());
        TblAccount tblAccount1 = tblAccountRepo.findByMobileNoHashAndAccountTypeName(saveCustomerRequest.getMobileNumber(), Constants.accountLevelTypeWallet);
        String response;
        int result = 0;
        tblCustomer.setCreatedate(new Date());
        if (tblNadra.getExpiryDate() != null && !tblNadra.getExpiryDate().isEmpty()) {
            Date expiryDate = new SimpleDateFormat(Constants.expiryDatePattern).parse(tblNadra.getExpiryDate());
            result = expiryDate.compareTo(new Date());
        } else {
            result = 0;
        }

        if (result > 0 || result == 0) {
            if (tblCustomer1 == null) {
                KmsResponse kmsResponse = new KmsResponse();
                kmsResponse.setCnic(tblNadra.getCnicHash());
                kmsResponse.setReserved1(tblNadra.getEmail());
                kmsResponse.setReserved2(tblNadra.getName());
                kmsResponse.setReserved3("");
                kmsResponse.setReserved4("");
                kmsResponse.setReserved5("");
                kmsResponse.setReserved6("");
                kmsResponse.setReserved7("");
                kmsResponse.setReserved8("");
                kmsResponse.setReserved9("");
                kmsResponse.setReserved10("");
                Request request1 = new Request();
                request1.setPayLoad(kmsResponse);
                String kmsReqResp = getResponseFromPostAPI(createHeaderMapBackOffice(httpServletRequest.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(request1), kmsKycUrl);
                Response response1 = new Gson().fromJson(kmsReqResp, Response.class);
                kmsResponse = new Gson().fromJson(new Gson().toJson(response1.getPayLoad()), KmsResponse.class);
                tblCustomer.setCustomerId(Long.parseLong(saveCustomerRequest.getCustomerId()));
                tblCustomer.setEmailHash(kmsResponse.getReserved1());
                tblCustomer.setFullNameHash(kmsResponse.getReserved2());
                tblCustomer.setEmail(tblNadra.getEmail());
                tblCustomer.setFatherHusbandName(tblNadra.getFatherHusbandNameEn());
                tblCustomer.setCnic(tblNadra.getCnic());
                tblCustomer.setBvs(Constants.not);
                tblCustomer.setCnicIssuanceDate(tblNadra.getIssuanceDate().toString());
                tblCustomer.setFullName(tblNadra.getName());
                tblCustomer.setMobileNo(tblNadra.getMobileNo());
                tblCustomer.setBirthPlace(tblNadra.getBirthPlace());
                tblCustomer.setCreateuser(BigDecimal.valueOf(1));
                tblCustomer.setMotherMaidenName(tblNadra.getMotherNameEn());
                tblCustomer.setCnicExpiryDate(tblNadra.getExpiryDate());
                tblCustomer.setDob(tblNadra.getDateOfBirth());
                tblCustomer.setCnicIssuanceDate(tblNadra.getIssuanceDate().toString());
                tblCustomer.setGender(tblNadra.getGender());
                tblCustomer.setMotherNameVerified(Constants.not);
                tblCustomer.setClsVerified(Constants.not);
                tblCustomer.setMpinRegistered(Constants.not);
                tblCustomer.setNadraVerified(Constants.yes);
                tblCustomer.setIsActive(Constants.yes);
                tblCustomer.setIsFiler(Constants.not);
                LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
                tblCustomer.setLkpStatus(lkpStatus);
                tblCustomer = tblCustomerRepo.saveAndFlush(tblCustomer);
            } else {
                tblCustomer = tblCustomer1;
            }

            if (tblCustomer != null) {
                List<TblAddress> tblAddresslist = tblAddressRepo.getAllTblCustomerId(tblCustomer.getCustomerId());
                TblAddress tblAddress = null;
                if (tblAddresslist == null || tblAddresslist.size() == 0) {
                    tblAddress = new TblAddress();
                    LkpCity lkpCity = lkpCityRepo.findByCityNameInUpper(tblNadra.getCity());
                    tblAddress.setLkpCity(lkpCity);
                    LkpAddressType lkpAddressType = lkpAddressTypeRepo.findByAddressTypeCode(Constants.PRESENT_ADDRESS_CODE);
                    tblAddress.setTblCustomer(tblCustomer);
                    tblAddress.setLkpAddressType(lkpAddressType);
                    tblAddress.setFullAddress(tblNadra.getPresentAddress());
                    tblAddress.setCreateuser(BigDecimal.valueOf(1L));
                    tblAddress.setCreatedate(new Date());
                    tblAddress = tblAddressRepo.saveAndFlush(tblAddress);

                } else {
                    tblAddress = tblAddresslist.get(0);
                }
                if (tblAddress != null) {

                    if (tblAccount1 == null) {
                        tblAccount.setTblCustomer(tblCustomer);
                        TblGlCodeCombination tblGlCodeCombination = tblGlCodeCombinationRepo.findTblGlCodeCombinationByAccountLevelName(tblAccount.getLkpAccountLevel().getAccountLevelName());
                        if (tblGlCodeCombination != null) {
                            tblAccount.setAccountId(Long.parseLong(saveCustomerRequest.getAccountId()));
                            tblAccount.setActualBalance(BigDecimal.valueOf(0.0));
                            tblAccount.setAvailableBalance(BigDecimal.valueOf(0.0));
                            tblAccount.setSecurityQuestionRetries(new BigDecimal(0));
                            tblAccount.setTblGlCodeCombination(tblGlCodeCombination);
                            tblAccount.setIsRaastIdLink(Constants.not);
                            //generating accountNo
//                            String accountNumber = getAccountNumber(saveCustomerRequest.getCurrencyCode(), Constants.accountLevelTypeWallet);
                            tblAccount.setAccountNo(saveCustomerRequest.getAccountNo());
                            //setting iban
                            tblAccount.setIban(generateIban(saveCustomerRequest.getMobileNumber()));
                            tblAccount.setCreatedate(new Date());
                            tblAccount.setIsActive("Y");
                            tblAccount.setIsGlAccount("N");
                            LkpStatus lkpStatus = lkpStatusRepo.findBystatusCode(Constants.STATUSAPPROVED);
                            tblAccount.setLkpStatus(lkpStatus);
                            LkpAccountNature lkpAccountNature = new LkpAccountNature();
                            lkpAccountNature.setAccountNatureId(BigDecimal.ONE);
                            tblAccount.setAppScreenStatus(Constants.appScreenAccountCreatedStatus);
                            tblAccount.setLkpAccountNature(lkpAccountNature);
                            tblAccount.setAppScreenStatus(Constants.appScreenAccountCreatedStatus);
                            tblAccount.setCreateuser(BigDecimal.valueOf(1L));
                            tblAccount = tblAccountRepo.saveAndFlush(tblAccount);
                            if (tblAccount.getLkpAccountLevel().getAccountLevelName().equalsIgnoreCase(accountLevelNameZero)) {
//                                createPetroAccount(saveCustomerRequest, tblAccount);
                            }
                        } else {
                            tblAccount = tblAccount1;
                        }

                        if (tblAccount != null) {
                            TblAccountDailyStat tblAccountDailyStat = null;
                            List<TblAccount> tblAccountDailyStats = accountDailyStatsRepo.getAllByAccountId(tblAccount.getAccountId());
                            if (tblAccountDailyStats == null || tblAccountDailyStats.size() == 0) {
                                tblAccountDailyStat = new TblAccountDailyStat();
                                tblAccountDailyStat.setBalanceDisbursed(BigDecimal.valueOf(0));
                                tblAccountDailyStat.setBalanceReceived(BigDecimal.valueOf(0));
                                tblAccountDailyStat.setEndDayBalance(BigDecimal.valueOf(0));
                                tblAccountDailyStat.setStartDayBalance(BigDecimal.valueOf(0));
                                tblAccountDailyStat.setTblAccount(tblAccount);
                                tblAccountDailyStat.setCreateuser(BigDecimal.valueOf(1L));
                                tblAccountDailyStat.setCreatedate(new Date());
                                tblAccountDailyStat.setUpdateindex(BigDecimal.valueOf(0));
                                tblAccountDailyStat = accountDailyStatsRepo.saveAndFlush(tblAccountDailyStat);
                            }
                            if (tblAccountDailyStat != null) {
                                TblAppUser tblAppUser = tblAppUserRepo.appUserByMobileNumber(saveCustomerRequest.getMobileNumber());
                                if (tblAppUser == null) {
                                    LkpAppUserType lkpAppUserType = lkpAppUserTypeRepo.findById(Long.valueOf(1)).orElse(null);
                                    if (lkpAppUserType != null) {

                                        tblAppUser = new TblAppUser();
                                        tblAppUser.setAppUserId(Long.parseLong(saveCustomerRequest.getAppUserId()));
                                        tblAppUser.setLkpAppUserType(lkpAppUserType);
                                        tblAppUser.setUsername(tblCustomer.getMobileNo());
                                        tblAppUser.setPasswordPin(generatePinSalt());
                                        tblAppUser.setCreatedate(new Date());
                                        tblAppUser.setCreateuser(BigDecimal.valueOf(1L));
                                        tblAppUser.setTblCustomer(tblCustomer);
                                        tblAppUser = tblAppUserRepo.save(tblAppUser);
                                    }
                                    if (tblAppUser != null) {
                                        TblDeviceInfo tblDeviceInfo = new TblDeviceInfo();
                                        if (saveCustomerRequest.getBulkAccountOpening().equalsIgnoreCase(Constants.N)) {
                                            tblDeviceInfo.setTblAppUser(tblAppUser);
                                            tblDeviceInfo.setAdditionalParams(deviceInfo.getAdditionalParams().toString());
                                            tblDeviceInfo.setAndroidApiLevel(deviceInfo.getAndroidApiLevel());
                                            tblDeviceInfo.setAndroidUuid(deviceInfo.getUuid());
                                            tblDeviceInfo.setAppVersionCode(deviceInfo.getAppVersionCode());
                                            tblDeviceInfo.setAppVersionName(deviceInfo.getAppVersionName());
                                            tblDeviceInfo.setCreatedate(new Date());
                                            tblDeviceInfo.setCreateuser(BigDecimal.valueOf(1L));
                                            tblDeviceInfo.setDensity(deviceInfo.getDensity());
                                            tblDeviceInfo.setDeviceId(deviceInfo.getDeviceId());
                                            tblDeviceInfo.setDeviceModel(deviceInfo.getDeviceModel());
                                            tblDeviceInfo.setDeviceType(deviceInfo.getDeviceType());
                                            tblDeviceInfo.setGooglePlayServicesVersion(deviceInfo.getGooglePlayServiceVersion());
                                            tblDeviceInfo.setIpAddress(deviceInfo.getIpAddress());
                                            tblDeviceInfo.setNetworkOperator(deviceInfo.getNetworkOperator());
                                            tblDeviceInfo.setNetworkType(deviceInfo.getNetworkType());
                                            tblDeviceInfo.setOsVersion(deviceInfo.getOsVersion());
                                            tblDeviceInfo.setResolution(deviceInfo.getResolution());
                                            tblDeviceInfo.setScreenSize(deviceInfo.getScreenSize());
                                            tblDeviceInfo.setUpdateindex(BigDecimal.valueOf(0));
                                            tblDeviceInfo.setLatitude(deviceInfo.getLatitude());
                                            tblDeviceInfo.setLongitude(deviceInfo.getLongitude());
                                            tblDeviceInfo = tblDeviceInfoRepo.saveAndFlush(tblDeviceInfo);
                                        }

                                        if (tblDeviceInfo != null || saveCustomerRequest.getBulkAccountOpening().equalsIgnoreCase(Constants.Y)) {
                                            response = Constants.accountCreatedSucessfully;
                                        } else {
                                            response = Constants.recordNotSaved;
                                        }


                                    } else {
                                        response = Constants.recordNotSaved;


                                    }
                                } else {
                                    response = Constants.recordNotSaved;
                                }
                            } else {
                                response = Constants.recordNotSaved;

                            }
                        } else {
                            response = Constants.recordNotSaved;

                        }
                    } else {
                        response = Constants.recordNotFound;

                    }
                } else {
                    response = Constants.recordNotSaved;

                }

            } else {
                response = Constants.recordNotSaved;

            }
        } else {
            response = Constants.cnicExpired;

        }

        return response;
    }

    @Transactional
    @Override
    public TblNadra saveTblNardra(TblNadra tblNadra) throws JsonProcessingException {
        TblNadra tblNadra1 = tblNadra;
        return tblNadraRepo.save(tblNadra1);



    }

    private void createPetroAccount(SaveCustomerRequest saveCustomerRequest, TblAccount tblAccount) throws CustomDataNotFoundException {
        TblAccount petroAccount = new TblAccount();
        BeanUtils.copyProperties(tblAccount, petroAccount);

        String accountNumber = getAccountNumber(saveCustomerRequest.getCurrencyCode(), Constants.accountLevelTypeWallet);
        petroAccount.setAccountNo(accountNumber);
        petroAccount.setIban(generateIban(accountNumber));
        petroAccount.setIsTAndCAccepted(Constants.N);
        LkpAccountType lkpAccountType = lkpAccountTypeRepo.findByAccountTypeName(petroAccountType);
        if (lkpAccountType == null) {
            throw new CustomDataNotFoundException(Constants.invalidAccountType, Constants.invalidAccountType);
        }
        LkpAccountStatus lkpAccountStatus = lkpAccountStatusRepo.findByAccountStatusName(petroAccountStatus);
        if (lkpAccountStatus == null) {
            throw new CustomDataNotFoundException(Constants.invalidAccountStatus, Constants.invalidAccountStatus);
        }
        petroAccount.setLkpAccountType(lkpAccountType);
        petroAccount.setLkpAccountStatus(lkpAccountStatus);
        petroAccount.setAccountId(0l);
        tblAccountRepo.saveAndFlush(petroAccount);
    }


    @Override
    public LkpRegistrationType getLkpRegistrationType(String registrationTypeName) {
        LkpRegistrationType lkpRegistrationType = lkpRegistrationTypeRepo.findByRegistrationTypeName(registrationTypeName);
        return lkpRegistrationType;
    }

    @Override
    public LkpAccountLevel getLkpAccountLevel(String accountLevelName, String accountClassificationName) {
        LkpAccountLevel lkpAccountLevel = lkpAccountLevelRepo.findByAccountLevelName(accountLevelName, accountClassificationName);
        return lkpAccountLevel;
    }

    @Override
    public LkpAccountStatus getLkpAccountStatus(String accountStatusCode) {
        LkpAccountStatus lkpAccountStatus = lkpAccountStatusRepo.findByAccountStatusName(accountStatusCode);
        return lkpAccountStatus;
    }

    @Override
    public LkpAccountType getLkpAccountType(String accountLevelType) {
        LkpAccountType lkpAccountType = lkpAccountTypeRepo.findByAccountTypeName(accountLevelType);
        return lkpAccountType;
    }

    @Override
    public LkpCurrency getLkpCurrency(String currencyCode) {
        LkpCurrency lkpCurrency = lkpCurrencyRepo.findByCurrencyName(currencyCode);
        return lkpCurrency;
    }

    @Override
    public String checkBlacklist(CheckBlacklistingRequest checkBlacklistingRequest) {
        int result = tblBlacklistCnicRepo.checkCnicBlacklisting(Long.valueOf(checkBlacklistingRequest.getCnic()));
        String response;
        if (result == 0) {
            response = Constants.N;
        } else {
            response = Constants.Y;
        }
        return response;
    }
}
