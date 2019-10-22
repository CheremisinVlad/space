package com.own.space.service.impls;

import com.own.space.domain.Record;
import com.own.space.repository.BlockRepository;
import com.own.space.service.BlockServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RecordServiceImpl extends BlockServiceImpl<Record> {

    public RecordServiceImpl(BlockRepository<Record> repository) {
        super(repository);
    }
}
