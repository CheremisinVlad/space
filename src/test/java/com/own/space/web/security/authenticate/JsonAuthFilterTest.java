package com.own.space.web.security.authenticate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class JsonAuthFilterTest {

    private final String AUTH_URL = "/authentications";
    private final String METHOD = "POST";

    @MockBean
    private AuthenticationManager manager;

    @Test(expected = InsufficientAuthenticationException.class)
    public void attemptAuth_nullCredentials_shouldFail() throws IOException, ServletException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest(METHOD, AUTH_URL);
        JsonAuthFilter filter = new JsonAuthFilter();
        filter.setAuthenticationManager(manager);
        filter.attemptAuthentication(mockRequest,new MockHttpServletResponse());
    }
    @Test(expected = InsufficientAuthenticationException.class)
    public void attemptAuth_invalidCredentials_shouldFail() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest(METHOD, AUTH_URL);
        request.setContent("username=incorrect&password=password".getBytes());
        JsonAuthFilter filter = new JsonAuthFilter();
        filter.setAuthenticationManager(manager);
        filter.attemptAuthentication(request, new MockHttpServletResponse());
    }
    @Test
    public void attemptAuth_withValidCredentials_shouldSucceed() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest(METHOD, AUTH_URL);
        request.setContent("{\"username\": \"vlad\", \"password\": \"password\"}".getBytes());
        JsonAuthFilter filter = new JsonAuthFilter();
        filter.setAuthenticationManager(manager);
        filter.attemptAuthentication(request, new MockHttpServletResponse());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("vlad", "password");
        Mockito.verify(manager).authenticate(token);
    }
}