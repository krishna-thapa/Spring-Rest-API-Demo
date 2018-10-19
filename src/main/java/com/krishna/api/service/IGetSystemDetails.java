package com.krishna.api.service;

import com.krishna.api.exception.getException;
import com.krishna.api.modle.ApiResponse;

public interface IGetSystemDetails {

    public ApiResponse getCustomerDetails () throws getException;
}
