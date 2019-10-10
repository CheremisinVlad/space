package com.own.space.util.encription;

import com.own.space.util.exceptions.InconsistentDataException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordEncryptor implements PasswordEncryptor {

    private PasswordEncoder encoder;

    public SimplePasswordEncryptor(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encrypt(String password) {
        validate(password);
        return encoder.encode(password);
    }

    private void validate(String password) {
        if(password.isBlank()|| password.length()<5 || password.length()>100){
            throw new InconsistentDataException("invalid password");
        }
    }
}
