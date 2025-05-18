package com.rodrigo.cadastrocliente.enums;

public enum RoleEnum {
    ROLE_ADMIN("admin"),
    ROLE_USUARIO("user");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

}
