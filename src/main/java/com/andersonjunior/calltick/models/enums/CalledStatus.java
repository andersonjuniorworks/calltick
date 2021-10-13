package com.andersonjunior.calltick.models.enums;

public enum CalledStatus {

    ABERTO(1, "Aberto"),
    FINALIZADO(2, "Finalizado"),
    CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private CalledStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription () {
		return description;
	}
	
	public static CalledStatus toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (CalledStatus x : CalledStatus.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Inválido: " + code);
	}
    
}
