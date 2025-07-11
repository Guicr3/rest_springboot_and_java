package com.guicr3.project_java_springboot.controllers;

import com.guicr3.project_java_springboot.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greetings")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "world") String content){
        return new Greeting(counter.incrementAndGet(), String.format(template, content));
    }
}
