package com.own.space.repository.dataJpa;

import com.own.space.data.RecordTestData;
import com.own.space.domain.Record;
import com.own.space.repository.BlockRepository;
import com.own.space.repository.impls.RecordRepositoryImpl;
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

import static com.own.space.data.RecordTestData.*;
import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/h2/createRecordDb.sql",config = @SqlConfig(encoding = "UTF-8"))
public class RecordRepositoryImplTest {

    @Autowired
    private BlockRepository<Record> repository;

    @TestConfiguration
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    public static class BlockRepositoryTestConfig{
        @Bean
        public BlockRepository<Record> directoryRepository(CrudRecordRepository crud){
            return new RecordRepositoryImpl(crud);
        }
        @Bean
        public RepositoryExceptionInterceptor repositoryExceptionInterceptor(){
            return new RepositoryExceptionInterceptor();
        }
    }

    @Test(expected = InconsistentDataException.class)
    public void save_withNullName_shouldFail(){
        Record invalid = createRecordWithNullName();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNameLessThenSize_shouldFail(){
        Record invalid = createRecordWithNameLessThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNameMoreThenSize_shouldFail(){
        Record invalid = createRecordWithNameMoreThenSize();
        repository.save(invalid);
    }

    @Test(expected = InconsistentDataException.class)
    public void save_withNullDescription_shouldFail(){
        Record invalid = createRecordWithNullDescription();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withDescriptionLessThenSize_shouldFail(){
        Record invalid = createRecordWithDescriptionLessThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withDescriptionMoreThenSize_shouldFail(){
        Record invalid = createRecordWithDescriptionMoreThenSize();
        repository.save(invalid);
    }

    @Test(expected = InconsistentDataException.class)
    public void save_withNullContent_shouldFail(){
        Record invalid = createRecordWithNullContent();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withContentLessThenSize_shouldFail(){
        Record invalid = createRecordWithContentLessThenSize();
        repository.save(invalid);
    }

    @Test(expected = InconsistentDataException.class)
    public void save_withNullUserId_shouldFail(){
        Record invalid = createRecordWithNullUserId();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullParentId_shouldFail(){
        Record invalid = createRecordWithNullParentId();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullIsMainFlag_shouldFail(){
        Record invalid = createRecordWithNullIsMainFlag();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withTrueMainFlagAndParentIdNotZero_shouldFail(){
        Record invalid = createRecordWithTrueMainFlagAndParentIdNotZero();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withFalseMainFlagAndParentIdZero_shouldFail(){
        Record invalid = createRecordWithFalseMainFlagAndParentIdZero();
        repository.save(invalid);
    }
    @Test
    public void get_withNotExistedId_shouldReturnNull(){
        Record nullRecord = repository.getById(1);
        assertNull("No record should be found", nullRecord);
    }
    @Test
    public void save_withCorrectData_shouldSucceed(){
        Record save1 = repository.save(createRecord1ForVladWithoutId());
        Record save2 = repository.save(createRecord2ForVladWithoutId());
        List<Record> all = repository.getAll(USER_VLAD_ID);
        assertMatch(List.of(save1,save2),all);
    }

    @Test
    public void get_withCorrectUserId_shouldSucceed(){
        Record save = repository.save(createRecord1ForVladWithoutId());
        Record found = repository.getById(save.getId());
        assertMatch(save,found);
    }
    @Test
    public void getAll_withCorrectUserId_shouldSucceed(){
        Record save1 = repository.save(createRecord1ForVladWithoutId());
        Record save2 = repository.save(createRecord2ForVladWithoutId());
        List<Record> all = repository.getAll(USER_VLAD_ID);
        assertMatch(all,save1,save2);
    }
    @Test
    public void getAll_withCorrectUserIdForMainWindow_shouldSucceed(){
        Record save1 = repository.save(createRecord1ForVladWithoutId());
        repository.save(createRecord2ForVladWithoutId());
        List<Record> all = repository.getAllForMainWindow(USER_VLAD_ID);
        assertMatch(all,save1);
    }
    @Test
    public void getAll_withCorrectParentId_shouldSucceed(){
        Record save2 = repository.save(createRecord2ForVladWithoutId());
        List<Record> all = repository.getAllForParent(save2.getParentId());
        assertMatch(all,save2);
    }
    @Test
    @Transactional
    public void delete_withCorrectId_shouldSucceed(){
        Record save2 = repository.save(createRecord2ForVladWithoutId());
        repository.delete(save2.getId());
        List<Record> all = repository.getAll(save2.getUserId());
        assertEquals(all.size(),0);
    }
    @Test
    @Transactional
    public void delete_withIncorrectId_shouldFail(){
        assertEquals(repository.delete(1),false);
    }


}
