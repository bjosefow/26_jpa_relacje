package com.example.jpa_relacje_26_zadaniekoncowe;

import com.example.jpa_relacje_26_zadaniekoncowe.access.user.UserService;
import com.example.jpa_relacje_26_zadaniekoncowe.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public HomeController(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("isCurrentUserAdmin", userService.isCurrentUserAdmin());
        return "home";
    }

}
