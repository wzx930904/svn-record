package com.sge.advice;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wzx on 2021/12/14.
 */
@Getter
@Setter
public class BusinessException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public BusinessException() {

    }

    public BusinessException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
