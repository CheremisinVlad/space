package com.own.space.web.controller;

import com.own.space.service.UserService;
import com.own.space.util.UserUtil;
import com.own.space.web.payload.UserTo;
import com.own.space.web.results.RequestResult;
import com.own.space.web.results.ResultUtil;
import com.own.space.web.util.exceptions.EmailExistsException;
import com.own.space.web.util.exceptions.UsernameExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registration")
    public ResponseEntity<RequestResult> create(@Valid @RequestBody UserTo to){
        try{
            service.create(UserUtil.transferUserTransferObjectToUser(to));
            return ResultUtil.created();
        }catch (EmailExistsException | UsernameExistException e){
            String errorMessage = "";
            if(e instanceof EmailExistsException){
                errorMessage = "email address already exist";
            }else if(e instanceof UsernameExistException){
                errorMessage = "username already exist";
            }
            return ResultUtil.error(errorMessage);
        }
    }
}
