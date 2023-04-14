package com.example.jpa_relacje_26_zadaniekoncowe.access.web;

import com.example.jpa_relacje_26_zadaniekoncowe.access.user.UserService;
import com.example.jpa_relacje_26_zadaniekoncowe.access.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String adminPanel(Model model) {
        model.addAttribute("userList", userService.findAllUserExcludeCurrentUser());
        return "adminPage";
    }

    @GetMapping("/delete-user")
    String deleteUser(@RequestParam String email){
        userService.deleteByEMail(email);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/addAdmin")
    public String addAdmin(@RequestParam String email){
        userService.addAdminRole(email);
        return "redirect:/admin";
    }
    @GetMapping("/updateUser/deleteAdmin")
    public String deleteAdmin(@RequestParam String email){
        userService.deleteAdminRole(email);
        return "redirect:/admin";
    }

}
