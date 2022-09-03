package org.example.service;

import org.example.dto.request.ClienteDTO;
import org.example.dto.response.ClienteResponseDTO;
import org.example.entity.Cliente;


import java.util.List;

public interface IClienteService {

    void insertCliente (Cliente cliente);
    ClienteResponseDTO updateCliente (ClienteDTO cliente , Integer id);
    void deleteCliente (Integer id);
    List<Cliente> listarClientes();
}
