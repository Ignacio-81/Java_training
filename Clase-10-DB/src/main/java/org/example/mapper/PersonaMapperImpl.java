package org.example.mapper;

import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;
import org.modelmapper.ModelMapper;

public class PersonaMapperImpl {

    private ModelMapper objMapper = new ModelMapper();

    public PersonaResponseDTO EntityToDto(Persona personaInput){
        PersonaResponseDTO personaResponseDTO = new PersonaResponseDTO();
        return objMapper.map(personaInput,PersonaResponseDTO.class);
    }
}
