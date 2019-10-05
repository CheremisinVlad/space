package com.own.space.data;

import com.own.space.domain.User;

import java.util.Arrays;

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
}
