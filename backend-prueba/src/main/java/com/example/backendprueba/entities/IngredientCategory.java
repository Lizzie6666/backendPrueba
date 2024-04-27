package com.example.backendprueba.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientCategory_id")
    private Long id;
    @Column(length = 20,nullable = false)
    private String name;

    //OneToMany Unidireccional IngreCat - Ingredient
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="ingredientCategory_id",referencedColumnName = "ingredientCategory_id")
    private List<Ingredient> ingredients =new ArrayList<>();

}
