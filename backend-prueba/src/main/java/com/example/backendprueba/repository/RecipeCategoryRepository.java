package com.example.backendprueba.repository;

import com.example.backendprueba.entities.Recipe;
import com.example.backendprueba.entities.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
    List<RecipeCategory>findAllByNameStartingWith(String name);

}
