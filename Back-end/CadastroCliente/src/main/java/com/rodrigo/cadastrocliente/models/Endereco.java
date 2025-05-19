package com.rodrigo.cadastrocliente.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Endereco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String uf;

    private String complemento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
}
