package com.ayi.trabajo_final.app.services.Impl;
/**
 * Customer Service Implementation
 * @Transactional
 *
 */

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
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
    public CustomerResponseDTO addCustomer(CustomerDTO customerDTO) throws ReadAccessException, WriteAccessException, DataBaseException{
        CustomerResponseDTO customerResponseDTO;

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new ReadAccessException("Error: DTO Data is empty");
        }
        Optional<CustomerEntity> entity_check = customerRepository.getCustomerByDNI(customerDTO.getDocumentNumber()); // Ya tengo todos los métodos para buscar, deletear, etc

        if (entity_check.isPresent()) {
            throw new WriteAccessException("Error: This customer already exists on the system");
        }

        try{
            CustomerEntity entity = CustomerEntity.builder()
                    .firstName(customerDTO.getFirstName())
                    .lastName(customerDTO.getLastName())
                    .documentNumber(customerDTO.getDocumentNumber())
                    .dateCreated(LocalDate.now())
                    .dateModified(LocalDate.now())

                    .build();
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
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        customerResponseDTO = customerMapper.entityToDto(entity.get());
        return customerResponseDTO;
    }


    @Override
    public void removeCustomerById(Long idCustomer) throws ReadAccessException {

        if (idCustomer == null || idCustomer == 0 || idCustomer < 0) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        try {
            customerRepository.deleteById(entity.get().getId());
            log.info("Completed Customer data physical removal physical id={}", idCustomer);
        } catch (Throwable e) {
            log.error("Can't remove Customer data physical removal data={}, cause={}", idCustomer, e.getMessage());
            throw new RuntimeException("Database Error not handled");
        }


    }
    @Override
    public CustomerResponseDTO updateCustomerById(Long idCustomer, CustomerDTO customerDTO) throws ReadAccessException {

        if (idCustomer == null || idCustomer == 0L || idCustomer < 0L) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error, no data for this ID " + idCustomer);
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
            throw new WriteAccessException("Runtime undefined Error");

        }
    }



}
