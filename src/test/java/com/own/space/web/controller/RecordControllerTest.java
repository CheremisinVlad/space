package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.data.RecordTestData;
import com.own.space.domain.Record;
import com.own.space.service.BlockService;
import com.own.space.util.exceptions.InconsistentDataException;
import com.own.space.util.exceptions.NotFoundException;
import com.own.space.web.util.JsonUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;

import java.util.List;

import static com.own.space.data.DirectoryTestData.DIRECTORY1_VLAD_ID;
import static com.own.space.data.RecordTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WithMockUser
@ContextConfiguration(classes = {SecurityConfig.class,RecordController.class,WebTestConfiguration.class})
@WebMvcTest
public class RecordControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService<Record> service;

    @Test
    public void create_newRecord_shouldSucceed() throws Exception {
        Record newRecord = createRecord1ForVladWithoutId();

        doReturn(RECORD1_VLAD)
                .when(service).save(newRecord);

        mvc.perform(post("/records/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(newRecord)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(RECORD1_VLAD_ID)))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("test content")))
                .andExpect(jsonPath("$.content", is("test is everything for anyone")))
                .andExpect(jsonPath("$.parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).save(newRecord);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void create_newRecordWithIncorrectData_shouldFail() throws Exception {
        Record newRecord = createRecordWithNullContent();

        doThrow(InconsistentDataException.class)
                .when(service).save(newRecord);

        mvc.perform(post("/records/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(newRecord)))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).save(newRecord);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_existedRecord_shouldSucceed() throws Exception {
        doReturn(RECORD1_VLAD)
                .when(service).get(RECORD1_VLAD_ID);

        mvc.perform(get("/records/{id}",RECORD1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(RECORD1_VLAD_ID)))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.description", is("test content")))
                .andExpect(jsonPath("$.content", is("test is everything for anyone")))
                .andExpect(jsonPath("$.parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).get(RECORD1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_NotExistedRecord_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).get(1);

        mvc.perform(get("/records/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).get(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void update_existedRecord_shouldSucceed() throws Exception {
        Record updated = updateRecord1ForVlad();
        doReturn(updated)
                .when(service).update(updated);

        mvc.perform(post("/records/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void update_notExistedRecord_shouldFail() throws Exception {
        Record updated = updateRecord1ForVlad();
        doThrow(NotFoundException.class)
                .when(service).update(updated);

        mvc.perform(post("/records/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void delete_existedRecord_shouldSucceed() throws Exception {
        doReturn(true)
                .when(service).delete(RECORD1_VLAD_ID);

        mvc.perform(post("/records/delete/{id}",RECORD1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(RECORD1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void delete_NotExistedRecord_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).delete(1);

        mvc.perform(post("/records/delete/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).delete(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_AllParent_shouldSucceed() throws Exception {
        doReturn(List.of(RECORD2_VLAD_WITH_PARENT1,RECORD3_VLAD_WITH_PARENT1))
                .when(service).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);

        mvc.perform(get("/records/{userId}/{parentId}",USER_VLAD_ID,DIRECTORY1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(RECORD2_VLAD_WITH_PARENT1_ID )))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].name", is("test 1")))
                .andExpect(jsonPath("$[0].description", is("test content 1")))
                .andExpect(jsonPath("$[0].content", is("test is everything for anyone 1")))
                .andExpect(jsonPath("$[0].parentId", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$[1].id", is(RECORD3_VLAD_WITH_PARENT1_ID )))
                .andExpect(jsonPath("$[1].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[1].name", is("test 2")))
                .andExpect(jsonPath("$[1].description", is("test content 2")))
                .andExpect(jsonPath("$[1].content", is("test is everything for anyone 2")))
                .andExpect(jsonPath("$[1].parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_AllMain_shouldSucceed() throws Exception {
        doReturn(List.of(RECORD1_VLAD))
                .when(service).getAllMain(USER_VLAD_ID);

        mvc.perform(get("/records/main/{userId}",USER_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(RECORD1_VLAD_ID)))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].description", is("test content")))
                .andExpect(jsonPath("$[0].content", is("test is everything for anyone")))
                .andExpect(jsonPath("$[0].parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).getAllMain(USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
}
