package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("/hello{name}") //{name} will be the path variable
    @ResponseBody // will return the value wanted for the GetMapping
    public String hello(@PathVariable String name) { //@PathVariable annotation to allow name
        //return "hello from spring!";
        return "Hello, " + name + " !";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
}
