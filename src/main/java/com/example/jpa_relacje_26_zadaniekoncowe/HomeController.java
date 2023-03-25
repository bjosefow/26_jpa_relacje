package com.example.jpa_relacje_26_zadaniekoncowe;

import com.example.jpa_relacje_26_zadaniekoncowe.category.CategoryRepository;
import com.example.jpa_relacje_26_zadaniekoncowe.recipe.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "home";
    }

}
