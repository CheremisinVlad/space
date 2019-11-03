package com.own.space.web.controller;

import com.own.space.domain.Record;
import com.own.space.service.BlockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/records")
public class RecordController extends BlockController<Record> {

    public RecordController(BlockService<Record> service) {
        super(service);
    }
}
