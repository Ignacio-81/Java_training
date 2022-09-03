package org.example.controller;

import org.example.dto.request.ClienteDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.entity.Cliente;
import org.example.service.IClienteService;
import org.example.service.IServicios;
import org.example.service.Impl.ClienteServiceImpl;

import java.util.List;


public class ClienteController {

    private IClienteService iServiciosCliente= new ClienteServiceImpl();

    public void agregarCliente (Cliente cliente){

        iServiciosCliente.insertCliente(cliente);
    }
    public void borrarCliente (Integer id){
        iServiciosCliente.deleteCliente(id);
    }
    public List<Cliente> listarClientes (){
        return iServiciosCliente.listarClientes();
    }

    public ClienteResponseDTO modificarCliente(ClienteDTO clienteDTO, Integer id){
        return iServiciosCliente.updateCliente(clienteDTO, id);
    }
}
