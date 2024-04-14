package com.example.backendprueba.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length=50,nullable = false)
    private String name;
    private Long calories;

    @ManyToOne(targetEntity = IngredientCategory.class)
    @JoinColumn(name = "ingredientCategory_id", referencedColumnName = "id")
    @JsonBackReference("ingredient_items")
    private IngredientCategory ingredientCategory;

}
