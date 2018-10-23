package com.krishna.api.dao;

import com.krishna.api.modle.ApiResponse;
import com.krishna.api.modle.CustomerDetails;

public interface ICustomerBankDetailsDAO {
    public ApiResponse getCustomerDetailsDao();
    public ApiResponse createNewcustomer(CustomerDetails customerDetails);
}
