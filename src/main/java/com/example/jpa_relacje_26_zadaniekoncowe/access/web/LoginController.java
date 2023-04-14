package com.example.jpa_relacje_26_zadaniekoncowe.access.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(){
        return "login-form";
    }

}
