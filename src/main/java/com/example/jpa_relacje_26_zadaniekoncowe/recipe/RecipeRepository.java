package com.example.jpa_relacje_26_zadaniekoncowe.recipe;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r.likes from  Recipe r WHERE r.id = :id")
    int getNumberOfLikesForRecipe(Long id);


    @Query("SELECT r from Recipe r ORDER BY r.likes DESC LIMIT 3")
    List<Recipe> getTopRecipes();

    List<Recipe> findRecipeByCategoryName(String categoryName);

    @Transactional
    @Modifying
    @Query("UPDATE Recipe r SET r.likes = r.likes + 1 WHERE r.id = :id")
    void addLikeForRecipe(Long id);
}
