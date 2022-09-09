package org.curso.ayi.jpa.app.services.Impl;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.dto.request.ClienteDTO;
import org.curso.ayi.jpa.app.dto.response.ClienteResponseDTO;
import org.curso.ayi.jpa.app.entity.Cliente;
import org.curso.ayi.jpa.app.repositories.IClienteRepository;
import org.curso.ayi.jpa.app.repositories.Impl.ClienteRepository;
import org.curso.ayi.jpa.app.services.IClienteService;

import java.util.List;
import java.util.Optional;

/**
 * Aca van las operaciones del negocio, operaciones y verificaciones
 */

public class ClienteServiceImpl implements IClienteService {

    private EntityManager em;
    private IClienteRepository<ClienteResponseDTO> iClienteRepository;

    public ClienteServiceImpl(EntityManager em){
        this.em = em;
        this.iClienteRepository = new ClienteRepository(em);
    }

    @Override
    public List<ClienteResponseDTO> getAll(){
        return iClienteRepository.listar();
    }

    @Override
    public Optional<ClienteResponseDTO> getById(Long id){
    //Optional sirve para saber que un objeto tiene otras condiciones, nos da la opcion de si "ispresent" .
        // se utiliza para manejar datos que pueden traer otra  informacion o nula.
        Optional <ClienteResponseDTO> clienteById;

        clienteById = Optional.ofNullable(iClienteRepository.getById(id));

        if (clienteById.isPresent()){
            return clienteById;
        }else{
            return null;
        }

    }

    @Override
    public void eliminar(Long id) {

        Optional<ClienteResponseDTO> clienteById;
        clienteById = Optional.ofNullable(iClienteRepository.getById(id));
        if (clienteById.isPresent()) {
            try {
                em.getTransaction().begin();
                iClienteRepository.eliminar(id);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }else {
            System.out.println("No se pudo borrar");
        }
    }

    @Override
    public void update(ClienteDTO cliente){

        if (cliente.getId() > 0 ) {
            Optional<ClienteResponseDTO> clienteById;
            clienteById = Optional.ofNullable(iClienteRepository.getById(cliente.getId()));

            if (clienteById.isPresent()) {
                try {
                    em.getTransaction().begin();
                    iClienteRepository.insertar(cliente);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println("El ID no existe");
        }
    }

    @Override
    public void insert(ClienteDTO cliente){

            try {
                em.getTransaction().begin();
                iClienteRepository.guardar(cliente);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }

    }

}
