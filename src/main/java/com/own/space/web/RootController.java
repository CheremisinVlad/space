package com.own.space.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.

@Controller
public class RootController {

    @GetMapping({"/","/login"})
    public String entryPoint(){
        return "index";
    }

}
