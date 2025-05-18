package com.rodrigo.cadastrocliente.dtos.response;

import com.rodrigo.cadastrocliente.dtos.request.EmailRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.EnderecoRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.TelefoneRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteResponseDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private List<TelefoneRequestDTO> telefones = new ArrayList<>();

    private List<EmailRequestDTO> emails = new ArrayList<>();

    private List<EnderecoRequestDTO> enderecos = new ArrayList<>();

}
