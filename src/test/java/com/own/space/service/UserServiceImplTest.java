package com.own.space.service;

import com.own.space.data.UserTestData;
import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.util.UserUtil;
import com.own.space.util.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static com.own.space.data.UserTestData.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository mockRepository;

    @Test
    public void create_newUser_shouldPass() {
        User newUser = new User("Ivan",new Date(),"ivan@mail.com","lolic");

        when(mockRepository.save(newUser)).thenReturn(newUser);
        when(mockRepository.getAll()).thenReturn(List.of(newUser,USER_VASYA,USER_VLAD));

        User createdUser = service.create(newUser);
        newUser.setId(createdUser.getId());
        assertMatch(newUser,createdUser);
        assertMatch(service.getAll(),createdUser,USER_VASYA,USER_VLAD);

    }

    @Test(expected = DataAccessException.class)
    public void create_withExistingEmail_shouldFail(){
        User newUser = new User("vasilii",new Date(),"vasya@mail.com","lolic");

        doThrow(new DataAccessException("emailExist") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).when(mockRepository).save(newUser);

        service.create(newUser);
    }

    @Test
    public void get_existingUser_shouldPass() {
        doReturn(USER_VLAD).when(mockRepository).getById(USER_VLAD_ID);

        User userVlad = service.get(USER_VLAD_ID);

        assertMatch(userVlad,USER_VLAD);
    }
    @Test(expected = NotFoundException.class)
    public void get_notExistingUser(){
        doThrow(NotFoundException.class).when(mockRepository).getById(1);
        service.get(1);
    }

    @Test
    public void getAll() {
        when(mockRepository.getAll()).thenReturn(List.of(USER_VASYA,USER_VLAD));

        assertMatch(service.getAll(),USER_VASYA,USER_VLAD);
    }
    @Test
    public void loadUserByUsername_withBlankName_ShouldFail(){
        Exception exception = null;
        try{
            service.loadUserByUsername(" ");
        }catch (Exception e){
            exception = e;
        }
        assertTrue(exception instanceof UsernameNotFoundException);
        verify(mockRepository,never()).getByEmail(" ");
        verify(mockRepository,never()).getByUsername(" ");
    }
    @Test
    public void loadUserByUsername_notFoundCredentials_shouldFail() {
        String incorrectUsername = "incorrect";
        when(mockRepository.getByUsername(incorrectUsername)).thenReturn(null);
        Exception exception = null;
        try {
            service.loadUserByUsername(incorrectUsername);
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof UsernameNotFoundException);
        verify(mockRepository).getByUsername(incorrectUsername);
    }
    @Test
    public void loadUserByUsername_existUsername_shouldSucceed() {
        String username= "vlad";
        when(mockRepository.getByUsername(username)).thenReturn(USER_VLAD);

        Exception exception = null;
        UserDetails userDetails = null;
        try {
            userDetails = service.loadUserByUsername(username);
        } catch (Exception e) {
            exception = e;
        }

        assertNull(exception);
        verify(mockRepository).getByUsername(username);
        verify(mockRepository, never()).getByEmail(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }
}