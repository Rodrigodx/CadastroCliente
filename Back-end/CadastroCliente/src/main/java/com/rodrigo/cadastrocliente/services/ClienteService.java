package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.dtos.request.ClienteRequestDTO;
import com.rodrigo.cadastrocliente.dtos.response.ClienteResponseDTO;
import com.rodrigo.cadastrocliente.mapper.ClienteMapper;
import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteResponseDTO> buscarTodosClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarClientePorId(Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado um cliente com o id " + id));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO cadastrarCliente(@Valid ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.toEntity(clienteRequestDTO);
        Cliente salvar = clienteRepository.save(cliente);
        return clienteMapper.toDTO(salvar);
    }

    public ClienteResponseDTO atualizarCliente(Integer id, @Valid ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não encontrado um cliente com o id " + id));

        clienteMapper.toEntity(clienteRequestDTO);
        Cliente salvar = clienteRepository.save(cliente);

        return clienteMapper.toDTO(salvar);

    }

    public void deletarCliente(Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não encontrado um cliente com o id " + id));

        clienteRepository.delete(cliente);
    }
}
