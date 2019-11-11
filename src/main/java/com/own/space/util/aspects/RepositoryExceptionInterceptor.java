package com.own.space.util.aspects;

import com.own.space.util.exceptions.InconsistentDataException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RepositoryExceptionInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(RepositoryExceptionInterceptor.class);


    @Pointcut("within(com.own.space.repository..*)")
    private void saveUser(){

    }

    @AfterThrowing(pointcut = "saveUser()",throwing = "ex")
    private void saveUserThrows(Exception ex) throws Throwable {

        try{
            throw getRootCase(ex);
        }finally {
            LOG.debug("repository exception has occurred {}",ex.getMessage());
        }
    }

    private Throwable getRootCase(Exception ex) {
        Throwable root = ex;
        while(root!=null){
            if(root instanceof ConstraintViolationException){
                return new InconsistentDataException("user with this credentials already exist");
            }if(root instanceof org.springframework.dao.DataIntegrityViolationException){
                return new InconsistentDataException("user with this credentials already exist");
            }else if(root instanceof javax.validation.ConstraintViolationException){
                return new InconsistentDataException("data don't pass validation");
            }else if(root instanceof InconsistentDataException){
                return new InconsistentDataException("data don't pass validation");
            }else if(root instanceof IllegalArgumentException){
                return new InconsistentDataException("data don't pass validation");
            }
            root = root.getCause();
        }
        return root==null ? new Exception("Unknown exception"):new Exception("Unknown exception");

    }


}
