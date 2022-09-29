package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import com.ayi.curso.rest.serv.app.exception.ReadAccessException;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repositories.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service //Indica que es un servicio y puede ser inyectado
@Slf4j
@Transactional //Maneja la transaccion, hace el commit y maneja el rollback begin , commit, rollback

public class PersonServiceImpl implements IPersonService {

    @Autowired // Le digo que es una "tubería", genera un puente entre una cosa y otra para traer o enviar info
    private IPersonRepository personRepository; // Fijate que no hicimos implementación de IPersonRepository, ya con esto es suficiente

    @Autowired
    private IPersonMapper personMapper; // Acá uso los mapper (me transforma una entidad a otra)

    @Override
    public List<PersonResponseDTO> findAllPersons() throws ReadAccessException { // Me devuelve todas las personas de la tabla

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        if (personEntities == null ||  personEntities.size() == 0 ){ // si tengo un error de lectura
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
    }

    @Override
    public PersonResponseDTO findPersonById(Long idPerson) throws ReadAccessException {
        PersonResponseDTO personResponseDTO;

        if (idPerson == null || idPerson <= 0) {
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        Optional<PersonEntity> entity = personRepository.findById(idPerson); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        personResponseDTO = personMapper.entityToDto(entity.get());
        return personResponseDTO;
    }

    @Override // Este lo hice yo, ver después si funciona. Funciona, muestra una lista vacía si no encuentra nada
    public List<PersonResponseDTO> findPersonByName(String firstName, String lastName) {
        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.getPersonByName(firstName, lastName);

        personResponseDTOs = personEntities.stream() // Acá voy agregando a la lista todas las filas que encuentra con el nombre y apellido que brinde como parámetro
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateBorn(),
                        lt.getDateCreated(),
                        lt.getDateModified()
                ))
                .collect(Collectors.toList());

        return personResponseDTOs;
    }

    @Override
    public void removePersonById(Long idPerson) {
        //PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        personRepository.deleteById(idPerson);
        //personResponseDTO = personMapper.entityToDto(entity.get());
        //return personResponseDTO;

    }

    @Override
    public PersonResponseDTO addPerson(PersonDTO persona) {
        PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity_check = personRepository.getPersonByDNI(persona.getNumberDocument()); // Ya tengo todos los métodos para buscar, deletear, etc

        if (entity_check.isPresent()) {
            throw new RuntimeException("Error el DNI ya existe en la Base");
        }

        PersonEntity entity = new PersonEntity(
                persona.getFirstName(),
                persona.getLastName(),
                persona.getTypeDocument(),
                persona.getNumberDocument(),
                persona.getDateBorn()
        );

        personRepository.save(entity);

        personResponseDTO = personMapper.entityToDto(entity);
        return personResponseDTO;

    }
    @Override
    public PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size) {

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
            throw new RuntimeException("Error no identificado de runtime");
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
    public PersonResponseDTO updatePersonById(Long idPerson, PersonDTO personaDTO) {

        Optional<PersonEntity> entity = personRepository.findById(idPerson);
        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new RuntimeException("Error no existe el id de persona buscado");
        }
        PersonEntity personRequest = entity.get();
        LocalDate dateCreated = personRequest.getDateCreated();
        personRequest = personMapper.dtoToEntity(personaDTO);
        personRequest.setDateCreated(dateCreated);
        personRequest.setDateModified(LocalDate.now());
        personRequest.setIdPerson(idPerson);
        personRepository.save(personRequest);

        return personMapper.entityToDto(personRequest);
    }
}
