package com.own.space.service.impls;

import com.own.space.data.DirectoryTestData;
import com.own.space.domain.Directory;
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

import static com.own.space.data.DirectoryTestData.*;
import static com.own.space.data.UserTestData.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = TestServiceConfig.class)
public class DirectoryServiceImplTest {

    @Autowired
    private BlockService<Directory> service;

    @MockBean
    private BlockRepository<Directory> mockRepository;

    @Test
    public void create_newDirectory_shouldPass() {
        Directory directory = createDirectory1ForVladWithoutId();
        when(mockRepository.save(directory)).thenReturn(DIRECTORY1_VLAD);
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(DIRECTORY1_VLAD,DIRECTORY2_VLAD));

        Directory created = service.save(directory);
        assertMatch(DIRECTORY1_VLAD,created);
        assertMatch(service.getAll(USER_VLAD_ID),DIRECTORY1_VLAD,DIRECTORY2_VLAD);
    }

    @Test
    public void update_existingDirectory_shouldPass() {
        Directory updated = updateDirectory1ForVlad();
        when(mockRepository.save(DIRECTORY1_VLAD)).thenReturn(updated);
        DIRECTORY1_VLAD.setName("new patterns");

        Directory newUpdate = service.update(DIRECTORY1_VLAD);

        assertMatch(updated,newUpdate);
    }

    @Test(expected = NotFoundException.class)
    public void update_notExistingDirectory_shouldFail() {
       Directory notExisted = DirectoryTestData.createNotExisted();

        doThrow(new NotFoundException("user does not exist"))
                .when(mockRepository).save(notExisted);

        service.update(notExisted);
    }

    @Test
    public void delete_existingDirectory_shouldPass() {
        when(mockRepository.delete(DIRECTORY1_VLAD_ID)).thenReturn(true);

        boolean isDeleted = service.delete(DIRECTORY1_VLAD_ID);

        assertEquals(isDeleted,true);
    }

    @Test(expected = NotFoundException.class)
    public void delete_notExistingDirectory_shouldFail() {
        when(mockRepository.delete(1)).thenThrow(NotFoundException.class);

        service.delete(1);
    }

    @Test
    public void getAll_existingDirectory_shouldPass() {
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(DIRECTORY1_VLAD,DIRECTORY2_VLAD));

        List<Directory> directories = service.getAll(USER_VLAD_ID);

        assertMatch(directories,DIRECTORY1_VLAD,DIRECTORY2_VLAD);
    }

    @Test
    public void getAllForParent_existingDirectory_shouldPass() {
        when(mockRepository.getAllForParent(DIRECTORY1_VLAD_ID)).thenReturn(List.of(DIRECTORY2_VLAD));

        List<Directory> directories = service.getAllForParent(DIRECTORY1_VLAD_ID);

        assertMatch(directories,DIRECTORY2_VLAD);
    }

    @Test
    public void getAllForMainWindow_existingDirectory_shouldPass() {
        when(mockRepository.getAllForMainWindow(USER_VLAD_ID)).thenReturn(List.of(DIRECTORY1_VLAD));

        List<Directory> directories = service.getAllForMainWindow(USER_VLAD_ID);

        assertMatch(directories,DIRECTORY1_VLAD);
    }
}