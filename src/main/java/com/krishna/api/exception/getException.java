package com.krishna.api.exception;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.dao.getSystemDetailsDAO;
import com.krishna.api.modle.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class getException {

    private static final Logger LOGGER = LoggerFactory.getLogger(getSystemDetailsDAO.class);

    public ApiResponse createException(Exception e){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
        apiResponse.setDescription(e.toString());
        LOGGER.warn("Error occurred: " + e.getMessage());
        return apiResponse;
    }

}
