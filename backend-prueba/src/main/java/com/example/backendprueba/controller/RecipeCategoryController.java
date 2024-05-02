package com.example.backendprueba.controller;

import com.example.backendprueba.dto.RecipeCategoryDTO;
import com.example.backendprueba.entities.RecipeCategory;
import com.example.backendprueba.service.RecipeCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/recipecategory")
public class RecipeCategoryController {


    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @PostMapping("/save")
    public ResponseEntity<RecipeCategoryDTO> save(@RequestBody RecipeCategoryDTO recipeCategoryDTO){
        ModelMapper modelMapper=new ModelMapper();
        RecipeCategory recipeCategory=modelMapper.map(recipeCategoryDTO,RecipeCategory.class);
        recipeCategory=recipeCategoryService.save(recipeCategory);
        recipeCategoryDTO=modelMapper.map(recipeCategory,RecipeCategoryDTO.class);
        return new ResponseEntity<>(recipeCategoryDTO,HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<RecipeCategoryDTO>> list(){
        ModelMapper modelMapper=new ModelMapper();
        List<RecipeCategoryDTO>rec= Arrays.asList(
                modelMapper.map(recipeCategoryService.list(),
                        RecipeCategoryDTO[].class)
        );
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }

    @PutMapping("/update")
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

    @DeleteMapping("/delete/{id}")
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
    @GetMapping("/search/{name}")
    public ResponseEntity<List<RecipeCategoryDTO>>search(@PathVariable("name") String name){
        try{
            ModelMapper modelMapper=new ModelMapper();
            List<RecipeCategoryDTO>rec= Arrays.asList(
                    modelMapper.map(recipeCategoryService.search(name),
                            RecipeCategoryDTO[].class)
            );
            return new ResponseEntity<>(rec,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe category not found");
        }
    }

}
