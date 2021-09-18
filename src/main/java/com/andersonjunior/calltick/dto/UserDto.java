package com.andersonjunior.calltick.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.calltick.models.User;

import org.hibernate.validator.constraints.Length;

public class UserDto implements Serializable {

    private Long id;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
