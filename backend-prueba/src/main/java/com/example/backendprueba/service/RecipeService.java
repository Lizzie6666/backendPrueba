package com.example.backendprueba.service;

import com.example.backendprueba.entities.Recipe;
import com.example.backendprueba.repository.RecipeRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    @Transactional
    public Recipe save(Recipe recipe){ return recipeRepository.save(recipe);}

    public List<Recipe> search(String prefix) throws Exception {
        List<Recipe> recipes = recipeRepository.findRecipeByTitleStartingWith(prefix);
        if (recipes.isEmpty()) {
            throw new Exception("Recipe not found");
        }
        return recipes;
    }

    public List<Recipe> list() { return recipeRepository.findAll();}

    public Recipe delete(Long id) throws Exception{
        Recipe recipe=recipeRepository.findById(id).orElseThrow(()->new Exception("Recipe not found"));
        recipeRepository.delete(recipe);
        return recipe;
    }

    public Recipe update(Recipe recipe) throws Exception{
        recipeRepository.findById(recipe.getId()).orElseThrow(()->new Exception("Recipe not found"));
        return recipeRepository.save(recipe);
    }

   public List<Recipe>filterByIngredient(String ingredient){
       return recipeRepository.findRecipesByIngredients(ingredient);
   }
    public List<Recipe>filterByType(String type){
        return recipeRepository.findRecipesByType(type);
    }
}
