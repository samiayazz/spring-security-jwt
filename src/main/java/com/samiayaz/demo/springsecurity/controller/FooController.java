package com.samiayaz.demo.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
@RequiredArgsConstructor
public class FooController {

    @GetMapping("/bar")
    public String bar() {
        return "bar";
    }

}
