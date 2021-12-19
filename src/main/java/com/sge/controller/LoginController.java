package com.sge.controller;

import com.sge.entity.BaseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wzx on 2021/12/16.
 */
@RestController
@RequestMapping("/svn/user")
@CrossOrigin
public class LoginController{

    @PostMapping("/login.htm")
    public BaseResult<String> login() {
        BaseResult<String> baseResult = new BaseResult<>();
        return baseResult;
    }
}
