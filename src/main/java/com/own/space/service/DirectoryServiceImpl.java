package com.own.space.service;

import com.own.space.domain.Directory;
import com.own.space.repository.DirectoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.own.space.service.validation.Validation.checkNew;
import static com.own.space.service.validation.Validation.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class DirectoryServiceImpl implements DirectoryService{

    private DirectoryRepository repository;

    public DirectoryServiceImpl(DirectoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Directory create(Directory directory) {
        checkNew(directory);
        return repository.save(directory);
    }

    @Override
    @Transactional
    public Directory update(Directory directory) {
        Assert.notNull(directory,"directory must not be null");
        return checkNotFoundWithId(repository.save(directory),directory.getId());
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Assert.notNull(id,"id must not be null");
        checkNotFoundWithId(repository.delete(id),id);
        return true;
    }

    @Override
    public List<Directory> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Directory> getAllForParent(int parentId) {
        return repository.getAllForParent(parentId);
    }

    @Override
    public List<Directory> getAllForMainWindow(int userId) {
        return repository.getAllForMainWindow(userId);
    }
}
