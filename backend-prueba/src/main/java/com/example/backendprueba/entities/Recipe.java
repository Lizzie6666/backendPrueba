package com.example.backendprueba.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String instructions;
    private Integer time;//for cooking
    private String description;
    private String type; //ex. vegetarian, vegan, etc.
    private Boolean favorite;

    @ManyToOne(targetEntity = User.class)
    private Long idUser;

    @OneToOne(targetEntity = RecipeCategory.class)
    private Long idRecipeCategory;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonManagedReference("recipe_items")
    private List<Ingredient> ingredients;


    public Recipe() {
    }

    public Recipe(Long id, String title, String instructions, Integer time, String description, String type, Boolean favorite, Long idUser, Long idRecipeCategory, List<Ingredient> ingredients) {
        this.id = id;
        this.title = title;
        this.instructions = instructions;
        this.time = time;
        this.description = description;
        this.type = type;
        this.favorite = favorite;
        this.idUser = idUser;
        this.idRecipeCategory = idRecipeCategory;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRecipeCategory() {
        return idRecipeCategory;
    }

    public void setIdRecipeCategory(Long idRecipeCategory) {
        this.idRecipeCategory = idRecipeCategory;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
