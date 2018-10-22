package com.krishna.api.aspects;

import com.krishna.api.modle.ActionType;
import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface InspectApi {
    String action() default ActionType.DEFAULT;
    String priority() default ActionType.INFO;
}
