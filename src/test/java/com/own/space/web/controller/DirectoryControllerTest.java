package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.domain.Directory;
import com.own.space.service.BlockService;
import static com.own.space.data.DirectoryTestData.*;

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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfig.class,DirectoryController.class})
@WithMockUser(username = "user1", password = "pwd", roles = "USER")
public class DirectoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlockService<Directory> service;

    @Test
    public void create_newDirectory_shouldSucceed() throws Exception {
        Directory newDirectory = createDirectory1ForVladWithoutId();

        doReturn(newDirectory)
                .when(service).save(DIRECTORY1_VLAD);

        mvc.perform(post("/api/directory")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.jsonFromObj(newDirectory)));

    }


}