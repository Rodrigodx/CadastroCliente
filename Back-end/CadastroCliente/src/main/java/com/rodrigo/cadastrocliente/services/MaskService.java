package com.rodrigo.cadastrocliente.services;

import org.springframework.stereotype.Service;


@Service
public class MaskService {

    public static String maskCPF(String cpf) {
        String CpfTratado = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        return CpfTratado;
    }

    public static String maskCEP(String cep) {
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    public static String maskTelefone(String telefone) {
        if (telefone.length() == 11) {
            return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else if (telefone.length() == 10) {
            return telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else {
            return telefone;
        }
    }



}
