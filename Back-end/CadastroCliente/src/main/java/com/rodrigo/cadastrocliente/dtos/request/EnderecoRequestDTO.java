package com.rodrigo.cadastrocliente.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoRequestDTO {

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    private String complemento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Valid
    private Cliente cliente;

}
