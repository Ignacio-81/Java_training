package org.curso.ayi.jpa.app.repositories;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.dto.request.ClienteDTO;
import org.curso.ayi.jpa.app.dto.response.ClienteResponseDTO;
import org.curso.ayi.jpa.app.entity.Cliente;

import java.util.List;

public interface IClienteRepository <T>{

    List<ClienteResponseDTO> listar();
    ClienteResponseDTO getById (Long id);
    void insertar(ClienteDTO cliente);
    void guardar(ClienteDTO cliente);
    void eliminar (Long id);


}
