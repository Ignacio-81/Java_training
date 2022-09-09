package org.example.controller;
/**
 * Implementacion Controller Cliente

 * Metodos:
 * @listarClientes List<ClienteResponseDTO> ()
 * @agregarCliente Integer (ClienteDTO cliente)
 * @modificarCliente Integer (ClienteDTO clienteDTO, Integer id)
 * @borrarCliente int (Integer id)

 */
import org.example.dto.request.ClienteDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.service.IClienteService;
import org.example.service.IServicios;
import org.example.service.Impl.ClienteServiceImpl;

import java.util.List;


public class ClienteController {

    private IClienteService iServiciosCliente= new ClienteServiceImpl();

    public List<ClienteResponseDTO> listarClientes() {
        return iServiciosCliente.getAllClientes();
    }

    public Integer agregarCliente(ClienteDTO cliente){
        return iServiciosCliente.insertCliente(cliente);
    }
    public Integer modificarCliente(ClienteDTO clienteDTO, Integer id) {
        return iServiciosCliente.updateCliente(clienteDTO, id);
    }
    public int borrarCliente(Integer id){
        return iServiciosCliente.deleteCliente(id);
    }

}
