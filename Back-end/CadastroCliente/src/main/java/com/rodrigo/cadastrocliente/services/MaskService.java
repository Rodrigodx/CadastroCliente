package com.rodrigo.cadastrocliente.services;

import org.springframework.stereotype.Service;


@Service
public class MaskService {

    public static String maskCPF(String cpf) {
        String CpfTratado = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"); // Correto

        return CpfTratado;
    }



}
