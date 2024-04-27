package com.example.backendprueba.service;


import com.example.backendprueba.entities.RecipeCategory;
import com.example.backendprueba.repository.RecipeCategoryRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service

public class RecipeCategoryService {
    private  RecipeCategoryRepository recipeCategoryRepository;

    @Transactional
    public  RecipeCategory save(RecipeCategory recipeCategory){
        return recipeCategoryRepository.save(recipeCategory);
    }
    public List<RecipeCategory> list(){
        return recipeCategoryRepository.findAll();
    }

    public RecipeCategory update(RecipeCategory recipeCategory) throws Exception {
        recipeCategoryRepository.findById(recipeCategory.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return recipeCategoryRepository.save(recipeCategory);
    }

    public RecipeCategory delete(Long id) throws Exception{
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(id).orElseThrow(() -> new Exception("No se encontró entidad"));
        recipeCategoryRepository.delete(recipeCategory);
        return recipeCategory;
    }
}
