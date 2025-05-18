package com.rodrigo.cadastrocliente.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {

    @Length(min = 3, max = 100)
    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Nome inválido")
    private String nome;

    @NotBlank
    private String cpf;

    @NotEmpty
    @Valid
    private List<TelefoneRequestDTO> telefones = new ArrayList<>();

    @NotEmpty
    @Valid
    private List<EmailRequestDTO> emails = new ArrayList<>();

    @NotEmpty
    @Valid
    private List<EnderecoRequestDTO> enderecos = new ArrayList<>();
}
