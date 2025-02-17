package com.andersonjunior.calltick.dto;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private Long id;
    @Length(max=80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String description;
    private Double price;
}
