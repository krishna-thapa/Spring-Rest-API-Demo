package com.krishna.api.controller;

import com.krishna.api.aspects.InspectApi;
import com.krishna.api.constants.SystemConstants;
import com.krishna.api.modle.ActionType;
import com.krishna.api.modle.ApiResponse;
import com.krishna.api.repository.AccountsRepository;
import com.krishna.api.service.IGetSystemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(path = "/bankSystem")
public class BankSystemMainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankSystemMainController.class);

    @Autowired
    IGetSystemDetails getSystemDetails;

    @Autowired
    AccountsRepository accountsRepository;

    @InspectApi(action = "Getting all the Customers", priority = ActionType.INFO)
    @GetMapping(path = "/getAllCustomers")
    public @ResponseBody ApiResponse getAllCustomers(HttpServletResponse response){
        long callStarted =  System.currentTimeMillis();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse = getSystemDetails.getCustomerDetails();
        LOGGER.info("Total time to complete getAllCustomers call: " + (System.currentTimeMillis()-callStarted) + " ms.");

        response.addDateHeader("globalTimestamp", System.currentTimeMillis());
        response.addHeader("UserId","Krishna-Thapa");
        return apiResponse;
    }

    /**
     * Get method using Hibernate and JPA to get all the fields of Account table
     * @return Api Response
     */
    @InspectApi(action = "Getting all the Accounts", priority = ActionType.INFO)
    @GetMapping(path = "/allAccounts")
    public @ResponseBody ApiResponse getAllAccounts(){
        // This returns a JSON or XML of all the accounts that are in DB
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponseCode(SystemConstants.RES_CODE);
            apiResponse.setRespObj(accountsRepository.findAll());
        }catch (Exception e){
            apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResponse.setDescription(e.getMessage());
        }
        return apiResponse;
    }

    @InspectApi(action = "Getting account as per requested customerID", priority = ActionType.NOTICE)
    @GetMapping(path = "/account/{customerId}")
    public @ResponseBody ApiResponse getCustomerAccount(@PathVariable("customerId") Integer customerId){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponseCode(200);
            apiResponse.setRespObj(accountsRepository.findByCustomerId(customerId));
        }catch (Exception e){
            apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResponse.setDescription(e.getMessage());
        }
        return apiResponse;
    }

    @InspectApi(action = "Getting Active Accounts in desc order of Last used", priority = ActionType.NOTICE)
    @GetMapping(path = "/activeAccounts")
    public @ResponseBody ApiResponse getActiveAccounts(){
        ApiResponse apiResponse = new ApiResponse();
        String status = "Active";
        try{
            apiResponse.setResponseCode(200);
            apiResponse.setRespObj(accountsRepository.findActiveAccounts(status));
        }catch (Exception e){
            apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResponse.setDescription(e.getMessage());
        }
        return apiResponse;
    }
    /**
     * This method is common method, used to handle Exception
     * @param
     * @param
     *

    private void handleException(ApiResponse apiResponse, Exception e) {
        LOGGER.warn("exception occurred" + e);
        apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
        apiResponse.setDescription(e.getMessage());
    }*/
}
