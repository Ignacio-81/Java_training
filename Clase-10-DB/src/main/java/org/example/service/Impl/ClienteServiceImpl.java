package org.example.service.Impl;
/**
 * Implementacion Servicio Cliente - Implementa IClienteService

 * Metodos:
 * @getAllClientes List<ClienteResponseDTO> ()
 * @getPersonaByIdCLiente List<ClienteResponseDTO> (List<ClienteResponseDTO> clientes)
 * @insertCliente Integer (ClienteDTO cliente)
 * @updateCliente Integer (ClienteDTO clienteDTO, Integer id)
 * @deleteCliente int (Integer id)

 */
import org.example.configuration.ConexionDB;
import org.example.constants.Constants;
import org.example.dto.request.ClienteDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.mapper.ClienteMapperImpl;
import org.example.service.IClienteService;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteServiceImpl implements IClienteService {

    private ClienteMapperImpl clienteMapperImpl = new ClienteMapperImpl();

    private ConexionDB conexionDB ;
    private ConexionDB conexionDBaux ;
    private Connection conn = null;
    private Connection connaux = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmtaux = null;
    private ResultSet rs = null;
    private ResultSet rsaux = null;

    @Override
    public List<ClienteResponseDTO> getAllClientes() {
        ClienteResponseDTO clienteResponseDTO = null;
        conexionDBaux = new ConexionDB();
        conexionDB = new ConexionDB();
        List<ClienteResponseDTO> clientes = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_CLIENTE);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String vip = rs.getString("vip");
                int idPersona_cliente = rs.getInt("id_persona");

                clienteResponseDTO = ClienteResponseDTO.builder()
                        .idCliente(idCliente)
                        .vip(vip)
                        .idPersona(idPersona_cliente)
                        .build();

                clientes.add(clienteResponseDTO);
            }

            clientes = getPersonaByIdCLiente(clientes);

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

            return clientes;
        }

    }
    public List<ClienteResponseDTO> getPersonaByIdCLiente (List<ClienteResponseDTO> clientes) {
        conexionDBaux = new ConexionDB();
        int response = 0;

        try {
            connaux = conexionDBaux.getConnection();
            for (ClienteResponseDTO cliente:clientes ) {
                stmtaux = connaux.prepareStatement(Constants.SQL_GET_PERSON_BY_ID);
                stmtaux.setInt(1, cliente.getIdPersona());
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


                cliente.setId(idPersona);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEdad(edad);
                cliente.setDireccion(direccion);

            }
            return clientes;
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
    public Integer insertCliente(ClienteDTO cliente){
        conexionDB = new ConexionDB();
        int indInsert = 0;

        try {
            conn  = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_INSERT_CLIENTE);
            stmt.setString(1,cliente.getVip());
            stmt.setInt(2,cliente.getIdPersona_cliente());

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
    public Integer updateCliente(ClienteDTO clienteDTO, Integer id) {

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
            stmt = conn.prepareStatement(Constants.SQL_UPDATE_CLIENTE);
            stmt.setString(1, clienteDTO.getVip());
            stmt.setInt(2, clienteDTO.getIdPersona_cliente());
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
    public int deleteCliente(Integer id)  {
        conexionDB = new ConexionDB();
        stmt = null;
        conn = null;
        int indDel = 0;
        PersonaServiceImpl personaimpl = new PersonaServiceImpl();

        try {
            conn = conexionDB.getConnection();
            indDel = personaimpl.checkRegistroById(id , Constants.SQL_SELECT_CLIENTE_BY_ID);

            if (indDel == 0){
                System.out.println("No se encontro el ID");
                throw new RuntimeException();
            }
            stmt = conn.prepareStatement(Constants.SQL_DELETE_CLIENTE);
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
    public void insertCliente(Cliente cliente) {
        Cliente cliente1 = new Cliente();
        cliente1 = cliente;
        System.out.println("Insertando datos del CLiente: " + cliente1.toString());
    }

    @Override
    public ClienteResponseDTO updateCliente(ClienteDTO clienteDTO, Integer id) {
        Cliente clienteModificado = new Cliente();

        //recupero el nombre y apellido
        clienteModificado.setNombre(clienteDTO.getNombre());
        clienteModificado.setApellido(clienteDTO.getApellido());
        //aqui se modifica el registro

        return clienteMapperImpl.EntityToDto(clienteModificado);
    }

    @Override
    public void deleteCliente(Integer id)  {
        System.out.println("Borrando datos de Cliente: " + id);
    }

    @Override
    public List<Cliente> listarClientes() {
        //creo la persona
        Cliente auxCliente = new Cliente("Pedro","Juarez",34,"Argentina",1222,"VIP");
        //lista de personas
        List<Cliente> listaCliente = new ArrayList<>();
        //agrego la persona a lista
        listaCliente.add(auxCliente);
        //devuelvo la lista
        return listaCliente;
    }
*/
/*    @Override
    public void insert(String nombre, String apellido){
        System.out.println("Insertando datos de cliente: " + nombre + " " + apellido);
    }

    @Override
    public String update(String nombre, String apellido, Integer id){

        return "Modificando datos de cliente: " + nombre + " " + apellido + " con el id: " + id;
    }

    @Override
    public String listarTodos(){

        return "Mostrando todos los datos de cliente ";
    }

    @Override
    public void delete(Integer id){
        System.out.println("Borrando datos de cliente: " + id);
    }*/
}
