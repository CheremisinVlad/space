package com.own.space.service.impls;

import com.own.space.domain.Url;
import com.own.space.repository.BlockRepository;
import com.own.space.service.BlockServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UrlServiceImpl extends BlockServiceImpl<Url> {

    public UrlServiceImpl(BlockRepository<Url> repository) {
        super(repository);
    }
}
