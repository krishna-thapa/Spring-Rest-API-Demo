package com.krishna.api.service;

import com.krishna.api.modle.ApiResponse;
import com.krishna.api.modle.CustomerDetails;

public interface ICustomerBankSystem {

    public ApiResponse getCustomerDetails ();

    public ApiResponse createCustomer(CustomerDetails customerDetails);
}
