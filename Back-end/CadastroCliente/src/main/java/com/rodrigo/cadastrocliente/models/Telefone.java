package com.rodrigo.cadastrocliente.models;

import com.rodrigo.cadastrocliente.enums.TelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
