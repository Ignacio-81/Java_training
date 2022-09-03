package org.example;

import org.example.controller.ClienteController;
import org.example.controller.EmpleadoController;
import org.example.controller.PersonaController;
import org.example.dto.request.ClienteDTO;
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Cliente;
import org.example.entity.Empleado;
import org.example.entity.Persona;
import org.example.service.Impl.PersonaServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DispatcherAppMain {
    public static void main(String[] args) {


        PersonaController personaController = new PersonaController();

/*
// Insertamos o colocamos una nueva persona en la Tabla Personas de la BD
        PersonaDTO persona1 = new PersonaDTO("Marina","Perez",24,"Buenos Aires");
        personaController.agregarPersona(persona1);
        System.out.println("Insertamos nueva Persona: "+ persona1.toString());
*/
        /*
        PersonaServiceImpl personaServiceImpl = new PersonaServiceImpl();
        int idpersonamodif = personaServiceImpl.getPersonaById(25);
        System.out.println("Id persona insertar: " + idpersonamodif);
*/

// Buscamos una persona por el id y la modificamos en la Tabla personas de la BD:
        PersonaDTO personaParaModif = new PersonaDTO("Carla","Gonzalez",12,"Santa Fe");
        Integer personaupdated = personaController.modificarPersona(personaParaModif,2);

// Buscamos todas las personas de la tabla Personas de la BD
        List<PersonaResponseDTO> personas = new ArrayList<>();
        personas = personaController.listarPersonas();
        personas.forEach(persona -> {
            System.out.println("Personas Tabla: " + persona);
        });

// Borramos una persona de la tabla personas con id:
        System.out.println("Se borro la persona de la tabla Personas con el siguiente id : " + personaController.borrarPersona(2));
        personas = personaController.listarPersonas();
        personas.forEach(persona -> {
            System.out.println("Personas Tabla: " + persona);
        });


    }
}