package com.own.space.repository.dataJpa;

import com.own.space.data.UrlTestData;
import com.own.space.domain.Url;
import com.own.space.repository.BlockRepository;
import com.own.space.util.exceptions.InconsistentDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.own.space.data.UrlTestData.*;
import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/h2/createUrlDb.sql",config = @SqlConfig(encoding = "UTF-8"))
@Import(TestRepositoryConfig.class)
public class UrlRepositoryImplTest{

    @Autowired
    private BlockRepository<Url> repository;


    @Test(expected = InconsistentDataException.class)
    public void save_withNullValue_shouldFail(){
        Url invalid = createUrlWithNullValue();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withInvalidValue_shouldFail(){
        Url invalid = UrlTestData.createUrlWithInvalidValue();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withDescriptionLessThenSize_shouldFail(){
        Url invalid = createUrlWithDescriptionLessThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withDescriptionMoreThenSize_shouldFail(){
        Url invalid = createUrlWithDescriptionMoreThenSize();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullDescription_shouldFail(){
        Url invalid = UrlTestData.createUrlWithNullDescription();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullUserId_shouldFail(){
        Url invalid = createUrlWithNullUserId();
        repository.save(invalid);
    }
    @Test(expected = InconsistentDataException.class)
    public void save_withNullParentId_shouldFail(){
        Url invalid = createUrlWithNullParentId();
        repository.save(invalid);
    }
    @Test
    public void get_withNotExistedId_shouldReturnNull(){
        Url nullRepository = repository.getById(1);
        assertNull("No url should be found", nullRepository);
    }
    @Test
    public void save_withCorrectData_shouldSucceed(){
        Url save1 = repository.save(createUrl1ForVladWithoutId());
        Url save2 = repository.save(createUrl2ForVladWithoutId());
        List<Url> all = repository.getAll(USER_VLAD_ID);
        assertMatch(List.of(save1,save2),all);
    }

    @Test
    public void get_withCorrectUserId_shouldSucceed(){
        Url save = repository.save(createUrl1ForVladWithoutId());
        Url found = repository.getById(save.getId());
        assertMatch(save,found);
    }
    @Test
    public void getAll_withCorrectUserId_shouldSucceed(){
        Url save1 = repository.save(createUrl1ForVladWithoutId());
        Url save2 = repository.save(createUrl2ForVladWithoutId());
        List<Url> all = repository.getAll(USER_VLAD_ID);
        assertMatch(all,save1,save2);
    }
    @Test
    public void getAll_withCorrectUserIdForMainWindow_shouldSucceed(){
        Url save1 = repository.save(createUrl1ForVladWithoutId());
        repository.save(createUrl2ForVladWithoutId());
        List<Url> all = repository.getAllMain(USER_VLAD_ID);
        assertMatch(all,save1);
    }
    @Test
    public void getAll_withCorrectParentId_shouldSucceed(){
        Url save2 = repository.save(createUrl2ForVladWithoutId());
        List<Url> all = repository.getAllParent(save2.getParentId(),save2.getUserId());
        assertMatch(all,save2);
    }
    @Test
    @Transactional
    public void delete_withCorrectId_shouldSucceed(){
        Url save2 = repository.save(createUrl2ForVladWithoutId());
        repository.delete(save2.getId());
        List<Url> all = repository.getAll(save2.getUserId());
        assertEquals(all.size(),0);
    }
    @Test
    @Transactional
    public void delete_withIncorrectId_shouldFail(){
        assertEquals(repository.delete(1),false);
    }



}
