package com.zindigi.account_migration.service.Impl;

import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.dto.Error;
import com.zindigi.account_migration.model.TblResponseMessage;
import com.zindigi.account_migration.repo.TblResponseMessageRepo;
import com.zindigi.account_migration.repo.TblValidatorRepo;
import com.zindigi.account_migration.service.ValidationService;
import com.zindigi.account_migration.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn =Exception.class )

public class ValidationServiceImpl implements ValidationService {

    @Autowired
    TblValidatorRepo tblValidatorRepo;
    @Autowired
    TblResponseMessageRepo tblResponseMessageRepo;

    @Value("${account.level.0}")
    private String accountLevelNameZero;

    @Value("${account.level.merchant}")
    private String accountLevelNameMerchant;
    @Override
    public List<Error> validateCheckaccountstatus(CheckAccountStatusRequest checkAccountStatusRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        String mobileValidator = Constants.mobileNumberPattern;
        String cnicValidator = Constants.CNIC_VALIDATION_PATTERN;
        List<Object> objectList = tblValidatorRepo.getKyc(checkAccountStatusRequest.getAccountClassificationName(), checkAccountStatusRequest.getAccountLevelName());
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;
                if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR)) {
                    mobileValidator = objects[1].toString();

                }
                if (objects[0].toString().equals(Constants.CNIC_VALIDATOR)) {
                    cnicValidator = objects[1].toString();

                }
                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }
            }
            if(isNullOrEmpty(checkAccountStatusRequest.getMobileNumber()) && isNullOrEmpty(checkAccountStatusRequest.getCnic())){
                error = new Error();
                tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.INVALID_MOBILE_NO_OR_CNIC);
                error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                validationErrors.add(error);
            } if(!isNullOrEmpty(checkAccountStatusRequest.getMobileNumber()) && !checkAccountStatusRequest.getMobileNumber().matches(mobileValidator)){
                error = new Error();
                tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                validationErrors.add(error);
            } if(!isNullOrEmpty(checkAccountStatusRequest.getCnic()) && !checkAccountStatusRequest.getCnic().matches(cnicValidator)){
                error = new Error();
                tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                validationErrors.add(error);
            }


            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }

        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }


    //do
    @Override
    public List<Error> validateUpdateAccountRequestL1(UpdateAccountRequest updateAccountRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        //checking Account Level
        if (updateAccountRequest.getAccountLevelName() == null || updateAccountRequest.getAccountLevelName().equals(Constants.empty)) {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidAccountLevel);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        List<Object> objectList = tblValidatorRepo.getKyc(updateAccountRequest.getAccountClassificationName(), updateAccountRequest.getAccountLevelName());
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;
                if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    if (updateAccountRequest.getMobileNumber() == null || updateAccountRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && updateAccountRequest.getMobileNumber() != null && !updateAccountRequest.getMobileNumber().isEmpty() && updateAccountRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.CNIC_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    if (updateAccountRequest.getCnic() == null || updateAccountRequest.getCnic().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.CNIC_VALIDATOR) && updateAccountRequest.getCnic() != null && !updateAccountRequest.getCnic().isEmpty() && updateAccountRequest.getCnic().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }
            }
            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }

        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }


    //do
    @Override
    public List<Error> validateCheckBlacklistingRequest(CheckBlacklistingRequest checkBlacklistingRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        String cnicValidator = Constants.CNIC_VALIDATION_PATTERN;
        List<Object> objectList = tblValidatorRepo.getKyc(Constants.accountClassificationName, accountLevelNameZero);
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;

                if (objects[0].toString().equals(Constants.CNIC_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    if (checkBlacklistingRequest.getCnic() == null || checkBlacklistingRequest.getCnic().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.CNIC_VALIDATOR) && checkBlacklistingRequest.getCnic() != null && !checkBlacklistingRequest.getCnic().isEmpty() && checkBlacklistingRequest.getCnic().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }


            }
            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }
            if(isNullOrEmpty(checkBlacklistingRequest.getCnic()) || !checkBlacklistingRequest.getCnic().matches(cnicValidator)){
                error = new Error();
                tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                validationErrors.add(error);
            }

        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }


    @Override
    public List<Error> validateBasicUpdateAccountRequest(UpdateAccountRequest updateAccountRequest, Request jsonRequest, List<AdditionalInformation> additionalInformation) {
        List<Error> validationErrors = new ArrayList<>();
        Error error;
        TblResponseMessage tblResponseMessage;
        if (isNullOrEmpty(updateAccountRequest.getMobileNumber()) || !updateAccountRequest.getMobileNumber().matches(Constants.mobileNumberPattern)) {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }

        return validationErrors;
    }


    //do
    @Override
    public List<Error> validateUpdateAccountLevelRequest(UpdateAccountLevelRequest updateAccountLevelRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        String mobileValidator = Constants.mobileNumberPattern;
        String cnicValidator = Constants.CNIC_VALIDATION_PATTERN;
        //checking Account Level
        if (updateAccountLevelRequest.getAccountLevelName() == null || updateAccountLevelRequest.getAccountLevelName().equals(Constants.empty)) {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidAccountLevel);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        List<Object> objectList = tblValidatorRepo.getKyc(updateAccountLevelRequest.getAccountClassificationName(), updateAccountLevelRequest.getAccountLevelName());
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;
                if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    mobileValidator = objects[1].toString();
                    if (updateAccountLevelRequest.getMobileNumber() == null || updateAccountLevelRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && updateAccountLevelRequest.getMobileNumber() != null && !updateAccountLevelRequest.getMobileNumber().isEmpty() && updateAccountLevelRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.CNIC_VALIDATOR)) {
                    cnicValidator = objects[1].toString();

                }

                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }
            }
            if ((updateAccountLevelRequest.getMobileNumber() == null || updateAccountLevelRequest.getMobileNumber().matches(mobileValidator) == false) && (updateAccountLevelRequest.getCnic() == null || updateAccountLevelRequest.getCnic().matches(cnicValidator) == false)) {
                if (updateAccountLevelRequest.getCnic().matches(cnicValidator) == false) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
                if (updateAccountLevelRequest.getMobileNumber().matches(mobileValidator) == false) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }
            if (updateAccountLevelRequest.getAccountLevelName() != null && updateAccountLevelRequest.getAccountLevelName().equals(accountLevelNameMerchant)) {
                if (updateAccountLevelRequest.getBusinessName() == null || updateAccountLevelRequest.getBusinessName().isEmpty()) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidBusinessName);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
                if (updateAccountLevelRequest.getMonthlySaleExpectedCode() == null || updateAccountLevelRequest.getMonthlySaleExpectedCode().isEmpty()) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.INVALID_MONTHLY_SALE_ECPECTED_CODE);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
                if (updateAccountLevelRequest.getBusinessAddress() == null || updateAccountLevelRequest.getBusinessAddress().isEmpty()) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidAddress);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
                if (updateAccountLevelRequest.getCityCode() == null || updateAccountLevelRequest.getCityCode().isEmpty()) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.INVALID_CITY_CODE);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
                if (updateAccountLevelRequest.getBusinessTypeCode() == null || updateAccountLevelRequest.getBusinessTypeCode().isEmpty()) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.INVALID_BUSINESS_TYPE_CODE);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }


            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }

        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }


    //do
    @Override
    public List<Error> validateUltraStep1(UpdateAccountRequest updateAccountRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        String mobileValidator = Constants.mobileNumberPattern;
        String emailValidator = Constants.EMAIL_VALIDATOR;
        //checking Account Level
        if (updateAccountRequest.getAccountLevelName() == null || updateAccountRequest.getAccountLevelName().equals(Constants.empty)) {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidAccountLevel);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        List<Object> objectList = tblValidatorRepo.getKyc(updateAccountRequest.getAccountClassificationName(), updateAccountRequest.getAccountLevelName());
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;
                if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    if (updateAccountRequest.getMobileNumber() == null || updateAccountRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR) && updateAccountRequest.getMobileNumber() != null && !updateAccountRequest.getMobileNumber().isEmpty() && updateAccountRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.EMAIL_VALIDATOR) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                    if (updateAccountRequest.getEmail() == null || updateAccountRequest.getEmail().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidEmail);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                } else {
                    if (objects[0].toString().equals(Constants.EMAIL_VALIDATOR) && updateAccountRequest.getEmail() != null && !updateAccountRequest.getEmail().isEmpty() && updateAccountRequest.getEmail().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidEmail);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }
            }


            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }

        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }

    @Override
    public List<Error> getKycAttributesByAccountType(SaveCustomerRequest saveCustomerRequest, List<AdditionalInformation> additionalInformationList) {
        List<Error> validationErrors = new ArrayList<>();
        TblResponseMessage tblResponseMessage;
        Error error;
        int addParamValidator = 0;
        List<Object> objectList = tblValidatorRepo.getKyc(saveCustomerRequest.getAccountClassificationName(), saveCustomerRequest.getAccountLevelName());
        if (objectList != null && !objectList.isEmpty()) {
            validationErrors = new ArrayList<>();
            for (Object object : objectList) {
                Object[] objects = (Object[]) object;
                if (objects[0].toString().equals(Constants.MOBILE_NUMBER_VALIDATOR)) {
                    if (saveCustomerRequest.getMobileNumber() == null || saveCustomerRequest.getMobileNumber().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidMobileNumber);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.CNIC)) {
                    if (saveCustomerRequest.getCnic() == null || saveCustomerRequest.getCnic().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidCnic);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.EMAIL_VALIDATOR) && saveCustomerRequest.getEmail() != null && !saveCustomerRequest.getEmail().isEmpty()) {
                    if (saveCustomerRequest.getEmail().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidEmail);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.CNIC_ISSUANCE_DATE)) {
                    if (saveCustomerRequest.getCnicIssuanceDate() == null || saveCustomerRequest.getCnicIssuanceDate().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidIssuenceDate);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }
                if (objects[0].toString().equals(Constants.FULL_NAME_VALIDATOR)) {

                    if (saveCustomerRequest.getChannelName() == null || saveCustomerRequest.getChannelName().matches(objects[1].toString()) == false) {
                        error = new Error();
                        tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.invalidChannelName);
                        error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                        error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                        validationErrors.add(error);
                    }
                }

                for (AdditionalInformation additionalInformation : additionalInformationList) {
                    if (additionalInformation.getInfoKey() != null && !additionalInformation.getInfoKey().isEmpty() && objects[0].equals(additionalInformation.getInfoKey())) {
                        addParamValidator++;
                    }
                    if (objects[0].equals(additionalInformation.getInfoKey()) && objects[2].toString().equalsIgnoreCase(Constants.yes)) {
                        if (additionalInformation.getInfoValue() == null || additionalInformation.getInfoValue().isEmpty() || additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    } else {
                        if (objects[0].equals(additionalInformation.getInfoKey()) && additionalInformation.getInfoValue() != null && !additionalInformation.getInfoValue().isEmpty() && additionalInformation.getInfoValue().matches(objects[1].toString()) == false) {
                            error = new Error();
                            error.setErrorCode(Constants.fieldValidationCode);
                            error.setErrorDescr(Constants.INVALID_ADDITIONAL_FIELD);
                            validationErrors.add(error);
                        }
                    }
                }

            }
            //Custom Validation
            if (saveCustomerRequest.gettAndCAccepted() == null || saveCustomerRequest.gettAndCAccepted().equals(Constants.empty) || !saveCustomerRequest.gettAndCAccepted().equalsIgnoreCase(Constants.yes)) {
                error = new Error();
                tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.ACCEPT_TERMS_AND_CONDITIONS_FIRST);
                error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                validationErrors.add(error);

            }
            if (additionalInformationList.size() == 1 && additionalInformationList.get(0).getInfoKey().isEmpty()) {
            } else {
                if (additionalInformationList.size() != addParamValidator) {
                    error = new Error();
                    tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.validationFailed);
                    error.setErrorCode(tblResponseMessage.getResponseMessageCode());
                    error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
                    validationErrors.add(error);
                }
            }
        } else {
            error = new Error();
            tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(Constants.noValidatorSetFound);
            error.setErrorCode(tblResponseMessage.getResponseMessageCode());
            error.setErrorDescr(tblResponseMessage.getResponseMessageDescr());
            validationErrors.add(error);
        }
        return validationErrors;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
