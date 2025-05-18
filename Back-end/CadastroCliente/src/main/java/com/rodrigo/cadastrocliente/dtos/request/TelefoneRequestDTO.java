package com.rodrigo.cadastrocliente.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rodrigo.cadastrocliente.enums.TelefoneEnum;
import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneRequestDTO {

    private Integer id;

    private String numero;

    private Integer tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    public void setTipo(TelefoneEnum tipo) {
        this.tipo = tipo.cod;
    }

    public TelefoneEnum getTipo() {
        return TelefoneEnum.findByCode(this.tipo).orElse(null);
    }
}
