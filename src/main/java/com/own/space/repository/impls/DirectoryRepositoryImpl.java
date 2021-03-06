package com.own.space.repository.impls;

import com.own.space.domain.Directory;
import com.own.space.repository.BlockRepositoryImpl;
import com.own.space.repository.dataJpa.CrudDirectoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DirectoryRepositoryImpl extends BlockRepositoryImpl<Directory> {

    public DirectoryRepositoryImpl(CrudDirectoryRepository repository) {
        super(repository);
    }
}
