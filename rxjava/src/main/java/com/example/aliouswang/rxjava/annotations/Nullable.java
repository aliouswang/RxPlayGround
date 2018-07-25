package com.example.aliouswang.rxjava.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by aliouswang on 2018/7/24.
 */

@Documented
@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
public @interface Nullable { }
