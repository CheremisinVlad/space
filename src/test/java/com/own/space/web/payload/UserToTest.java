package com.own.space.web.payload;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UserToTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void validate_blankUserTo_shouldFail() {
        UserTo to = new UserTo();
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(3, violations.size());
    }
    @Test
    public void validate_userToWithInvalidEmailAddress_shouldFail() {
        UserTo to = new UserTo();
        to.setEmail("badEmail");
        to.setUsername("vlad");
        to.setPassword("password");
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void validate_userToWithEmailLongerThan100_shouldFail() {
        UserTo to = new UserTo();
        String mainPart = RandomStringUtils.random(64,true,false);
        String domainPart = RandomStringUtils.random(36,true,false);
        to.setEmail(mainPart + "@" + domainPart + ".com");
        to.setUsername("vlad");
        to.setPassword("password");
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void validate_userToWithUsernameLessThan2_shouldFail() {
        UserTo to = new UserTo();
        to.setEmail("vlad@email.com");
        to.setUsername("v");
        to.setPassword("password");
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void validate_userToWithUsernameMoreThan40_shouldFail() {
        UserTo to = new UserTo();
        to.setEmail("vlad@email.com");
        to.setUsername(RandomStringUtils.random(41));
        to.setPassword("password");
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void validate_userToWithPasswordMoreThan30_shouldFail() {
        UserTo to = new UserTo();
        to.setEmail("vlad@email.com");
        to.setUsername("vladislav");
        to.setPassword(RandomStringUtils.random(31));
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void validate_userToWithPasswordLessThen6_shouldFail() {
        UserTo to = new UserTo();
        to.setEmail("vlad@email.com");
        to.setUsername("vladislav");
        to.setPassword("pass");
        Set<ConstraintViolation<UserTo>> violations = validator.validate(to);
        Assert.assertEquals(1, violations.size());
    }
}