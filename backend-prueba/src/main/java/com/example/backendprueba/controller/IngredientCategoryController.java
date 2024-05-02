package com.example.backendprueba.controller;
import com.example.backendprueba.dto.IngredientCategoryDTO;
import com.example.backendprueba.entities.IngredientCategory;
import com.example.backendprueba.service.IngredientCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/ingredientsCategory")
public class IngredientCategoryController {
    @Autowired
    private IngredientCategoryService ingredientCategoryService;

    @GetMapping("/list")
    public ResponseEntity<List<IngredientCategoryDTO>> list(){
       ModelMapper modelMapper=new ModelMapper();
       List<IngredientCategoryDTO>ing= Arrays.asList(
               modelMapper.map(ingredientCategoryService.list(),
                       IngredientCategoryDTO[].class)
       );
       return new ResponseEntity<>(ing,HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<IngredientCategoryDTO>save(@RequestBody IngredientCategoryDTO ingredientCategoryDTO)
    {
        ModelMapper modelMapper=new ModelMapper();
        IngredientCategory ingredientCategory=modelMapper.map(ingredientCategoryDTO,IngredientCategory.class);
        ingredientCategory=ingredientCategoryService.save(ingredientCategory);
        ingredientCategoryDTO=modelMapper.map(ingredientCategory,IngredientCategoryDTO.class);
        return new ResponseEntity<>(ingredientCategoryDTO,HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<IngredientCategoryDTO> update(@RequestBody IngredientCategoryDTO ingredientCategoryDto){

        IngredientCategory ingredientCategory;
        try{
            ModelMapper modelMapper=new ModelMapper();
            ingredientCategory=modelMapper.map(ingredientCategoryDto,IngredientCategory.class);
            ingredientCategory=ingredientCategoryService.update(ingredientCategory);
            ingredientCategoryDto=modelMapper.map(ingredientCategory,IngredientCategoryDTO.class);
            return new ResponseEntity<>(ingredientCategoryDto,HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<IngredientCategoryDTO>delete(@PathVariable("id") Long id){
        IngredientCategory ingredientCategory;
        IngredientCategoryDTO ingredientCategoryDTO;
        try {
            ModelMapper modelMapper=new ModelMapper();
            ingredientCategory=ingredientCategoryService.delete(id);
            ingredientCategoryDTO=modelMapper.map(ingredientCategory,IngredientCategoryDTO.class);
            return new ResponseEntity<>(ingredientCategoryDTO,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete");
        }
    }


}
