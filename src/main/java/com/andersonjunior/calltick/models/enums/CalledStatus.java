package com.andersonjunior.calltick.models.enums;

public enum CalledStatus {

    ABERTO(1, "Aberto"),
	PENDENTE(2, "Pendente"),
    FINALIZADO(3, "Finalizado"),
    CANCELADO(4, "Cancelado");
	
	private int code;
	private String description;
	
	private TipoCliente(int code, String description) {
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
			if (code.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Inválido: " + code);
	}
    
}
