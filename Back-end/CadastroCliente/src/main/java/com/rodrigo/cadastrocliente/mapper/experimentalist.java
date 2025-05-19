package com.rodrigo.cadastrocliente.mapper;

import com.rodrigo.cadastrocliente.models.Cliente;
import org.mapstruct.*;

@Mapper()
public abstract class experimentalist implements ClienteMapper {

    @AfterMapping
    public void vincularCliente(@MappingTarget Cliente cliente) {
        if (cliente.getTelefones() != null) {
            cliente.getTelefones().forEach(t -> t.setCliente(cliente));
        }
        if (cliente.getEmails() != null) {
            cliente.getEmails().forEach(e -> e.setCliente(cliente));
        }
        if (cliente.getEnderecos() != null) {
            cliente.getEnderecos().forEach(e -> e.setCliente(cliente));
        }
    }
}

