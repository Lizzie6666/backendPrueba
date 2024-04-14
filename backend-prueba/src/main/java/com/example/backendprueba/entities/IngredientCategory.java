package com.example.backendprueba.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20,nullable = false)
    private String name;
    @OneToMany(mappedBy = "ingredientCategory", cascade = CascadeType.ALL)
    @JsonManagedReference("ingredientCategory_items")
    private List<Ingredient> ingredients ;

}
