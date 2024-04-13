package com.example.backendprueba.dto;

import com.upc.foodia.entities.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RecipeDTO {
    Long id;
    private String title;
    private String instructions;
    private Integer time;//for cooking
    private String description;
    private String type; //ex. vegetarian, vegan, etc.
    private Boolean favorite;
    private Long idUser;

    private Long idRecipeCategory;

    private List<Ingredient> ingredients;
}
