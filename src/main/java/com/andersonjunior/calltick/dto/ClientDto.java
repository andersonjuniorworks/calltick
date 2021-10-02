package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.services.validation.ClientInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ClientInsert
public class ClientDto implements Serializable {

    private Long id;
    private Integer type;

    @NotEmpty(message="Preenchimento obrigatório")
    private String document;

    private String stateRegistration;

    @NotEmpty(message="Preenchimento obrigatório")
    private String fullname;
    private String nickname;
    private String zipcode;
    private String address;
    private String homeNumber;
    private String complement;
    private String neighborhood;
    private String state;
    private String city;

    @NotEmpty(message="Preenchimento obrigatório")
    private String phoneNumberOne;

    private String phoneNumberTwo;

    @Email(message="Email inválido")
    private String email;

    private Contract contract;

    private Date registrationDate;
    
}
