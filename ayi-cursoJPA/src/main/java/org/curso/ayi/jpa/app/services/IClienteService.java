package org.curso.ayi.jpa.app.services;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.dto.request.ClienteDTO;
import org.curso.ayi.jpa.app.dto.response.ClienteResponseDTO;
import org.curso.ayi.jpa.app.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {


    List<ClienteResponseDTO> getAll();
    Optional<ClienteResponseDTO> getById(Long id);
    void eliminar(Long id);
    void update(ClienteDTO cliente);
    void insert(ClienteDTO cliente);
}
