package com.upc.foodia.services;

import com.upc.foodia.entities.Ingredient;
import com.upc.foodia.entities.IngredientCategory;
import com.upc.foodia.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> list(){
        return ingredientRepository.findAll();
    }

    public Ingredient update(Ingredient ingredient) throws Exception{
        ingredientRepository.findById(ingredient.getId()).orElseThrow(()->new Exception("Not found"));
        return ingredientRepository.save(ingredient);
    }
    public Ingredient delete(Long id) throws Exception {
        Ingredient ingredient;
        ingredient=ingredientRepository.findById(id).orElseThrow(()->new Exception("Not found"));
        ingredientRepository.delete(ingredient);
        return ingredient;
    }
}
