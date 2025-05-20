package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.dtos.request.ClienteRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.EmailRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.EnderecoRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.TelefoneRequestDTO;
import com.rodrigo.cadastrocliente.dtos.response.ClienteResponseDTO;
import com.rodrigo.cadastrocliente.dtos.response.TelefoneResponseDTO;
import com.rodrigo.cadastrocliente.mapper.ClienteMapper;
import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.models.Email;
import com.rodrigo.cadastrocliente.models.Endereco;
import com.rodrigo.cadastrocliente.models.Telefone;
import com.rodrigo.cadastrocliente.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
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
                .peek(dto -> {
                    dto.setCpf(MaskService.maskCPF(dto.getCpf()));

                    if (dto.getEnderecos() != null) {
                        dto.getEnderecos().forEach(endereco ->
                                endereco.setCep(MaskService.maskCEP(endereco.getCep()))
                        );
                    }

                    if (dto.getTelefones() != null) {
                        dto.getTelefones().forEach(telefone ->
                                telefone.setNumero(MaskService.maskTelefone(telefone.getNumero()))
                        );
                    }
                })
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarClientePorId(Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado um cliente com o id " + id));

        cliente.setCpf(MaskService.maskCPF(cliente.getCpf()));
        if (cliente.getEnderecos() != null) {
            cliente.getEnderecos().forEach(endereco ->
                    endereco.setCep(MaskService.maskCEP(endereco.getCep()))
            );
        }

        if (cliente.getTelefones() != null) {
            cliente.getTelefones().forEach(telefone ->
                    telefone.setNumero(MaskService.maskTelefone(telefone.getNumero()))
            );
        }
        return clienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO cadastrarCliente(@Valid ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.toEntity(clienteRequestDTO);

        List<Endereco> enderecos = clienteRequestDTO.getEnderecos().stream()
                .map(dto -> {
                    Endereco viaCep = ViaCepService.buscarEnderecoPorCep(dto.getCep());

                    Endereco endereco = new Endereco();
                    endereco.setCep(dto.getCep());
                    endereco.setLogradouro(viaCep.getLogradouro());
                    endereco.setBairro(viaCep.getBairro());
                    endereco.setCidade(viaCep.getCidade());
                    endereco.setUf(viaCep.getUf());
                    endereco.setCliente(cliente); // MUITO IMPORTANTE

                    return endereco;
                })
                .collect(Collectors.toList());

        cliente.setEnderecos(enderecos);

        Cliente salvar = clienteRepository.save(cliente);
        
        salvar.setCpf(MaskService.maskCPF(salvar.getCpf()));

        if (salvar.getEnderecos() != null) {
            salvar.getEnderecos().forEach(endereco ->
                    endereco.setCep(MaskService.maskCEP(endereco.getCep()))
            );
        }

        if (salvar.getTelefones() != null) {
            salvar.getTelefones().forEach(telefone ->
                    telefone.setNumero(MaskService.maskTelefone(telefone.getNumero()))
            );
        }

        return clienteMapper.toDTO(salvar);
    }

    public ClienteResponseDTO atualizarCliente(Integer id, @Valid ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com id " + id + " não encontrado"));

        clienteExistente.setNome(clienteRequestDTO.getNome());
        clienteExistente.setCpf(clienteRequestDTO.getCpf());

        Map<String, Email> emailsExistentesMap = clienteExistente.getEmails().stream()
                .collect(Collectors.toMap(Email::getEmail, e -> e));

        List<Email> emailsAtualizados = new ArrayList<>();

        for (EmailRequestDTO dto : clienteRequestDTO.getEmails()) {
            Email email;

            if (emailsExistentesMap.containsKey(dto.getEmail())) {
                email = emailsExistentesMap.get(dto.getEmail());
            } else {
                email = new Email();
                email.setEmail(dto.getEmail());
                email.setCliente(clienteExistente);
            }

            emailsAtualizados.add(email);
        }

        clienteExistente.getEmails().clear();
        clienteExistente.getEmails().addAll(emailsAtualizados);

        Cliente cliente = new Cliente();


        Map<Integer, Endereco> enderecosExistentesMap = cliente.getEnderecos().stream()
                .filter(e -> e.getId() != null)
                .collect(Collectors.toMap(Endereco::getId, e -> e));

        List<Endereco> enderecosAtualizados = new ArrayList<>();

        for (Endereco endereco : clienteExistente.getEnderecos()) {
            if (endereco.getId() != null && enderecosExistentesMap.containsKey(endereco.getId())) {
                Endereco existente = enderecosExistentesMap.get(endereco.getId());

                if (!existente.getCep().equals(endereco.getCep())) {
                    Endereco viaCep = ViaCepService.buscarEnderecoPorCep(endereco.getCep());
                    existente.setLogradouro(viaCep.getLogradouro());
                    existente.setBairro(viaCep.getBairro());
                    existente.setCidade(viaCep.getCidade());
                    existente.setUf(viaCep.getUf());
                }

                existente.setCep(endereco.getCep());
                enderecosAtualizados.add(existente);
            } else {
                Endereco viaCep = ViaCepService.buscarEnderecoPorCep(endereco.getCep());
                endereco.setLogradouro(viaCep.getLogradouro());
                endereco.setBairro(viaCep.getBairro());
                endereco.setCidade(viaCep.getCidade());
                endereco.setUf(viaCep.getUf());
                endereco.setCliente(clienteExistente);
                enderecosAtualizados.add(endereco);
            }
        }

        clienteExistente.getEnderecos().clear();
        clienteExistente.getEnderecos().addAll(enderecosAtualizados);

        Map<Integer, Telefone> telefonesExistentesMap = clienteExistente.getTelefones().stream()
                .filter(t -> t.getId() != null)
                .collect(Collectors.toMap(Telefone::getId, t -> t));

        List<Telefone> telefonesAtualizados = new ArrayList<>();

        for (Telefone dto : clienteExistente.getTelefones()) {
            Telefone telefone;

            if (dto.getId() != null && telefonesExistentesMap.containsKey(dto.getId())) {
                telefone = telefonesExistentesMap.get(dto.getId());
                telefone.setNumero(dto.getNumero());
            } else {
                telefone = new Telefone();
                telefone.setNumero(dto.getNumero());
                telefone.setCliente(clienteExistente);
            }

            telefonesAtualizados.add(telefone);
        }

        clienteExistente.getTelefones().clear();
        clienteExistente.getTelefones().addAll(telefonesAtualizados);

        Cliente salvar = clienteRepository.save(clienteExistente);

        salvar.setCpf(MaskService.maskCPF(salvar.getCpf()));

        if (salvar.getEnderecos() != null) {
            salvar.getEnderecos().forEach(endereco ->
                    endereco.setCep(MaskService.maskCEP(endereco.getCep()))
            );
        }

        if (salvar.getTelefones() != null) {
            salvar.getTelefones().forEach(telefone ->
                    telefone.setNumero(MaskService.maskTelefone(telefone.getNumero()))
            );
        }

        return clienteMapper.toDTO(salvar);
    }


    public void deletarCliente(Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não encontrado um cliente com o id " + id));

        clienteRepository.delete(cliente);
    }
}
