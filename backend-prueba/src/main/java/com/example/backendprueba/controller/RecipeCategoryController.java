package com.example.backendprueba.controller;

import com.example.backendprueba.dto.RecipeCategoryDTO;
import com.example.backendprueba.dto.RecipeDTO;
import com.example.backendprueba.entities.RecipeCategory;
import com.example.backendprueba.service.RecipeCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecipeCategoryController {


    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @PostMapping("/recipecategory")
    public ResponseEntity<RecipeCategoryDTO> register(@RequestBody RecipeCategoryDTO recipeCategoryDTO){
        ModelMapper modelMapper=new ModelMapper();
        RecipeCategory recipeCategory=modelMapper.map(recipeCategoryDTO,RecipeCategory.class);
        recipeCategory=recipeCategoryService.save(recipeCategory);
        recipeCategoryDTO=modelMapper.map(recipeCategory,RecipeCategoryDTO.class);
        return new ResponseEntity<>(recipeCategoryDTO,HttpStatus.OK);
    }
    @GetMapping("/recipecategories")
    public ResponseEntity<List<RecipeCategoryDTO>> list(){
        ModelMapper modelMapper=new ModelMapper();
        List<RecipeCategoryDTO>rec= Arrays.asList(
                modelMapper.map(recipeCategoryService.list(),
                        RecipeCategoryDTO[].class)
        );
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }

    @PutMapping("/recipecategory/update")
    public ResponseEntity<RecipeCategoryDTO> update(@RequestBody RecipeCategoryDTO recipeCategoryDTO) {

        RecipeCategory recipeCategory;
        try {
            ModelMapper modelMapper=new ModelMapper();
            recipeCategory=modelMapper.map(recipeCategoryDTO,RecipeCategory.class);
            recipeCategory=recipeCategoryService.update(recipeCategory);
            recipeCategoryDTO=modelMapper.map(recipeCategory,RecipeCategoryDTO.class);
            return new ResponseEntity<RecipeCategoryDTO>(recipeCategoryDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update");
        }
    }

    @DeleteMapping("/recipecategory/{id}")
    public ResponseEntity<RecipeCategoryDTO> delete(@PathVariable("id") Long id){
        RecipeCategory recipeCategory;
        RecipeCategoryDTO recipeCategoryDTO;
        try{
            ModelMapper modelMapper=new ModelMapper();
            recipeCategory=recipeCategoryService.delete(id);
            recipeCategoryDTO=modelMapper.map(recipeCategory, RecipeCategoryDTO.class);
            return new ResponseEntity<>(recipeCategoryDTO,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete");
        }
    }

}
