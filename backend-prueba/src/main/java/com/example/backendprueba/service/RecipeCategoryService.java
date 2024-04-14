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
    public  RecipeCategory register(RecipeCategory recipeCategory){
        return recipeCategoryRepository.save(recipeCategory);
    }
    public List<RecipeCategory> listado(){
        return recipeCategoryRepository.findAll();
    }

    public RecipeCategory update(RecipeCategory recipeCategory) throws Exception {
        recipeCategoryRepository.findById(recipeCategory.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return recipeCategoryRepository.save(recipeCategory);
    }

    public RecipeCategory delete(Long codigo) throws Exception{
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        recipeCategoryRepository.delete(recipeCategory);
        return recipeCategory;
    }
}
