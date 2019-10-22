package com.own.space.data;

import com.own.space.domain.Directory;
import com.own.space.domain.Url;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UrlTestData {
    private static int START_SEQ = 100_000;

    public static int URL1_VLAD_ID = START_SEQ;
    public static int URL2_VLAD_ID = START_SEQ+1;

    public static final Url URL1_VLAD = new Url(URL1_VLAD_ID,USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"https://github.com","it's null url");
    public static final Url URL2_VLAD = new Url(URL2_VLAD_ID ,USER_VLAD_ID,0,true,"https://github.com","it's null url");


    public static void assertMatch(Url actual, Url expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
    public static void assertMatch(Iterable<Url> actual,Url... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Url> actual,Iterable<Url> expected){
        assertThat(actual).isEqualTo(expected);
    }


    public static Url createUrlWithNullValue() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,null,"it's null url");
    }
    public static Url createUrlWithInvalidValue() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,"it's incorrect url","it's null url");
    }

    public static Url createUrlWithDescriptionLessThenSize() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,"https://github.com","a");
    }

    public static Url createUrlWithDescriptionMoreThenSize() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,"https://github.com",RandomStringUtils.random(51,true,true));
    }
    public static Url createUrlWithNullDescription() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"https://github.com",null);
    }

    public static Url createUrlWithNullParentId() {
        return new Url(USER_VLAD_ID,null,true,"https://github.com","alpha");
    }

    public static Url createUrlWithNullUserId() {
        return new Url(null,DIRECTORY1_VLAD_ID,true,"https://github.com","alpha");
    }

    public static Url createUrlWithNullIsMainFlag() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,null,"https://github.com","alpha");
    }

    public static Url createUrlWithTrueMainFlagAndParentIdNotZero() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,"https://github.com","alpha");
    }

    public static Url createUrlWithFalseMainFlagAndParentIdZero() {
        return new Url(USER_VLAD_ID,0,false,"https://github.com","alpha");
    }

    public static Url createUrl1ForVladWithoutId() {
        return new Url(USER_VLAD_ID,0,true,"https://github.com","alpha");
    }

    public static Url createUrl2ForVladWithoutId() {
        return new Url(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"https://github.com","alpha");
    }


    public static Url updateUrl1ForVlad() {
        return new Url(URL1_VLAD_ID,USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"https://github.com","it's updated");
    }

    public static Url createNotExisted() {
        return new Url(1,DIRECTORY1_VLAD_ID,false,"https://github.com","alpha");
    }
}
