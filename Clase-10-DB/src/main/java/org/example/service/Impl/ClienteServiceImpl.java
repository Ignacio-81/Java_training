package org.example.service.Impl;

import org.example.dto.request.ClienteDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.entity.Cliente;
import org.example.mapper.ClienteMapperImpl;
import org.example.service.IClienteService;
import org.example.service.IServicios;

import java.util.ArrayList;
import java.util.List;

public class ClienteServiceImpl implements IClienteService {

    private ClienteMapperImpl clienteMapperImpl = new ClienteMapperImpl();

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
