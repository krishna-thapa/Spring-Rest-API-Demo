package com.krishna.api.dao;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.dao.rowMapper.CustomerMapper;
import com.krishna.api.exception.getException;
import com.krishna.api.modle.ApiResponse;
import com.krishna.api.modle.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class getSystemDetailsDAO implements IGetSystemDetailsDAO{

    @Autowired
    @Qualifier("krishnaJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public static final String GET_ALL_CUSTOMERS = "SELECT * from customer";

    @Override
    public ApiResponse getCustomerDetailsDao() throws getException {
        ApiResponse apiResp = new ApiResponse();
        List<Customers> customers;
        try{
            customers = jdbcTemplate.query(GET_ALL_CUSTOMERS, new CustomerMapper());
            apiResp.setResponseCode(SystemConstants.RES_CODE);
            apiResp.setRespObj(customers);
        }catch (Exception e){
            apiResp.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResp.setDescription(e.toString());
        }
        return apiResp;
    }
}
