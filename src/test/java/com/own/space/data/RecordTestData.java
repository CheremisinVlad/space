package com.own.space.data;

import com.own.space.domain.Record;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RecordTestData {
    public static void assertMatch(Record actual, Record expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
    public static void assertMatch(Iterable<Record> actual,Record... expected){
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Record> actual,Iterable<Record> expected){
        assertThat(actual).isEqualTo(expected);
    }
    public static Record createRecordWithNullName() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,null,"test content","test is everything for anyone");
    }

    public static Record createRecordWithNameLessThenSize() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"a","test content","test is everything for anyone");
    }

    public static Record createRecordWithNameMoreThenSize() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,RandomStringUtils.random(51,true,true),"test content","test is everything for anyone");

    }

    public static Record createRecordWithNullDescription() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test",null,"test is everything for anyone");
    }

    public static Record createRecordWithDescriptionLessThenSize() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test","a","tests is everything for anyone");

    }

    public static Record createRecordWithDescriptionMoreThenSize() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test",RandomStringUtils.random(201,true,true),"tests is everything for anyone");

    }

    public static Record createRecordWithNullContent() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test","test content",null);

    }

    public static Record createRecordWithContentLessThenSize() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test","test content","a");
    }

    public static Record createRecordWithNullUserId() {
        return new Record(null,DIRECTORY1_VLAD_ID,false,"test","test content","tests is everything for anyone");

    }

    public static Record createRecordWithNullParentId() {
        return new Record(USER_VLAD_ID,null,false,"test","test content","tests is everything for anyone");
    }

    public static Record createRecordWithNullIsMainFlag() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,null,"test","test content","tests is everything for anyone");
    }

    public static Record createRecordWithTrueMainFlagAndParentIdNotZero() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,true,"test","test content","tests is everything for anyone");
    }

    public static Record createRecordWithFalseMainFlagAndParentIdZero() {
        return new Record(USER_VLAD_ID,0,false,"test","test content","tests is everything for anyone");
    }

    public static Record createRecord1ForVladWithoutId() {
        return new Record(USER_VLAD_ID,0,true,"test","test content","tests is everything for anyone");
    }

    public static Record createRecord2ForVladWithoutId() {
        return new Record(USER_VLAD_ID,DIRECTORY1_VLAD_ID,false,"test","test content","tests is important");
    }
}
