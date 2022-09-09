package org.example.controller;
/**
 * Implementacion Controller Persona

 * Metodos:
 * @listarPersonas List<PersonaResponseDTO> ()
 * @agregarPersona (PersonaDTO personas)
 * @modificarPersona Integer (PersonaDTO personaDTO, Integer id)
 * @borrarPersona int (Integer id)

 */
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;
import org.example.service.IPersonaService;
import org.example.service.IServicios;
import org.example.service.Impl.PersonaServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    IPersonaService iPersonaServicios = new PersonaServiceImpl();

    public List<PersonaResponseDTO> listarPersonas() {
        return iPersonaServicios.getAllPersonas();
    }

    public void agregarPersona(PersonaDTO persona){
        iPersonaServicios.insertPersona(persona);
    }

    public Integer modificarPersona(PersonaDTO persona, Integer id) {
        return iPersonaServicios.updatePersona(persona, id);
    }

    public int borrarPersona(Integer id){
        return iPersonaServicios.deletePersona(id);
    }
/*
    public List<Persona> listarPersonas(){
        return iPersonaServicios.listarPersonas();
    }

    public PersonaResponseDTO modificarPersona(PersonaDTO persona, Integer id){
        return iPersonaServicios.updatePersona(persona, id);
    }*/

    /*
    private IServicios iServiciosPersona = new PersonaServiceImpl();


    public void agregarPersona (String nombre, String apellido){

        iServiciosPersona.insert(nombre, apellido);
    }
    public void borrarPersona (Integer id){
        iServiciosPersona.delete(id);
    }

    public String listarPersonas (){
        return iServiciosPersona.listarTodos();
    }

    public String modificarPersona(String nombre, String apellido, Integer id){
        return iServiciosPersona.update(nombre, apellido, id);
    }
*/
}
