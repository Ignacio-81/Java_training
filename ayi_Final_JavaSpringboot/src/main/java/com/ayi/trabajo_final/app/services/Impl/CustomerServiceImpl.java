package com.ayi.trabajo_final.app.services.Impl;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.entities.TicketEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.exceptions.WriteAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerRepository;
import com.ayi.trabajo_final.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service //Indica que es un servicio y puede ser inyectado
@Slf4j
@Transactional //Maneja la transaccion, hace el commit y maneja el rollback begin , commit, rollback

public class CustomerServiceImpl implements ICustomerService {

    @Autowired // Le digo que es una "tubería", genera un puente entre una cosa y otra para traer o enviar info
    private ICustomerRepository customerRepository; // Fijate que no hicimos implementación de IPersonRepository, ya con esto es suficiente

    @Autowired
    private ICustomerMapper customerMapper; // Acá uso los mapper (me transforma una entidad a otra)

    @Override
    public CustomerResponseDTO addCustomer(CustomerDTO customerDTO) throws ReadAccessException, DataBaseException {
        CustomerResponseDTO customerResponseDTO;

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new ReadAccessException("Error datos de la DTO estan vacios");
        }
        Optional<CustomerEntity> entity_check = customerRepository.getCustomerByDNI(customerDTO.getDocumentNumber()); // Ya tengo todos los métodos para buscar, deletear, etc

        if (entity_check.isPresent()) {
            throw new WriteAccessException("Persona ya existe registrada en el sistema");
        }


        CustomerEntity entity = CustomerEntity.builder()
            .firstName(customerDTO.getFirstName())
            .lastName(customerDTO.getLastName())
            .documentNumber(customerDTO.getDocumentNumber())
            .dateCreated(LocalDate.now())
            .dateModified(LocalDate.now())

            .build();

        try{
            customerRepository.save(entity);
            customerResponseDTO = customerMapper.entityToDto(entity);
            return customerResponseDTO;
        } catch (RuntimeException th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            throw new RuntimeException("" + th.getStackTrace());
        }
    }
    @Override
    public CustomerResponseDTO findCustomerById(Long idCustomer) throws ReadAccessException {
        CustomerResponseDTO customerResponseDTO;

        if (idCustomer == null || idCustomer <= 0) {
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        customerResponseDTO = customerMapper.entityToDto(entity.get());
        return customerResponseDTO;
    }


    @Override
    public void removeCustomerById(Long idCustomer) throws ReadAccessException {

        if (idCustomer == null || idCustomer == 0 || idCustomer < 0) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        try {
            customerRepository.deleteById(entity.get().getId());
            log.info("Completed Person data physical removal physical id={}", idCustomer);
        } catch (Throwable e) {
            log.error("Can't remove List Person data physical removal data={}, cause={}", idCustomer, e.getMessage());
            throw new RuntimeException("Error de base de datos no controlado");
        }


    }
    @Override
    public CustomerResponseDTO updateCustomerById(Long idCustomer, CustomerDTO customerDTO) throws ReadAccessException {

        if (idCustomer == null || idCustomer == 0L || idCustomer < 0L) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error identificador de persona no existe: " + idCustomer);
        }

        try {
            CustomerEntity customerRequest = entity.get();
            LocalDate dateCreated = customerRequest.getDateCreated();
            customerRequest = customerMapper.dtoToEntity(customerDTO);
            customerRequest.setDateCreated(dateCreated);
            customerRequest.setDateModified(LocalDate.now());
            customerRequest.setId(idCustomer);
            customerRepository.save(customerRequest);

            return customerMapper.entityToDto(customerRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getMessage());
            throw new WriteAccessException("Error no identificado de runtime");

        }
    }
    @Override
    public List<CustomerTicketsResponseDTO> findAllTicketByCustomerById(Long idCustomer) throws ReadAccessException {
        List<CustomerTicketsResponseDTO> ticketEntityList;

        if (idCustomer == null || idCustomer <= 0) {
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }
        ticketEntityList = entity.get().getTickets().stream()
                .map(lt -> new CustomerTicketsResponseDTO(
                        lt.getId(),
                        lt.getDescription(),
                        lt.getTotal()
                )) // Tdo esto es lo que estoy enviando al constructor de PersonResponseDTO
                .collect(Collectors.toList()); // A través de stream, mapeo los campos de personEntities a personResponseDTOs
;

        return ticketEntityList;
    }


}
