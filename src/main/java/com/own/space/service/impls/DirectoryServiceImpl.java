package com.own.space.service.impls;

import com.own.space.domain.Directory;
import com.own.space.repository.BlockRepository;
import com.own.space.service.BlockService;
import com.own.space.service.BlockServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.own.space.service.validation.Validation.checkNew;
import static com.own.space.service.validation.Validation.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class DirectoryServiceImpl extends BlockServiceImpl<Directory> {

    public DirectoryServiceImpl(BlockRepository<Directory> repository) {
        super(repository);
    }


}
