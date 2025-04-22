package com.zindigi.account_migration.repo.app;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.zindigi.account_migration.util.app.Utility.generateRandomNumberForAccountNumber;

import com.mfs.commonservice.dto.Request;
import com.mfs.commonservice.dto.Response;
import com.mfs.commonservice.model.LkpAccountType;
import com.mfs.commonservice.model.LkpCurrency;
import com.mfs.commonservice.util.AbstractApi;
import com.mfs.commonservice.util.ResponseCodeConstants;
import com.zindigi.account_migration.config.app.ConstantsApp;
import com.zindigi.account_migration.model.TblAccountModel;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import com.zindigi.account_migration.util.app.Utility;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;



/**
 * Implements Service Implementation for Domain CRUD API's.
 *
 * @author Shahzad Sadiq
 * @since 29/09/2023
 */

@Service
//@RequiredArgsConstructor

public class CommonServiceAppImpl extends AbstractApi implements CommonServiceApp {


    @Value("${moduleId}")
    private String moduleId;

    @Autowired
    private ResponseService responseService;


    public  String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }


    public String convertObjecttoJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getResponseFromRestApiUsingWebClient(String url,String headerName,String headerValue,Object requestBody) {
        Object responseBody = null;
        WebClient webClient = WebClient.create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(headerName, headerValue);
        try {
            responseBody = webClient.post()
                    .uri(url)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .body(BodyInserters.fromObject(requestBody))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

        }catch (Exception e){
            responseBody=e;
        }finally {
            return responseBody;
        }
    }



    public Request convertStringToRequestObjectDataAPP(String data) throws JsonProcessingException {
        JSONObject jsonData = new JSONObject(data);
        ObjectMapper objectMapper = new ObjectMapper();
        Request readValueToString = objectMapper.readValue(jsonData.get(Constants.requestData).toString(),Request.class);
        return readValueToString;
    }

    public ResponseEntity<Response> convertStringToResponseObjectAPP(Response response, String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // check response code and set http status accordingly
        if (code.equals(moduleId+ResponseCodeConstants.SUCCESS)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (handleResponseCode(response.getResponseCode()).equals(ResponseCodeConstants.SUCCESS)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }else if (code.equals(moduleId+ ConstantsApp.API_DOWN_CODE)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        }
        else if (response.getMessage().equals(ResponseCodeConstants.ACCOUNT_CREATED_SUCESSFULLY)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(ResponseCodeConstants.RECORD_ALREADY_EXIST)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(ResponseCodeConstants.ACCOUNT_ALREADY_EXIST)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (code.equals(ResponseCodeConstants.RECORD_NOT_FOUND)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(ResponseCodeConstants.INVALID_TOKEN)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.UNAUTHORIZED);
        } else if (response.getMessage().equals(ResponseCodeConstants.ACCOUNT_PARKED_FOR_APPROVAL)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }
        else if(response.getMessage().contains(Constants.ACTION_TAKEN_SCUESSFULLY)){
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public String getAccountNumber(String currencyCode, String accountTypeCode) {
        LkpCurrency lkpCurrency = responseService.getLkpCurrencyByCode(currencyCode);
        String accountNumber = "";
        if (lkpCurrency != null) {
            String currencyISOCode = lkpCurrency.getIsoCode();
            LkpAccountType lkpAccountType = responseService.getLkpAccountTypeByCode(accountTypeCode);
            if (lkpAccountType !=null) {
                String accTypeCode = lkpAccountType.getAccountTypeCode();
                String randomNumber = generateRandomNumberForAccountNumber();
                Integer numberForModulus = Integer.valueOf(currencyISOCode) + Integer.valueOf(accountTypeCode) + Integer.valueOf(randomNumber);
                Integer modulus = numberForModulus % 10;
                String finalModulus = String.format("%02d", modulus);
                accountNumber = currencyISOCode + accountTypeCode + randomNumber + finalModulus;
                TblAccountModel tblAccountModel = responseService.getTblAccountByAccountNumber(accountNumber);
                if (tblAccountModel != null) {
                    getAccountNumber(currencyCode, accTypeCode);
                }
            } else {
                accountNumber = ResponseCodeConstants.UNSUCCESSFUL;
            }
        } else {
            accountNumber = ResponseCodeConstants.UNSUCCESSFUL;
        }
        return accountNumber;
    }


}
