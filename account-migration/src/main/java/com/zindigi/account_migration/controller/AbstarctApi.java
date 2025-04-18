package com.zindigi.account_migration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zindigi.account_migration.dto.*;
import com.zindigi.account_migration.model.LkpAccountType;
import com.zindigi.account_migration.model.LkpCurrency;
import com.zindigi.account_migration.model.TblAccount;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;

@RestControllerAdvice
public class AbstarctApi {

    @Autowired
    private Environment env;

    @Value("${moduleId}")
    public String moduleId;

    @Autowired
    public ResponseService responseService;

    public String getCurrentMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length >= 3) {
            return stackTrace[2].getMethodName();
        } else {
            return Constants.UNKNOWN_METHOD;
        }
    }

    public Request convertStringToRequestObjectData(String data) throws JsonProcessingException {
        JSONObject jsonData = new JSONObject(data);
        ObjectMapper objectMapper = new ObjectMapper();
        Request readValueToString = objectMapper.readValue(jsonData.get(Constants.requestData).toString(), Request.class);
        return readValueToString;
    }
    //this method is used to park logs to kafka
    @Async
    public String logs(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response resp) throws HttpClientErrorException {
        RequestKafka requestKafka = setLogsRequestFromPostApis(endPoint, logLevel, className, methodName, packageDetails, request, message, resp);
        String url = env.getProperty(Constants.logsurl).toString();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put(Constants.accept, Constants.applicationJson);
        Map<String, Object> postParam = new HashMap<String, Object>();
        headers.setAll(headerMap);
        postParam.put(Constants.securityVariable, requestKafka.getSecurity());
        postParam.put(Constants.payloadVariable, requestKafka.getPayload());
        postParam.put(Constants.indexNameVariable, Constants.configuration);
        return getResponseFromPostAPI(headerMap, postParam, url);
    }


    public BigDecimal setUpdateIndex(BigDecimal value) {
        return value == null ? BigDecimal.ONE : value.add(BigDecimal.ONE);
    }

    public <T> boolean isNullOrEmpty(T input) {
        return input == null || (input instanceof String && ((String) input).isEmpty()) ||
                (input instanceof List && ((List<?>) input).isEmpty()) ||
                (input instanceof Map && ((Map<?, ?>) input).isEmpty());

    }

    public Response getResponseFromPostAPIData(Map headerMap, Object postParam, String url) throws HttpClientErrorException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.setAll(headerMap);
        HttpEntity<?> request = new HttpEntity<>(postParam, headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<?> response = null;
        try {
            response = restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        } finally {

            HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
            if (entitystatus.value() == 200) {
                String entityResponse = (String) response.getBody();
                if (entityResponse != null && !entityResponse.isEmpty()) {

                    return new Gson().fromJson(entityResponse, Response.class);
                } else {
                    return null;
                }
            } else if (entitystatus.value() == 500) {
                Response response1 = new Response();
                response1.setResponseCode(Constants.hostDownCode);
                response1.setMessage(Constants.hostDown);
                String entityResponse = (String) response.getBody();
                if (entityResponse != null && !entityResponse.isEmpty()) {
                    response1.setMessage(new Gson().fromJson(entityResponse, Response.class).getMessage());
                }
                return response1;
            } else {
                return null;
            }
        }
    }


    public RequestKafka setLogsRequestFromPostApis(String endPoint, String logLevel, String className, String methodName, String packageDetails, Request request, String message, Response response) {
        RequestKafka requestKafka = new RequestKafka();
        PayloadKafka payloadKafka = new PayloadKafka();
        String serviceName = env.getProperty(Constants.serviceNameVar).toString();
        payloadKafka.setDateTime(new Date().toString());
        payloadKafka.setEndpoint(endPoint);
        payloadKafka.setClassName(className);
        payloadKafka.setMethodName(methodName);
        payloadKafka.setLoggingLevel(logLevel);
        if (response.getResponseCode() != null && response.getResponseCode() != "") {
            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(response));
        } else {
            payloadKafka.setPayloadService(Constants.kafkaDataVar + convertObjecttoJson(request));
        }
        payloadKafka.setMessage(message);
        payloadKafka.setServiceName(serviceName);
        payloadKafka.setPackageName(packageDetails);
        payloadKafka.setLoggerID(Constants.kafkaPidVar + System.getProperty(Constants.kafkaPidVar));
        if (response.getResponseCode() != null && response.getResponseCode() != "") {
            requestKafka.setSecurity(new Security());
        } else {
            requestKafka.setSecurity(request.getSecurity());
        }
        requestKafka.setPayload(payloadKafka);
        return requestKafka;
    }

    //this method is used to convert json to string
    public String convertObjecttoJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public String getResponseFromPostAPI(Map headerMap, Object postParam, String url) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.setAll(headerMap);
        HttpEntity<Object> request = new HttpEntity<>(postParam, headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<String> response;

        try {
            response = restTemplate.postForEntity(url, request, String.class);
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx) here
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx) here
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }

        HttpStatus entityStatus = (HttpStatus) response.getStatusCode();
        String entityResponse = response.getBody();

        if (entityStatus.is2xxSuccessful()) {
            if (entityResponse != null && !entityResponse.isEmpty()) {
                return entityResponse;
            } else {
                return Constants.unhandleException;
            }
        } else {
            return entityResponse;
        }
    }

    //this method is used to set connection time and read time when call external apis
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(60000);
        clientHttpRequestFactory.setReadTimeout(60000);
        return clientHttpRequestFactory;
    }


//    public Map<String, Object> createPostParamBackOffice(Object data) {
//        // Create a new HashMap object to store the post parameters
//        Map<String, Object> postParam = new HashMap<>();
//
//        // Convert the data object to JSON and store it in the "data" parameter
//        postParam.put("data", convertObjectToJson(data));
//
//        // Return the populated postParam object
//        return postParam;
//    }


    public RequestData createPostParamBackOffice(Request data) {
        RequestData requestData = new RequestData();
        requestData.setData(data);
        return requestData;
}



    public String convertObjectToJson(Object object) {
        // Create an instance of ObjectMapper, which is a part of the Jackson library used for JSON serialization and deserialization
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert the given object to a JSON string using the ObjectMapper
            // The writerWithDefaultPrettyPrinter() method configures the ObjectMapper to format the JSON string with indentation for improved readability

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

            // Return the JSON string
            return jsonString;
        } catch (Exception e) {
            // If an exception occurs during the conversion, print the stack trace for debugging purposes
            e.printStackTrace();

            // Return null to indicate that the conversion failed
            return null;
        }
    }

    public Map<String, String> createHeaderMapBackOffice(String token) {
        // Create a new HashMap object to store the headers
        Map<String, String> headerMap = new HashMap<>();

        // Set the "content-type" header to "application/json"
        headerMap.put("content-type", "application/json");

        // Set the "accept" header to "application/json"
        headerMap.put("accept", "application/json");
        //Set the "Authorization" Token
        headerMap.put("Authorization",token);

        // Return the populated headerMap object
        return headerMap;
    }
    public String convertCharactersToAscii(String x) {

        byte[] bytes = x.getBytes(StandardCharsets.US_ASCII);
        List<Integer> result = new ArrayList<>();   // convert bytes to ascii
        for (byte aByte : bytes) {
            int ascii = (int) aByte;                // byte -> int
            result.add(ascii);
        }
        String finalResult = result.toString().replace("[", "");
        finalResult = finalResult.replace("]", "");
        finalResult = finalResult.replace(",", "");
        finalResult = finalResult.replace(" ", "");
        return finalResult;

    }

    public String genrateCheckDigit(String x) {
        String result;
        long sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum = sum + Long.valueOf(x.charAt(i));
        }
        sum = sum % 97;
        sum = 98 - sum;
        if (sum < 10) {
            result = "0" + sum;
        } else {
            result = String.valueOf(sum);
        }
        return result;
    }

    public String getAccountNumber(String currencyName, String accountTypeName) {
        LkpCurrency lkpCurrency = responseService.getLkpCurrency(currencyName);
        String accountNumber = "";
        if (lkpCurrency != null) {
            String currencyISOCode = lkpCurrency.getIsoCode();
            LkpAccountType lkpAccountType = responseService.getLkpAccountType(accountTypeName);
            if (lkpAccountType != null) {
                String accountTypeCode = lkpAccountType.getAccountTypeCode();
                String randomNumber = generateRandomNumberForAccountNumber();
                Integer numberForModulus = Integer.valueOf(currencyISOCode) + Integer.valueOf(accountTypeCode) + Integer.valueOf(randomNumber);
                Integer modulus = numberForModulus % 10;
                String finalModulus = String.format("%02d", modulus);
                accountNumber = currencyISOCode + accountTypeCode + randomNumber + finalModulus;
                TblAccount tblAccount = responseService.getTblAccountByAccountNumber(accountNumber);
                if (tblAccount != null) {
                    getAccountNumber(currencyName, accountTypeName);
                }
            } else {
                accountNumber = Constants.FAILED;
            }
        } else {
            accountNumber = Constants.FAILED;
        }
        return accountNumber;
    }

    public String generateRandomNumberForAccountNumber() {

        int length = Integer.valueOf(9);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public String generatePinSalt() {

//        logs(Constants.generateRrnNumber, Constants.logInfo,  this.getClass().getSimpleName(), Constants.generateRrnNumber, this.getClass().getPackageName(), new Request(), Constants.callingMethodInfo,null);

        int length = Integer.valueOf(4);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }

        //       logs(Constants.generateRrnNumber, Constants.logInfo,  this.getClass().getSimpleName(), Constants.generateRrnNumber, this.getClass().getPackageName(), new Request(), Constants.endingMethodInfo,null);

        return new String(digits);
    }

    public String generateIban(String accountNumber) {
        String bankCode = convertCharactersToAscii("JSBL");
        String countryCode = convertCharactersToAscii(convertCharactersToAscii("PK"));
        String checkDigit = genrateCheckDigit(bankCode + accountNumber + countryCode + "00");
        String iban = "PK" + checkDigit + "JSBL" + accountNumber;
        return iban;

    }
    public ResponseEntity<Response> convertStringToResponseObject(Response response, String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

//        // check response code and set http status accordingly

        if (code.equals(Constants.successCode)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(Constants.success)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }else if (response.getMessage().equals(Constants.SUCCESS)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().contains(Constants.accountUpdatedto)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().contains(Constants.accountAlreadyUpdatedto)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(Constants.accountCreatedSucessfully)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().equals(Constants.recordExist)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(Constants.accountAlreadyExist)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (code.equals(Constants.recordNotFoundCode)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        } else if (response.getMessage().equals(Constants.invalidToken)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.UNAUTHORIZED);
        } else if (response.getMessage().equals(Constants.ACCOUNT_PARKED_FOR_APPROVAL)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        } else if (response.getMessage().contains(Constants.ACTION_TAKEN_SCUESSFULLY)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }else if (response.getMessage().equals(Constants.OTP_SENT_TO_MOBILE_NUMBER) ||response.getMessage().equals(Constants.OTP_SENT_TO_EMAIL)) {
            return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }



}
