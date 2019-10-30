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

    public T get(int id){
        Assert.notNull(id,"id must not be null");
        return repository.getById(id);
    }

    @Override
    @Transactional
    public T save(T block) {
        checkNew(block);
        return repository.save(block);
    }

    @Override
    @Transactional
    public T update(T block) {
        Assert.notNull(block,"block must not be null");
        return checkNotFoundWithId(repository.save(block),block.getId());
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
    public List<T> getAllParent(int parentId) {
        return repository.getAllParent(parentId);
    }

    @Override
    public List<T> getAllMain(int userId) {
        return repository.getAllMain(userId);
    }
}
