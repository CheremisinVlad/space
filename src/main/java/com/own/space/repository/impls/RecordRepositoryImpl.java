package com.own.space.repository.impls;

import com.own.space.domain.Record;
import com.own.space.repository.BlockRepositoryImpl;
import com.own.space.repository.dataJpa.CrudBlockRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepositoryImpl extends BlockRepositoryImpl<Record> {
    public RecordRepositoryImpl(CrudBlockRepository<Record> repository) {
        super(repository);
    }
}
