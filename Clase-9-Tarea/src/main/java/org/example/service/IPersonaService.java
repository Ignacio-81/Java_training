package org.example.service;
/**
 * Servicio Persona -

 * Metodos:
 * @insertPersona (Persona persona)
 * @updatePersona PersonaResponseDTO (PersonaDTO personaDTO, Integer id)
 * @deletePersona (Integer id)
 * @listarPersonas List<Persona> ()

 */
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;

import java.util.List;

public interface IPersonaService {

    void insertPersona (Persona persona);
    PersonaResponseDTO updatePersona (PersonaDTO persona , Integer id);
    void deletePersona (Integer id);
    List<Persona> listarPersonas();

}
