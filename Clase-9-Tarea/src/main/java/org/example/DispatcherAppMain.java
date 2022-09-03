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

public class DispatcherAppMain {
    public static void main(String[] args) {

        PersonaController personaController = new PersonaController();
        EmpleadoController empleadoController = new EmpleadoController();
        ClienteController clienteController = new ClienteController();
        Persona persona = new Persona();
        PersonaDTO personaDTO = new PersonaDTO();
        PersonaResponseDTO personaResponseDTO = new PersonaResponseDTO();

        Cliente cliente = new Cliente();
        ClienteDTO clienteDTO = new ClienteDTO();
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        EmpleadoResponseDTO empleadoResponseDTO = new EmpleadoResponseDTO();

        /*String nombre1 = "Gimena";
        String apellido1 = "Binaghi";
        Integer id1 = 32;
        String nombre2 = "Juancito";
        String apellido2 = "Ramirez";
        Integer id2 = 1;
        String nombre3 = "Pedrito";
        String apellido3 = "Perez";
        Integer id3 = 7;*/
        Integer id1 = 32;
        Integer id2 = 1;
        Integer id3 = 7;

        persona.setNombre("Cristiano");
        persona.setApellido("Ronaldo");

        personaDTO.setNombre("Cristiano");
        personaDTO.setApellido("Ronaldo");

        empleado.setNombre("Martin");
        empleado.setApellido("Guzman");
        empleado.setSueldo(30043.11);

        empleadoDTO.setNombre("Roberto");
        empleadoDTO.setApellido("Perez");

        cliente.setNombre("Marina");
        cliente.setApellido("Caceres");

        clienteDTO.setNombre("Pablo");
        clienteDTO.setApellido("Mercedes");
        clienteDTO.setVip("NOVIP");

        System.out.println("--------Persona--------");
        personaController.agregarPersona(persona);
        personaResponseDTO = personaController.modificarPersona(personaDTO, id1);
        System.out.println("Modificado: " + personaResponseDTO.toString());
        System.out.println(personaController.listarPersonas().toString());
        personaController.borrarPersona(id1);
/*        personaController.agregarPersona(nombre1, apellido1);
        System.out.println(personaController.modificarPersona(nombre1, apellido1, id1));
        System.out.println(personaController.listarPersonas());
        personaController.borrarPersona(id1);*/

        System.out.println("--------Empleado--------");
        empleadoController.agregarEmpleado(empleado);
        empleadoResponseDTO = empleadoController.modificarEmpleado(empleadoDTO, id2);
        System.out.println("Modificado: " + empleadoResponseDTO.toString());
        System.out.println(empleadoController.listarEmpleados().toString());
        empleadoController.borrarEmpleado(id2);

/*        empleadoController.agregarEmpleado(nombre2, apellido2);
        System.out.println(empleadoController.modificarEmpleado(nombre2, apellido2, id2));
        System.out.println(empleadoController.listarEmpleados());
        empleadoController.borrarEmpleado(id2)*/;

        System.out.println("--------Cliente--------");
        clienteController.agregarCliente(cliente);
        clienteResponseDTO = clienteController.modificarCliente(clienteDTO, id3);
        System.out.println("Modificado: " + clienteResponseDTO.toString());
        System.out.println(clienteController.listarClientes().toString());
        clienteController.borrarCliente(id3);
/*        clienteController.agregarCliente(nombre3, apellido3);
        System.out.println(clienteController.modificarCliente(nombre3, apellido3, id3));
        System.out.println(clienteController.listarClientes());
        clienteController.borrarCliente(id3);*/


        /*
        String nombre = "Ignacio";
        String apellido = "Badella";
        Integer id = 41;

        personacontroller.agregarPersona(nombre, apellido);
        personacontroller.modificarPersona(nombre, apellido, id);
        personacontroller.listarPersona();
        personacontroller.borrarPersona(41);

    }
*/
    }
}