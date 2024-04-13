package com.foodia.pruebafoodia.repository;
import com.foodia.pruebafoodia.entities.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {

}
