package com.example.jpa_relacje_26_zadaniekoncowe.auth.web;

import com.example.jpa_relacje_26_zadaniekoncowe.auth.user.UserService;
import com.example.jpa_relacje_26_zadaniekoncowe.auth.user.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userToRegister", new UserDto());
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userToRegister") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        userService.register(userDto);
        return "redirect:/registration-success";
    }

    @GetMapping("/registration-success")
    public String registerSuccess() {
        return "registrationSuccessPage";
    }
}
