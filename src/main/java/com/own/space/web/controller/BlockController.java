package com.own.space.web.controller;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.service.BlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class BlockController<T extends AbstractBaseBlock> {

    private BlockService<T> service;

    public BlockController(BlockService<T> service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T block) {
        T newBlock = service.save(block);
        return ResponseEntity.ok(newBlock);
    }
    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable int id) {
        T block = service.get(id);
        return ResponseEntity.ok(block);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody T block) {
        service.update(block);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/main/{userId}")
    public ResponseEntity<List<T>> getAllMain(@PathVariable int userId) {
        List<T> newBlocks = service.getAllMain(userId);
        return ResponseEntity.ok(newBlocks);
    }
    @GetMapping("/{userId}/{parentId}")
    public ResponseEntity<List<T>> getAllParent(@PathVariable int userId,@PathVariable int parentId) {
        List<T> newBlocks = service.getAllParent(parentId,userId);
        return ResponseEntity.ok(newBlocks);
    }


}
