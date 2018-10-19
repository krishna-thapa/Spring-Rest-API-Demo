package com.krishna.api.controller;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.exception.getException;
import com.krishna.api.modle.ApiResponse;
import com.krishna.api.repository.AccountsRepository;
import com.krishna.api.service.IGetSystemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(path = "/orderSystem")
public class BankSystemMainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankSystemMainController.class);

    @Autowired
    IGetSystemDetails getSystemDetails;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    getException getException;

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
    @GetMapping(path = "/allAccounts")
    public @ResponseBody ApiResponse getAllAccounts(){
        // This returns a JSON or XML of all the accounts that are in DB
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setRespObj(accountsRepository.findAll());
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping(path = "/account/{customerId}")
    public @ResponseBody ApiResponse getCustomerAccount(@PathVariable("customerId") Integer customerId){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setRespObj(accountsRepository.findByCustomerId(customerId));
            apiResponse.setResponseCode(200);
        }catch (Exception e){
            getException.createException(e);
            //LOGGER.warn(e.getMessage());
            //handleException(apiResponse,e);
        }
        return apiResponse;
    }

    /**
     * This method is common method, used to handle Exception
     * @param
     * @param
     *
     */
    private void handleException(ApiResponse apiResponse, Exception e) {
        LOGGER.warn("exception occurred" + e);
        apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
        apiResponse.setDescription(e.getMessage());
    }
}
