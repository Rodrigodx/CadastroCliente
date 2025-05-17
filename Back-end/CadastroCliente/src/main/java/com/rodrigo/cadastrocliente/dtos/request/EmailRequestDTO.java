package com.rodrigo.cadastrocliente.dtos.request;

import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequestDTO {

    private String email;

    private Cliente cliente;

}
