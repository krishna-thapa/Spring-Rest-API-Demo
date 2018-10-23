package com.krishna.api.modle;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDetails {
    /* For the Customer Table*/
    private Integer customerId;
    private String customerAddress;
    private String customerCity;
    private String customerTypeCode;
    private String customerFedCode;
    private String customerPostalCode;
    private String customerState;
    /* For the Business Table*/
    private Date incorpDate;
    private String businessName;
    private String stateId;
    /* For the individual Table*/
    private Date birthDate;
    private String firstName;
    private String lastName;

}
