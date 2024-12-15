package com.Mikey.App.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contoller {

    @GetMapping("/hello")
   /* public void Hello(){
        System.out.println("Hello");
    }
    */
    public String Hello(){
        return "Hello World";
    }
}
