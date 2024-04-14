package com.example.backendprueba.controller;

import com.example.backendprueba.dto.IngredientDTO;
import com.example.backendprueba.entities.Ingredient;
import com.example.backendprueba.service.IngredientService;
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
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    Logger logger = LoggerFactory.getLogger(IngredientController.class);
    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDTO>> list(){
        List<Ingredient> list;
        List<IngredientDTO> listDto;
        try {
            list=ingredientService.list();
            listDto=convertToListDTO(list);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can´t list");
        }
        return new ResponseEntity<List<IngredientDTO>>(listDto,HttpStatus.OK);
    }
    @PostMapping("/ingredient")
    public ResponseEntity<IngredientDTO> save(@RequestBody IngredientDTO ingredientDto){
        Ingredient ingredient=convertToEntity(ingredientDto);
        IngredientDTO ingredientDTO;
        try{
            ingredient = ingredientService.register(ingredient);
            ingredientDTO=convertToDTO(ingredient);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not register");
        }
        return new ResponseEntity<IngredientDTO>(ingredientDTO,HttpStatus.OK);
    }

    @PutMapping("/ingredient/update")
    public ResponseEntity<IngredientDTO> update(@RequestBody IngredientDTO ingredientDto) {
        IngredientDTO ingredientDTO;
        Ingredient ingredient;
        try{
            ingredient=convertToEntity(ingredientDto);
            logger.debug("Update recipe");
            ingredient = ingredientService.update(ingredient);
            logger.debug("Update recipe");
            ingredientDTO=convertToDTO(ingredient);
        } catch (Exception e) {
            logger.error("Update error",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not update");
        }
        return null;
    }

    @DeleteMapping("/ingredient/{id}")
    public ResponseEntity<IngredientDTO>delete(@PathVariable(value = "id") Long id){
        Ingredient ingredient;
        IngredientDTO ingredientDTO;
        try{
            ingredient=ingredientService.delete(id);
            logger.debug("deleted recipe");
            ingredientDTO = convertToDTO(ingredient);
        } catch (Exception e) {
            logger.error("Deleted error",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not deleted");
        }
        return null;
    }

    private Ingredient convertToEntity(IngredientDTO ingredientDTO){
        ModelMapper modelMapper = new ModelMapper();
        Ingredient i= modelMapper.map(ingredientDTO, Ingredient.class);
        return i;
    }
    private IngredientDTO convertToDTO(Ingredient ingredient){
        ModelMapper modelMapper =new ModelMapper();
        IngredientDTO ingredientDTO=modelMapper.map(ingredient,IngredientDTO.class);
        return ingredientDTO;
    }

    private List<IngredientDTO> convertToListDTO(List<Ingredient> list){
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
