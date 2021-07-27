package com.andersonjunior.calltick.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.calltick.models.User;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto implements Serializable {

    private Integer id;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 a 120 caracteres")
    private String fullname;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public UserDto() {
	}

	public UserDto(User obj) {
		id = obj.getId();
		fullname = obj.getFullname();
		email = obj.getEmail();
	}
    
}
