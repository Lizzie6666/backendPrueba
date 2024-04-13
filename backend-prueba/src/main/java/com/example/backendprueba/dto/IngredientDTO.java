package com.example.backendprueba.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class IngredientDTO {
    private Long id;
    private String name;
    private Long calorias;
}
