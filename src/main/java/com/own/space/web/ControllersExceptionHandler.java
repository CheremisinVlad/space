package com.own.space.web;

import com.own.space.util.exceptions.InconsistentDataException;
import com.own.space.util.exceptions.NotFoundException;
import com.own.space.web.results.RequestResult;
import com.own.space.web.results.ResultUtil;
import com.own.space.web.util.exceptions.EmailExistsException;
import com.own.space.web.util.exceptions.UsernameExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages={"com.own.space.web.controller"})
public class ControllersExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(InconsistentDataException.class)
    public ResponseEntity<RequestResult> handleIncorrectData(InconsistentDataException ex){
        return ResultUtil.error(ex.getMessage());
    }

    @ExceptionHandler(EmailExistsException .class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RequestResult> handleExistedEmailRegistrationError(){

        return ResultUtil.error("email address already exist");
    }

    @ExceptionHandler(UsernameExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RequestResult> handleExistedUsernameRegistrationError(){
        return ResultUtil.error("username already exist");
    }


}
