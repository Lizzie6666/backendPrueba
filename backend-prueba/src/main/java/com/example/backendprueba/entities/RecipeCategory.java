package com.foodia.pruebafoodia.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Getter //manera mas rapida
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipeCategory")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
  /* @OneToMany(mappedBy = "recipeCategory", cascade = CascadeType.ALL)
    private Set<Recipe> recipe = new HashSet<>();*/
}
