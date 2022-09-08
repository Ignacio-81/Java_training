package org.example.service.Impl;
/**
 * Implementacion Servicio Empleado - Implementa IEmpleadoService

 * Metodos:
 * @getAllEmpleados List<EmpleadoResponseDTO> ()
 * @getPersonaByIdEmpleado List<EmpleadoResponseDTO> (List<EmpleadoResponseDTO> empleados)
 * @insertEmpleado Integer (EmpleadoDTO empleados)
 * @updateEmpleado Integer (EmpleadoDTO empleadoDTO, Integer id)
 * @deleteEmpleado int (Integer id)

 */
import org.example.configuration.ConexionDB;
import org.example.constants.Constants;
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IEmpleadoService;

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

    @Override
    public List<EmpleadoResponseDTO> getAllEmpleados() {
        EmpleadoResponseDTO empleadoResponseDTO = null;
        conexionDBaux = new ConexionDB();
        conexionDB = new ConexionDB();
        List<EmpleadoResponseDTO> empleados = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_EMPLEADO);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idempleado");
                Double sueldo = rs.getDouble("sueldo");
                int idPersona_empleado = rs.getInt("id_persona");

                empleadoResponseDTO = EmpleadoResponseDTO.builder()
                        .idEmpleado(idEmpleado)
                        .sueldo(sueldo)
                        .idPersona(idPersona_empleado)
                        .build();

                empleados.add(empleadoResponseDTO);
            }

            empleados = getPersonaByIdEmpleado(empleados);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex){
            throw new RuntimeException("Se produjo un problema al insertar los datos en la BD");
        }finally {
            try {
                if (rs != null) {
                    conexionDB.close(rs);
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }

            }catch (SQLException ex){
                ex.printStackTrace(System.out);
            }

            return empleados;
        }

    }
    public List<EmpleadoResponseDTO> getPersonaByIdEmpleado (List<EmpleadoResponseDTO> empleados) {
        conexionDBaux = new ConexionDB();
        int response = 0;

            try {
                connaux = conexionDBaux.getConnection();
                for (EmpleadoResponseDTO empleado:empleados ) {
                    stmtaux = connaux.prepareStatement(Constants.SQL_GET_PERSON_BY_ID);
                    stmtaux.setInt(1, empleado.getIdPersona());
                    rsaux = stmtaux.executeQuery();

                    if (!rsaux.next()) {
                        System.out.println("Errro: el id no existe.");

                    } else {
                        response = 1;
                    }
                    int idPersona = rsaux.getInt("id_persona");
                    String nombre = rsaux.getString("nombre");
                    String apellido = rsaux.getString("apellido");
                    int edad = rsaux.getInt("edad");
                    String direccion = rsaux.getString("direccion");


                    empleado.setId(idPersona);
                    empleado.setNombre(nombre);
                    empleado.setApellido(apellido);
                    empleado.setEdad(edad);
                    empleado.setDireccion(direccion);

                }
                return empleados;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally{
                try {
                    if (rsaux != null) {
                        conexionDBaux.close(rsaux);
                        conexionDBaux.close(stmtaux);
                        conexionDBaux.close(connaux);
                    }
                }catch (SQLException e){
                    throw new RuntimeException("Error al cerrar las conexiones");
                }
            }
        }
    @Override
    public Integer insertEmpleado(EmpleadoDTO empleado){
        conexionDB = new ConexionDB();
        int indInsert = 0;

        // ResultSet resultSet = null;

        try {
            conn  = conexionDB.getConnection();
            // stmt = conn.prepareStatement(Constants.SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
            stmt = conn.prepareStatement(Constants.SQL_INSERT_EMPLEADO);
            stmt.setDouble(1,empleado.getSueldo());
            stmt.setInt(2,empleado.getIdPersona_empleado());

            indInsert = stmt.executeUpdate(); // devuelve 1 si esta OK o 0 si NOK

            //rs = stmt.getGeneratedKeys();
            if (indInsert == 0){ //Error
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex){
            throw new RuntimeException("Se produjo un problema al insertar los datos en la BD");
        } finally {
            try {
                conexionDB.close(stmt);
                conexionDB.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);

            }
        }

        return indInsert;
    }

    @Override
    public Integer updateEmpleado(EmpleadoDTO empleadoDTO, Integer id) {

        conexionDB = new ConexionDB();
        stmt = null;
        conn = null;
        int indInsert = 0, getPersona = 0;
        PersonaServiceImpl personaimpl = new PersonaServiceImpl();

        try {
            conn = conexionDB.getConnection();
            getPersona = personaimpl.checkRegistroById(id, Constants.SQL_SELECT_EMPLEADO_BY_ID);

            if (getPersona == 0){

                throw new RuntimeException("Error con la verificacion del ID");
            }
            stmt = conn.prepareStatement(Constants.SQL_UPDATE_EMPLEADO);
            stmt.setDouble(1, empleadoDTO.getSueldo());
            stmt.setInt(2, empleadoDTO.getIdPersona_empleado());
            stmt.setInt(3, id);

            indInsert = stmt.executeUpdate(); // devuelve 1 si esta OK o 0 si NOK

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Se produjo un problema al Modificar los datos en la BD");
        } finally {
            try {
                if (stmt != null) {
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);

            }
        }
        return indInsert;
    }
    @Override
    public int deleteEmpleado(Integer id)  {
        conexionDB = new ConexionDB();
        stmt = null;
        conn = null;
        int indDel = 0;
        PersonaServiceImpl personaimpl = new PersonaServiceImpl();

        try {
            conn = conexionDB.getConnection();
            indDel = personaimpl.checkRegistroById(id , Constants.SQL_SELECT_EMPLEADO_BY_ID);
            stmt = conn.prepareStatement(Constants.SQL_DELETE_EMPLEADO);
            if (indDel == 0){
                throw new RuntimeException();
            }
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Se produjo un problema al borrar los datos en la  BD");
        } finally {
            try {
                if (stmt != null) {
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);

            }
        }
        return id;
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
