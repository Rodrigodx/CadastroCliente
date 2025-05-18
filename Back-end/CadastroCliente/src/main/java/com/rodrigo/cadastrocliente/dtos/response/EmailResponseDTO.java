package com.rodrigo.cadastrocliente.dtos.response;

import com.rodrigo.cadastrocliente.dtos.ClienteSimpleDTO;
import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailResponseDTO {

    private Integer id;

    private String email;

    private ClienteSimpleDTO cliente;
}
