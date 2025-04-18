package com.zindigi.account_migration.util;/*
Author Name: ahmad.raza

Project Name: usermanagement

Package Name: com.mfs.usermanagement.config

Class Name: IntegrationFilter

Date and Time:2/15/2023 3:00 PM

Version:1.0
*/


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AccountMigrationFilter implements Filter {

    private final String[] sensitiveHeaders = {"Host", "Origin", "Referer", "User-Agent"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Remove sensitive headers
        for (String header : sensitiveHeaders) {
            httpResponse.setHeader(header, null);
        }

        // Add your existing logic here
        httpResponse.setHeader(Constants.accessControlAllowOrigin, Constants.s1);
        httpResponse.setHeader(Constants.accessControlAllowMethods, Constants.s1);
        httpResponse.setHeader(Constants.accessControlAllowHeaders, Constants.s1);
        httpResponse.setHeader(Constants.accessControlAllowCredentials, Constants.s1True);
        httpResponse.setHeader(Constants.accessControlMaxAge, Constants.maxAge);

        final String contextPath = httpRequest.getRequestURL().toString();
        if (contextPath.contains(Constants.configuration)) {
            chain.doFilter(request, response);
        }
    }

}
