package com.own.space.web.controller;

import com.own.space.domain.Url;
import com.own.space.service.BlockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/urls")
public class UrlController extends BlockController<Url> {

    public UrlController(BlockService<Url> service) {
        super(service);
    }
}
