package com.example.backendprueba.dto;


import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private String name;
    private Long calories;

}
