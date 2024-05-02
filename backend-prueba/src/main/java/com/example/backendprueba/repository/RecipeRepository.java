package com.example.backendprueba.repository;

import com.example.backendprueba.entities.Recipe;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    public List<Recipe> findRecipeByTitleStartingWith(String prefix);
    public List<Recipe> findRecipeByIngredientsIsStartingWith(String ingredient);
    public List<Recipe>findRecipesByType(String type);



}
