package com.rodrigo.cadastrocliente.mapper;

import com.rodrigo.cadastrocliente.dtos.request.ClienteRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.EmailRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.EnderecoRequestDTO;
import com.rodrigo.cadastrocliente.dtos.request.TelefoneRequestDTO;
import com.rodrigo.cadastrocliente.dtos.response.ClienteResponseDTO;
import com.rodrigo.cadastrocliente.dtos.response.EmailResponseDTO;
import com.rodrigo.cadastrocliente.dtos.response.EnderecoResponseDTO;
import com.rodrigo.cadastrocliente.dtos.response.TelefoneResponseDTO;
import com.rodrigo.cadastrocliente.models.Cliente;
import com.rodrigo.cadastrocliente.models.Email;
import com.rodrigo.cadastrocliente.models.Endereco;
import com.rodrigo.cadastrocliente.models.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDTO dto);
    ClienteResponseDTO toDTO(Cliente entity);

    Telefone toEntity(TelefoneRequestDTO dto);
    TelefoneResponseDTO toDTO(Telefone entity);

    Email toEntity(EmailRequestDTO dto);
    EmailResponseDTO toDTO(Email entity);

    Endereco toEntity(EnderecoRequestDTO dto);
    EnderecoResponseDTO toDTO(Endereco entity);

    List<Telefone> toEntityTelefones(List<TelefoneRequestDTO> dtos);
    List<TelefoneResponseDTO> toDTOsTelefones(List<Telefone> entities);

    List<Email> toEntityEmails(List<EmailRequestDTO> dtos);
    List<EmailResponseDTO> toDTOsEmails(List<Email> entities);

    List<Endereco> toEntityEnderecos(List<EnderecoRequestDTO> dtos);
    List<EnderecoResponseDTO> toDTOsEnderecos(List<Endereco> entities);

}
