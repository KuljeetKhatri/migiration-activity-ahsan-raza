package com.zindigi.account_migration.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.zindigi.account_migration.controller.AbstarctApi;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.*;
import com.zindigi.account_migration.repo.*;
import com.zindigi.account_migration.service.RaastServices;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(rollbackOn = Exception.class)
public class RaastServiceImpl extends AbstarctApi implements RaastServices {
    @Autowired
    private TblAccountRepo tblAccountRepo;
    @Value("${get.kyc.url}")
    private String getKycUrl;
    @Autowired
    private LKpRaastAliasTypeRepo lKpRaastAliasTypeRepo;
    @Autowired
    private TblRaastResponseRepo tblRaastResponseRepo;
    @Autowired
    private TblRaastRequestRepo tblRaastRequestRepo;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private LkpProvinceRepo lkpProvinceRepo;
    @Autowired
    private TblMerchantRepo tblMerchantRepo;
    @Value("${raast.update.linking.url}")
    private String raastUpdateLinkingUrl;
    @Value("${default.alias.type}")
    private String defaultAliasType;
    @Value("${account.type.wallet}")
    private String accountTypeWallet;
    @Value("${account.level.0}")
    private String accountLevelNameZero;

    @Override
    public RaastUpdateLinkingResponse updateRaastLinking(Request jsonRequest, RaastLinkingRequest raastLinkingRequest, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        RaastUpdateLinkingRequest raastUpdateLinkingRequest = new RaastUpdateLinkingRequest();
        RaastUpdateLinkingResponse raastUpdateLinkingResponse;
        TblAccount tblAccount = tblAccountRepo.findByMobileNoHashAndAccountLevelName(raastLinkingRequest.getMobileNumber(), raastLinkingRequest.getAccountLevelName(),accountTypeWallet);
        if(tblAccount!=null) {
            GetKycRequest getKycRequest = new GetKycRequest();
            getKycRequest.setMobileNumber(raastLinkingRequest.getMobileNumber());
            getKycRequest.setAccountClassificationName(Constants.individual);
            getKycRequest.setAccountLevelName(accountLevelNameZero);
            jsonRequest = new Request();
            jsonRequest.setPayLoad(getKycRequest);
            Response getKycRespnse = getResponseFromPostAPIData(createHeaderMapBackOffice(httpServletRequest.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), getKycUrl);
//            ResponseEntity<Response> getKycResp = l0PostApis.getKyc(new Gson().toJson(postParam), httpServletRequest);
//            Response getKycRespnse = getKycResp.getBody();
            if (getKycRespnse != null && getKycRespnse.getMessage().equals(Constants.success)) {
                GetKycResponse getKycResponse = new ObjectMapper().readValue(convertObjecttoJson(getKycRespnse.getPayLoad()), GetKycResponse.class);
                if (raastLinkingRequest.isIsdeLinking()) {
                    TblRaastResponse tblRaastResponse = tblRaastResponseRepo.findByDocValue(getKycResponse.getCnic());
                    if (tblRaastResponse != null) {
                        raastUpdateLinkingRequest.setAccountId(tblRaastResponse.getRaastAccountId());
                        raastUpdateLinkingRequest.setCustomerId(tblRaastResponse.getRaastCustomerId());
                        raastUpdateLinkingRequest.setAliasId(tblRaastResponse.getAliasId());
                        raastUpdateLinkingRequest.setIsdeLinking(raastLinkingRequest.isIsdeLinking());
                    } else {
                        raastUpdateLinkingRequest = null;
                    }

                } else {

                    if(raastLinkingRequest.getType().equalsIgnoreCase(Constants.MERCHANT)){
                        TblMerchant tblMerchant=tblMerchantRepo.findByMobileNumber(raastLinkingRequest.getMobileNumber());
                        if(tblMerchant!=null){
                            raastUpdateLinkingRequest.setAddress(getKycResponse.getPermanentAddressEn().toUpperCase());
                            raastUpdateLinkingRequest.setAliasType(defaultAliasType);
                            raastUpdateLinkingRequest.setAliasValue(raastLinkingRequest.getAliasValue());
                            raastUpdateLinkingRequest.setCnic(getKycResponse.getCnic());
                            raastUpdateLinkingRequest.setCnicExpiryDate(tblAccount.getTblCustomer().getCnicExpiryDate());
                            raastUpdateLinkingRequest.setDateOfBirth(getKycResponse.getDateOfBirth());
                            raastUpdateLinkingRequest.setType(raastLinkingRequest.getType());
                            raastUpdateLinkingRequest.setIsdeLinking(raastLinkingRequest.isIsdeLinking());
                            raastUpdateLinkingRequest.setGender(tblAccount.getTblCustomer().getGender().toUpperCase());
                            raastUpdateLinkingRequest.setName(getKycResponse.getAccountTitle().toUpperCase());
                            raastUpdateLinkingRequest.setIban(tblMerchant.getTblAccount().getIban());
                            raastUpdateLinkingRequest.setBusinessName(tblMerchant.getBusinessName());
                            raastUpdateLinkingRequest.setCity(tblMerchant.getLkpCity().getCityName().toUpperCase());
                            LkpProvince lkpProvince=lkpProvinceRepo.findById(tblMerchant.getLkpCity().getProvinceId()).orElse(null);
                            raastUpdateLinkingRequest.setStateProvinceRegion(lkpProvince!=null?lkpProvince.getProvinceName():Constants.empty);
                            raastUpdateLinkingRequest.setCountry(lkpProvince.getLkpCountry().getCountryCode().toUpperCase());
                            raastUpdateLinkingRequest.setAddress(getKycResponse.getPresentAddressEn().toUpperCase());
                            raastUpdateLinkingRequest.setCurrency(tblAccount.getLkpCurrency().getCurrencyCode().toUpperCase());
                            raastUpdateLinkingRequest.setMcc("1271");

                        }else {
                            raastUpdateLinkingRequest = null;
                        }

                    }else {
                        raastUpdateLinkingRequest.setAddress(getKycResponse.getPresentAddressEn().toUpperCase());
                        raastUpdateLinkingRequest.setAliasType(defaultAliasType);
                        raastUpdateLinkingRequest.setAliasValue(raastLinkingRequest.getAliasValue());
                        raastUpdateLinkingRequest.setCnic(getKycResponse.getCnic());
                        raastUpdateLinkingRequest.setCnicExpiryDate(tblAccount.getTblCustomer().getCnicExpiryDate());
                        raastUpdateLinkingRequest.setDateOfBirth(getKycResponse.getDateOfBirth());
                        raastUpdateLinkingRequest.setType(raastLinkingRequest.getType());
                        raastUpdateLinkingRequest.setIsdeLinking(raastLinkingRequest.isIsdeLinking());
                        raastUpdateLinkingRequest.setGender(tblAccount.getTblCustomer().getGender().toUpperCase());
                        raastUpdateLinkingRequest.setName(getKycResponse.getAccountTitle().toUpperCase());
                        raastUpdateLinkingRequest.setIban(tblAccount.getIban());

                    }
                }
                if (raastUpdateLinkingRequest != null) {
                    jsonRequest.setPayLoad(raastUpdateLinkingRequest);
                    Response response = getResponseFromPostAPIData(createHeaderMapBackOffice(httpServletRequest.getHeader(Constants.AUTHORIZATION)), createPostParamBackOffice(jsonRequest), raastUpdateLinkingUrl);
                    if (response != null) {
                        raastUpdateLinkingResponse = new Gson().fromJson(convertObjecttoJson(response.getPayLoad()), RaastUpdateLinkingResponse.class);
                        if (response.getMessage().equalsIgnoreCase(Constants.success)) {
                            LkpRaastAliasType lkpRaastAliasType = lKpRaastAliasTypeRepo.findByRaastAliasTypeName(defaultAliasType);
                            if (!raastLinkingRequest.isIsdeLinking()) {
                                TblRaastRequest tblRaastRequest = tblRaastRequestRepo.findByDocValue(raastUpdateLinkingRequest.getCnic());
                                TblRaastResponse tblRaastResponse = null;
                                if (tblRaastRequest != null) {
                                    tblRaastResponse = tblRaastResponseRepo.findByRaastRequestId(tblRaastRequest.getRaastRequestId());
                                }
                                tblRaastResponse = tblRaastResponse != null ? tblRaastResponse : new TblRaastResponse();
                                tblRaastRequest = tblRaastRequest != null ? tblRaastRequest : new TblRaastRequest();
                                tblRaastRequest.setIban(raastUpdateLinkingRequest.getIban());
                                tblRaastRequest.setMcc(raastUpdateLinkingRequest.getMcc());
                                tblRaastRequest.setLkpRaastAliasType(lkpRaastAliasType);
                                tblRaastRequest.setAliasValue(raastLinkingRequest.getAliasValue());
                                tblRaastRequest.setUidType(raastLinkingRequest.getType().equalsIgnoreCase(Constants.MERCHANT) ? "MCNIC" : "CNIC");
                                tblRaastRequest.setUidValue(raastUpdateLinkingRequest.getCnic());
                                tblRaastRequest.setCurrency(raastUpdateLinkingRequest.getCurrency());
                                tblRaastRequest.setBusinessName(raastUpdateLinkingRequest.getBusinessName());
                                tblRaastRequest.setDocValidity(raastUpdateLinkingRequest.getCnicExpiryDate());
                                tblRaastRequest.setDocType(tblRaastRequest.getUidType());
                                tblRaastRequest.setDocValue(tblRaastRequest.getUidValue());
                                tblRaastRequest.setAliasId(String.valueOf(lkpRaastAliasType.getRaastAliasTypeId()));
//                                tblRaastRequest.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
                                tblRaastRequest.setCreatedate(new Date());
                                tblRaastRequest = tblRaastRequestRepo.saveAndFlush(tblRaastRequest);
                                tblRaastResponse.setRaastCustomerId(raastUpdateLinkingResponse.getCustomerId());
                                tblRaastResponse.setRaastAccountId(raastUpdateLinkingResponse.getAccountId());
                                tblRaastResponse.setAliasId(raastUpdateLinkingResponse.getAliasId());
                                tblRaastResponse.setTblRaastRequest(tblRaastRequest);
//                                tblRaastResponse.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
                                tblRaastResponse.setCreatedate(new Date());
                                tblRaastResponse = tblRaastResponseRepo.saveAndFlush(tblRaastResponse);
                            }


                            if (raastLinkingRequest.isIsdeLinking()) {
                                tblAccount.setIsRaastIdLink(Constants.not);
                            } else {
                                tblAccount.setIsRaastIdLink(Constants.Y);
                                tblAccount.setRaastAliasValue(raastLinkingRequest.getAliasValue());
                                tblAccount.setLkpRaastAliasType(lkpRaastAliasType);

                            }
//                            tblAccount.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
                            tblAccount.setLastupdatedate(new Date());
                            tblAccount = tblAccountRepo.saveAndFlush(tblAccount);

                        }else {
                            if (response.getMessage().equalsIgnoreCase(Constants.CUSTOMER_ALREADY_REGISTERED_WITH_SAME_CNIC_ON_RAAST) ||
                                    response.getMessage().equalsIgnoreCase(Constants.ALIAS_ALREADY_LINKED_WITH_OTHER_ACCOUNT) ||
                                    response.getMessage().equalsIgnoreCase(Constants.ACCOUNT_ALREADY_LINKED_TO_OTHER_ALIAS)) {
                                raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
                                raastUpdateLinkingResponse.setResponseCode(Constants.hostDownCode);
                                raastUpdateLinkingResponse.setResponseDescription(response.getMessage());
                            }else {
                                raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
                                raastUpdateLinkingResponse.setResponseCode(Constants.hostDownCode);
                                raastUpdateLinkingResponse.setResponseDescription(Constants.FAILED_TO_LINK_TO_RAAST);
                            }
                        }

                    } else {
                        raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
                        raastUpdateLinkingResponse.setResponseCode(Constants.hostDownCode);
                        raastUpdateLinkingResponse.setResponseDescription(Constants.hostDown);
                    }
                } else {
                    raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
                    raastUpdateLinkingResponse.setResponseCode(Constants.hostDownCode);
                    raastUpdateLinkingResponse.setResponseDescription(Constants.ALREADY_DELINKED);
                }
            } else {
                raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
                raastUpdateLinkingResponse.setResponseCode(getKycRespnse.getResponseCode());
                raastUpdateLinkingResponse.setResponseDescription(getKycRespnse.getMessage());

            }
        }else {
            raastUpdateLinkingResponse = new RaastUpdateLinkingResponse();
            TblResponseMessage tblResponseMessage=responseService.getResponseMessageByResponseDescr(Constants.noAccountFound);
            raastUpdateLinkingResponse.setResponseCode(tblResponseMessage.getResponseMessageCode());
            raastUpdateLinkingResponse.setResponseDescription(tblResponseMessage.getResponseMessageDescr());
        }
        return raastUpdateLinkingResponse;
    }
}
