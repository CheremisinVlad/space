package com.own.space.repository.impls;

import com.own.space.domain.Directory;
import com.own.space.repository.BlockRepositoryImpl;
import com.own.space.repository.dataJpa.CrudBlockRepository;
import com.own.space.repository.validation.RepositoryValidationUtil;
import com.own.space.repository.dataJpa.CrudDirectoryRepository;
import com.own.space.service.BlockServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DirectoryRepositoryImpl extends BlockRepositoryImpl<Directory> {

    public DirectoryRepositoryImpl(CrudBlockRepository<Directory> repository) {
        super(repository);
    }
}
