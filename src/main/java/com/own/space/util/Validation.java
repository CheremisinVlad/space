package com.own.space.util;

import com.own.space.domain.AbstractBaseEntity;
import com.own.space.util.exceptions.NotFoundException;

import java.util.Objects;

public class Validation {
    private Validation(){

    }

    public static void checkNew(AbstractBaseEntity entity){
        if(!entity.isNew()){
            throw new IllegalArgumentException(entity + " must be new  (id=null)");
        }
    }
    public static <T> T checkNotFoundWithId(T obj,int id) throws NotFoundException {
        return checkNotFound(obj,"id" + id);
    }

    private static <T> T checkNotFound(T obj, String message) {
        if (Objects.nonNull(obj)) {
            return obj;
        }else{
            throw new NotFoundException(message);
        }
    }
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }
}
