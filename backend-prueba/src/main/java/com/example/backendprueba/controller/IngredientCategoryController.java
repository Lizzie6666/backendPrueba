package com.example.backendprueba.controller;
import com.example.backendprueba.dto.IngredientCategoryDTO;
import com.example.backendprueba.dto.RecipeDTO;
import com.example.backendprueba.entities.IngredientCategory;
import com.example.backendprueba.service.IngredientCategoryService;
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
public class IngredientCategoryController {
    @Autowired
    private IngredientCategoryService ingredientCategoryService;
    Logger logger = LoggerFactory.getLogger(IngredientCategoryController.class);
    @GetMapping("/ingredientsCategory")
    public ResponseEntity<List<IngredientCategoryDTO>> list(){
        List<IngredientCategory> list;
        List<IngredientCategoryDTO> listDto;
        try {
            list=ingredientCategoryService.list();
            listDto=convertToListDTO(list);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can´t list");
        }
        return new ResponseEntity<List<IngredientCategoryDTO>>(listDto,HttpStatus.OK);
    }
    @PostMapping("/ingredientCategory")
    public ResponseEntity<IngredientCategoryDTO>register(@RequestBody IngredientCategoryDTO ingredientCategoryDTO)
    {
        IngredientCategory ingredientCategory=convertToEntity(ingredientCategoryDTO);
        IngredientCategoryDTO ingredientCategoryDTO1;
        try {
            ingredientCategory = ingredientCategoryService.register(ingredientCategory);
            ingredientCategoryDTO1 = convertToDTO(ingredientCategory);
        }catch (Exception e){
            e.printStackTrace();
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Could not register");
        }
        return new ResponseEntity<IngredientCategoryDTO>(ingredientCategoryDTO1, HttpStatus.OK);
    }


    @PutMapping("/ingredientCategory")
    public ResponseEntity<IngredientCategoryDTO> update(@RequestBody IngredientCategoryDTO ingredientCategoryDto){

        IngredientCategory ingredientCategory=convertToEntity(ingredientCategoryDto);
        IngredientCategoryDTO ingredientCategoryDTO;
        try{
            ingredientCategory=convertToEntity(ingredientCategoryDto);
            logger.debug("Update ingredient");
            ingredientCategory=ingredientCategoryService.update(ingredientCategory);
            logger.debug("Update ingredient");
            ingredientCategoryDTO=convertToDTO(ingredientCategory);
        }catch (Exception e){
            logger.error("Update error", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not update");
        }
        return null;
    }
    @DeleteMapping("/ingredientCategory/{id}")
    public ResponseEntity<IngredientCategory>delete(@PathVariable(value = "id") Long id){
        IngredientCategory ingredientCategory;
        IngredientCategoryDTO ingredientCategoryDTO;
        try{
            ingredientCategory=ingredientCategoryService.delete(id);
            logger.debug("Deleted Ingredient Category");
            ingredientCategoryDTO=convertToDTO(ingredientCategory);
        }catch (Exception e){
            logger.error("Deleted error",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not deleted");
        }
        return null;
    }

    private IngredientCategory convertToEntity(IngredientCategoryDTO ingredientCategoryDTO){
        ModelMapper modelMapper = new ModelMapper();
        IngredientCategory ingredientCategory= modelMapper.map(ingredientCategoryDTO, IngredientCategory.class);
        return ingredientCategory;
    }
    private IngredientCategoryDTO convertToDTO(IngredientCategory ingredientCategory){
        ModelMapper modelMapper =new ModelMapper();
        IngredientCategoryDTO i=modelMapper.map(ingredientCategory,IngredientCategoryDTO.class);
        return i;
    }

    private List<IngredientCategoryDTO> convertToListDTO(List<IngredientCategory> list){
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
