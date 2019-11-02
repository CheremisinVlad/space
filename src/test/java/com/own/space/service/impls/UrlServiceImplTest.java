package com.own.space.service.impls;

import com.own.space.data.UrlTestData;
import com.own.space.domain.Url;
import com.own.space.repository.BlockRepository;
import com.own.space.service.BlockService;
import com.own.space.util.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.own.space.data.UrlTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = TestServiceConfig.class)
public class UrlServiceImplTest {

    @Autowired
    private BlockService<Url> service;

    @MockBean
    private BlockRepository<Url> mockRepository;

    @Test
    public void create_newUrl_shouldPass() {
        Url url = createUrl1ForVladWithoutId();
        when(mockRepository.save(url)).thenReturn(URL1_VLAD);
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(URL1_VLAD,URL2_VLAD));

        Url created = service.save(url);
        assertMatch(URL1_VLAD,created);
        assertMatch(service.getAll(USER_VLAD_ID),URL1_VLAD,URL2_VLAD);
    }

    @Test
    public void update_existingUrl_shouldPass() {
        Url updated = UrlTestData.updateUrl1ForVlad();
        when(mockRepository.save(URL1_VLAD)).thenReturn(updated);

        URL1_VLAD.setDescription("it's updated");
        Url newUpdate = service.update(URL1_VLAD);

        assertMatch(updated,newUpdate);
    }

    @Test(expected = NotFoundException.class)
    public void update_notExistingUrl_shouldFail() {
        Url notExisted = UrlTestData.createNotExisted();

        doThrow(new NotFoundException("user does not exist"))
                .when(mockRepository).save(notExisted);

        service.update(notExisted);
    }

    @Test
    public void delete_existingUrl_shouldPass() {
        when(mockRepository.delete(URL1_VLAD_ID)).thenReturn(true);

        boolean isDeleted = service.delete(URL1_VLAD_ID);

        assertEquals(isDeleted,true);
    }

    @Test(expected = NotFoundException.class)
    public void delete_notExistingUrl_shouldFail() {
        when(mockRepository.delete(1)).thenThrow(NotFoundException.class);

        service.delete(1);
    }

    @Test
    public void getAll_existingUrl_shouldPass() {
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(URL1_VLAD,URL2_VLAD));

        List<Url> urls = service.getAll(USER_VLAD_ID);

        assertMatch(urls,URL1_VLAD,URL2_VLAD);
    }

    @Test
    public void getAllForParent_existingUrl_shouldPass() {
        when(mockRepository.getAllParent(URL1_VLAD_ID,USER_VLAD_ID)).thenReturn(List.of(URL2_VLAD));

        List<Url> urls = service.getAllParent(URL1_VLAD_ID,USER_VLAD_ID);

        assertMatch(urls,URL2_VLAD);
    }

    @Test
    public void getAllForMainWindow_existingUrl_shouldPass() {
        when(mockRepository.getAllMain(USER_VLAD_ID)).thenReturn(List.of(URL2_VLAD));

        List<Url> urls = service.getAllMain(USER_VLAD_ID);

        assertMatch(urls,URL2_VLAD);
    }

    @Test
    public void getById_existingDirectory_shouldPass(){
        when(mockRepository.getById(URL1_VLAD_ID)).thenReturn(URL1_VLAD);

        Url url= service.get(URL1_VLAD_ID);

        assertMatch(url,URL1_VLAD);
    }
    @Test(expected = NotFoundException.class)
    public void getById_notExistingDirectory_shouldPass(){
        when(mockRepository.getById(1)).thenThrow(NotFoundException.class);

        service.get(1);
    }

}