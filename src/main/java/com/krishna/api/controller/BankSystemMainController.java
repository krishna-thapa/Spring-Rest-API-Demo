package com.krishna.api.controller;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.exception.getException;
import com.krishna.api.mode.ApiResponse;
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

    @GetMapping(path = "/getAllCustomers")
    public @ResponseBody ApiResponse getAllCustomers(HttpServletResponse response){
        LOGGER.debug("Request received at: " + System.currentTimeMillis());
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse = getSystemDetails.getCustomerDetails();
        }catch(getException e){
            handleException(apiResponse,e);
        } finally {
            LOGGER.debug("Request completed at: " + System.currentTimeMillis());
        }

        response.addDateHeader("globalTimestamp", System.currentTimeMillis());
        response.addHeader("UserId","Krishna-Thapa");
        return apiResponse;
    }

    @GetMapping(path = "/allAccounts")
    public @ResponseBody ApiResponse getAllAccounts(){
        // This returns a JSON or XML of all the accounts that are in DB
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setRespObj(accountsRepository.findAll());
        }catch (Exception e){
            System.out.println("----krishna----"+e.getMessage());
        }
        return apiResponse;
    }

    /**
     * This method is common method, used to handle Exception
     * @param ApiResponse
     * @param getException
     *
     */
    private void handleException(ApiResponse apiResponse, Exception e) {
        LOGGER.debug("exception occurred" + e);
        apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
        apiResponse.setDescription(e.getMessage());
    }
}
