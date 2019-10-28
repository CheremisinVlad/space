package com.own.space.service;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.repository.BlockRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.own.space.service.validation.Validation.checkNew;
import static com.own.space.service.validation.Validation.checkNotFoundWithId;


public class BlockServiceImpl<T extends AbstractBaseBlock> implements BlockService<T> {

    private BlockRepository<T> repository;

    public BlockServiceImpl(BlockRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T save(T directory) {
        checkNew(directory);
        return repository.save(directory);
    }

    @Override
    @Transactional
    public T update(T directory) {
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
    public List<T> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<T> getAllForParent(int parentId) {
        return repository.getAllForParent(parentId);
    }

    @Override
    public List<T> getAllForMainWindow(int userId) {
        return repository.getAllForMainWindow(userId);
    }
}
