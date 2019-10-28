package com.own.space.web.controller;

import com.own.space.domain.Directory;
import com.own.space.service.BlockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/directory")
public class DirectoryController  extends BlockController<Directory>{

    public DirectoryController(BlockService<Directory> service) {
        super(service);
    }
}
