package com.own.space.repository.dataJpa;

import com.own.space.data.DirectoryTestData;
import com.own.space.domain.Directory;
import com.own.space.repository.BlockRepository;
import com.own.space.repository.BlockRepositoryImpl;
import com.own.space.repository.impls.DirectoryRepositoryImpl;
import com.own.space.util.aspects.RepositoryExceptionInterceptor;
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

import java.util.List;

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.junit.Assert.assertNull;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/h2/createDirectoryDb.sql",config = @SqlConfig(encoding = "UTF-8"))
public class DirectoryRepositoryImplTest {

    @Autowired
    private BlockRepository<Directory> repository;

    @TestConfiguration
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    public static class DirectoryRepositoryTestConfig{
        @Bean
        public BlockRepository<Directory> directoryRepository(CrudDirectoryRepository crud){
            return new DirectoryRepositoryImpl(crud);
        }
        @Bean
        public RepositoryExceptionInterceptor repositoryExceptionInterceptor(){
            return new RepositoryExceptionInterceptor();
        }
    }

    @Test(expected = InconsistentDataException.class)
    public void save_withNullName_shouldFail(){
        Directory invalid = createDirectoryWithNullName();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNameLessThenSize_shouldFail(){
        Directory invalid = createDirectoryWithNameLessThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNameMoreThenSize_shouldFail(){
        Directory invalid = createDirectoryWithNameMoreThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullUserId_shouldFail(){
        Directory invalid = createDirectoryWithNullUserId();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullParentId_shouldFail(){
        Directory invalid = createDirectoryWithNullParentId();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullIsMainFlag_shouldFail(){
        Directory invalid = createDirectoryWithNullIsMainFlag();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withTrueMainFlagAndParentIdNotZero_shouldFail(){
        Directory invalid = createDirectoryWithTrueMainFlagAndParentIdNotZero();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withFalseMainFlagAndParentIdZero_shouldFail(){
        Directory invalid = createDirectoryWithFalseMainFlagAndParentIdZero();
        repository.save(invalid);
    }
    @Test
    public void get_withNotExistedId_shouldReturnNull(){
        Directory nullRepository = repository.getById(1);
        assertNull("No repository should be found", nullRepository);
    }
    @Test
    public void save_withCorrectData_shouldSucceed(){
        Directory save1 = repository.save(createDirectory1ForVladWithoutId());
        Directory save2 = repository.save(createDirectory2ForVladWithoutId());
        List<Directory> all = repository.getAll(USER_VLAD_ID);
        DirectoryTestData.assertMatch(List.of(save1,save2),all);
    }
    
    @Test
    public void get_withCorrectUserId_shouldSucceed(){
        Directory save = repository.save(createDirectory1ForVladWithoutId());
        Directory found = repository.getById(save.getId());
        assertMatch(save,found);
    }
    @Test
    public void getAll_withCorrectUserId_shouldSucceed(){
        Directory save1 = repository.save(createDirectory1ForVladWithoutId());
        Directory save2 = repository.save(createDirectory2ForVladWithoutId());
        List<Directory> all = repository.getAll(USER_VLAD_ID);
        assertMatch(all,save1,save2);
    }
    @Test
    public void getAll_withCorrectUserIdForMainWindow_shouldSucceed(){
        Directory save1 = repository.save(createDirectory1ForVladWithoutId());
        repository.save(createDirectory2ForVladWithoutId());
        List<Directory> all = repository.getAllForMainWindow(USER_VLAD_ID);
        assertMatch(all,save1);
    }
    @Test
    public void getAll_withCorrectParentId_shouldSucceed(){
        Directory save2 = repository.save(createDirectory2ForVladWithoutId());
        List<Directory> all = repository.getAllForParent(save2.getParentId());
        assertMatch(all,save2);
    }
    
    




}