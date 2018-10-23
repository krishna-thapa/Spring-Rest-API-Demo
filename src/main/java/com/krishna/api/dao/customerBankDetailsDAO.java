package com.krishna.api.dao;

import com.krishna.api.constants.SystemConstants;
import com.krishna.api.dao.rowMapper.CustomerMapper;
import com.krishna.api.modle.ApiResponse;
import com.krishna.api.modle.CustomerDetails;
import com.krishna.api.modle.Customers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class customerBankDetailsDAO implements ICustomerBankDetailsDAO {

    @Autowired
    @Qualifier("krishnaJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert jdbcInsertTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(customerBankDetailsDAO.class);

    public static final String GET_ALL_CUSTOMERS = "SELECT * from customer";

    @Override
    public ApiResponse getCustomerDetailsDao(){
        ApiResponse apiResp = new ApiResponse();
        List<Customers> customers;
        try{
            customers = jdbcTemplate.query(GET_ALL_CUSTOMERS, new CustomerMapper());
            apiResp.setResponseCode(SystemConstants.RES_CODE);
            apiResp.setRespObj(customers);
        }catch (Exception e){
            apiResp.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResp.setDescription(SystemConstants.ERR_RES_DATABASE + " "+ e.getMessage());
        }
        return apiResp;
    }

    @Override
    public ApiResponse createNewcustomer(CustomerDetails customerDetails) {
        ApiResponse apiResponse = new ApiResponse();
        LOGGER.info("Inside the createNewcustomer method.");
        try{
            LOGGER.info("Insert in the table Customer");
            Number customerIdGen = insertIntoCustomer(customerDetails);
            LOGGER.info("Insert in the table Business");
            insertIntoBusiness(customerIdGen,customerDetails);
            LOGGER.info("Insert in the table INDIVIDUAL");
            insertIntoindividual(customerIdGen, customerDetails);
            apiResponse.setResponseCode(SystemConstants.RES_CODE);
            apiResponse.setDescription("Success on creating new customer");
        }catch(Exception e){
            apiResponse.setResponseCode(SystemConstants.DB_ERROR_CODE);
            apiResponse.setDescription(SystemConstants.ERR_RES_DATABASE+" "+ e.getMessage());
        }
        return apiResponse;
    }

    public Number insertIntoCustomer(CustomerDetails customerDetails){
        jdbcInsertTemplate = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsertTemplate.withTableName("CUSTOMER").usingGeneratedKeyColumns("CUST_ID");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ADDRESS",customerDetails.getCustomerAddress());
        parameters.put("CITY", customerDetails.getCustomerCity());
        parameters.put("CUST_TYPE_CD", customerDetails.getCustomerTypeCode());
        parameters.put("FED_ID", customerDetails.getCustomerFedCode());
        parameters.put("POSTAL_CODE", customerDetails.getCustomerPostalCode());
        parameters.put("STATE", customerDetails.getCustomerState());
        return jdbcInsertTemplate.executeAndReturnKey(parameters);
    }

    public void insertIntoBusiness(Number customerIdGen, CustomerDetails customerDetails){
        jdbcInsertTemplate = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsertTemplate.withTableName("BUSINESS");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("INCORP_DATE", customerDetails.getIncorpDate());
        parameters.put("NAME", customerDetails.getBusinessName());
        parameters.put("STATE_ID", customerDetails.getStateId());
        parameters.put("CUST_ID", customerIdGen);
        jdbcInsertTemplate.execute(parameters);
    }

    public void insertIntoindividual( Number customerIdGen, CustomerDetails customerDetails){
        jdbcInsertTemplate = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsertTemplate.withTableName("INDIVIDUAL");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("BIRTH_DATE", customerDetails.getBirthDate());
        parameters.put("FIRST_NAME", customerDetails.getFirstName());
        parameters.put("LAST_NAME", customerDetails.getLastName());
        parameters.put("CUST_ID", customerIdGen);
        jdbcInsertTemplate.execute(parameters);
    }
}
