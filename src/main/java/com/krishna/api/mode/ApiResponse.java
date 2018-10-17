/*******************************************************************************
 * Copyright 2018 Jaguar Land Rover Ltd. all rights reserved.
 ******************************************************************************/
package com.krishna.api.mode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ApiResponse {

	private Integer responseCode;
	private String description;
	private Object respObj;

}
