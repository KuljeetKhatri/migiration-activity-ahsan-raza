/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zindigi.account_migration.repo.app;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.commonservice.dto.Request;
import com.mfs.commonservice.dto.Response;
import org.springframework.http.ResponseEntity;

/**
 * Interface for DomainService Implementation
 *
 * @author shahzadsadiq
 */

public interface CommonServiceApp {



    String getMethodName();

    Object getResponseFromRestApiUsingWebClient(String url, String headerName, String headerValue, Object requestBody);

    String convertObjecttoJson(Object object);

    Request convertStringToRequestObjectDataAPP(String data) throws JsonProcessingException;
    ResponseEntity<Response> convertStringToResponseObjectAPP(Response response, String code);
    String getAccountNumber(String currencyCode, String accountTypeName);

}