
package com.example.backendprueba.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import com.example.backendprueba.entities.Recipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipeCategory_id")
    private Long id;

    private String name;

    //OnoToMany unidireccional RecipCat - Recipe
   @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
   @JoinColumn(name="recipeCategory_id",referencedColumnName = "recipeCategory_id")
    private List<Recipe> recipes =new ArrayList<>();
}
