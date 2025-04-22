package com.zindigi.account_migration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mfs.commonservice.dto.Request;
import com.zindigi.account_migration.dto.RaastLinkingRequest;
import com.zindigi.account_migration.dto.RaastUpdateLinkingResponse;


import javax.servlet.http.HttpServletRequest;

public interface RaastServices {

    RaastUpdateLinkingResponse updateRaastLinking(Request jsonRequest, RaastLinkingRequest raastLinkingRequest, HttpServletRequest httpServletRequest) throws JsonProcessingException;
}
