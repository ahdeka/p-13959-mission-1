package com.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public void index() {
        System.out.println("Hello, Spring Boot!");
    }
}
