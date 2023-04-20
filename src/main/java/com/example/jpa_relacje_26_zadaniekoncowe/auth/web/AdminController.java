package com.example.jpa_relacje_26_zadaniekoncowe.auth.web;

import com.example.jpa_relacje_26_zadaniekoncowe.auth.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/update-user/add-admin")
    public String addAdmin(@RequestParam String email) {
        userService.addAdminRole(email);
        return "redirect:/admin";
    }

    @GetMapping("/update-user/delete-admin")
    public String deleteAdmin(@RequestParam String email) {
        userService.deleteAdminRole(email);
        return "redirect:/admin";
    }

}
