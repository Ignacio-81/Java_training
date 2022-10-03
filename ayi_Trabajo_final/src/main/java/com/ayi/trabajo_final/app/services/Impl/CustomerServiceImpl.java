package com.ayi.trabajo_final.app.services.Impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import com.ayi.curso.rest.serv.app.exception.DataBaseException;
import com.ayi.curso.rest.serv.app.exception.ReadAccessException;
import com.ayi.curso.rest.serv.app.exception.WriteAccessException;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repositories.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerRepository;
import com.ayi.trabajo_final.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

 /*   @Override
    public List<PersonResponseDTO> findAllPersons() throws ReadAccessException { // Me devuelve todas las personas de la tabla

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        if (personEntities.size() == 0 || personEntities.isEmpty()) { // si tengo un error de lectura
            throw new ReadAccessException("No existe registros para personas");
        }

        personResponseDTOs = personEntities.stream()
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateBorn(),
                        lt.getDateCreated(),
                        lt.getDateModified() // Tdo esto es lo que estoy enviando al constructor de PersonResponseDTO
                ))
                .collect(Collectors.toList()); // A través de stream, mapeo los campos de personEntities a personResponseDTOs

        return personResponseDTOs;
    }*/

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
    public CustomerResponseDTO addCustomer(CustomerDTO persona) throws ReadAccessException, DataBaseException {
        CustomerResponseDTO customerResponseDTO;

        if (ObjectUtils.isEmpty(persona)) {
            throw new ReadAccessException("Error datos de la DTO estan vacios");
        }
        Optional<CustomerEntity> entity_check = customerResponseDTO.getPersonByDNI(persona.getNumberDocument()); // Ya tengo todos los métodos para buscar, deletear, etc

        if (entity_check.isPresent()) {
            throw new WriteAccessException("Persona ya existe registrada en el sistema");
        }


        PersonEntity entity = new PersonEntity(
                persona.getFirstName(),
                persona.getLastName(),
                persona.getTypeDocument(),
                persona.getNumberDocument(),
                persona.getDateBorn()
        );
        try{
            personRepository.save(entity);

            personResponseDTO = personMapper.entityToDto(entity);
            return personResponseDTO;
        } catch (RuntimeException th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", persona.getFirstName() + " " + persona.getLastName(), th.getStackTrace());
            log.error("Found an error when saving List Master Type code={}, cause={}", persona.getFirstName() + " " + persona.getLastName(), th.getStackTrace());
            throw new RuntimeException("" + th.getStackTrace());
        }
    }
    @Override
    public PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size)throws ReadAccessException {

        PersonResponseDTOFull personResponseDTOFull;

        Pageable pageable = PageRequest.of(page, size);

        Page<PersonEntity> personEntityPages = personRepository.findAll(pageable);

        if (personEntityPages != null && !personEntityPages.isEmpty())  {
            personResponseDTOFull = personMapper.listPersonDTOs(personEntityPages.getContent());
            personResponseDTOFull.setSize(personEntityPages.getSize());
            personResponseDTOFull.setCurrentPage(personEntityPages.getNumber() + 1);
            personResponseDTOFull.setTotalPages(personEntityPages.getTotalPages());
            personResponseDTOFull.setTotalElements((int) personEntityPages.getTotalElements());
            return personResponseDTOFull;
        } else {
            throw new ReadAccessException("No hay informacion en el sistema de personas registradas");
        }

    }

    /*@Override
    public PersonResponseDTO updatePersonById(Long idPerson, PersonDTO personaDTO) {
        PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson);

        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        Integer result = personRepository.putPersonById(
                idPerson,
                personaDTO.getNumberDocument(),
                personaDTO.getTypeDocument(),
                personaDTO.getFirstName(),
                personaDTO.getLastName(),
                personaDTO.getDateBorn()
        );
        PersonEntity person = personRepository.getReferenceById(idPerson);
        personResponseDTO = personMapper.entityToDto(person);
        return personResponseDTO;
    }*/

/*    @Override
    public PersonResponseDTO updatePerson(Long idPerson, PersonDTO request) {
        PersonEntity entity = personRepository.findById(idPerson)
                .map(personEntity -> {
                    LocalDate dateCreated = personEntity.getDateCreated();

                    personEntity = personMapper.dtoToEntity(request);
                    personEntity.setIdPerson(idPerson);
                    personEntity.setDateCreated(dateCreated);
                    personEntity.setDateModified(LocalDate.now());

                    return personRepository.save(personEntity);
                }).orElseThrow(() -> new RuntimeException("ID NOT FOUND"));

        return personMapper.entityToDto(entity);
    }*/
    public PersonResponseDTO updatePersonById(Long idPerson, PersonDTO personaDTO) throws ReadAccessException {

        if (idPerson == null || idPerson == 0L || idPerson < 0L) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<PersonEntity> entity = personRepository.findById(idPerson);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error identificador de persona no existe: " + idPerson);
        }

        try {
            PersonEntity personRequest = entity.get();
            LocalDate dateCreated = personRequest.getDateCreated();
            personRequest = personMapper.dtoToEntity(personaDTO);
            personRequest.setDateCreated(dateCreated);
            personRequest.setDateModified(LocalDate.now());
            personRequest.setIdPerson(idPerson);
            personRepository.save(personRequest);

            return personMapper.entityToDto(personRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", personaDTO.getFirstName() + " " + personaDTO.getLastName(), th.getMessage());
            throw new WriteAccessException("Error no identificado de runtime");

        }
    }
}
