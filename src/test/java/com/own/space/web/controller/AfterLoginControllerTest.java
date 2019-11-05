package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.data.DirectoryTestData;
import com.own.space.data.RecordTestData;
import com.own.space.data.UrlTestData;
import com.own.space.data.UserTestData;
import com.own.space.domain.Directory;
import com.own.space.domain.Record;
import com.own.space.domain.Url;
import com.own.space.service.BlockService;
import com.own.space.util.exceptions.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;

import java.util.List;

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.RecordTestData.*;
import static com.own.space.data.UrlTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WithMockUser
@WebMvcTest
@ContextConfiguration(classes = {SecurityConfig.class,AfterLoginController.class,WebTestConfiguration.class})
public class AfterLoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService<Directory> directoryService;

    @MockBean
    private BlockService<Url> urlService;

    @MockBean
    private BlockService<Record> recordService;


    @Test
    public void getSpace_withCorrectUserId_shouldSucceed() throws Exception {
        when(directoryService.getAllMain(USER_VLAD_ID))
                .thenReturn(List.of(DIRECTORY1_VLAD));
        when(recordService.getAllMain(USER_VLAD_ID))
                .thenReturn(List.of(RECORD2_VLAD));
        when(urlService.getAllMain(USER_VLAD_ID))
                .thenReturn(List.of(URL2_VLAD));

        mvc.perform(get("/space/{userId}",USER_VLAD_ID))
                .andDo(print())
                .andExpect(jsonPath("$.urls.[0].id",is(URL2_VLAD_ID)))
                .andExpect(jsonPath("$.urls.[0].userId",is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.urls.[0].parentId",is(0)))
                .andExpect(jsonPath("$.urls.[0].url",is("https://github.com")))
                .andExpect(jsonPath("$.urls.[0].description",is("it's null url")))
                .andExpect(jsonPath("$.records.[0].id",is(RECORD2_VLAD_ID)))
                .andExpect(jsonPath("$.records.[0].userId",is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.records.[0].parentId",is(0)))
                .andExpect(jsonPath("$.records.[0].name",is("main record")))
                .andExpect(jsonPath("$.records.[0].description",is("test content")))
                .andExpect(jsonPath("$.records.[0].content",is("test is everything for anyone")))
                .andExpect(jsonPath("$.directories.[0].id",is(DIRECTORY1_VLAD_ID)))
                .andExpect(jsonPath("$.directories.[0].userId",is(USER_VLAD_ID)))
                .andExpect(jsonPath("$.directories.[0].parentId",is(0)))
                .andExpect(jsonPath("$.directories.[0].name",is("patterns")))
                .andExpect(jsonPath("$.userId",is(USER_VLAD_ID)));

        verify(directoryService, times(1)).getAllMain(USER_VLAD_ID);
        verify(recordService, times(1)).getAllMain(USER_VLAD_ID);
        verify(urlService, times(1)).getAllMain(USER_VLAD_ID);
        verifyNoMoreInteractions(recordService);
        verifyNoMoreInteractions(directoryService);
        verifyNoMoreInteractions(urlService);
    }

    @Test
    public void getSpace_withIncorrectUserId_shouldFail() throws Exception {
        when(directoryService.getAllMain(1))
                .thenThrow(NotFoundException.class);
        when(recordService.getAllMain(1))
                .thenThrow(NotFoundException.class);
        when(urlService.getAllMain(1))
                .thenThrow(NotFoundException.class);

        mvc.perform(get("/space/{userId}",1))
                .andExpect(status().isNotFound());
        verify(directoryService, times(1)).getAllMain(1);
        verify(recordService, never()).getAllMain(1);
        verify(urlService, never()).getAllMain(1);
        verifyNoMoreInteractions(directoryService);
    }
}