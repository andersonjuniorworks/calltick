package com.andersonjunior.calltick.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CredentialsDto implements Serializable {
    
    private String email;
    private String password;

}
