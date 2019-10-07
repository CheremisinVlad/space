package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static com.own.space.data.UserTestData.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
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
        }).when(mockRepository).save(new User());

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
}