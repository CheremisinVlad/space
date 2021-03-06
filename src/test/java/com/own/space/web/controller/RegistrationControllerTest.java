package com.own.space.web.controller;

import com.own.space.config.SecurityConfig;
import com.own.space.service.UserService;
import com.own.space.service.impls.UserServiceImpl;
import com.own.space.util.UserUtil;
import com.own.space.web.payload.UserTo;
import com.own.space.web.util.JsonUtils;
import com.own.space.web.util.exceptions.EmailExistsException;
import com.own.space.web.util.exceptions.UsernameExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {SecurityConfig.class,RegistrationController.class,WebTestConfiguration.class})
public class RegistrationControllerTest {
    private static final String URL = "/registration";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService mockService;


    @Test
    public void register_blankUserTo_shouldFailAndReturn400() throws Exception{
        mvc.perform(post(URL))
                .andExpect(status()
                        .is(400));
    }
    @Test
    public void register_existedUsername_shouldFailAndReturn400() throws Exception{
        UserTo existed = new UserTo();
        existed.setUsername("vlad");
        existed.setEmail("vlad@mail.com");
        existed.setPassword("password");

        doThrow(UsernameExistException.class)
                .when(mockService)
                .create(UserUtil.transferUserTransferObjectToUser(existed));

        mvc.perform(post(URL)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(JsonUtils.jsonFromObj(existed)))
                    .andExpect(status().is(400))
                    .andExpect(jsonPath("message").value("username already exist"));
    }
    @Test
    public void register_existedEmail_shouldFailAndReturn400() throws Exception{
        UserTo existed = new UserTo();
        existed.setUsername("vlad");
        existed.setEmail("vlad@mail.com");
        existed.setPassword("password");

        doThrow(EmailExistsException.class)
                .when(mockService)
                .create(UserUtil.transferUserTransferObjectToUser(existed));

        mvc.perform(
                post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.jsonFromObj(existed)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("message").value("email address already exist"));
    }
    @Test
    public void register_existedEmail_shouldSucceedAndReturn201() throws Exception{
        UserTo newUser = new UserTo();
        newUser.setUsername("vlad");
        newUser.setEmail("vlad@mail.com");
        newUser.setPassword("password");

        doReturn(UserUtil.transferUserTransferObjectToUser(newUser)).when(mockService)
                .create(UserUtil.transferUserTransferObjectToUser(newUser));

        mvc.perform(
                post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.jsonFromObj(newUser)))
                .andExpect(status().isCreated());
    }

}