package com.game.guess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories1")
public class testCallAPI {

    @GetMapping
    public String test() {
        return "OK";
    }
}
