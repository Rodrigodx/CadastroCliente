package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private static ClienteRepository clienteRepository;

    public List<Cliente> buscarTodosClientes(){
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
