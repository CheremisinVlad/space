package com.own.space.service.impls;

import com.own.space.data.RecordTestData;
import com.own.space.domain.Record;
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

import static com.own.space.data.RecordTestData.*;
import static com.own.space.data.UserTestData.USER_VLAD_ID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = TestServiceConfig.class)
public class RecordServiceImplTest {

    @Autowired
    private BlockService<Record> service;

    @MockBean
    private BlockRepository<Record> mockRepository;

    @Test
    public void create_newRecord_shouldPass() {
        Record record = createRecord1ForVladWithoutId();
        when(mockRepository.save(record)).thenReturn(RECORD1_VLAD);
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(RECORD1_VLAD,RECORD2_VLAD));

        Record created = service.save(record);
        assertMatch(RECORD1_VLAD,created);
        assertMatch(service.getAll(USER_VLAD_ID),RECORD1_VLAD,RECORD2_VLAD);
    }

    @Test
    public void update_existingRecord_shouldPass() {
        Record updated = RecordTestData.updateRecord1ForVlad();
        when(mockRepository.save(RECORD1_VLAD)).thenReturn(updated);
        RECORD1_VLAD.setName("test update");

        Record newUpdate = service.update(RECORD1_VLAD);

        assertMatch(updated,newUpdate);
    }

    @Test(expected = NotFoundException.class)
    public void update_notExistingRecord_shouldFail() {
        Record notExisted = RecordTestData.createNotExisted();

        doThrow(new NotFoundException("user does not exist"))
                .when(mockRepository).save(notExisted);

        service.update(notExisted);
    }

    @Test
    public void delete_existingRecord_shouldPass() {
        when(mockRepository.delete(RECORD1_VLAD_ID)).thenReturn(true);

        boolean isDeleted = service.delete(RECORD1_VLAD_ID);

        assertEquals(isDeleted,true);
    }

    @Test(expected = NotFoundException.class)
    public void delete_notExistingRecord_shouldFail() {
        when(mockRepository.delete(1)).thenThrow(NotFoundException.class);

        service.delete(1);
    }

    @Test
    public void getAll_existingRecord_shouldPass() {
        when(mockRepository.getAll(USER_VLAD_ID)).thenReturn(List.of(RECORD1_VLAD,RECORD2_VLAD));

        List<Record> records = service.getAll(USER_VLAD_ID);

        assertMatch(records,RECORD1_VLAD,RECORD2_VLAD);
    }

    @Test
    public void getAllForParent_existingRecord_shouldPass() {
        when(mockRepository.getAllParent(RECORD1_VLAD_ID,USER_VLAD_ID)).thenReturn(List.of(RECORD1_VLAD));

        List<Record> records = service.getAllParent(RECORD1_VLAD_ID,USER_VLAD_ID);

        assertMatch(records,RECORD1_VLAD);
    }

    @Test
    public void getAllForMainWindow_existingRecord_shouldPass() {
        when(mockRepository.getAllMain(USER_VLAD_ID)).thenReturn(List.of(RECORD2_VLAD));

        List<Record> records = service.getAllMain(USER_VLAD_ID);

        assertMatch(records,RECORD2_VLAD);
    }
    @Test
    public void getById_existingDirectory_shouldPass(){
        when(mockRepository.getById(RECORD1_VLAD_ID)).thenReturn(RECORD1_VLAD);

        Record record = service.get(RECORD1_VLAD_ID);

        assertMatch(record,RECORD1_VLAD);
    }
    @Test(expected = NotFoundException.class)
    public void getById_notExistingDirectory_shouldPass(){
        when(mockRepository.getById(1)).thenThrow(NotFoundException.class);

        service.get(1);
    }

}