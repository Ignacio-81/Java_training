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
        EmpleadoController empleadoController = new EmpleadoController();
        ClienteController clienteController = new ClienteController();

//************************************************GESTION PERSONAS*****************************************************
        List<PersonaResponseDTO> personas = new ArrayList<>();
// Insertamos o colocamos una nueva persona en la Tabla Personas de la BD
        PersonaDTO persona1 = new PersonaDTO("Marina","Perez",24,"Buenos Aires");
        personaController.agregarPersona(persona1);
        System.out.println("Insertamos nueva Persona: "+ persona1.toString());


// Buscamos una persona por el id y la modificamos en la Tabla personas de la BD:
        PersonaDTO personaParaModif = new PersonaDTO("Pedro","Huguain",20,"Chaco");
        Integer personaupdated = personaController.modificarPersona(personaParaModif,11);
        System.out.println("Insertamos nueva Persona: "+ personaupdated.toString());

// Buscamos todas las personas de la tabla Personas de la BD

        personas = personaController.listarPersonas();
        personas.forEach(persona -> {
            System.out.println("Personas Tabla: " + persona);
        });

// Borramos una persona de la tabla personas con id:
        System.out.println("Se borro la persona de la tabla Personas con el siguiente id : " + personaController.borrarPersona(18));
        personas = personaController.listarPersonas();
        personas.forEach(persona -> {
            System.out.println("Personas Tabla: " + persona);
        });

//************************************************GESTION EMPLEADOS*****************************************************
        List<EmpleadoResponseDTO> empleados = new ArrayList<>();
// Insertamos o colocamos un nuevo Empleado en la Tabla Personas de la BD
        EmpleadoDTO empleado1 = new EmpleadoDTO(1500.0, 17);
        empleadoController.agregarEmpleado(empleado1);
        System.out.println("Insertamos nuevo Empleado: "+ empleado1.toString());

// Buscamos un Empleado por el id y lo modificamos en la Tabla empeleados de la BD:
        EmpleadoDTO empleadoParaModif = new EmpleadoDTO(550.3,10);
        Integer empleadoupdated = empleadoController.modificarEmpleado(empleadoParaModif,2);


// Borramos un empleado de la tabla personas con id:
        System.out.println("Se borro el Empleado de la tabla empleados con el siguiente id : " + empleadoController.borrarEmpleado(5));


        // Buscamos todos los Empleados y sus datos de persona de la tabla Personas de la BD
    empleados = empleadoController.listarEmpleados();
    empleados.forEach(empleado -> {
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Apellido: " + empleado.getApellido());
        System.out.println("Edad: " + empleado.getEdad());
        System.out.println("DIreccion: " + empleado.getDireccion());
        System.out.println("ID Empleado: " + empleado.getIdEmpleado());
        System.out.println("Sueldo: " + empleado.getSueldo());

    });

//************************************************GESTION CLIENTES*****************************************************
        List<ClienteResponseDTO> clientes = new ArrayList<>();

// Insertamos o colocamos un nuevo Cliente en la Tabla Personas de la BD
        ClienteDTO cliente1 = new ClienteDTO("VIP", 10);
        clienteController.agregarCliente(cliente1);
        System.out.println("Insertamos nuevo Cliente: "+ cliente1.toString());

// Buscamos un Cliente por el id y lo modificamos en la Tabla clientes de la BD:
        ClienteDTO clienteParaModif = new ClienteDTO("NOVIP",7);
        Integer clienteupdated = clienteController.modificarCliente(clienteParaModif,2);
        System.out.println("Insertamos nuevo Cliente: "+ clienteupdated.toString());

// Borramos un Cliente de la tabla personas con id:
        System.out.println("Se borro el Cliente de la tabla clientes con el siguiente id : " + clienteController.borrarCliente(2));


        // Buscamos todos los Clientes y sus datos de persona de la tabla Personas de la BD
        clientes = clienteController.listarClientes();
        clientes.forEach(cliente -> {
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("Edad: " + cliente.getEdad());
        System.out.println("DIreccion: " + cliente.getDireccion());
        System.out.println("El Cliente es VIP?: " + cliente.getVip());
        System.out.println("ID CLiente: " + cliente.getIdCliente());

    });

    }

}