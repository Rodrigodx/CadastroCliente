package com.rodrigo.cadastrocliente.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Length(min = 3, max = 100)
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String nome;

    @NotNull
    @NotBlank
    private String cpf;

}
