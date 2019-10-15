package com.own.space.repository.dataJpa;

import com.own.space.domain.Directory;
import com.own.space.domain.validation.DirectoryValidationUtil;
import com.own.space.repository.DirectoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectoryRepositoryImpl implements DirectoryRepository {

    private CrudDirectoryRepository repository;

    public DirectoryRepositoryImpl(CrudDirectoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Directory getById(int id) {
        return repository.getById(id).orElse(null);
    }

    @Override
    public List<Directory> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Directory save(Directory directory) {
        DirectoryValidationUtil.validate(directory);
        return repository.save(directory);
    }

    @Override
    public List<Directory> getAllForParent(int parentId) {
        return repository.getAllForParent(parentId);
    }

    @Override
    public List<Directory> getAllForMainWindow(int userId) {
        return repository.getAllForMainWindow(userId,true);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id)!=0;
    }
}
