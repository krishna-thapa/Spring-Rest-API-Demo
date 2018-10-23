package com.krishna.api.modle;

import lombok.Data;

import java.util.Date;

@Data
public class Business {
    private Date incorpDate;
    private String businessName;
    private String stateId;
    private Integer customerId;
}
