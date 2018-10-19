package com.krishna.api.mode;

import lombok.Data;

@Data
public class Customers {

    private Integer customerId;
    private String customerAddress;
    private String customerCity;
    private String customerTypeCode;
    private String customerFedCode;
    private String customerPostalCode;
    private String customerState;

}
