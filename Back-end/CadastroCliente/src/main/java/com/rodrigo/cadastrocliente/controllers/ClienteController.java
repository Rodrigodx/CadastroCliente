package com.rodrigo.cadastrocliente.controllers;

import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cliente")
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getClientes() {
        return clienteService.buscarTodosClientes();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente postCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.cadastrarCliente(cliente);
    }

}
