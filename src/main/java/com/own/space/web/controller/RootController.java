package com.own.space.web.controller;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.service.BlockService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class RootController<T extends AbstractBaseBlock> {

    private BlockService<T> service;

    public RootController(BlockService<T> service) {
        this.service = service;
    }
}
