
package com.example.backendprueba.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import com.example.backendprueba.entities.Recipe;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipeCategory")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
   @OneToMany(mappedBy = "recipeCategory", cascade = CascadeType.ALL)
   @JsonManagedReference("recipeCategory_items")
    private List<Recipe> recipes ;
}
