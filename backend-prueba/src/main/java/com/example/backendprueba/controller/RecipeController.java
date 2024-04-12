package com.example.backendprueba.controller;

import com.example.backendprueba.dto.RecipeDTO;
import com.example.backendprueba.entities.Recipe;
import com.example.backendprueba.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    public RecipeService recipeService;

    Logger logger = LoggerFactory.getLogger(RecipeController.class);
    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDTO>>recipeList() {
        List<Recipe> list;
        List<RecipeDTO> listDto;
        try{
            list = recipeService.recipeList();
            listDto = convertToLisDto(list);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't list");
        }
        return new ResponseEntity<List<RecipeDTO>>(listDto,HttpStatus.OK);
    }
    @PostMapping("/recipe")
    public ResponseEntity<RecipeDTO> register(@RequestBody RecipeDTO recipeDto){
        Recipe recipe = convertToEntity(recipeDto);
        RecipeDTO recipeDTO;
        try {
            recipe = recipeService.register(recipe);
            recipeDTO = convertToDto(recipe);
        }catch (Exception e){
            e.printStackTrace();
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Could not register");
        }
        return new ResponseEntity<RecipeDTO>(recipeDTO, HttpStatus.OK);
    }
    @PutMapping("/recipe/update")
    public ResponseEntity<RecipeDTO> actualizarAutor(@RequestBody RecipeDTO recipeDto) {
        RecipeDTO recipeDTO;
        Recipe recipe;
        try {
            recipe = convertToEntity(recipeDto);
            logger.debug("Updated recipe");
            recipe = recipeService.update(recipe);
            logger.debug("Updated recipe");
            recipeDTO = convertToDto(recipe);
            return new ResponseEntity<RecipeDTO>(recipeDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Update error", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update");
        }
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<RecipeDTO> delete(@PathVariable(value = "id") Long id){
        Recipe recipe;
        RecipeDTO recipeDTO;
        try {
            recipe = recipeService.delete(id);
            logger.debug("Deleted recipe");
            recipeDTO = convertToDto(recipe);
            return new ResponseEntity<RecipeDTO>(recipeDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Deletion error ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ould not delete");
        }
    }


    private RecipeDTO convertToDto(Recipe recipe) {
        ModelMapper modelMapper = new ModelMapper();
        RecipeDTO recipeDTO = modelMapper.map(recipe, RecipeDTO.class);
        return recipeDTO;
    }
    private Recipe convertToEntity(RecipeDTO recipeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
        return recipe;
    }
    private List<RecipeDTO> convertToLisDto(List<Recipe> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
