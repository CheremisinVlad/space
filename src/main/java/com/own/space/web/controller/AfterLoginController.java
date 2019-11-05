package com.own.space.web.controller;

import com.own.space.domain.Directory;
import com.own.space.domain.Record;
import com.own.space.domain.Url;
import com.own.space.service.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/space")
public class AfterLoginController {

    private BlockService<Directory> directoryService;
    private BlockService<Url> urlService;
    private BlockService<Record> recordService;

    public AfterLoginController(BlockService<Directory> directoryService, BlockService<Url> urlService, BlockService<Record> recordService) {
        this.directoryService = directoryService;
        this.urlService = urlService;
        this.recordService = recordService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getSpace(@PathVariable int userId){
        Map<String,Object> result = new HashMap<>();
        result.put("userId",userId);
        result.put("directories",directoryService.getAllMain(userId));
        result.put("urls",urlService.getAllMain(userId));
        result.put("records",recordService.getAllMain(userId));
        return ResponseEntity.ok(result);
        }
}
