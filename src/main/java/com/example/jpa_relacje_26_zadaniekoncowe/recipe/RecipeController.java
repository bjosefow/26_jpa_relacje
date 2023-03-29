package com.example.jpa_relacje_26_zadaniekoncowe.recipe;

import com.example.jpa_relacje_26_zadaniekoncowe.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/recipes")
    public String recipes(Model model, @RequestParam(required = false) String categoryName) {

        Iterable<Recipe> recipeList;

        if (categoryName != null) {
            recipeList = recipeRepository.findRecipeByCategoryName(categoryName);
            model.addAttribute("category", categoryRepository.findByName(categoryName));
        } else {
            recipeList = recipeRepository.findAll();
        }
        model.addAttribute("recipesList", recipeList);
        return "recipesPage";
    }

    @GetMapping("/recipe/{id}")
    public String recipe(Model model, @PathVariable Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if (recipeOpt.isPresent()) {
            model.addAttribute("selectedRecipe", recipeOpt.get());
            return "oneRecipePage";
        } else {
            return "error";
        }
    }

    @GetMapping("/add-like/{id}")
    public String addLikeToRecipe(@PathVariable Long id) {
        recipeRepository.addLikeForRecipe(id);
        return "redirect:/recipe/" + id;
    }

    @GetMapping("/add-new-recipe")
    public String addForm(Model model) {
        model.addAttribute("recipeToSave", new Recipe());
        model.addAttribute("categoriesList", categoryRepository.findAll());
        return "saveRecipePage";
    }

    @GetMapping("/update-recipe/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        recipeOpt.ifPresent(recipe -> model.addAttribute("recipeToSave", recipe));
        model.addAttribute("categoriesList", categoryRepository.findAll());
        return "saveRecipePage";
    }

    @PostMapping("/save-recipe")
    public String saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/";
    }

    @GetMapping("/delete-recipe/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/the-best-recipe")
    public String deleteRecipe(Model model) {
        model.addAttribute("theBestRecipesList", recipeRepository.getTopRecipes());
        return "theBestRecipesPage";
    }
}
