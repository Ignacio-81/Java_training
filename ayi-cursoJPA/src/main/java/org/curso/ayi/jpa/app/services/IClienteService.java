package org.curso.ayi.jpa.app.services;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {


    List<Cliente> getAll();
    Optional<Cliente> getById(Long id);
    void eliminar(Long id);
    void update(Cliente cliente);
    void insert(Cliente cliente);
}
