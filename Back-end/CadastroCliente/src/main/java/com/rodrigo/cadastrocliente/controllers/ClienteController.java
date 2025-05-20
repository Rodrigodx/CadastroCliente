package com.rodrigo.cadastrocliente.controllers;

import com.rodrigo.cadastrocliente.dtos.request.ClienteRequestDTO;
import com.rodrigo.cadastrocliente.dtos.response.ClienteResponseDTO;
import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<ClienteResponseDTO> getClientes() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarClientePorId(@PathVariable Integer id){
        return clienteService.buscarClientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO postCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        return clienteService.cadastrarCliente(clienteRequestDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO updateCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, @PathVariable Integer id){
        return clienteService.atualizarCliente(id, clienteRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
        clienteService.deletarCliente(id);
    }



}
