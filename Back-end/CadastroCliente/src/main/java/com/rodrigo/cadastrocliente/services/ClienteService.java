package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.models.Email;
import com.rodrigo.cadastrocliente.models.Telefone;
import com.rodrigo.cadastrocliente.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarTodosClientes(){
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(@Valid Cliente cliente){
        for (Telefone tel : cliente.getTelefones()) {
            tel.setCliente(cliente); // <- isso é essencial
        }

        for (Email email : cliente.getEmails()) {
            email.setCliente(cliente);
        }
        return clienteRepository.save(cliente);
    }
}
