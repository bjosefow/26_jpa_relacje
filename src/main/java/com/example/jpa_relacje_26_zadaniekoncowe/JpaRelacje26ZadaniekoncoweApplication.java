package com.example.jpa_relacje_26_zadaniekoncowe;

import com.example.jpa_relacje_26_zadaniekoncowe.category.CategoryRepository;
import com.example.jpa_relacje_26_zadaniekoncowe.recipe.RecipeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaRelacje26ZadaniekoncoweApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(JpaRelacje26ZadaniekoncoweApplication.class, args);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        RecipeRepository recipeRepository = context.getBean(RecipeRepository.class);
    }

}
