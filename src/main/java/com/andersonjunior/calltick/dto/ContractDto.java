package com.andersonjunior.calltick.dto;

import org.hibernate.validator.constraints.Length;

public class ContractDto {
    private Long id;
    @Length(min=5, max=80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String description;
    private Double price;
}
