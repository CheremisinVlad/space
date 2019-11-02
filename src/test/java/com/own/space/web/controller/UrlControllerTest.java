package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.data.UrlTestData;
import com.own.space.domain.Url;
import com.own.space.service.BlockService;
import com.own.space.util.exceptions.InconsistentDataException;
import com.own.space.util.exceptions.NotFoundException;
import com.own.space.web.util.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.UrlTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
@WithMockUser
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfig.class,UrlController.class,WebTestConfiguration.class})
public class UrlControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService<Url> service;

    @Test
    public void create_newUrl_shouldSucceed() throws Exception {
        Url newUrl = createUrl1ForVladWithoutId();

        doReturn(URL1_VLAD)
                .when(service).save(newUrl);

        mvc.perform(post("/urls/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(newUrl)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(URL1_VLAD_ID)))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.url", is("https://github.com")))
                .andExpect(jsonPath("$.description", is("it's null url")))
                .andExpect(jsonPath("$.parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).save(newUrl);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void create_newUrlWithIncorrectData_shouldFail() throws Exception {
       Url newUrl = createUrlWithInvalidValue();

        doThrow(InconsistentDataException.class)
                .when(service).save(newUrl);

        mvc.perform(post("/urls/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(newUrl)))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).save(newUrl);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_existedUrl_shouldSucceed() throws Exception {
        doReturn(URL1_VLAD)
                .when(service).get(URL1_VLAD_ID);

        mvc.perform(get("/urls/{id}",URL1_VLAD_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(URL1_VLAD)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(URL1_VLAD_ID)))
                .andExpect(jsonPath("$.userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.url", is("https://github.com")))
                .andExpect(jsonPath("$.description", is("it's null url")))
                .andExpect(jsonPath("$.parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).get(URL1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_NotExistedUrl_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).get(1);

        mvc.perform(get("/urls/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(UrlTestData.createNotExisted())))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).get(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void update_existedUrl_shouldSucceed() throws Exception {
        Url updated = updateUrl1ForVlad();
        doReturn(updated)
                .when(service).update(updated);

        mvc.perform(post("/urls/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void update_notExistedUrl_shouldFail() throws Exception {
        Url updated = updateUrl1ForVlad();
        doThrow(NotFoundException.class)
                .when(service).update(updated);

        mvc.perform(post("/urls/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JsonUtils.jsonFromObj(updated)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).update(updated);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void delete_existedUrl_shouldSucceed() throws Exception {
        doReturn(true)
                .when(service).delete(URL1_VLAD_ID);

        mvc.perform(post("/urls/delete/{id}",URL1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(URL1_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void delete_NotExistedUrl_shouldFail() throws Exception {
        doThrow(NotFoundException.class)
                .when(service).delete(1);

        mvc.perform(post("/urls/delete/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).delete(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void get_AllParent_shouldSucceed() throws Exception {
        doReturn(List.of(URl2_VLAD_WITH_PARENT1,URl3_VLAD_WITH_PARENT1))
                .when(service).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);

        mvc.perform(get("/urls/{userId}/{parentId}",USER_VLAD_ID,DIRECTORY1_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(URL1_VLAD_ID)))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].url", is("https://github.com")))
                .andExpect(jsonPath("$[0].description", is("url 2")))
                .andExpect(jsonPath("$[0].parentId", is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$[1].id", is(URL1_VLAD_ID)))
                .andExpect(jsonPath("$[1].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[1].url", is("https://github.com")))
                .andExpect(jsonPath("$[1].description", is("url 3")))
                .andExpect(jsonPath("$[1].parentId", is(DIRECTORY1_VLAD_ID)));

        verify(service, times(1)).getAllParent(DIRECTORY1_VLAD_ID,USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
    @Test
    public void get_AllMain_shouldSucceed() throws Exception {
        doReturn(List.of(URL2_VLAD))
                .when(service).getAllMain(USER_VLAD_ID);

        mvc.perform(get("/urls/main/{userId}",USER_VLAD_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(URL2_VLAD_ID)))
                .andExpect(jsonPath("$[0].userId", is(USER_VLAD_ID)))
                .andExpect(jsonPath("$[0].url", is("https://github.com")))
                .andExpect(jsonPath("$[0].description", is("it's null url")))
                .andExpect(jsonPath("$[0].parentId", is(0)));

        verify(service, times(1)).getAllMain(USER_VLAD_ID);
        verifyNoMoreInteractions(service);
    }
}
