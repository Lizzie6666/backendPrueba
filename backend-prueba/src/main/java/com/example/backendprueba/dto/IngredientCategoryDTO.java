package com.example.backendprueba.dto;

import com.example.backendprueba.entities.Ingredient;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategoryDTO {
    private Long id;
    private String name;
    private List<Ingredient> ingredients ;
}
