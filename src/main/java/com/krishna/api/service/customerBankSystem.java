package com.krishna.api.service;

import com.krishna.api.dao.ICustomerBankDetailsDAO;
import com.krishna.api.modle.ApiResponse;
import com.krishna.api.modle.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class customerBankSystem implements ICustomerBankSystem {

    @Autowired
    ICustomerBankDetailsDAO customerBankDetailsDAO;

    @Override
    public ApiResponse getCustomerDetails (){
        return customerBankDetailsDAO.getCustomerDetailsDao();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ApiResponse createCustomer (CustomerDetails customerDetails){
        return customerBankDetailsDAO.createNewcustomer(customerDetails);
    }

}
