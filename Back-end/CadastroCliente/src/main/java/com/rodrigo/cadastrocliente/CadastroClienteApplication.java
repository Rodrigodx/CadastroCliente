package com.rodrigo.cadastrocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CadastroClienteApplication {

    public static void main(String[] args) {

        SpringApplication.run(CadastroClienteApplication.class, args);

    }
}
