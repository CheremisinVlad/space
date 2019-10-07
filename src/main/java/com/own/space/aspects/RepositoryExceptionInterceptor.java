package com.own.space.aspects;

import com.own.space.util.exceptions.InconsistentDataException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RepositoryExceptionInterceptor {


    @Pointcut("within(com.own.space.repository..*)")
    private void saveUser(){

    }

    @AfterThrowing(pointcut = "saveUser()",throwing = "ex")
    private void saveUserThrows(Exception ex) throws Throwable {
        throw getRootCase(ex);
    }

    private Throwable getRootCase(Exception ex) {
        Throwable root = ex;
        while(root!=null){
            if(root instanceof ConstraintViolationException){
                return new InconsistentDataException("data don't unique");
            }else if(root instanceof javax.validation.ConstraintViolationException){
                return new InconsistentDataException("data don't pass validation");
            }
            root = root.getCause();
        }
        return root==null ? new Exception("Unknown exception"):new Exception("Unknown exception");

    }


}
