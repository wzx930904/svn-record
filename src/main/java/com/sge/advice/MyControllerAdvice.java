package com.sge.advice;

import com.sge.entity.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

/**
 * Created by wzx on 2021/12/14.
 */
@ControllerAdvice
public class MyControllerAdvice  {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public BaseResult<BusinessException> gloableException(HttpServletResponse response,BusinessException ex) {
        BaseResult<BusinessException> errorResult = new BaseResult<>();
        errorResult.setSuccess(false);
        errorResult.setMessage(ex.getErrorMessage());
        errorResult.setResult(ex);
        return errorResult;
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    public BaseResult<NoSuchElementException> gloableException(HttpServletResponse response,NoSuchElementException ex) {
        BaseResult<NoSuchElementException> errorResult = new BaseResult<>();
        errorResult.setSuccess(false);
        errorResult.setMessage("记录不存在");
        errorResult.setResult(ex);
        return errorResult;
    }
}
