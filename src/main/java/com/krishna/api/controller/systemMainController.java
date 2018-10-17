package com.krishna.api.controller;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.exception.getException;
import com.krishna.api.mode.ApiResponse;
import com.krishna.api.service.IGetSystemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/orderSystem")
public class SystemMainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemMainController.class);

    @Autowired
    IGetSystemDetails getSystemDetails;

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
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
