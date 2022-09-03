package org.example.service.Impl;
/**
 * Implementacion Servicio Persona - Implementa IPersonaService

 * Metodos:
 * @insertPersona (Persona persona)
 * @updatePersona PersonaResponseDTO (PersonaDTO personaDTO, Integer id)
 * @deletePersona (Integer id)
 * @listarPersonas List<Persona> ()

 */
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;
import org.example.mapper.PersonaMapperImpl;
import org.example.service.IPersonaService;
import org.example.service.IServicios;

import java.util.ArrayList;
import java.util.List;

public class PersonaServiceImpl implements IPersonaService {
    private PersonaMapperImpl personaMapperImpl = new PersonaMapperImpl();

    @Override
    public void insertPersona(Persona persona) {
        Persona persona1 = new Persona();
        persona1 = persona;
        System.out.println("Insertando datos de persona: " + persona1.toString());
    }

    @Override
    public PersonaResponseDTO updatePersona(PersonaDTO personaDTO, Integer id) {
        Persona personaModificada = new Persona();

        //recupero el nombre y apellido
        personaModificada.setNombre(personaDTO.getNombre());
        personaModificada.setApellido(personaDTO.getApellido());
        //aqui se modifica el registro

        return personaMapperImpl.EntityToDto(personaModificada);
    }

    @Override
    public void deletePersona(Integer id)  {
        System.out.println("Borrando datos de persona: " + id);
    }

    @Override
    public List<Persona> listarPersonas() {
        //creo la persona
        Persona auxPersona = new Persona("Leonel","Messi",34,"Argentina");
        //lista de personas
        List<Persona> listaPersona = new ArrayList<>();
        //agrego la persona a lista
        listaPersona.add(auxPersona);
        //devuelvo la lista
        return listaPersona;
    }


/*    @Override
    public void insert(String nombre, String apellido){
        System.out.println("Insertando datos de persona: " + nombre + " " + apellido);
    }

    @Override
    public String update(String nombre, String apellido, Integer id){

        return "Modificando datos de persona: " + nombre + " " + apellido + " con el id: " + id;
    }

    @Override
    public String listarTodos(){

        return "Mostrando todos los datos de persona ";
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Borrando datos de persona: " + id);
    }*/

}

