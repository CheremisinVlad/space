package com.own.space.repository.dataJpa;

import com.own.space.util.aspects.RepositoryExceptionInterceptor;
import com.own.space.data.UserTestData;
import com.own.space.domain.User;
import com.own.space.repository.UserRepository;
import com.own.space.util.encription.PasswordEncryptor;
import com.own.space.util.encription.SimplePasswordEncryptor;
import com.own.space.util.exceptions.InconsistentDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.own.space.data.UserTestData.*;
import static org.junit.Assert.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/import.sql",config = @SqlConfig(encoding = "UTF-8"))
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository repository;

    @TestConfiguration
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    public static class UserRepositoryTestConfig{
        @Bean
        public UserRepository userRepository(CrudUserRepository crud){
            return new UserRepositoryImpl(crud,passwordEncryptor());
        }
        @Bean
        public PasswordEncryptor passwordEncryptor(){
            return new SimplePasswordEncryptor();
        }
        @Bean
        public RepositoryExceptionInterceptor serviceAspect(){
            return new RepositoryExceptionInterceptor();
        }
    }

    @Test(expected = InconsistentDataException.class)
    public void save_userWithNullName_shouldFail() {
        User user = UserTestData.createUserWithNullName();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithShortName_shouldFail() {
        User user = UserTestData.createUserWithShortName();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithLongName_shouldFail() {
        User user = UserTestData.createUserWithLongName();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithNullEmail_shouldFail() {
        User user = UserTestData.createUserWithNullEmail();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithBlankEmail_shouldFail() {
        User user = UserTestData.createUserWithBlankEmail();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithLongEmail_shouldFail() {
        User user = UserTestData.createUserWithLongEmail();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithNullDate_shouldFail() {
        User user = UserTestData.createUserWithNullDate();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithBlankPassword_shouldFail() {
        User user = UserTestData.createUserWithBlankPassword();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithShortPassword_shouldFail() {
        User user = UserTestData.createUserWithShortPassword();
        repository.save(user);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_userWithLongPassword_shouldFail() {
        User user = UserTestData.createUserWithLongPassword();
        repository.save(user);
    }
    @Test()
    public void save_userWithCorrectData_shouldPass() {
        User saved = repository.save(USER_VLAD);
        assertMatch(saved,repository.getById(USER_VLAD_ID));
    }

    @Test
    public void findByUserName_exist_shouldReturnResult(){
        repository.save(USER_VLAD);
        User found = repository.getByUsername("vlad");
        assertMatch(USER_VLAD,found);
    }
    @Test
    public void findByUserName_notExist_shouldReturnNull() {
        String username = "vasya";
        User found = repository.getByUsername(username);
        assertNull("No user should be found", found);
    }

    @Test
    public void findByEmail_exist_shouldReturnResult(){
        repository.save(USER_VLAD);
        User found = repository.getByEmail("cheremisinvladw@gmail.com");
        assertMatch(USER_VLAD,found);
    }
    @Test
    public void findByEmail_notExist_shouldReturnNull() {
        String email = "vasya@mail.com";
        User found = repository.getByEmail(email);
        assertNull("No user should be found", found);
    }
    @Test(expected = InconsistentDataException.class)
    public void create_withAlreadyExistedEmail_shouldFail(){
       User existed = UserTestData.createWithAlreadyExistedEmail();
       repository.save(USER_VLAD);
       repository.save(existed);
    }
    @Test
    public void findById_existed_shouldReturnResult() {
        repository.save(USER_VLAD);
        User found = repository.getById(USER_VLAD_ID);
        assertMatch(USER_VLAD,found);
    }
    @Test
    public void findById_notExisted_shouldReturnNull() {
        User found = repository.getById(1);
        assertNull("No user should be found", found);
    }

    @Test
    public void get_AllUsers_shouldPass() {
        repository.save(USER_VLAD);
        repository.save(USER_VASYA);
        assertMatch(repository.getAll(),USER_VASYA,USER_VLAD);
    }
}