package com.enigma.restservice.controllers;

import com.enigma.restservice.exception.PathNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController{
    
    @RequestMapping("/error")
    public void handleError(){
        throw new PathNotFoundException();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
