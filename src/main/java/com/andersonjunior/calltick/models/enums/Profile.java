package com.andersonjunior.calltick.models.enums;

public enum Profile {

    ADMINISTRADOR(1, "ROLE_ADMIN"),
    TECNICO(2, "ROLE_TECNICO"),
	ATENDENTE(3, "ROLE_ATENDENTE");

    private int code;
	private String description;
	
	private Perfil(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription () {
		return description;
	}
	
	public static Profile toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (Profile x : Profile.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Inválido: " + code);
	}
    
}
