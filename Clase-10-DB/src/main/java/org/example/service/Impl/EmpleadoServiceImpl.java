package org.example.service.Impl;

import org.example.configuration.ConexionDB;
import org.example.constants.Constants;
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Empleado;
import org.example.entity.Persona;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IEmpleadoService;
import org.example.service.Impl.PersonaServiceImpl;
import org.example.service.IServicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoServiceImpl implements IEmpleadoService {

    private ConexionDB conexionDB ;
    private ConexionDB conexionDBaux ;
    private Connection conn = null;
    private Connection connaux = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmtaux = null;
    private ResultSet rs = null;
    private ResultSet rsaux = null;
    private EmpleadoMapperImpl empleadoMapperImpl = new EmpleadoMapperImpl();
    private PersonaServiceImpl personaServiceImpl = new PersonaServiceImpl();

    @Override
    public List<EmpleadoResponseDTO> getAllEmpleados() {
        PersonaResponseDTO personaResponseDTO;
        EmpleadoResponseDTO empleadoResponseDTO = null;
        conexionDBaux = new ConexionDB();
        conexionDB = new ConexionDB();
        Persona dataPersonas;
        List<EmpleadoResponseDTO> empleados = new ArrayList<>();
        int getPersona = 0;

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_EMPLEADO);
            conexionDBaux.getConnection();
            stmtaux = connaux.prepareStatement(Constants.SQL_SELECT_PERSON_BY_ID);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idempleado");
                Double sueldo = rs.getDouble("sueldo");
                int idPersona_empleado = rs.getInt("id_persona");
/*
                empleadoResponseDTO.setIdEmpleado(idEmpleado);
                empleadoResponseDTO.setSueldo(sueldo);
                empleadoResponseDTO.setIdPersona(idPersona_empleado);
*/
                //empleadoResponseDTO = new EmpleadoResponseDTO(idEmpleado, sueldo, idPersona_empleado);

                //getPersona = personaServiceImpl.getPersonaById(empleadoResponseDTO.getIdPersona());
                //if (getPersona == 0){

                  //  throw new RuntimeException();
                //}

                    stmtaux.setInt(1, empleadoResponseDTO.getIdPersona());
                    rsaux = stmtaux.executeQuery();

                    int idPersona = rsaux.getInt("id_persona");
                    String nombre = rsaux.getString("nombre");
                    String apellido = rsaux.getString("apellido");
                    String direccion = rsaux.getString("direccion");
                    Integer edad = rsaux.getInt("edad");

                    //dataPersonas = new Persona(idPersona, nombre, apellido, edad, direccion);
/*
                    empleadoResponseDTO.setId(idPersona);
                    empleadoResponseDTO.setNombre(nombre);
                    empleadoResponseDTO.setNombre(apellido);
                    empleadoResponseDTO.setEdad(edad);
                    empleadoResponseDTO.setDireccion(direccion);
*/
                EmpleadoResponseDTO empleadoResponseDTO = EmpleadoResponseDTO.builder()
                        .idEmpleado(idEmpleado)
                        .sueldo(sueldo)
                        .idPersona(idPersona_empleado)
                        .

                        .build();
                empleadoResponseDTO = new EmpleadoResponseDTO (idEmpleado, sueldo, idPersona_empleado,idPersona, nombre, apellido, edad, direccion );
                    empleados.add(empleadoResponseDTO);


                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    conexionDB.close(rs);
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }
                if (rsaux != null) {
                    conexionDBaux.close(rsaux);
                    conexionDBaux.close(stmtaux);
                    conexionDBaux.close(connaux);
                }
            }catch (SQLException ex){
                ex.printStackTrace(System.out);
            }

            return empleados;
        }

    }
    /*
    @Override
    public void insertEmpleado(Empleado empleado) {
        Empleado empleado1 = new Empleado();
        empleado1 = empleado;
        System.out.println("Insertando datos del Empleado: " + empleado1.toString());
    }

    @Override
    public EmpleadoResponseDTO updateEmpleado(EmpleadoDTO empleadoDTO, Integer id) {
        Empleado empleadoModificado = new Empleado();

        //recupero el nombre y apellido
        empleadoModificado.setNombre(empleadoDTO.getNombre());
        empleadoModificado.setApellido(empleadoDTO.getApellido());
        //aqui se modifica el registro

        return empleadoMapperImpl.EntityToDto(empleadoModificado);
    }

    @Override
    public void deleteEmpleado(Integer id)  {
        System.out.println("Borrando datos de Empleado: " + id);
    }
/*
    @Override
    public List<Empleado> listarEmpleados() {
        //creo la persona
        Empleado auxEmpleado = new Empleado("Jose","Martinez",34,"Argentina",3002,300.3);
        //lista de personas
        List<Empleado> listaEmpleado = new ArrayList<>();
        //agrego la persona a lista
        listaEmpleado.add(auxEmpleado);
        //devuelvo la lista
        return listaEmpleado;
    }
*/
}
