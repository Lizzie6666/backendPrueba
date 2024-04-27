package com.example.backendprueba.controller;

import com.example.backendprueba.dto.IngredientDTO;
import com.example.backendprueba.dto.RecipeDTO;
import com.example.backendprueba.entities.Ingredient;
import com.example.backendprueba.entities.Recipe;
import com.example.backendprueba.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDTO>> list(){
        ModelMapper modelMapper=new ModelMapper();
        List<IngredientDTO> ingredient=Arrays.asList(
                modelMapper.map(ingredientService.list(),
                        IngredientDTO[].class)
        );
        return new ResponseEntity<>(ingredient,HttpStatus.OK);
    }
    @PostMapping("/ingredient")
    public ResponseEntity<IngredientDTO> save(@RequestBody IngredientDTO ingredientDto){
        ModelMapper modelMapper=new ModelMapper();
        Ingredient ingredient=modelMapper.map(ingredientDto,Ingredient.class);
        ingredient=ingredientService.save(ingredient);
        ingredientDto=modelMapper.map(ingredient,IngredientDTO.class);
        return new ResponseEntity<>(ingredientDto,HttpStatus.OK);
    }

    @PutMapping("/ingredient/update")
    public ResponseEntity<IngredientDTO> update(@RequestBody IngredientDTO ingredientDto) {
        Ingredient ingredient;
        try{
            ModelMapper modelMapper=new ModelMapper();
            ingredient=modelMapper.map(ingredientDto,Ingredient.class);
            ingredient=ingredientService.update(ingredient);
            ingredientDto=modelMapper.map(ingredient, IngredientDTO.class);
            return new ResponseEntity<>(ingredientDto,HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update");
        }
    }

    @DeleteMapping("/ingredient/{id}")
    public ResponseEntity<IngredientDTO>delete(@PathVariable("id") Long id){
        Ingredient ingredient;
        IngredientDTO ingredientDTO;
        try {
            ModelMapper modelMapper=new ModelMapper();
            ingredient=ingredientService.delete(id);
            ingredientDTO=modelMapper.map(ingredient,IngredientDTO.class);
            return new ResponseEntity<>(ingredientDTO,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete");
        }

    }


}
