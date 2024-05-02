package com.example.backendprueba.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String image;
    private String instructions;
    private Integer time;//for cooking
    private String description;
    private String type; //ex. vegetarian, vegan, etc.
    private Boolean favorite;
    @ManyToMany (mappedBy = "ingredientsRecipe",cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JsonBackReference
    private Set<Ingredient> ingredients=new HashSet<>();

}
