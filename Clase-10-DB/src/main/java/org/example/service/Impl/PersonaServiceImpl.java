package org.example.service.Impl;

import org.example.configuration.ConexionDB;
import org.example.constants.Constants;
import org.example.dto.request.PersonaDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Persona;
import org.example.mapper.PersonaMapperImpl;
import org.example.service.IPersonaService;
import org.example.service.IServicios;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaServiceImpl implements IPersonaService {

    private ConexionDB conexionDB ;
    private ConexionDB conexionDBaux ;
    private Connection conn = null;
    private Connection connaux = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmtaux = null;
    private ResultSet rs = null;
    private ResultSet rsaux = null;

    @Override
    public List<PersonaResponseDTO> getAllPersonas() {

        PersonaResponseDTO personaResponseDTO = null;
        conexionDB = new ConexionDB();
        List<PersonaResponseDTO> personas = new ArrayList<>();
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_PERSON);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                Integer edad = rs.getInt("edad");

                personaResponseDTO = new PersonaResponseDTO(idPersona, nombre, apellido, edad, direccion);

                personas.add(personaResponseDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexionDB.close(rs);
                conexionDB.close(stmt);
                conexionDB.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);

            }


            return personas;
        }

    }

    @Override
    public Integer insertPersona(PersonaDTO persona){
        conexionDB = new ConexionDB();
        int indInsert = 0;

       // ResultSet resultSet = null;

        try {
            conn  = conexionDB.getConnection();
           // stmt = conn.prepareStatement(Constants.SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
            stmt = conn.prepareStatement(Constants.SQL_INSERT_PERSON);
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setInt(3,persona.getEdad());
            stmt.setString(4,persona.getDireccion());

            indInsert = stmt.executeUpdate(); // devuelve 1 si esta OK o 0 si NOK

            //resultSet = stmt.executeQuery();

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
    public Integer updatePersona(PersonaDTO personaDTO, Integer id) {

        conexionDB = new ConexionDB();
        stmt = null;
        conn = null;
        int indInsert = 0, getPersona = 0;


        try {
            conn = conexionDB.getConnection();
            getPersona = getPersonaById(id);
            stmt = conn.prepareStatement(Constants.SQL_UPDATE_PERSON);
            if (getPersona == 0){

                throw new RuntimeException();
            }

            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());
            stmt.setInt(3, personaDTO.getEdad());
            stmt.setString(4, personaDTO.getDireccion());
            stmt.setInt(5, id);

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
    public int deletePersona(Integer id)  {
        conexionDB = new ConexionDB();
        stmt = null;
        conn = null;
        int indDel = 0;

        try {
            conn = conexionDB.getConnection();
            indDel = getPersonaById(id);
            stmt = conn.prepareStatement(Constants.SQL_DELETE_PERSON);
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

    public int getPersonaById (Integer id){
            conexionDBaux = new ConexionDB();
        int response = 0;
        try {
            connaux = conexionDBaux.getConnection();
            //consultamos por el ID a ver si ya existe ne la base

            stmtaux = connaux.prepareStatement(Constants.SQL_SELECT_PERSON_BY_ID);
            stmtaux.setInt(1, id);
            rsaux = stmtaux.executeQuery();

            // Tiene informacion ??
            if (!rsaux.next()) {
                System.out.println("Errro: el id: ' "+ id+ " ' no existe.");
                //System.out.println("Error al tratar de traer un ID que NO existe: " + "' "+ id+ " '");
                //throw new RuntimeException("Error al tratar de traer un ID que NO existe: " + "' "+ id+ " '");
                // con el throw salimos y rompemos el proceso
            }else{
                response = 1;
            }
            return response;
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




 /*   private PersonaMapperImpl personaMapperImpl = new PersonaMapperImpl();

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
*/

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

