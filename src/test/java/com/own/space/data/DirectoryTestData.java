package com.own.space.data;


import com.own.space.domain.AbstractBaseBlock;
import com.own.space.domain.Directory;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.own.space.data.UserTestData.USER_VASYA_ID;
import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.assertj.core.api.Assertions.assertThat;


public class DirectoryTestData{
    private static int START_SEQ = 10_000;

    public static int DIRECTORY1_VLAD_ID = START_SEQ;
    public static int DIRECTORY2_VLAD_ID = START_SEQ+1;
    public static int DIRECTORY1_VASYA_ID = START_SEQ+2;
    public static int DIRECTORY2_VASYA_ID = START_SEQ+3;

    public static Directory DIRECTORY1_VLAD = new Directory(DIRECTORY1_VLAD_ID,"patterns",USER_VLAD_ID,0);
    public static Directory DIRECTORY2_VLAD = new Directory(DIRECTORY2_VLAD_ID,"patterns",USER_VLAD_ID,DIRECTORY1_VLAD_ID);
    public static Directory DIRECTORY1_VASYA = new Directory(DIRECTORY1_VASYA_ID ,"patterns",USER_VASYA_ID,0);
    public static Directory DIRECTORY2_VASYA = new Directory(DIRECTORY2_VASYA_ID ,"patterns",USER_VASYA_ID,DIRECTORY1_VASYA_ID);

    public static void assertMatch(Directory actual, Directory expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
    public static void assertMatch(Iterable<Directory> actual,Directory... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Directory> actual,Iterable<Directory> expected){
        assertThat(actual).isEqualTo(expected);
    }
    public static Directory createDirectoryWithMainTrueAndParentIdNotZero() {
        return new Directory(DIRECTORY1_VLAD_ID,"patterns",USER_VLAD_ID,1);
    }

    public static Directory createDirectoryWithNullName() {
        return new Directory(null,USER_VLAD_ID,0);
    }

    public static Directory createDirectoryWithNameLessThenSize() {
        return new Directory("a",USER_VLAD_ID,0);
    }

    public static Directory createDirectoryWithNameMoreThenSize() {
        return new Directory(RandomStringUtils.random(31,true,true),USER_VLAD_ID,0);
    }

    public static Directory createDirectoryWithNullUserId() {
        return new Directory("vlad",null,0);
    }

    public static Directory createDirectoryWithNullParentId() {
        return new Directory("vlad",USER_VLAD_ID,null);
    }

    public static Directory createDirectoryWithNullIsMainFlag() {
        return new Directory("vlad",USER_VLAD_ID,DIRECTORY1_VLAD_ID );
    }

    public static Directory createDirectoryWithTrueMainFlagAndParentIdNotZero() {
        return new Directory("vlad",USER_VLAD_ID,DIRECTORY1_VLAD_ID );
    }

    public static Directory createDirectoryWithFalseMainFlagAndParentIdZero() {
        return new Directory("vlad",USER_VLAD_ID,0 );
    }

    public static Directory createDirectory1ForVladWithoutId() {
        return new Directory("patterns",USER_VLAD_ID,0);
    }
    public static Directory createDirectory2ForVladWithoutId() {
        return new Directory(DIRECTORY2_VLAD_ID,"patterns",USER_VLAD_ID,DIRECTORY1_VLAD_ID);
    }

    public static Directory updateDirectory1ForVlad() {
        return new Directory(DIRECTORY1_VLAD_ID,"new patterns",USER_VLAD_ID,0);
    }

    public static Directory createNotExisted() {
        return new Directory(1,"not exist",1,0);
    }

}
