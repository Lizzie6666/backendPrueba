package com.foodia.pruebafoodia.controller;


import com.foodia.pruebafoodia.dto.RecipeCategoryDTO;
import com.foodia.pruebafoodia.entities.RecipeCategory;
import com.foodia.pruebafoodia.service.RecipeCategoryService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecipeCategoryController {
    Logger logger = LoggerFactory.getLogger(RecipeCategoryController.class);
    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @PostMapping("/recipecategory")
    public ResponseEntity<RecipeCategoryDTO> register(@RequestBody RecipeCategoryDTO recipeCategoryDTO){
        RecipeCategory recipeCategory = convertToEntity(recipeCategoryDTO);
        RecipeCategoryDTO recipeCategoryDTO1;
        try {
            recipeCategory = recipeCategoryService.register(recipeCategory);
            recipeCategoryDTO1 = convertToDto(recipeCategory);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not register");
        }
        return new ResponseEntity<RecipeCategoryDTO>(recipeCategoryDTO1, HttpStatus.OK);
    }
    @GetMapping("/recipecategories")
    public ResponseEntity<List<RecipeCategoryDTO>> list(){
        List<RecipeCategory> list;
        List<RecipeCategoryDTO> listDto;
        try{
            list = recipeCategoryService.listado();
            listDto = convertToLisDto(list);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo listar");
        }
        return new ResponseEntity<List<RecipeCategoryDTO>>(listDto,HttpStatus.OK);
    }
    @PutMapping("/recipecategory")
    public ResponseEntity<RecipeCategoryDTO> update(@RequestBody RecipeCategoryDTO recipeCategoryDTO) {
        RecipeCategoryDTO recipeCategoryDTO1;
        RecipeCategory recipeCategory;
        try {
            recipeCategory = convertToEntity(recipeCategoryDTO);
            logger.debug("Updated recipe");
            recipeCategory = recipeCategoryService.update(recipeCategory);
            logger.debug("Updated recipe");
            recipeCategoryDTO = convertToDto(recipeCategory);
            return new ResponseEntity<RecipeCategoryDTO>(recipeCategoryDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Update error", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    @DeleteMapping("/recipecategory/{id}")
    public ResponseEntity<RecipeCategoryDTO> delete(@PathVariable(value = "id") Long id){
        RecipeCategory recipeCategory;
        RecipeCategoryDTO recipeCategoryDTO;
        try {
            recipeCategory = recipeCategoryService.delete(id);
            logger.debug("Deleted recipe");
            recipeCategoryDTO= convertToDto(recipeCategory);
            return new ResponseEntity<RecipeCategoryDTO>(recipeCategoryDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Deletion error ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }

    private List<RecipeCategoryDTO> convertToLisDto(List<RecipeCategory> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private RecipeCategory convertToEntity(RecipeCategoryDTO recipeCategoryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        RecipeCategory post = modelMapper.map(recipeCategoryDTO, RecipeCategory.class);
        return post;
    }

    private RecipeCategoryDTO convertToDto(RecipeCategory recipeCategory) {
        ModelMapper modelMapper = new ModelMapper();
        RecipeCategoryDTO recipeCategoryDTO = modelMapper.map(recipeCategory, RecipeCategoryDTO.class);
        return recipeCategoryDTO;
    }


}
