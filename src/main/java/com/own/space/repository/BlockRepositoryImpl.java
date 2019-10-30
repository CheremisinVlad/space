package com.own.space.repository;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.repository.dataJpa.CrudBlockRepository;

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
    public List<T> getAllParent(int parentId) {
        return repository.getAllParent(parentId);
    }

    @Override
    public List<T> getAllMain(int userId) {
        return repository.getAllMain(userId);
    }
}
