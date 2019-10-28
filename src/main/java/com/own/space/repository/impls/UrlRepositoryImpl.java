package com.own.space.repository.impls;

import com.own.space.domain.Url;
import com.own.space.repository.BlockRepositoryImpl;
import com.own.space.repository.dataJpa.CrudBlockRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepositoryImpl extends BlockRepositoryImpl<Url> {

    public UrlRepositoryImpl(CrudBlockRepository<Url> url) {
        super(url);
    }
}
