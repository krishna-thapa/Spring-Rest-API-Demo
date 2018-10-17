package com.krishna.api.controller;

import com.krishna.api.mode.ApiResponse;
import com.krishna.api.service.IGetSystemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderSystem")
public class systemMainController {

    @Autowired
    IGetSystemDetails getSystemDetails;

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public @ResponseBody ApiResponse getAllCustomers(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse = getSystemDetails.getCustomerDetails();
            System.out.println(apiResponse);
        }catch(Exception e){
            System.out.println(e);

        }
        return apiResponse;
    }

}
