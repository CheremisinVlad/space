package com.own.space.service;

import com.own.space.SpaceApplication;
import com.own.space.domain.User;
import com.own.space.util.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.own.space.data.UserTestData.*;


@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql",config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
@ActiveProfiles(value = "dev")
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = new User("Ivan",new Date(),"ivan@mail.com","lolic");
        User createdUser = service.create(newUser);
        newUser.setId(createdUser.getId());
        assertMatch(newUser,createdUser);
        assertMatch(service.getAll(),createdUser,USER_VASYA,USER_VLAD);

    }

    @Test(expected = DataAccessException.class)
    public void createWithExistingEmail(){
        User newUser = new User("Ivan",new Date(),"vasya@mail.com","lolic");
        service.create(newUser);
    }

    @Test
    public void get() {
        User userVlad = service.get(USER_VLAD_ID);
        assertMatch(userVlad,USER_VLAD);
    }
    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        service.get(1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(),USER_VASYA,USER_VLAD);
    }
}