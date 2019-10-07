package com.own.space.data;

import com.own.space.domain.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    private static int START_SEQ = 1000;

    public static int USER_VLAD_ID = START_SEQ;
    public static int USER_VASYA_ID = START_SEQ+1;

    public static User USER_VLAD = new User(USER_VLAD_ID,"vlad","cheremisinvladw@gmail.com","password");
    public static User USER_VASYA = new User(USER_VASYA_ID,"vasya","vasya@mail.com","yavasya");

    public static void assertMatch(User actual,User expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"registered");
    }
    public static void assertMatch(Iterable<User> actual,User... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<User> actual,Iterable<User> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
    }

    public static User createUserWithNullName() {
        return new User(null,new Date(),"cheremisinvladw@gmail.com","password");
    }

    public static User createUserWithNullEmail() {
        return new User("vlad",new Date(),null,"password");
    }

    public static User createUserWithShortName() {
        return new User("v",new Date(),"cheremisinvladw@gmail.com","password");
    }

    public static User createUserWithLongName() {
        return new User(RandomStringUtils.random(31,true,false),new Date(),null,"password");
    }

    public static User createUserWithBlankEmail() {
        return new User(RandomStringUtils.random(20,true,false),new Date(),"    ","password");
    }

    public static User createUserWithLongEmail() {
        return new User(RandomStringUtils.random(20,true,false),new Date(),RandomStringUtils.random(101,true,false),"password");
    }

    public static User createUserWithNullDate() {
        return new User(RandomStringUtils.random(20,true,false),null,RandomStringUtils.random(30,true,false),"password");
    }

    public static User createUserWithBlankPassword() {
        return new User(RandomStringUtils.random(20,true,false),new Date(),"cheremisinvladw@gmail.com","   ");
    }

    public static User createUserWithShortPassword() {
        return new User(RandomStringUtils.random(20,true,false),new Date(),"cheremisinvladw@gmail.com","pass");
    }

    public static User createUserWithLongPassword() {
        return new User(RandomStringUtils.random(20,true,false),new Date(),"cheremisinvladw@gmail.com",RandomStringUtils.random(101,true,false));

    }

    public static User createWithAlreadyExistedEmail() {
        return new User(1,"vlad2","cheremisinvladw@gmail.com","password2");
    }
}
