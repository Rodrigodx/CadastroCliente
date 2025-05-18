package com.rodrigo.cadastrocliente.dtos.response;

import com.rodrigo.cadastrocliente.enums.TelefoneEnum;
import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneResponseDTO {

    private Integer id;

    private String numero;

    private Integer tipo;

    private Cliente cliente;

    public void setTipo(TelefoneEnum tipo) {
        this.tipo = tipo.cod;
    }

    public TelefoneEnum getTipo() {
        return TelefoneEnum.findByCode(this.tipo).orElse(null);
    }
}
