package com.own.space.web.controller;

import com.own.space.domain.User;
import com.own.space.service.UserService;
import com.own.space.util.UserUtil;
import com.own.space.web.payload.UserTo;

import org.springframework.http.HttpStatus;
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

    @PostMapping("/registration")
    public ResponseEntity<UserTo> create(@Valid @RequestBody UserTo to){
        User created = service.create(UserUtil.transferUserTransferObjectToUser(to));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserUtil.transferUserToUserTransferObject(created));

    }
}
