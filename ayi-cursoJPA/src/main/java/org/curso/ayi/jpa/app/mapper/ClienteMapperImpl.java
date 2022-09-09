package org.curso.ayi.jpa.app.mapper;

import org.curso.ayi.jpa.app.dto.response.ClienteResponseDTO;
import org.curso.ayi.jpa.app.entity.Cliente;
import org.modelmapper.ModelMapper;

public class ClienteMapperImpl {
    private ModelMapper objMapper = new ModelMapper();

    public ClienteResponseDTO EntityToDto(Cliente clienteInput){
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        return objMapper.map(clienteInput,ClienteResponseDTO.class);
    }
}
