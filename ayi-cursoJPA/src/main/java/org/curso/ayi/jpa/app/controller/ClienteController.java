package org.curso.ayi.jpa.app.controller;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.dto.request.ClienteDTO;
import org.curso.ayi.jpa.app.dto.response.ClienteResponseDTO;
import org.curso.ayi.jpa.app.services.IClienteService;
import org.curso.ayi.jpa.app.services.Impl.ClienteServiceImpl;

import java.util.List;

public class ClienteController {

    private EntityManager em;

    IClienteService iClienteServicios = new ClienteServiceImpl(em);

    public List<ClienteResponseDTO> listarCliente() {
        return iClienteServicios.getAll();
    }

    public void agregarPersona(ClienteDTO cliente){
        iClienteServicios.insert(cliente);
    }

    public void modificarPersona(ClienteDTO cliente, Integer id) {
        iClienteServicios.update(cliente);
    }

    public void borrarPersona(Long id){
        iClienteServicios.eliminar(id);
    }
}
