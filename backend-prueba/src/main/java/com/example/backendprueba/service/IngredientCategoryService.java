package com.example.backendprueba.service;


import com.example.backendprueba.entities.IngredientCategory;

import com.example.backendprueba.repository.IngredientCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientCategoryService {
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    public IngredientCategory saves(IngredientCategory ingredientCategory){
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    public List<IngredientCategory> list(){ return ingredientCategoryRepository.findAll();}

    public IngredientCategory update(IngredientCategory ingredientCategory) throws Exception{
        ingredientCategoryRepository.findById(ingredientCategory.getId()).orElseThrow(()->new Exception("Not Found"));
        return ingredientCategoryRepository.save(ingredientCategory);
    }
    public IngredientCategory delete(Long id) throws Exception {
        IngredientCategory ingredientCategory;
        ingredientCategory=ingredientCategoryRepository.findById(id).orElseThrow(()->new Exception("Not found"));
        ingredientCategoryRepository.delete(ingredientCategory);
        return ingredientCategory;
    }
}
