package com.upc.foodia.repository;

import com.upc.foodia.entities.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
}
