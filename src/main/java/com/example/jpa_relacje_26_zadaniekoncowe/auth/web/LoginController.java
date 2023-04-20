package com.example.jpa_relacje_26_zadaniekoncowe.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, Model model) {
        //error jest pusty ale nie jest nullem, dlatego to zadziala
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "login-form";
    }

}
