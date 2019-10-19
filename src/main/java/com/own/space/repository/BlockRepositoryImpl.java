package com.own.space.repository;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.repository.validation.RepositoryValidationUtil;
import com.own.space.repository.dataJpa.CrudBlockRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public abstract class BlockRepositoryImpl<T extends AbstractBaseBlock> implements BlockRepository<T> {

    private CrudBlockRepository<T> repository;

    public BlockRepositoryImpl(CrudBlockRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T getById(int id) {
        return repository.getById(id).orElse(null);
    }

    @Override
    public T save(T block) {
        RepositoryValidationUtil.validate(block);
        return repository.save(block);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id)!=0;
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
        return repository.getAllForMainWindow(userId,true);
    }
}