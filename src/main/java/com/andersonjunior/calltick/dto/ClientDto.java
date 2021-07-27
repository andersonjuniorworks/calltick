package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ClientDto implements Serializable {

    private Integer id;
    private Integer type;
    @NotEmpty(message="Preenchimento obrigatório")
    private String cpfOrCnpj;
    @NotEmpty(message="Preenchimento obrigatório")
    private String fullname;
    private String nickname;
    private String zipcode;
    private String address;
    private String homeNumber;
    private String complement;
    private String state;
    private String city;
    @NotEmpty(message="Preenchimento obrigatório")
    private String phoneNumberOne;
    private String phoneNumberTwo;
    private String email;
    private Date registrationDate;
    
}
