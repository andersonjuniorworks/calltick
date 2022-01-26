package com.andersonjunior.calltick.models.enums;

public enum UserStatus {

    ONLINE(1, "Online"),
    OFFLINE(2, "Offline");

    private int code;
    private String description;

    private UserStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static UserStatus toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (UserStatus x : UserStatus.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("ID Inválido: " + code);
    }

}
