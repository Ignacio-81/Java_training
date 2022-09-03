package org.example.mapper;


import org.example.dto.response.EmpleadoResponseDTO;
import org.example.entity.Empleado;
import org.modelmapper.ModelMapper;

public class EmpleadoMapperImpl {

    private ModelMapper objMapper = new ModelMapper();

    public EmpleadoResponseDTO EntityToDto(Empleado clienteInput){
        EmpleadoResponseDTO empleadoResponseDTO = new EmpleadoResponseDTO();
        return objMapper.map(clienteInput,EmpleadoResponseDTO.class);
    }

}
