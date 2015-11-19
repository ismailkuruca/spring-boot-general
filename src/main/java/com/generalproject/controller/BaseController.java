package com.generalproject.controller;


import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.generalproject.filter.AuthFilter;
import com.generalproject.util.AuthUtils;
import com.nimbusds.jose.JOSEException;

public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    public Long getUserIdentifier() throws IOException{
    	 String authHeader = request.getHeader(AuthUtils.AUTH_HEADER_KEY);
    	 
         if (!StringUtils.isBlank(authHeader) && authHeader.split(" ").length == 2) {
        	 try{
        		 return Long.parseLong(AuthUtils.getSubject(authHeader));
             } catch (NumberFormatException | ParseException | JOSEException e1) {
                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AuthFilter.AUTH_ERROR_MSG);
                 e1.printStackTrace();
             }
         }
         return null;
    }
}
