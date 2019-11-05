package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.data.DirectoryTestData;
import com.own.space.domain.Directory;
import com.own.space.service.BlockService;
import static com.own.space.data.DirectoryTestData.*;

import com.own.space.util.exceptions.InconsistentDataException;
import com.own.space.util.exceptions.NotFoundException;
import com.own.space.web.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfig.class,DirectoryController.class,WebTestConfiguration.class})
@WithMockUser(username = "user1", password = "pwd")
public class DirectoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService<Directory> service;

    @Test
    public void create_newDirectory_shouldSucceed() throws Exception {
        Directory newDirectory = createDirectory1ForVladWithoutId();

        doReturn(DIRECTORY1_VLAD)
                .when(service).save(newDirectory);

        mvc.perform(post("/directories/create")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(JsonUtils.jsonFromObj(newDirectory)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$.name", is("patterns")))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.parentId", is(0)));

        verify(service, times(1)).save(newDirectory);
        verifyNoMoreInteractions(service);
    }
 @Test
    public void create_newDirectoryWithIncorrectData_shouldFail() throws Exception {
        Directory newDirectory = DirectoryTestData.createDirectoryWithNameMoreThenSize();

        doThrow(InconsistentDataException.class)
                .when(service).save(newDirectory);

        mvc.perform(post("/directories/create")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(JsonUtils.jsonFromObj(newDirectory)))
                .andExpect(status().is4xxClientError());

        verify(service, times(1)).save(newDirectory);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_existedDirectory_shouldSucceed() throws Exception {
        doReturn(DIRECTORY1_VLAD)
                .when(service).get(DIRECTORY1_VLAD_ID);

        mvc.perform(get("/directories/{id}",DIRECTORY1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$.name", is("patterns")))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.parentId", is(0)));

        verify(service, times(1)).get(DIRECTORY1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_NotExistedDirectory_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).get(1);

        mvc.perform(get("/directories/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).get(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void update_existedDirectory_shouldSucceed() throws Exception {
        Directory updated = updateDirectory1ForVlad();
        doReturn(updated)
                .when(service).update(updated);

        mvc.perform(post("/directories/update")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void update_notExistedDirectory_shouldFail() throws Exception {
        Directory updated = updateDirectory1ForVlad();
        doThrow(NotFoundException.class)
                .when(service).update(updated);

        mvc.perform(post("/directories/update")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void delete_existedDirectory_shouldSucceed() throws Exception {
        doReturn(true)
                .when(service).delete(DIRECTORY1_VLAD_ID);

        mvc.perform(post("/directories/delete/{id}",DIRECTORY1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(DIRECTORY1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void delete_NotExistedDirectory_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).delete(1);

        mvc.perform(post("/directories/delete/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).delete(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_AllParent_shouldSucceed() throws Exception {
        doReturn(List.of(DIRECTORY2_VLAD_WITH_PARENT1,DIRECTORY3_VLAD_WITH_PARENT1))
                .when(service).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);

        mvc.perform(get("/directories/{userId}/{parentId}",USER_VLAD_ID,DIRECTORY1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(DIRECTORY2_VLAD_ID)))
                .andExpect(jsonPath("$[0].name", is("patterns")))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].parentId", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$[1].id", is(DIRECTORY3_VLAD_ID)))
                .andExpect(jsonPath("$[1].name", is("algorithms")))
                .andExpect(jsonPath("$[1].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[1].parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_AllMain_shouldSucceed() throws Exception {
        doReturn(List.of(DIRECTORY1_VLAD))
                .when(service).getAllMain(USER_VLAD_ID);

        mvc.perform(get("/directories/main/{userId}",USER_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$[0].name", is("patterns")))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].parentId", is(0)));

        verify(service, times(1)).getAllMain(USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }


}