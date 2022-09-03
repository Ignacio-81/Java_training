package org.example.mapper;
/**
 * Mapper para Cliente - Entity to DTO

 */
import org.example.dto.response.ClienteResponseDTO;

import org.example.entity.Cliente;

import org.modelmapper.ModelMapper;

public class ClienteMapperImpl {

    private ModelMapper objMapper = new ModelMapper();

    public ClienteResponseDTO EntityToDto(Cliente clienteInput){
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        return objMapper.map(clienteInput,ClienteResponseDTO.class);
    }
}
