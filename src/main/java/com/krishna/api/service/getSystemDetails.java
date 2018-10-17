package com.krishna.api.service;

import com.krishna.api.dao.IGetSystemDetailsDAO;
import com.krishna.api.mode.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getSystemDetails implements IGetSystemDetails{

    @Autowired
    IGetSystemDetailsDAO getSystemDetailsDAO;

    @Override
    public ApiResponse getCustomerDetails (){
        return getSystemDetailsDAO.getCustomerDetailsDao();
    }
}
