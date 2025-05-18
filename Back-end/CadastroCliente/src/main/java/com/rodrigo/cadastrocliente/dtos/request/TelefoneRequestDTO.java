package com.rodrigo.cadastrocliente.dtos.request;

import com.rodrigo.cadastrocliente.enums.TelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneRequestDTO {

    private Integer id;

    private String numero;

    private Integer tipo;

    public void setTipo(TelefoneEnum tipo) {
        this.tipo = tipo.cod;
    }

    public TelefoneEnum getTipo() {
        return TelefoneEnum.findByCode(this.tipo).orElse(null);
    }
}
