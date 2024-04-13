package com.example.backendprueba.repository;


import com.example.backendprueba.entities.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
}
