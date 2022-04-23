package br.com.vitorino.ui.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingRestController {

    @GetMapping
    public String hello() {
        return "Hello Spring";
    }
}
