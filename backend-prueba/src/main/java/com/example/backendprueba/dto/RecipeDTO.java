package com.example.backendprueba.dto;


import com.example.backendprueba.entities.Ingredient;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    Long id;
    private String title;
    private String image;
    private String instructions;
    private Integer time;//for cooking
    private String description;
    private String type; //ex. vegetarian, vegan, etc.
    private Boolean favorite;


    private List<Ingredient> ingredients;
}
