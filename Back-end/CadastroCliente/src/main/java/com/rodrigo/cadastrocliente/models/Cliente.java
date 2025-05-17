package com.rodrigo.cadastrocliente.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Length(min = 3, max = 100)
    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Nome inválido")
    private String nome;

    @NotBlank
    private String cpf;
}
