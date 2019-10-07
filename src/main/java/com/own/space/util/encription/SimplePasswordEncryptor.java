package com.own.space.util.encription;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordEncryptor implements PasswordEncryptor {
    @Override
    public String encrypt(String password) {
        //todo
        return RandomStringUtils.random(password.length(),true,true);
    }
}
