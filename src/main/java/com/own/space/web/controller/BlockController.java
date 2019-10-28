package com.own.space.web.controller;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.domain.AuthUser;
import com.own.space.service.BlockService;
import com.own.space.web.payload.RegisteredUser;
import com.own.space.web.results.BlockResult;
import com.own.space.web.results.RequestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class BlockController<T extends AbstractBaseBlock> {

    private BlockService<T> service;

    public BlockController(BlockService<T> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RequestResult> create(@RequestBody T block,
                                                @RegisteredUser AuthUser currentUser) {
        block.setUserId(currentUser.getId());
        T newBlock = service.save(block);
        return BlockResult.buildCreateResult(newBlock);
    }

    @PatchMapping
    public ResponseEntity<RequestResult> update(@RequestBody T block,
                                                @RegisteredUser AuthUser currentUser) {
        block.setUserId(currentUser.getId());
        T newBlock = service.update(block);
        return BlockResult.buildUpdateResult(newBlock);
    }

    @DeleteMapping
    public ResponseEntity<RequestResult> delete(@RequestBody T block,
                                                @RegisteredUser AuthUser currentUser) {
        block.setUserId(currentUser.getId());
        boolean deleted = service.delete(block.getId());
        return BlockResult.buildDeleteResult(deleted);
    }


}
