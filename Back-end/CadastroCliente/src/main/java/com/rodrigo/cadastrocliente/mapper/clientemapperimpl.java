package com.rodrigo.cadastrocliente.mapper;

import com.rodrigo.cadastrocliente.models.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class clientemapperimpl implements ClienteMapper {

    @AfterMapping
    protected void vincularCliente(@MappingTarget Cliente cliente) {
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

