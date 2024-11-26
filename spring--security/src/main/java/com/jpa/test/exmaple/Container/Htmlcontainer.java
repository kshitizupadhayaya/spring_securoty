package com.jpa.test.exmaple.Container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Htmlcontainer {
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/user")
    public String User()
    {
        return "user";
    }
    @GetMapping("/admin")
            public String Admin()
    {
        return "admin";
    }


}
