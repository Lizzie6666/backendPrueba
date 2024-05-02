package com.example.backendprueba.controller;

import com.example.backendprueba.dto.RecipeDTO;
import com.example.backendprueba.entities.Recipe;
import com.example.backendprueba.service.RecipeService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    public RecipeService recipeService;
    @GetMapping("/list")
    public ResponseEntity<List<RecipeDTO>>recipeList() {
        ModelMapper modelMapper=new ModelMapper();
        List<RecipeDTO>rec= Arrays.asList(
                modelMapper.map(recipeService.list(),
                        RecipeDTO[].class)
        );
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<RecipeDTO> save(@RequestBody RecipeDTO recipeDto){
        ModelMapper modelMapper=new ModelMapper();
        Recipe recipe=modelMapper.map(recipeDto,Recipe.class);
        recipe=recipeService.save(recipe);
        recipeDto=modelMapper.map(recipe,RecipeDTO.class);
        return new ResponseEntity<>(recipeDto,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<RecipeDTO> update(@RequestBody RecipeDTO recipeDto) {
        Recipe recipe;
        try{
            ModelMapper modelMapper=new ModelMapper();
            recipe=modelMapper.map(recipeDto,Recipe.class);
            recipe=recipeService.update(recipe);
            recipeDto=modelMapper.map(recipe,RecipeDTO.class);
            return new ResponseEntity<>(recipeDto,HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RecipeDTO> delete(@PathVariable("id") Long id){
        Recipe recipe;
        RecipeDTO recipeDTO;
         try {
             ModelMapper modelMapper=new ModelMapper();
             recipe=recipeService.delete(id);
             recipeDTO=modelMapper.map(recipe,RecipeDTO.class);
             return new ResponseEntity<>(recipeDTO,HttpStatus.OK);
         }catch (Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete");
         }
    }
    @GetMapping("/filterByIngredient/{ingredient}")
    public ResponseEntity<List<RecipeDTO>>filterByIngredient(@PathVariable("ingredient")String ingredient){
        ModelMapper modelMapper=new ModelMapper();
        List<RecipeDTO>rec= Arrays.asList(
                modelMapper.map(recipeService.filterByIngredient(ingredient),
                        RecipeDTO[].class)
        );
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }
    @GetMapping("/filterByType/{type}")
    public ResponseEntity<List<RecipeDTO>>filterByType(@PathVariable("type")String type){
        ModelMapper modelMapper=new ModelMapper();
        List<RecipeDTO>rec= Arrays.asList(
                modelMapper.map(recipeService.filterByType(type),
                        RecipeDTO[].class)
        );
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }
    @GetMapping("/search/{prefix}")
    public ResponseEntity<List<RecipeDTO>>search(@PathVariable("prefix")String prefix){
        try{
            ModelMapper modelMapper=new ModelMapper();
            List<RecipeDTO>rec= Arrays.asList(
                    modelMapper.map(recipeService.search(prefix),
                            RecipeDTO[].class)
            );
            return new ResponseEntity<>(rec,HttpStatus.OK);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }
}
