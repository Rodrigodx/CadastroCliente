package com.rodrigo.cadastrocliente.enums;
import java.util.Arrays;
import java.util.Optional;

public enum TelefoneEnum {

    RESIDENCIAL(1),
    COMERCIAL(2),
    CELULAR(3);

    public final Integer cod;

    private TelefoneEnum(Integer cod) {
        this.cod = cod;
    }

    public static Optional<TelefoneEnum> findByCode(Integer cod) {
        return Arrays.stream(values())
                .filter(t -> t.cod.equals(cod))
                .findFirst();
    }
}
