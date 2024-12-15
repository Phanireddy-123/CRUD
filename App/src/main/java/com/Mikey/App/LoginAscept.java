package com.Mikey.App;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAscept {

     private static final Logger LOGGER  = LoggerFactory.getLogger((LoginAscept.class));

     public void logMethodCall(){
         System.out.println("Hello World");
     }
}
