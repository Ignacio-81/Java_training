package org.example.service;

import org.example.configuration.ConexionDB;
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;

import java.util.List;

public interface IPersonaService {

    public List<PersonaResponseDTO> getAllPersonas();


    Integer insertPersona(PersonaDTO persona);

    Integer updatePersona (PersonaDTO persona , Integer id);

    int deletePersona (Integer id);

}
