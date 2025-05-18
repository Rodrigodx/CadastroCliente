package com.rodrigo.cadastrocliente.dtos.response;

import com.rodrigo.cadastrocliente.dtos.ClienteSimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoResponseDTO {

    private Integer id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String uf;

    private String complemento;

}
