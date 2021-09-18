package com.andersonjunior.calltick.dto;

import java.io.Serializable;

import com.andersonjunior.calltick.models.Sector;

import org.hibernate.validator.constraints.Length;

public class SectorDto implements Serializable {

    private Integer id;

    @Length(min=5, max=80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String description;

    public SectorDto() {
	}
	
	public SectorDto(Sector obj) {
		id = obj.getId();
		description = obj.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
