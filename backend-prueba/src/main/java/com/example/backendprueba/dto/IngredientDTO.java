package com.example.backendprueba.dto;


import com.example.backendprueba.entities.Recipe;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private String name;
    private Long calories;
    private Set<Recipe>recipes;
}
