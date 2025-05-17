package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.models.Cliente;
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
        return clienteRepository.save(cliente);
    }
}
