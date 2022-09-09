package org.example.service;

import org.example.dto.request.ClienteDTO;
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.entity.Cliente;


import java.util.List;

public interface IClienteService {

    public List<ClienteResponseDTO> getAllClientes();

    public Integer insertCliente(ClienteDTO cliente);

    public Integer updateCliente(ClienteDTO clienteDTO, Integer id);
    public int deleteCliente(Integer id);

/*    void insertCliente (Cliente cliente);
    ClienteResponseDTO updateCliente (ClienteDTO cliente , Integer id);
    void deleteCliente (Integer id);
    List<Cliente> listarClientes();*/
}
