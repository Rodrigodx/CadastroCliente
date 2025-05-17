package com.rodrigo.cadastrocliente.dtos.response;

import com.rodrigo.cadastrocliente.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailResponseDTO {

    private Integer id;

    private String email;

    private Cliente cliente;
}
