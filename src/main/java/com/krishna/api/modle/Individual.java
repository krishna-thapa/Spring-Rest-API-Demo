package com.krishna.api.modle;

import lombok.Data;

import java.util.Date;

@Data
public class Individual {
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Integer customerId;

}
