package com.andersonjunior.calltick.models.enums;

public enum ClientType {

    PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
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
	
	public static ClientType toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (ClientType x : ClientType.values()) {
			if (code.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Inválido: " + code);
	}
    
}
