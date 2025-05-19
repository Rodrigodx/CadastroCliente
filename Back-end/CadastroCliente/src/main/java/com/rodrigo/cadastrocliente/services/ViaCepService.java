package com.rodrigo.cadastrocliente.services;

import com.rodrigo.cadastrocliente.dtos.request.ViaCepRequestDTO;
import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.models.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Endereco buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepRequestDTO dto = restTemplate.getForObject(url, ViaCepRequestDTO.class);

        if (dto == null || dto.getCep() == null) {
            throw new RuntimeException("CEP invalido ou não encontrado");
        }


        Cliente cliente = new Cliente();

        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep().replace("-", ""));
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getLocalidade());
        endereco.setUf(dto.getUf());
        endereco.setCliente(cliente);
        return endereco;
    }

}
